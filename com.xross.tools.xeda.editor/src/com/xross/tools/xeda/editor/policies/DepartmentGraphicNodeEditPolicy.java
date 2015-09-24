package com.xross.tools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.xross.tools.xeda.editor.commands.CreateTransitionCommand;
import com.xross.tools.xeda.editor.commands.ReconnectSourceCommand;
import com.xross.tools.xeda.editor.commands.ReconnectTargetCommand;
import com.xross.tools.xeda.editor.model.TopicNode;
import com.xross.tools.xeda.editor.model.QueueNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class DepartmentGraphicNodeEditPolicy extends GraphicalNodeEditPolicy {
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		if(getHost().getModel() instanceof QueueNode)
			return null;
		
		CreateTransitionCommand cmd = (CreateTransitionCommand)request.getStartCommand();
		cmd.setTarget((ActorNode)getHost().getModel());
		cmd.setStateMachine((DepartmentNode)getHost().getParent().getModel());
		return cmd;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		if(getHost().getModel() instanceof TopicNode)
			return null;
		
		CreateTransitionCommand cmd = new CreateTransitionCommand();
		cmd.setSource((ActorNode)getHost().getModel());
		request.setStartCommand(cmd);
		return cmd;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		return new ReconnectSourceCommand(
				(MessageRoute)request.getConnectionEditPart().getModel(), 
				(ActorNode)getHost().getModel());
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		return new ReconnectTargetCommand(
				(MessageRoute)request.getConnectionEditPart().getModel(), 
				(ActorNode)getHost().getModel());
	}

}
