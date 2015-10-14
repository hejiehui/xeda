package com.xross.tools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.ContextMenuBuilder;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.model.RouteStyle;
import com.xross.tools.xeda.editor.model.XedaConstants;
import com.xross.tools.xeda.editor.policies.BaseNodeComponentEditPolicy;
import com.xross.tools.xeda.editor.policies.DepartmentGraphicNodeEditPolicy;

public abstract class BaseNodePart extends AbstractGraphicalEditPart implements XedaConstants, PropertyChangeListener, NodeEditPart, ContextMenuBuilder {
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new BaseNodeAnchor(getFigure(), true, ((MessageRoute)connection.getModel()).getStyle());
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new BaseNodeAnchor(getFigure(), false, ((MessageRoute)connection.getModel()).getStyle());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new BaseNodeAnchor(getFigure(), true, RouteStyle.heightFirst);
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new BaseNodeAnchor(getFigure(), false, RouteStyle.heightFirst);
	}
	
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new BaseNodeComponentEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new DepartmentGraphicNodeEditPolicy());
	}
	
    protected List<MessageRoute> getModelSourceConnections() {
    	return ((BaseNode)getModel()).getOutputs();
    }

    protected List<MessageRoute> getModelTargetConnections() {
    	return ((BaseNode)getModel()).getInputs();
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
    	((BaseNode) getModel()).getListeners().addPropertyChangeListener(this);
    }
    
    public void deactivate() {
    	super.deactivate();
    	((BaseNode) getModel()).getListeners().removePropertyChangeListener(this);
    }
    
	@Override
	public void buildContextMenu(IMenuManager menu, IWorkbenchPart editor, ImplementationFinder finder) {
	}
}
