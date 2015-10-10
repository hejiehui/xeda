package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.XedaDiagram;

public class LayoutDepartmentsCommand extends Command {
	private XedaDiagram diagram;
	private boolean oldOrintation;
	private boolean orintation;
	private float oldAlignment;
	private float alignment;
	public LayoutDepartmentsCommand(XedaDiagram diagram, boolean isHorizantal, float alignment){
		this.diagram = diagram;
		oldOrintation = diagram.isHorizantal();
		oldAlignment = diagram.getAlignment();
		this.alignment = alignment;
		orintation = isHorizantal;
	}
	
    public void execute() {
    	diagram.setAlignment(alignment); 
    	diagram.setHorizantal(orintation);
    }

    public void redo() {
    	execute();
    }

    public void undo() {
    	diagram.setAlignment(oldAlignment);
    	diagram.setHorizantal(oldOrintation);
    }
}
