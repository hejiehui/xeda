package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.MessageType;
import com.xross.tools.xeda.editor.model.DepartmentNode;

public class AddEventCommand extends Command{
    private DepartmentNode stateMachine;
    private MessageType event;
    
    public AddEventCommand(DepartmentNode diagram, MessageType event){
    	this.stateMachine = diagram;
    	this.event = event;
    }
    
    public void execute() {
    	stateMachine.addEvent(event);
    }

    public String getLabel() {
        return "Create new event";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	stateMachine.removeEvent(event);
    }
}
