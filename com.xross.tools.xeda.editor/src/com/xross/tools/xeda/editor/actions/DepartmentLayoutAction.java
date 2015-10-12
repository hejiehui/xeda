package com.xross.tools.xeda.editor.actions;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.commands.MoveDepartmentCommand;
import com.xross.tools.xeda.editor.model.DepartmentNode;

public class DepartmentLayoutAction extends WorkbenchPartAction implements XedaActionConstants, XedaMessages {
	private DepartmentNode department;
	private Dimension size;
	public DepartmentLayoutAction(IWorkbenchPart part, DepartmentNode department, Dimension size){
		super(part);
		setId(ID_PREFIX + AUTO_LAYOUT_DEPARTMENT);
		setText(AUTO_LAYOUT_DEPARTMENT_MSG);
		this.department = department;
		this.size = size;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	private Command createAlignmentCommand() {
		MoveDepartmentCommand command  = new MoveDepartmentCommand();
		command.setNode(department);
		command.setConstrain(new Rectangle(department.getConstrain().getLocation(), size));
		return command;
	}

	public void run() {
		execute(createAlignmentCommand());
	}
}