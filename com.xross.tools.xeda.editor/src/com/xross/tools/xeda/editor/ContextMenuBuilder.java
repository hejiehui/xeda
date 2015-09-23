package com.xross.tools.xeda.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.parts.ImplementationFinder;

public interface ContextMenuBuilder {
	public void buildContextMenu(IMenuManager menu, IWorkbenchPart editor, ImplementationFinder finder);
}
