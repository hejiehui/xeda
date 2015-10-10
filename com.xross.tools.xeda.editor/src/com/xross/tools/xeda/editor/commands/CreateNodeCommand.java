package com.xross.tools.xeda.editor.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.BaseNode;

public class CreateNodeCommand extends Command{
    private DepartmentNode department;
    private BaseNode node;
    private Point location;
    
    public CreateNodeCommand(
    		DepartmentNode department, 
    		BaseNode node, 
    		Point location){
    	this.department = department;
    	this.node = node;
    	this.location = location;
    }
    
    public void execute() {
        node.setLocation(location);
        department.addNode(node);
    }

    public String getLabel() {
        return "Create Node";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	department.removeNode(node);
    }
}
