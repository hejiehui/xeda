package com.xross.tools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.xross.tools.xeda.editor.commands.CreateTransitionCommand;
import com.xross.tools.xeda.editor.commands.ReconnectSourceCommand;
import com.xross.tools.xeda.editor.commands.ReconnectTargetCommand;
import com.xross.tools.xeda.editor.model.MessageEndNode;
import com.xross.tools.xeda.editor.model.MessageStartNode;
import com.xross.tools.xeda.editor.model.ActorGroup;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class StateMachineGraphicNodeEditPolicy extends GraphicalNodeEditPolicy {
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		if(getHost().getModel() instanceof MessageStartNode)
			return null;
		
		CreateTransitionCommand cmd = (CreateTransitionCommand)request.getStartCommand();
		cmd.setTarget((ActorNode)getHost().getModel());
		cmd.setStateMachine((ActorGroup)getHost().getParent().getModel());
		return cmd;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		if(getHost().getModel() instanceof MessageEndNode)
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
