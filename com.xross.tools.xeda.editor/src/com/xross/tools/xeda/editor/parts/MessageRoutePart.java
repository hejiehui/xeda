package com.xross.tools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.model.RouteStyle;
import com.xross.tools.xeda.editor.policies.MessageRouteComponentEditPolicy;

public class MessageRoutePart extends AbstractConnectionEditPart implements PropertyChangeListener {
	private Label label;
    protected IFigure createFigure() {
        MessageRoute nodeConn = (MessageRoute)getModel();
        
        PolylineConnection conn = new PolylineConnection();
        conn.setTargetDecoration(new PolygonDecoration());
        conn.setConnectionRouter(new MyRouter(nodeConn.getStyle()));//BendpointConnectionRouter());
        conn.setForegroundColor(ColorConstants.black);
        
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
    
    private class MyRouter extends AbstractRouter {
    	RouteStyle style;
    	public MyRouter(RouteStyle style) {
    		this.style = style;
    	}
    	
		@Override
		public void route(Connection conn) {
			if(style == RouteStyle.direct)
				return;

			PointList pl = conn.getPoints();
			pl.removeAllPoints();
			Point start = getStartPoint(conn);
			conn.translateToRelative(start);
			Point end = getEndPoint(conn);
			conn.translateToRelative(end);
			
			if(start.x == end.x || start.y == end.y) {
				pl.addPoint(start);
				pl.addPoint(end);
				return;
			}
		        
			pl.addPoint(start);
			if(style == RouteStyle.heightFirst) {
				Point middle = new Point(start.x, end.y);
		    	pl.addPoint(middle);
			}else {
		    	Point middle = new Point(end.x, start.y);
		    	pl.addPoint(middle);
			}
			pl.addPoint(end);
		}
    }
}
