package com.xrosstools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.model.MessageType;

public class ChangeEventNameCommand extends Command{
    private MessageType event;
    private String oldId;
    private String newId;
    
    public ChangeEventNameCommand(MessageType event, String newId){
    	this.event = event;
    	oldId = event.getId();
    	this.newId = newId;
    }
    
    public void execute() {
        event.setId(newId);
    }

    public String getLabel() {
        return "Change event name";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	event.setId(oldId);
    }
}
