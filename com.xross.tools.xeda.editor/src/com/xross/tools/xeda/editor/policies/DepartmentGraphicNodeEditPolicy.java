package com.xross.tools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.xross.tools.xeda.editor.commands.CreateTransitionCommand;
import com.xross.tools.xeda.editor.commands.ReconnectSourceCommand;
import com.xross.tools.xeda.editor.commands.ReconnectTargetCommand;
import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.model.TopicNode;

public class DepartmentGraphicNodeEditPolicy extends GraphicalNodeEditPolicy {
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		CreateTransitionCommand cmd = (CreateTransitionCommand)request.getStartCommand();
		cmd.setTarget((BaseNode)getHost().getModel());
		return cmd;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		if(getHost().getModel() instanceof TopicNode)
			return null;
		
		CreateTransitionCommand cmd = new CreateTransitionCommand();
		cmd.setSource((BaseNode)getHost().getModel());
		request.setStartCommand(cmd);
		return cmd;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		return new ReconnectSourceCommand(
				(MessageRoute)request.getConnectionEditPart().getModel(), 
				(BaseNode)getHost().getModel());
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		return new ReconnectTargetCommand(
				(MessageRoute)request.getConnectionEditPart().getModel(), 
				(BaseNode)getHost().getModel());
	}

}
