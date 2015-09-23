package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.commands.ChangeTransitionActionCommand;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class StateMachineRemoveTransitionAction extends WorkbenchPartAction implements StateMachineActionConstants, StateMachineMessages{
	private MessageRoute transition;
	public StateMachineRemoveTransitionAction(IWorkbenchPart part, MessageRoute transition){
		super(part);
		setId(ID_PREFIX + REMOVE_TRANSIT_ACTION);
		setText(REMOVE_TRANSIT_ACTION_MSG);
		this.transition = transition;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		execute(new ChangeTransitionActionCommand(transition, null));
	}
}
