package com.xross.tools.xeda.editor.treeparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xross.tools.xeda.editor.Activator;
import com.xross.tools.xeda.editor.model.MessageType;

public class EventTreePart extends AbstractTreeEditPart implements PropertyChangeListener {
	private MessageType event;
	public EventTreePart(Object model) {
        super(model);
        this.event = (MessageType)model;
    }
	
    protected String getText() {
        return event.getId();
    }
    
    protected Image getImage() {
    	return Activator.getDefault().getImage(Activator.ACTOR_NODE);
    }
    
	public void activate() {
		super.activate();
		event.getListeners().addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		event.getListeners().removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		refreshVisuals();
	}
}
