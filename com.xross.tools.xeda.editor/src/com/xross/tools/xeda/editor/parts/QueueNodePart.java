package com.xross.tools.xeda.editor.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;

import com.xross.tools.xeda.editor.figures.QueueNodeFigure;
import com.xross.tools.xeda.editor.model.QueueNode;

public class QueueNodePart extends BaseNodePart {
	protected IFigure createFigure() {
		return new QueueNodeFigure();
    }

    protected void refreshVisuals() {
    	QueueNode node = (QueueNode) getModel();
    	QueueNodeFigure figure = (QueueNodeFigure)getFigure();

    	figure.setName(node.getId());
		Point loc = node.getLocation();
		Dimension size = figure.getPreferredSize();
        Rectangle rectangle = new Rectangle(loc, size);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
    }
}
