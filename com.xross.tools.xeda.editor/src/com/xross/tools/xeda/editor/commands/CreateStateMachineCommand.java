package com.xross.tools.xeda.editor.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;

public class CreateStateMachineCommand extends Command {
	private XedaDiagram diagram;
	private DepartmentNode department;
	private Point location;
	
	public CreateStateMachineCommand(XedaDiagram diagram, DepartmentNode department, Point location){
		this.diagram = diagram;
		this.department = department;
		this.location = location;
	}
	
	public void execute() {
		department.setLocation(location);
		diagram.addDepartment(department);
	}
	
    public String getLabel() {
        return "Add state machine";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	diagram.removeDepartment(department);
	}
}
