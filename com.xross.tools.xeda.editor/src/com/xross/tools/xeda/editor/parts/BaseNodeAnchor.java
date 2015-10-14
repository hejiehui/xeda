package com.xross.tools.xeda.editor.parts;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

import com.xross.tools.xeda.editor.model.RouteStyle;

public class BaseNodeAnchor extends AbstractConnectionAnchor {
	private boolean isSource;
	private RouteStyle style;
	public BaseNodeAnchor(IFigure owner, boolean isSource, RouteStyle style) {
		super(owner);
		this.isSource = isSource;
		this.style = style;
	}

	public Point getLocation(Point loc)
	{
		return getLocationX(loc);
//		Rectangle r = getOwner().getBounds();
//		int x = r.x + r.width/2;
//		int x = loc.x < r.x ? r.x : r.x + r.width;
//		int y = r.y + r.height/2;
//		int y = loc.y < r.y ? r.y : r.y + r.height;
//		int x = compute(loc.x, r.x, r.width);
//		int y = compute(loc.y, r.y, r.height);
//		Point p = new PrecisionPoint(x,y);
//		getOwner().translateToAbsolute(p);
//		return p;
	}
	
	private int compute(int loc, int r, int edge) {
		if(loc < r)
			return r;
		
		if(loc < (edge + r))
			return r + edge/2;
		
		return r + edge;
	}
	
	public Point getLocationX(Point loc) {
		Rectangle r = getOwner().getBounds();
		int sectionX = decideSection(loc.x, r.x, r.width);
		int sectionY = decideSection(loc.y, r.y, r.height);
		return decide(sectionX, sectionY);
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
	
	int[][] decisoinSource = new int[][] {
			new int[]{TOP, TOP, TOP},
			new int[]{LEFT, CENTERX, RIGHT},
			new int[]{BOTTOM, BOTTOM, BOTTOM},
	};
	
	int[][] decisoinTarget = new int[][] {
			new int[]{LEFT, TOP, RIGHT},
			new int[]{LEFT, CENTERX, RIGHT},
			new int[]{LEFT, BOTTOM, RIGHT},
	};
	
	private Point decide(int sectionX, int sectionY) {
		Rectangle r = getOwner().getBounds();
		
		int[][] decisoinMatrix = isSource ? decisoinSource: decisoinTarget;
		int decision = decisoinMatrix[sectionX][sectionY];
		switch (decision) {
			case TOP:
				return r.getTop();
			case LEFT:
				return r.getLeft();
			case CENTERX:
				return r.getCenter();
			case RIGHT:
				return r.getRight();
			case BOTTOM:
				return r.getBottom();
			default:
				return null;
		}
	}
}