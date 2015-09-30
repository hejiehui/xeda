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

import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.policies.MessageRouteComponentEditPolicy;

public class MessageRoutePart extends AbstractConnectionEditPart implements PropertyChangeListener {
	private Label label;
    protected IFigure createFigure() {
        PolylineConnection conn = new PolylineConnection();
        conn.setTargetDecoration(new PolygonDecoration());
        conn.setConnectionRouter(new BendpointConnectionRouter());
        conn.setForegroundColor(ColorConstants.black);
        
        MessageRoute nodeConn = (MessageRoute)getModel();
        label = new Label();
        label.setText(nodeConn.getRouteId());
        label.setOpaque(true);
        conn.add(label, new MidpointLocator(conn, 0));
        return conn;
    }

    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new MessageRouteComponentEditPolicy());
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
    	((MessageRoute) getModel()).getListeners().addPropertyChangeListener(this);
    }
    
    public void deactivate() {
    	super.deactivate();
    	((MessageRoute) getModel()).getListeners().removePropertyChangeListener(this);
    }
    
    public void propertyChange(PropertyChangeEvent event){
    	MessageRoute nodeConn = (MessageRoute)getModel();
    	label.setText(nodeConn.getRouteId());
    }

}
