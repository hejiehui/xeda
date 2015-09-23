package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class ReconnectSourceCommand extends Command {

	private MessageRoute transition;
	private ActorNode oldSource;
	private ActorNode newSource;

	public ReconnectSourceCommand(MessageRoute transition, ActorNode newSource){
		this.transition = transition;
		this.newSource = newSource;
		oldSource = transition.getSource();
	}
	
	public boolean canExecute() {
		return transition.getSource().equals(newSource);
	}

	public void execute() {
		oldSource.removeOutput(transition);
		transition.setSource(newSource);
		newSource.addOutput(transition);
	}

	public void undo() {
		newSource.removeOutput(transition);
		transition.setSource(oldSource);
		oldSource.addOutput(transition);
	}
}