package com.xross.tools.xeda.editor.treeparts;

import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xross.tools.xeda.editor.Activator;
import com.xross.tools.xeda.editor.model.ActorGroup;
import com.xross.tools.xeda.editor.model.XedaDiagram;

public class StateMachineDiagramTreePart extends AbstractTreeEditPart {
    public StateMachineDiagramTreePart(Object model) {
        super(model);
     }

    protected List<ActorGroup> getModelChildren() {
    	return ((XedaDiagram)getModel()).getMachines();
    }
    
    protected String getText() {
    	XedaDiagram diagram = (XedaDiagram)getModel();
    	return diagram.getName();
    }
    
    protected Image getImage() {
    	return Activator.getDefault().getImage(Activator.STATE_MACHINE_DIAGRAM);
    }
}
