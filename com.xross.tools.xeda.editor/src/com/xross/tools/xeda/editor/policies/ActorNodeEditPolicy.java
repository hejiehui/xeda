package com.xross.tools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.xross.tools.xeda.editor.commands.DeleteNodeCommand;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.ActorNode;

public class ActorNodeEditPolicy extends ComponentEditPolicy {

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		return new DeleteNodeCommand(
				(DepartmentNode)(getHost().getParent().getModel()), 
				(ActorNode)(getHost().getModel()));
	}
}
