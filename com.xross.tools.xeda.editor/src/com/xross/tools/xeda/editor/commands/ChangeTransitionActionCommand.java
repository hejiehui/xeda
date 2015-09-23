package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.MessageRoute;

public class ChangeTransitionActionCommand extends Command{
    private MessageRoute transition;
    private String oldValue;
    private String newValue;
    
    public ChangeTransitionActionCommand(MessageRoute transition, String newValue){
    	this.transition = transition;
    	this.newValue = newValue;
    	oldValue = transition.getTransitAction();
    }
    
    public void execute() {
    	transition.setTransitAction(newValue);
    }

    public String getLabel() {
        return "Change transition action";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	transition.setTransitAction(oldValue);
    }
}
