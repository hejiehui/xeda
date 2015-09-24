package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class CreateTransitionCommand extends Command {
	private MessageRoute transition;
	private DepartmentNode machine;
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
	
	public void setStateMachine(DepartmentNode machine) {
		this.machine = machine;
	}

	public void undo() {
		source.removeOutput(transition);
		target.removeInput(transition);
	}
}