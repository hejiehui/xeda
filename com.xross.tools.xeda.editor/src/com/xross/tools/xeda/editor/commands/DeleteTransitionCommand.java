package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.MessageRoute;

public class DeleteTransitionCommand extends Command {
	private MessageRoute transition;
	public DeleteTransitionCommand(MessageRoute transition){
		this.transition = transition;
	}
	
    public void execute() {
    	transition.getSource().removeOutput(transition);
    	transition.getTarget().removeInput(transition);
    }

    public String getLabel() {
        return "Delete transition";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	transition.getSource().addOutput(transition);
    	transition.getTarget().addInput(transition);
    }
}
