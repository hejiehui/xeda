package com.xross.tools.xeda.editor.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.actions.ChangeActorClass;
import com.xross.tools.xeda.editor.actions.StateMachineChangeExitAction;
import com.xross.tools.xeda.editor.actions.StateMachineCreateEntryAction;
import com.xross.tools.xeda.editor.actions.StateMachineCreateExitAction;
import com.xross.tools.xeda.editor.actions.StateMachineOpenExitAction;
import com.xross.tools.xeda.editor.actions.StateMachineRemoveEntryAction;
import com.xross.tools.xeda.editor.actions.StateMachineRemoveExitAction;
import com.xross.tools.xeda.editor.figures.ActorNodeFigure;
import com.xross.tools.xeda.editor.model.ActorNode;

public class ActorNodePart extends BaseNodePart {
	protected IFigure createFigure() {
        return new ActorNodeFigure();
    }

    protected void refreshVisuals() {
    	ActorNode node = (ActorNode)getModel();
    	ActorNodeFigure figure = (ActorNodeFigure)getFigure();
       	figure.setName(node.getId());

		Point loc = node.getLocation();
		Dimension size = figure.getPreferredSize();
        Rectangle rectangle = new Rectangle(loc, size);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
    }
    
	@Override
	public void buildContextMenu(IMenuManager menu, IWorkbenchPart editor, ImplementationFinder finder) {
    	menu.add(new Separator());
    	ActorNode node = (ActorNode)getModel();
    	if(isEmpty(node.getActorClassName()))
    		menu.add(new StateMachineCreateEntryAction(editor, node, finder));
    	else{
    		menu.add(new ChangeActorClass(editor, node, finder));
    		menu.add(new StateMachineRemoveEntryAction(editor, node));
    		menu.add(new StateMachineOpenExitAction(editor, node, finder));
    	}

    	menu.add(new Separator());
    	if(isEmpty(node.getErrorHandler()))
    		menu.add(new StateMachineCreateExitAction(editor, node, finder));
    	else{
    		menu.add(new StateMachineChangeExitAction(editor, node, finder));
    		menu.add(new StateMachineRemoveExitAction(editor, node));
    		menu.add(new StateMachineOpenExitAction(editor, node, finder));
    	}
	}
	
	private boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}
}
