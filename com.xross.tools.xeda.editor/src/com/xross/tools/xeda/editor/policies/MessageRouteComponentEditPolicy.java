package com.xross.tools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.xross.tools.xeda.editor.commands.DeleteRouteCommand;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class MessageRouteComponentEditPolicy extends ComponentEditPolicy {

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		return new DeleteRouteCommand(
				(MessageRoute)(getHost().getModel()));
	}
}
