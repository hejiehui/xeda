package com.xross.tools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.ContextMenuBuilder;
import com.xross.tools.xeda.editor.actions.StateMachineChangeTransitionAction;
import com.xross.tools.xeda.editor.actions.StateMachineCreateTransitionAction;
import com.xross.tools.xeda.editor.actions.StateMachineOpenTransitionAction;
import com.xross.tools.xeda.editor.actions.StateMachineRemoveTransitionAction;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.policies.StateTransitionComponentEditPolicy;

public class StateTransitionPart extends AbstractConnectionEditPart implements PropertyChangeListener, ContextMenuBuilder {
	private Label label;
    protected IFigure createFigure() {
        PolylineConnection conn = new PolylineConnection();
        conn.setTargetDecoration(new PolygonDecoration());
        conn.setConnectionRouter(new BendpointConnectionRouter());
        conn.setForegroundColor(ColorConstants.black);
        
        MessageRoute nodeConn = (MessageRoute)getModel();
        label = new Label();
//        StateMachine diagram = (StateMachine)getRoot().getContents().getModel();
        label.setText(nodeConn.getDisplayLabel());
        label.setOpaque(true);
        conn.add(label, new MidpointLocator(conn, 0));
        return conn;
    }

    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new StateTransitionComponentEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
    }

    public void setSelected(int value) {
        super.setSelected(value);
        if (value != EditPart.SELECTED_NONE)
            ((PolylineConnection) getFigure()).setLineWidth(2);
        else
            ((PolylineConnection) getFigure()).setLineWidth(1);
    }
    
    public void activate() {
    	super.activate();
//    	((StateMachineDiagram)getRoot().getContents().getModel()).getListeners().addPropertyChangeListener(this);
    	((MessageRoute) getModel()).getListeners().addPropertyChangeListener(this);
    }
    
    public void deactivate() {
    	super.deactivate();
//    	((StateMachine)getRoot().getContents().getModel()).getListeners().removePropertyChangeListener(this);
    	((MessageRoute) getModel()).getListeners().removePropertyChangeListener(this);
    }
    
    public void propertyChange(PropertyChangeEvent event){
    	MessageRoute nodeConn = (MessageRoute)getModel();
    	label.setText(nodeConn.getDisplayLabel());
    }

	@Override
	public void buildContextMenu(IMenuManager menu, IWorkbenchPart editor, ImplementationFinder finder) {
    	menu.add(new Separator());
    	MessageRoute transition = (MessageRoute)getModel();

    	if(isEmpty(transition.getTransitAction()))
    		menu.add(new StateMachineCreateTransitionAction(editor, transition, finder));
    	else{
    		menu.add(new StateMachineChangeTransitionAction(editor, transition, finder));
    		menu.add(new StateMachineRemoveTransitionAction(editor, transition));
    		menu.add(new StateMachineOpenTransitionAction(editor, transition, finder));
    	}
	}
	
	private boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}
}
