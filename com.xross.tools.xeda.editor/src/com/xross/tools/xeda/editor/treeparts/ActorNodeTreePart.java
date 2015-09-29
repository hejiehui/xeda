package com.xross.tools.xeda.editor.treeparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xross.tools.xeda.editor.Activator;
import com.xross.tools.xeda.editor.model.TopicNode;
import com.xross.tools.xeda.editor.model.QueueNode;
import com.xross.tools.xeda.editor.model.ActorNode;

public class ActorNodeTreePart extends AbstractTreeEditPart implements PropertyChangeListener {
	private ActorNode node;
	public ActorNodeTreePart(Object model) {
        super(model);
        this.node = (ActorNode)model;
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
		((ActorNode)getModel()).getListeners().removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		refreshVisuals();
	}
}
