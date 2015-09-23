package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.XedaDiagramGraphicalEditor;
import com.xross.tools.xeda.editor.commands.ChangeTransitionActionCommand;
import com.xross.tools.xeda.editor.model.MessageType;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.parts.ImplementationFinder;

public class StateMachineChangeTransitionAction extends WorkbenchPartAction implements StateMachineActionConstants, StateMachineMessages{
	private MessageRoute transition;
	private ImplementationFinder finder;
	public StateMachineChangeTransitionAction(IWorkbenchPart part, MessageRoute transition, ImplementationFinder finder){
		super(part);
		setId(ID_PREFIX + CHANGE_TRANSIT_ACTION);
		setText(CHANGE_TRANSIT_ACTION_MSG);
		this.transition = transition;
		this.finder = finder;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		String impl = finder.assignImpl(transition.getTransitAction());
		execute(new ChangeTransitionActionCommand(transition, impl));
	}
}
