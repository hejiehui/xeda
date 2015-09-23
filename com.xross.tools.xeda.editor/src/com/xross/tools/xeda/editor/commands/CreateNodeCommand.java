package com.xross.tools.xeda.editor.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.ActorGroup;
import com.xross.tools.xeda.editor.model.ActorNode;

public class CreateNodeCommand extends Command{
    private ActorGroup stateMachine;
    private ActorNode node;
    private Point location;
    
    public CreateNodeCommand(
    		ActorGroup stateMachine, 
    		ActorNode node, 
    		Point location){
    	this.stateMachine = stateMachine;
    	this.node = node;
    	this.location = location;
    }
    
    public void execute() {
        node.setLocation(location);
        stateMachine.addNode(node);
    }

    public String getLabel() {
        return "Create Node";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	stateMachine.removeNode(node);
    }
}
