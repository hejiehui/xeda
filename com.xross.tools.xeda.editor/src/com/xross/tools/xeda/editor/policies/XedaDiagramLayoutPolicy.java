package com.xross.tools.xeda.editor.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.xross.tools.xeda.editor.commands.CreateDepartmentCommand;
import com.xross.tools.xeda.editor.commands.MoveDepartmentCommand;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;

public class XedaDiagramLayoutPolicy extends XYLayoutEditPolicy {
    protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
    }

    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (!(child instanceof AbstractGraphicalEditPart))
            return null;
        
        MoveDepartmentCommand cmd = new MoveDepartmentCommand();
        cmd.setNode((DepartmentNode) child.getModel());
        cmd.setConstrain(((Rectangle)constraint));
        return cmd;
    }

	protected Command getCreateCommand(CreateRequest request) {
		if(!(request.getNewObject() instanceof DepartmentNode))
			return null;
        
		return new CreateDepartmentCommand(
        		(XedaDiagram)getHost().getModel(),
        		(DepartmentNode)request.getNewObject(), 
        		((Rectangle) getConstraintFor(request)));
    }
}
