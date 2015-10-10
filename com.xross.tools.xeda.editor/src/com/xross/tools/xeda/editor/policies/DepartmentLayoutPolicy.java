package com.xross.tools.xeda.editor.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.xross.tools.xeda.editor.commands.AddNodeCommand;
import com.xross.tools.xeda.editor.commands.CreateNodeCommand;
import com.xross.tools.xeda.editor.commands.LayoutActorsCommand;
import com.xross.tools.xeda.editor.commands.MoveNodeCommand;
import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.requests.ActorLayoutRequest;

public class DepartmentLayoutPolicy extends XYLayoutEditPolicy {

    protected Command createAddCommand(EditPart child, Object constraint) {
    	if(!(getHost().getModel() instanceof DepartmentNode))
    		return null;
    	
    	if(!(child.getModel() instanceof BaseNode))
    		return null;

    	Rectangle constr = (Rectangle)constraint;
    	return new AddNodeCommand(
        		(DepartmentNode)getHost().getModel(),
        		(BaseNode)child.getModel(),
        		(DepartmentNode)child.getParent().getModel(),
        		constr.getTopLeft()
        		);
    }

    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (!(child instanceof AbstractGraphicalEditPart))
            return null;
        
        MoveNodeCommand cmd = new MoveNodeCommand();
        cmd.setNode((BaseNode) child.getModel());
        cmd.setLocation(((Rectangle)constraint).getLocation());
        return cmd;
    }

    public Command getCommand(Request request) {
    	if(request.getType() == RequestConstants.REQ_ALIGN){
    		ActorLayoutRequest layoutReq = (ActorLayoutRequest)request;
    		return new LayoutActorsCommand(layoutReq.getDepartmentNode(), layoutReq.isHorizantal(), layoutReq.getAlignment());
    	}
    	
//    	if(request.getType() == RequestConstants.REQ_RESIZE){
//    		StateNodeResizeRequest resizeReq = (StateNodeResizeRequest)request;
//    		return new ResizeNodeCommand(resizeReq.getDiagram(), resizeReq.isNodeSize(), resizeReq.isHorizantal(), resizeReq.isIncrease());
//    	}
    	
    	return super.getCommand(request);
    }
    
    protected Command getCreateCommand(CreateRequest request) {
    	if(!(request.getNewObject() instanceof BaseNode))
			return null;
        return new CreateNodeCommand(
        		(DepartmentNode)getHost().getModel(),
        		(BaseNode)request.getNewObject(),
        		((Rectangle) getConstraintFor(request)).getLocation());
    }
}