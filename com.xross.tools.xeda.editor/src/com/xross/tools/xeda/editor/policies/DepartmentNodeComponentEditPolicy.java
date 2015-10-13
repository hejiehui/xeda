package com.xross.tools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.xross.tools.xeda.editor.commands.DeleteDepartmentNodeCommand;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;

public class DepartmentNodeComponentEditPolicy extends ComponentEditPolicy {

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		return new DeleteDepartmentNodeCommand(
				(XedaDiagram)getHost().getParent().getModel(),
				(DepartmentNode)(getHost().getModel()));
	}
}
