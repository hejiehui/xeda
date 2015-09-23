package com.xross.tools.xeda.editor.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.xross.tools.xeda.editor.commands.AddStateNodeCommand;
import com.xross.tools.xeda.editor.commands.CreateNodeCommand;
import com.xross.tools.xeda.editor.commands.LayoutStateMachineCommand;
import com.xross.tools.xeda.editor.commands.MoveNodeCommand;
import com.xross.tools.xeda.editor.commands.ResizeNodeCommand;
import com.xross.tools.xeda.editor.model.ActorGroup;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.requests.StateMachineLayoutRequest;
import com.xross.tools.xeda.editor.requests.StateNodeResizeRequest;

public class StateMachineLayoutPolicy extends XYLayoutEditPolicy {

    protected Command createAddCommand(EditPart child, Object constraint) {
    	if(!(getHost().getModel() instanceof ActorGroup))
    		return null;
    	
    	if(!(child.getModel() instanceof ActorNode))
    		return null;

    	Rectangle constr = (Rectangle)constraint;
    	return new AddStateNodeCommand(
        		(ActorGroup)getHost().getModel(),
        		(ActorNode)child.getModel(),
        		(ActorGroup)child.getParent().getModel(),
        		constr.getTopLeft()
        		);
    }

    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (!(child instanceof AbstractGraphicalEditPart))
            return null;
        
        MoveNodeCommand cmd = new MoveNodeCommand();
        cmd.setNode((ActorNode) child.getModel());
        cmd.setConstraint((Rectangle)constraint);
        return cmd;
    }

    public Command getCommand(Request request) {
    	if(request.getType() == RequestConstants.REQ_ALIGN){
    		StateMachineLayoutRequest layoutReq = (StateMachineLayoutRequest)request;
    		return new LayoutStateMachineCommand(layoutReq.getDiagram(), layoutReq.isHorizantal(), layoutReq.getAlignment());
    	}
    	
//    	if(request.getType() == RequestConstants.REQ_RESIZE){
//    		StateNodeResizeRequest resizeReq = (StateNodeResizeRequest)request;
//    		return new ResizeNodeCommand(resizeReq.getDiagram(), resizeReq.isNodeSize(), resizeReq.isHorizantal(), resizeReq.isIncrease());
//    	}
    	
    	return super.getCommand(request);
    }
    
    protected Command getCreateCommand(CreateRequest request) {
    	if(!(request.getNewObject() instanceof ActorNode))
			return null;
        return new CreateNodeCommand(
        		(ActorGroup)getHost().getModel(),
        		(ActorNode)request.getNewObject(),
        		((Rectangle) getConstraintFor(request)).getLocation());
    }
}