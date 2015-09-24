package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class DeleteNodeCommand extends Command{
    private DepartmentNode stateMachine;
    private ActorNode node;
    
    public DeleteNodeCommand(
    		DepartmentNode stateMachine, 
    		ActorNode node){
    	this.stateMachine = stateMachine;
    	this.node = node;
    }
    
    public void execute() {
        for(MessageRoute transition: node.getOutputs()){
        	transition.getTarget().removeInput(transition);
        }
        
        for(MessageRoute transition: node.getInputs()){
        	transition.getSource().removeOutput(transition);
        }
        
    	stateMachine.removeNode(node);
    }

    public String getLabel() {
        return "Delete Node";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	stateMachine.addNode(node);
        for(MessageRoute transition: node.getOutputs()){
        	transition.getTarget().addInput(transition);
        }
        
        for(MessageRoute transition: node.getInputs()){
        	transition.getSource().addOutput(transition);
        }
    }
}
