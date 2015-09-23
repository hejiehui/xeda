package com.xross.tools.xeda.editor.treeparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xross.tools.xeda.editor.Activator;
import com.xross.tools.xeda.editor.model.ActorGroup;

public class StateMachineTreePart extends AbstractTreeEditPart implements PropertyChangeListener {
	private ActorGroup machine;
    public StateMachineTreePart(Object model) {
        super(model);
        this.machine = (ActorGroup)model;
     }

    protected List<Object> getModelChildren() {
    	List<Object> children = new ArrayList<Object>();
    	children.addAll(machine.getEvents());
    	children.addAll(machine.getNodes());
    	return children;
    }
    
    protected String getText() {
    	ActorGroup stateMachine = (ActorGroup)getModel();
    	return stateMachine.getName();
    }
    
    protected Image getImage() {
    	return Activator.getDefault().getImage(Activator.STATE_MACHINE);
    }
    
	public void activate() {
		super.activate();
		machine.getListeners().addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		machine.getListeners().removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		refreshVisuals();
		refreshChildren();
	}
}
