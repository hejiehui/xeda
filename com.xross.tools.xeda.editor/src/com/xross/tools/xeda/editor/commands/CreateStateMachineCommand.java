package com.xross.tools.xeda.editor.commands;

import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;

public class CreateStateMachineCommand extends Command {
	private XedaDiagram diagram;
	private DepartmentNode machine;
	private int index;
	
	public CreateStateMachineCommand(XedaDiagram diagram, DepartmentNode machine, int index){
		this.diagram = diagram;
		this.machine = machine;
		this.index = index;
	}
	
	public void execute() {
		diagram.addMachine(index, machine);
	}
	
    public String getLabel() {
        return "Add state machine";
    }

    public void redo() {
        execute();
    }

    public void undo() {
    	diagram.removeMachine(machine);
	}
}
