package com.xross.tools.xeda.editor.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.xross.tools.xeda.editor.commands.AddStateMachineCommand;
import com.xross.tools.xeda.editor.commands.CreateStateMachineCommand;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;

public class XedaDiagramLayoutPolicy extends FlowLayoutEditPolicy {
	/**
	 * @return <code>true</code> if the host's LayoutManager is in a horizontal
	 *         orientation
	 */
	protected boolean isHorizontal() {
		return false;
	}

	protected Command getCreateCommand(CreateRequest request) {
		if(!(request.getNewObject() instanceof DepartmentNode))
			return null;
        
		return new CreateStateMachineCommand(
        		(XedaDiagram)getHost().getModel(),
        		(DepartmentNode)request.getNewObject(), getIndex(request));
    }

	@Override
	protected Command createAddCommand(EditPart child, EditPart after) {
		return null;
	}

	@Override
	protected Command createMoveChildCommand(EditPart child, EditPart after) {
    	if(!(child.getModel() instanceof DepartmentNode))
    		return null;

    	return new AddStateMachineCommand(
        		(XedaDiagram)getHost().getModel(),
        		(DepartmentNode)child.getModel(),
        		getIndex(after)
        		);
	}

	private int getIndex(Request request) {
		return getIndex(getInsertionReference(request));
	}
	
	private int getIndex(EditPart after) {
		XedaDiagram container = (XedaDiagram)getHost().getModel();
		int index = -1;
		if(after == null)
			index = container.getMachines().size();
		else
			index = container.getMachines().indexOf((DepartmentNode)after.getModel());
		
		return index;
	}
}
