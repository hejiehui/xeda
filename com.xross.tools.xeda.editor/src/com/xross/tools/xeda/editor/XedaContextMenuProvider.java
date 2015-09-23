package com.xross.tools.xeda.editor;

import java.util.List;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jface.action.IMenuManager;

import com.xross.tools.xeda.editor.actions.StateMachineJunitCodeGenAction;
import com.xross.tools.xeda.editor.actions.StateMachineUsageCodeGenAction;
import com.xross.tools.xeda.editor.parts.ImplementationFinder;

public class XedaContextMenuProvider extends ContextMenuProvider {
	private GraphicalEditor editor;
	private ImplementationFinder finder = new ImplementationFinder();
    public XedaContextMenuProvider(EditPartViewer viewer, GraphicalEditor editor) {
        super(viewer);
        this.editor = editor;
    }
    public void buildContextMenu(IMenuManager menu) {
        // Add standard action groups to the menu
    	GEFActionConstants.addStandardActionGroups(menu);
     	menu.add(new StateMachineJunitCodeGenAction(editor));
     	menu.add(new StateMachineUsageCodeGenAction(editor));

    	EditPartViewer viewer = this.getViewer();
		List selected = viewer.getSelectedEditParts();
		if(selected.size() != 1 || !(selected.get(0) instanceof ContextMenuBuilder))
			return;
		
		ContextMenuBuilder builder = (ContextMenuBuilder)selected.get(0);
		builder.buildContextMenu(menu, editor, finder);
    }
}
