package com.xross.tools.xeda.editor.parts;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.xross.tools.xeda.editor.model.RouteStyle;

public class BaseNodeAnchor extends AbstractConnectionAnchor {
	private boolean isSource;
	private boolean isTarget;
	private RouteStyle style;
	public BaseNodeAnchor(IFigure owner, boolean isSource, RouteStyle style) {
		super(owner);
		this.isSource = isSource;
		isTarget = !isSource;
		this.style = style;
	}

	public Point getLocation(Point reference)
	{
		Rectangle r = getOwner().getBounds();
		int sectionX = decideSection(reference.x, r.x, r.width);
		int sectionY = decideSection(reference.y, r.y, r.height);
		return decide(sectionY, sectionX, reference);
	}

	private static final int TOP_LEFT = 0;
	private static final int CENTER = 1;
	private static final int BOTTOM_RIGHT = 2;
	
	private static final int TOP = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	private static final int BOTTOM = 3;
	private static final int CENTERX = 4;

	
	private int decideSection(int loc, int topLeft, int length) {
		int bottomRight = topLeft + length;
		if(loc < topLeft)
			return TOP_LEFT;
		
		return loc < (bottomRight) ? CENTER :BOTTOM_RIGHT;
	}
	
	private int[][] decisoinSource = new int[][] {
			new int[]{TOP, TOP, TOP},
			new int[]{LEFT, CENTERX, RIGHT},
			new int[]{BOTTOM, BOTTOM, BOTTOM},
	};
	
	private int[][] decisoinTarget = new int[][] {
			new int[]{LEFT, TOP, RIGHT},
			new int[]{LEFT, CENTERX, RIGHT},
			new int[]{LEFT, BOTTOM, RIGHT},
	};
	
	private Point decide(int sectionX, int sectionY, Point reference) {
		Rectangle r = getOwner().getBounds();
		
		int[][] decisoinMatrix = isSource ? decisoinSource: decisoinTarget;
		int decision = decisoinMatrix[sectionX][sectionY];
		switch (decision) {
			case TOP:
				return isTarget && style == RouteStyle.heightFirst ? r.getTop().setX(reference.x) : r.getTop();
			case LEFT:
				return isSource && style == RouteStyle.heightFirst ? r.getLeft().setY(reference.y) : r.getLeft();
			case CENTERX:
				return r.getCenter();
			case RIGHT:
				return isSource && style == RouteStyle.heightFirst ? r.getRight().setY(reference.y) : r.getRight();
			case BOTTOM:
				return isTarget && style == RouteStyle.heightFirst ? r.getBottom().setX(reference.x) : r.getBottom();
			default:
				return null;
		}
	}
}