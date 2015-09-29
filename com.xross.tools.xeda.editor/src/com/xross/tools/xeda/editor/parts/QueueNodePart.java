package com.xross.tools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;

import com.xross.tools.xeda.editor.figures.QueueNodeFigure;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.model.QueueNode;
import com.xross.tools.xeda.editor.model.XedaConstants;
import com.xross.tools.xeda.editor.policies.ActorNodeComponentEditPolicy;
import com.xross.tools.xeda.editor.policies.DepartmentGraphicNodeEditPolicy;

public class QueueNodePart extends AbstractGraphicalEditPart implements XedaConstants, PropertyChangeListener, NodeEditPart {
    private DirectEditManager manager;
	protected IFigure createFigure() {
		return new QueueNodeFigure();
    }
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
	}

	public void performRequest(Request req) {
//		if (req.getType() == RequestConstants.REQ_DIRECT_EDIT){
//            if (manager == null) {
//                StateNodeFigure figure = (StateNodeFigure) getFigure();
//                manager = new StateNodeDirectEditManager(this, ((StateMachine)getParent().getModel()).getFactors(), TextCellEditor.class, new StateNodeCellEditorLocator(figure));
//            }
//            manager.show();
//		}
	}
	
	protected void createEditPolicies() {
//		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new StateNodeDirectEditPolicy(((StateMachine)getParent().getModel()).getFactors()));
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ActorNodeComponentEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new DepartmentGraphicNodeEditPolicy());
	}
	
    protected List<MessageRoute> getModelSourceConnections() {
    	return ((ActorNode)getModel()).getOutputs();
    }

    protected List<MessageRoute> getModelTargetConnections() {
    	return ((ActorNode)getModel()).getInputs();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ActorNode.PROP_INPUTS))
            refreshTargetConnections();
        else if (evt.getPropertyName().equals(ActorNode.PROP_OUTPUTS))
            refreshSourceConnections();
        else
            refreshVisuals();
    }
    
    public void activate() {
    	super.activate();
    	((ActorNode) getModel()).getListeners().addPropertyChangeListener(this);
    }
    
    public void deactivate() {
    	super.deactivate();
    	((ActorNode) getModel()).getListeners().removePropertyChangeListener(this);
    }

    protected void refreshVisuals() {
    	QueueNode node = (QueueNode) getModel();
    	QueueNodeFigure figure = (QueueNodeFigure)getFigure();

		Point loc = node.getLocation();
		Dimension size = figure.getGoodSize();
        Rectangle rectangle = new Rectangle(loc, size);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
    }
}
