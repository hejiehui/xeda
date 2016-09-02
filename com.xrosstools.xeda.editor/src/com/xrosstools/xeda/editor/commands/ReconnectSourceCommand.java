package com.xrosstools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.MessageRoute;

public class ReconnectSourceCommand extends Command {

	private MessageRoute transition;
	private BaseNode oldSource;
	private BaseNode newSource;

	public ReconnectSourceCommand(MessageRoute transition, BaseNode newSource){
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