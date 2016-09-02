package com.xrosstools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xrosstools.xeda.editor.commands.ChangeExitActionCommand;
import com.xrosstools.xeda.editor.model.ActorNode;
import com.xrosstools.xeda.editor.parts.ImplementationFinder;

public class StateMachineCreateExitAction extends WorkbenchPartAction implements XedaActionConstants, XedaMessages{
	private ActorNode node;
	private ImplementationFinder finder;
	public StateMachineCreateExitAction(IWorkbenchPart part, ActorNode node, ImplementationFinder finder){
		super(part);
		setId(ID_PREFIX + CREATE_EXIT_ACTION);
		setText(CREATE_EXIT_ACTION_MSG);
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
		
		execute(new ChangeExitActionCommand(node, impl));
	}
}
