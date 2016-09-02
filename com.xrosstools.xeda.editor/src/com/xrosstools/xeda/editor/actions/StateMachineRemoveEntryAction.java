package com.xrosstools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xrosstools.xeda.editor.commands.ChangeEntryActionCommand;
import com.xrosstools.xeda.editor.model.ActorNode;

public class StateMachineRemoveEntryAction extends WorkbenchPartAction implements XedaActionConstants, XedaMessages{
	private ActorNode node;
	public StateMachineRemoveEntryAction(IWorkbenchPart part, ActorNode node){
		super(part);
		setId(ID_PREFIX + REMOVE_ENTRY_ACTION);
		setText(REMOVE_ENTRY_ACTION_MSG);
		this.node = node;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		execute(new ChangeEntryActionCommand(node, null));
	}
}
