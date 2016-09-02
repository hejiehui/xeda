package com.xrosstools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.model.XedaDiagram;

public class DeleteDepartmentNodeCommand extends Command{
	private XedaDiagram diagram;
    private DepartmentNode department;
    
    public DeleteDepartmentNodeCommand(
    		XedaDiagram diagram,
    		DepartmentNode department){
    	this.diagram = diagram;
    	this.department = department;
    }
    
    public void execute() {
    	diagram.removeDepartment(department);
    }

    public String getLabel() {
        return "Delete Department";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	diagram.addDepartment(department);
    }
}
