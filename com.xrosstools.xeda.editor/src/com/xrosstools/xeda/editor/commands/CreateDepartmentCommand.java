package com.xrosstools.xeda.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.model.XedaDiagram;

public class CreateDepartmentCommand extends Command {
	private XedaDiagram diagram;
	private DepartmentNode department;
	private Rectangle constraint;
	
	public CreateDepartmentCommand(XedaDiagram diagram, DepartmentNode department, Rectangle constraint){
		this.diagram = diagram;
		this.department = department;
		this.constraint = constraint;
	}
	
	public void execute() {
		department.setConstrain(constraint);
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
