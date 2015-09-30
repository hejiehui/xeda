package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.ActorNode;

public class AssignClassCommand extends Command{
    private boolean isEntyAction;
    private ActorNode node;
    private String value;
    private String oldValue;
    
    public AssignClassCommand(ActorNode node, boolean isEntyAction, String value){
    	this.node = node;
    	this.isEntyAction = isEntyAction;
    	oldValue = isEntyAction ? node.getActorClassName() : node.getErrorHandler();
    	this.value = value;
    }
    
    public void execute() {
    	if(isEntyAction)
    		node.setActorClassName(value);
    	else
    		node.setErrorHandler(value);
    }

    public String getLabel() {
        return "Change action class";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	if(isEntyAction)
    		node.setActorClassName(oldValue);
    	else
    		node.setErrorHandler(oldValue);
    }
}
