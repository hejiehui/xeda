package com.xrosstools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.xrosstools.xeda.editor.commands.DeleteRouteCommand;
import com.xrosstools.xeda.editor.model.MessageRoute;

public class MessageRouteComponentEditPolicy extends ComponentEditPolicy {

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		return new DeleteRouteCommand(
				(MessageRoute)(getHost().getModel()));
	}
}
