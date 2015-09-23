package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.XedaDiagramGraphicalEditor;
import com.xross.tools.xeda.editor.model.XedaDiagram;
import com.xross.tools.xeda.editor.requests.StateMachineLayoutRequest;

public class StateMachineLayoutAction extends WorkbenchPartAction implements StateMachineActionConstants {
	private boolean horizantal;
	private float alignment;
	
	public StateMachineLayoutAction(IWorkbenchPart part, String id, boolean horizantal, float alignment){
		super(part);
		setId(ID_PREFIX + id);
		this.alignment = alignment;
		this.horizantal = horizantal;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	private Command createAlignmentCommand() {
		XedaDiagramGraphicalEditor editor = (XedaDiagramGraphicalEditor)getWorkbenchPart();
		StateMachineLayoutRequest request = new StateMachineLayoutRequest((XedaDiagram)editor.getRootEditPart().getContents().getModel(), horizantal, alignment);
		return editor.getRootEditPart().getContents().getCommand(request);
	}

	public void run() {
		execute(createAlignmentCommand());
	}
}