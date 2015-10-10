package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.DepartmentNode;

public class LayoutActorsCommand extends Command {
	private DepartmentNode department;
	private boolean oldOrintation;
	private boolean orintation;
	private float oldAlignment;
	private float alignment;
	public LayoutActorsCommand(DepartmentNode department, boolean isHorizantal, float alignment){
		this.department = department;
		this.alignment = alignment;
		orintation = isHorizantal;
	}
	
    public void execute() {
    }

    public void redo() {
    	execute();
    }

    public void undo() {
    }
}
