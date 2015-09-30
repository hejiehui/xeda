package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class CreateTransitionCommand extends Command {
	private MessageRoute transition;
	private BaseNode source;
	private BaseNode target;

	public void execute() {
		transition = new MessageRoute(source, target);
	}

	public void redo() {
		source.addOutput(transition);
		target.addInput(transition);
	}

	public void setSource(BaseNode source) {
		this.source = source;
	}

	public void setTarget(BaseNode target) {
		this.target = target;
	}
	
	public void undo() {
		source.removeOutput(transition);
		target.removeInput(transition);
	}
}