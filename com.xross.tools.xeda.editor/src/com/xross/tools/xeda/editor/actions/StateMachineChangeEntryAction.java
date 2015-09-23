package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.commands.ChangeEntryActionCommand;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.parts.ImplementationFinder;

public class StateMachineChangeEntryAction extends WorkbenchPartAction implements StateMachineActionConstants, StateMachineMessages{
	private ActorNode node;
	private ImplementationFinder finder;
	public StateMachineChangeEntryAction(IWorkbenchPart part, ActorNode node, ImplementationFinder finder){
		super(part);
		setId(ID_PREFIX + CHANGE_ENTRY_ACTION);
		setText(CHANGE_ENTRY_ACTION_MSG);
		this.node = node;
		this.finder = finder;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		String impl = finder.assignImpl(node.getEntryAction());
		execute(new ChangeEntryActionCommand(node, impl));
	}
}
