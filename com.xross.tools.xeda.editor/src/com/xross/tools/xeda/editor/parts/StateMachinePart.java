package com.xross.tools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xeda.editor.ContextMenuBuilder;
import com.xross.tools.xeda.editor.actions.StateMachineCreateEventAction;
import com.xross.tools.xeda.editor.figures.ActorGroupFigure;
import com.xross.tools.xeda.editor.model.ActorGroup;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.policies.StateMachineLayoutPolicy;

public class StateMachinePart  extends AbstractGraphicalEditPart implements PropertyChangeListener, ContextMenuBuilder {
    protected List<ActorNode> getModelChildren() {
    	ActorGroup diagram = (ActorGroup)getModel();
        return diagram.getNodes();
    }

	protected IFigure createFigure() {
		IFigure figure  = new ActorGroupFigure();
        return figure;
	}
	
	public IFigure getContentPane(){
		return ((ActorGroupFigure)getFigure()).getFigure();
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}
	
	public void activate() {
		super.activate();
		((ActorGroup)getModel()).getListeners().addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((ActorGroup)getModel()).getListeners().removePropertyChangeListener(this);
	}

    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new StateMachineLayoutPolicy());
    }
    
	protected void addChildVisual(EditPart childEditPart, int index) {
		ActorGroupFigure figure = (ActorGroupFigure)getFigure();
		IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
		figure.getFigure().add(child);
	}
	
	protected void removeChildVisual(EditPart childEditPart) {
		IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
		child.getParent().remove(child);
	}
	
    protected void refreshVisuals() {
    	ActorGroup node = (ActorGroup) getModel();
    	ActorGroupFigure figure = (ActorGroupFigure)getFigure();
    	
       	figure.setName(node.getName(), node.getDescription());
    }

	@Override
	public void buildContextMenu(IMenuManager menu, IWorkbenchPart editor, ImplementationFinder finder) {
    	menu.add(new Separator());
    	ActorGroup machine = (ActorGroup)getModel();
    	menu.add(new StateMachineCreateEventAction(editor, machine));
	}
}
