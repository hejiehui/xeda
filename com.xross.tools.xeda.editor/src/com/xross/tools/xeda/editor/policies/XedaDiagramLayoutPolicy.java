package com.xross.tools.xeda.editor.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.xross.tools.xeda.editor.commands.CreateStateMachineCommand;
import com.xross.tools.xeda.editor.commands.LayoutDepartmentsCommand;
import com.xross.tools.xeda.editor.commands.MoveDepartmentCommand;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;
import com.xross.tools.xeda.editor.requests.DepartmentLayoutRequest;

public class XedaDiagramLayoutPolicy extends XYLayoutEditPolicy {
    protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
    }

    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (!(child instanceof AbstractGraphicalEditPart))
            return null;
        
        MoveDepartmentCommand cmd = new MoveDepartmentCommand();
        cmd.setNode((DepartmentNode) child.getModel());
        cmd.setLocation(((Rectangle)constraint).getLocation());
        return cmd;
    }

    public Command getCommand(Request request) {
    	if(request.getType() == RequestConstants.REQ_ALIGN){
    		DepartmentLayoutRequest layoutReq = (DepartmentLayoutRequest)request;
    		return new LayoutDepartmentsCommand(layoutReq.getDiagram(), layoutReq.isHorizantal(), layoutReq.getAlignment());
    	}
    	
//    	if(request.getType() == RequestConstants.REQ_RESIZE){
//    		StateNodeResizeRequest resizeReq = (StateNodeResizeRequest)request;
//    		return new ResizeNodeCommand(resizeReq.getDiagram(), resizeReq.isNodeSize(), resizeReq.isHorizantal(), resizeReq.isIncrease());
//    	}
    	
    	return super.getCommand(request);
    }
    
	protected Command getCreateCommand(CreateRequest request) {
		if(!(request.getNewObject() instanceof DepartmentNode))
			return null;
        
		return new CreateStateMachineCommand(
        		(XedaDiagram)getHost().getModel(),
        		(DepartmentNode)request.getNewObject(), 
        		((Rectangle) getConstraintFor(request)).getLocation());
    }
}
