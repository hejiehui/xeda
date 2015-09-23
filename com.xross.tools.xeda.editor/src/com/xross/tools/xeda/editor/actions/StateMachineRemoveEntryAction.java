package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.commands.ChangeEntryActionCommand;
import com.xross.tools.xeda.editor.model.ActorNode;

public class StateMachineRemoveEntryAction extends WorkbenchPartAction implements StateMachineActionConstants, StateMachineMessages{
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
