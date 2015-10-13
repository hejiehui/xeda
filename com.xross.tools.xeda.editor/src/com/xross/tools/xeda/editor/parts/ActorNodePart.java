package com.xross.tools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.ContextMenuBuilder;
import com.xross.tools.xeda.editor.actions.ChangeActorClass;
import com.xross.tools.xeda.editor.actions.StateMachineChangeExitAction;
import com.xross.tools.xeda.editor.actions.StateMachineCreateEntryAction;
import com.xross.tools.xeda.editor.actions.StateMachineCreateExitAction;
import com.xross.tools.xeda.editor.actions.StateMachineOpenExitAction;
import com.xross.tools.xeda.editor.actions.StateMachineRemoveEntryAction;
import com.xross.tools.xeda.editor.actions.StateMachineRemoveExitAction;
import com.xross.tools.xeda.editor.figures.ActorNodeFigure;
import com.xross.tools.xeda.editor.model.XedaConstants;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.policies.DepartmentGraphicNodeEditPolicy;
import com.xross.tools.xeda.editor.policies.BaseNodeComponentEditPolicy;

public class ActorNodePart extends AbstractGraphicalEditPart implements XedaConstants, PropertyChangeListener, NodeEditPart, ContextMenuBuilder {
	protected IFigure createFigure() {
        return new ActorNodeFigure();
    }
	
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new BaseNodeAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new BaseNodeAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new BaseNodeAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new BaseNodeAnchor(getFigure());
	}
	
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new BaseNodeComponentEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new DepartmentGraphicNodeEditPolicy());
	}
	
	public ActorNode getStateNode() {
		return (ActorNode)getModel();
	}
    protected List<MessageRoute> getModelSourceConnections() {
    	return getStateNode().getOutputs();
    }

    protected List<MessageRoute> getModelTargetConnections() {
    	return getStateNode().getInputs();
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
    	getStateNode().getListeners().addPropertyChangeListener(this);
    }
    
    public void deactivate() {
    	super.deactivate();
    	getStateNode().getListeners().removePropertyChangeListener(this);
    }

    protected void refreshVisuals() {
    	ActorNode node = getStateNode();
    	ActorNodeFigure figure = (ActorNodeFigure)getFigure();
       	figure.setName(node.getId());

		Point loc = node.getLocation();
		Dimension size = figure.getPreferredSize();
        Rectangle rectangle = new Rectangle(loc, size);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
    }
    
	@Override
	public void buildContextMenu(IMenuManager menu, IWorkbenchPart editor, ImplementationFinder finder) {
    	menu.add(new Separator());
    	ActorNode node = getStateNode();
    	if(isEmpty(node.getActorClassName()))
    		menu.add(new StateMachineCreateEntryAction(editor, node, finder));
    	else{
    		menu.add(new ChangeActorClass(editor, node, finder));
    		menu.add(new StateMachineRemoveEntryAction(editor, node));
    		menu.add(new StateMachineOpenExitAction(editor, node, finder));
    	}

    	menu.add(new Separator());
    	if(isEmpty(node.getErrorHandler()))
    		menu.add(new StateMachineCreateExitAction(editor, node, finder));
    	else{
    		menu.add(new StateMachineChangeExitAction(editor, node, finder));
    		menu.add(new StateMachineRemoveExitAction(editor, node));
    		menu.add(new StateMachineOpenExitAction(editor, node, finder));
    	}
	}
	
	private boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}
}
