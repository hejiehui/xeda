package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.commands.ChangeTransitionActionCommand;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.parts.ImplementationFinder;

public class StateMachineCreateTransitionAction extends WorkbenchPartAction implements StateMachineActionConstants, StateMachineMessages{
	private MessageRoute transition;
	private ImplementationFinder finder;
	public StateMachineCreateTransitionAction(IWorkbenchPart part, MessageRoute transition, ImplementationFinder finder){
		super(part);
		setId(ID_PREFIX + CREATE_TRANSIT_ACTION);
		setText(CREATE_TRANSIT_ACTION_MSG);
		this.transition = transition;
		this.finder = finder;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		String impl = finder.assignImpl("");
		
		if(impl == null)
			return;
		
		execute(new ChangeTransitionActionCommand(transition, impl));
	}
}
