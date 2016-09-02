package com.xrosstools.xeda.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

import com.xrosstools.xeda.editor.parts.ImplementationFinder;

public interface ContextMenuBuilder {
	public void buildContextMenu(IMenuManager menu, IWorkbenchPart editor, ImplementationFinder finder);
}
