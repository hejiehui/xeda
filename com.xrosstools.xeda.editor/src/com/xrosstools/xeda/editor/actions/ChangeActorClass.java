package com.xrosstools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xrosstools.xeda.editor.commands.ChangeEntryActionCommand;
import com.xrosstools.xeda.editor.model.ActorNode;
import com.xrosstools.xeda.editor.parts.ImplementationFinder;

public class ChangeActorClass extends WorkbenchPartAction implements XedaActionConstants, XedaMessages{
	private ActorNode node;
	private ImplementationFinder finder;
	public ChangeActorClass(IWorkbenchPart part, ActorNode node, ImplementationFinder finder){
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
		String impl = finder.assignImpl(node.getActorClassName());
		execute(new ChangeEntryActionCommand(node, impl));
	}
}
