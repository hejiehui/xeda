package com.xrosstools.xeda.editor.treeparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xrosstools.xeda.editor.Activator;
import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.QueueNode;
import com.xrosstools.xeda.editor.model.TopicNode;

public class BaseNodeTreePart extends AbstractTreeEditPart implements PropertyChangeListener {
	private BaseNode node;
	public BaseNodeTreePart(Object model) {
        super(model);
        this.node = (BaseNode)model;
    }
	
    protected String getText() {
        return node.getId() == null ? "" : node.getId();
    }
    
    protected Image getImage() {
    	if(node instanceof QueueNode)
    		return Activator.getDefault().getImage(Activator.QUEUE_NODE);
    	
    	if(node instanceof TopicNode)
    		return Activator.getDefault().getImage(Activator.TOPIC_NODE);
    	
    	return Activator.getDefault().getImage(Activator.ACTOR_NODE);
    }
    
	public void activate() {
		super.activate();
		node.getListeners().addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((BaseNode)getModel()).getListeners().removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		refreshVisuals();
	}
}
