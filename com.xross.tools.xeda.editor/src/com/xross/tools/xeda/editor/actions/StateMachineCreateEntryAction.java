package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.commands.ChangeEntryActionCommand;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.parts.ImplementationFinder;

public class StateMachineCreateEntryAction extends WorkbenchPartAction implements XedaActionConstants, XedaMessages{
	private ActorNode node;
	private ImplementationFinder finder;
	public StateMachineCreateEntryAction(IWorkbenchPart part, ActorNode node, ImplementationFinder finder){
		super(part);
		setId(ID_PREFIX + CREATE_ENTRY_ACTION);
		setText(CREATE_ENTRY_ACTION_MSG);
		this.node = node;
		this.finder = finder;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		String impl = finder.assignImpl("");
		
		if(impl == null)
			return;
		
		execute(new ChangeEntryActionCommand(node, impl));
	}
}
