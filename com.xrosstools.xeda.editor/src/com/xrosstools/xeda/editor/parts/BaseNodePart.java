package com.xrosstools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

import com.xrosstools.xeda.editor.ContextMenuBuilder;
import com.xrosstools.xeda.editor.model.ActorNode;
import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.MessageRoute;
import com.xrosstools.xeda.editor.model.RouteStyle;
import com.xrosstools.xeda.editor.model.XedaConstants;
import com.xrosstools.xeda.editor.policies.BaseNodeComponentEditPolicy;
import com.xrosstools.xeda.editor.policies.DepartmentGraphicNodeEditPolicy;

public abstract class BaseNodePart extends AbstractGraphicalEditPart implements XedaConstants, PropertyChangeListener, NodeEditPart, ContextMenuBuilder {
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getAnchor(true, ((MessageRoute)connection.getModel()).getStyle());
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return getAnchor(false, ((MessageRoute)connection.getModel()).getStyle());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return getAnchor(true, RouteStyle.direct);
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return getAnchor(false, RouteStyle.direct);
	}
	
	private ConnectionAnchor getAnchor(boolean isSource, RouteStyle style) {
		if(style == RouteStyle.direct)
			return new ChopboxAnchor(getFigure());
		
		return new BaseNodeAnchor(getFigure(), isSource, style);
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
