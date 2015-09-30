package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.ActorNode;

public class ChangeEntryActionCommand extends Command{
    private ActorNode node;
    private String oldValue;
    private String newValue;
    
    public ChangeEntryActionCommand(ActorNode node, String newValue){
    	this.node = node;
    	this.newValue = newValue;
    	oldValue = node.getActorClassName();
    }
    
    public void execute() {
    	node.setActorClassName(newValue);
    }

    public String getLabel() {
        return "Change node action";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	node.setActorClassName(oldValue);
    }
}
