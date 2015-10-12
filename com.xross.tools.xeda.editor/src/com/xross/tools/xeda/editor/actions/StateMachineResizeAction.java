package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.XedaDiagramGraphicalEditor;
import com.xross.tools.xeda.editor.model.XedaDiagram;
import com.xross.tools.xeda.editor.requests.ActorNodeResizeRequest;

public class StateMachineResizeAction extends WorkbenchPartAction implements XedaActionConstants {
	private boolean horizantal;
	private boolean nodeSize;
	private boolean increase;
	
	public StateMachineResizeAction(IWorkbenchPart part, String id, boolean nodeSize, boolean horizantal, boolean increase){
		super(part);
		setId(ID_PREFIX + id);
		this.horizantal = horizantal;
		this.increase = increase;
		this.nodeSize = nodeSize;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	private Command createAlignmentCommand() {
		XedaDiagramGraphicalEditor editor = (XedaDiagramGraphicalEditor)getWorkbenchPart();
		ActorNodeResizeRequest request = new ActorNodeResizeRequest((XedaDiagram)editor.getRootEditPart().getContents().getModel(), nodeSize, horizantal, increase);
		return editor.getRootEditPart().getContents().getCommand(request);
	}

	public void run() {
		execute(createAlignmentCommand());
	}
}
