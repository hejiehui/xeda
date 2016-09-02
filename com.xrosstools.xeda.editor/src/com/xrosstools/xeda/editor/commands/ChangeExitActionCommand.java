package com.xrosstools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.model.ActorNode;

public class ChangeExitActionCommand extends Command{
    private ActorNode node;
    private String oldValue;
    private String newValue;
    
    public ChangeExitActionCommand(ActorNode node, String newValue){
    	this.node = node;
    	this.newValue = newValue;
    	oldValue = node.getErrorHandler();
    }
    
    public void execute() {
    	node.setErrorHandler(newValue);
    }

    public String getLabel() {
        return "Change node action";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	node.setErrorHandler(oldValue);
    }
}
