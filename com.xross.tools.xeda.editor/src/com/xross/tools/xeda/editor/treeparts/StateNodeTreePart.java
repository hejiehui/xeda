package com.xross.tools.xeda.editor.treeparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xross.tools.xeda.editor.Activator;
import com.xross.tools.xeda.editor.model.MessageEndNode;
import com.xross.tools.xeda.editor.model.MessageStartNode;
import com.xross.tools.xeda.editor.model.ActorNode;

public class StateNodeTreePart extends AbstractTreeEditPart implements PropertyChangeListener {
	private ActorNode node;
	public StateNodeTreePart(Object model) {
        super(model);
        this.node = (ActorNode)model;
    }
	
    protected String getText() {
        return node.getId() == null ? "" : node.getId();
    }
    
    protected Image getImage() {
    	if(node instanceof MessageStartNode)
    		return Activator.getDefault().getImage(Activator.START_NODE);
    	
    	if(node instanceof MessageEndNode)
    		return Activator.getDefault().getImage(Activator.END_NODE);
    	
    	return Activator.getDefault().getImage(Activator.STATE_NODE);
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
