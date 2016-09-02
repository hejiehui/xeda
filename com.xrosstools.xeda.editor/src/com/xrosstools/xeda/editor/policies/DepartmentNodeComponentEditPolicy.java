package com.xrosstools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.xrosstools.xeda.editor.commands.DeleteDepartmentNodeCommand;
import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.model.XedaDiagram;

public class DepartmentNodeComponentEditPolicy extends ComponentEditPolicy {

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		return new DeleteDepartmentNodeCommand(
				(XedaDiagram)getHost().getParent().getModel(),
				(DepartmentNode)(getHost().getModel()));
	}
}
