package com.xross.tools.xeda.editor.commands;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.hamcrest.core.IsInstanceOf;

import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.model.TopicNode;

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
		String defaultNamePrefix = node instanceof ActorNode ? "actor" : node instanceof TopicNode ? "topic" : "queue";

		Set<String> existingNames = new HashSet<>();
		for(BaseNode aNode: department.getNodes()) {
			existingNames.add(aNode.getId());
		}

		int i = 1;
		String defaultName = defaultNamePrefix;
		while(existingNames.contains(defaultName)) {
			defaultName = defaultNamePrefix + i++;
		}
		
        node.setLocation(location);
        node.setId(defaultName);
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
