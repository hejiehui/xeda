package com.xrosstools.xeda.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.figures.DepartmentNodeFigure;
import com.xrosstools.xeda.editor.model.DepartmentNode;

public class LayoutDepartmentsCommand extends Command {
	private DepartmentNode department;
    private Rectangle oldConstrain;
    private Rectangle newConstrain;

	public LayoutDepartmentsCommand(DepartmentNode department, DepartmentNodeFigure figure){
		this.department = department;
		oldConstrain = department.getConstrain();
		newConstrain = new Rectangle(oldConstrain.getLocation(), figure.getPreferredSize(0, 0));
		
	}
	
    public void execute() {
    	department.setConstrain(newConstrain); 
    }

    public void redo() {
    	execute();
    }

    public void undo() {
    	department.setConstrain(oldConstrain); 
    }
}
