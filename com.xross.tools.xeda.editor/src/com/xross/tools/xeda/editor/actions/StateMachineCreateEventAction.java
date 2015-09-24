package com.xross.tools.xeda.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.XedaDiagramGraphicalEditor;
import com.xross.tools.xeda.editor.commands.AddEventCommand;
import com.xross.tools.xeda.editor.model.MessageType;
import com.xross.tools.xeda.editor.model.DepartmentNode;

public class StateMachineCreateEventAction extends WorkbenchPartAction implements StateMachineActionConstants, StateMachineMessages{
	private DepartmentNode machine;
	public StateMachineCreateEventAction(IWorkbenchPart part, DepartmentNode machine){
		super(part);
		setId(ID_PREFIX + CREATE_EVENT);
		setText(CREATE_EVENT_MSG);
		this.machine = machine;
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(), "Create new Event: ", "Event", "event", null);
		if (dlg.open() != Window.OK)
			return;
		String name = dlg.getValue();
		
		XedaDiagramGraphicalEditor editor = (XedaDiagramGraphicalEditor)getWorkbenchPart();
		MessageType event = new MessageType();
		event.setId(name);
		execute(new AddEventCommand(machine, event));
	}
}
