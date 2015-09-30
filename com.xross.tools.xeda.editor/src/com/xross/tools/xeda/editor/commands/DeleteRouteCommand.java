package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.MessageRoute;

public class DeleteRouteCommand extends Command {
	private MessageRoute route;
	public DeleteRouteCommand(MessageRoute route){
		this.route = route;
	}
	
    public void execute() {
    	route.getSource().removeOutput(route);
    	route.getTarget().removeInput(route);
    }

    public String getLabel() {
        return "Delete route";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	route.getSource().addOutput(route);
    	route.getTarget().addInput(route);
    }
}
