package com.xrosstools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.MessageRoute;

public class ReconnectTargetCommand extends Command {

	private MessageRoute transition;
	private BaseNode oldTarget;
	private BaseNode newTarget;

	public ReconnectTargetCommand(MessageRoute transition, BaseNode newTarget){
		this.transition = transition;
		this.newTarget = newTarget;
		oldTarget = transition.getTarget();
	}
	
	public boolean canExecute() {
		return transition.getTarget().equals(newTarget);
	}

	public void execute() {
		oldTarget.removeInput(transition);
		transition.setTarget(newTarget);
		newTarget.addInput(transition);
	}

	public void undo() {
		newTarget.removeInput(transition);
		transition.setTarget(oldTarget);
		oldTarget.addInput(transition);
	}
}
