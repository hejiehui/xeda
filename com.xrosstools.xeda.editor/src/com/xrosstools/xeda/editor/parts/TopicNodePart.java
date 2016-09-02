package com.xrosstools.xeda.editor.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;

import com.xrosstools.xeda.editor.figures.TopicNodeFigure;
import com.xrosstools.xeda.editor.model.TopicNode;

public class TopicNodePart extends BaseNodePart {
	protected IFigure createFigure() {
		return new TopicNodeFigure();
    }

    protected void refreshVisuals() {
    	TopicNode node = (TopicNode) getModel();
    	TopicNodeFigure figure = (TopicNodeFigure)getFigure();

    	figure.setName(node.getId());
		Point loc = node.getLocation();
		Dimension size = figure.getPreferredSize();
        Rectangle rectangle = new Rectangle(loc, size);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
    }
}
