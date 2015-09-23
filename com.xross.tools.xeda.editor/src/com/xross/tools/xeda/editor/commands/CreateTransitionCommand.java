package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.ActorGroup;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class CreateTransitionCommand extends Command {
	private MessageRoute transition;
	private ActorGroup machine;
	private ActorNode source;
	private ActorNode target;

	public void execute() {
		transition = new MessageRoute(source, target, machine.getHelper());
	}

	public void redo() {
		source.addOutput(transition);
		target.addInput(transition);
	}

	public void setSource(ActorNode source) {
		this.source = source;
	}

	public void setTarget(ActorNode target) {
		this.target = target;
	}
	
	public void setStateMachine(ActorGroup machine) {
		this.machine = machine;
	}

	public void undo() {
		source.removeOutput(transition);
		target.removeInput(transition);
	}
}