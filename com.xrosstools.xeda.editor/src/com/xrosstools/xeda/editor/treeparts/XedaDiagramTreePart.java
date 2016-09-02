package com.xrosstools.xeda.editor.treeparts;

import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xrosstools.xeda.editor.Activator;
import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.model.XedaDiagram;

public class XedaDiagramTreePart extends AbstractTreeEditPart {
    public XedaDiagramTreePart(Object model) {
        super(model);
     }

    protected List<DepartmentNode> getModelChildren() {
    	return ((XedaDiagram)getModel()).getDepartments();
    }
    
    protected String getText() {
    	XedaDiagram diagram = (XedaDiagram)getModel();
    	return diagram.getName();
    }
    
    protected Image getImage() {
    	return Activator.getDefault().getImage(Activator.STATE_MACHINE_DIAGRAM);
    }
}
