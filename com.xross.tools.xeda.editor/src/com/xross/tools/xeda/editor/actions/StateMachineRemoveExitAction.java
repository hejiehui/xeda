package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.commands.ChangeExitActionCommand;
import com.xross.tools.xeda.editor.model.ActorNode;

public class StateMachineRemoveExitAction extends WorkbenchPartAction implements StateMachineActionConstants, StateMachineMessages{
	private ActorNode node;
	public StateMachineRemoveExitAction(IWorkbenchPart part, ActorNode node){
		super(part);
		setId(ID_PREFIX + REMOVE_EXIT_ACTION);
		setText(REMOVE_EXIT_ACTION_MSG);
		this.node = node;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		execute(new ChangeExitActionCommand(node, null));
	}
}
