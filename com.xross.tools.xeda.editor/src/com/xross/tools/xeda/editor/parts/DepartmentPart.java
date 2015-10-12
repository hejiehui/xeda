package com.xross.tools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.xross.tools.xeda.editor.figures.DepartmentNodeFigure;
import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.policies.DepartmentLayoutPolicy;

public class DepartmentPart extends AbstractGraphicalEditPart implements PropertyChangeListener {
    protected List<BaseNode> getModelChildren() {
    	DepartmentNode diagram = (DepartmentNode)getModel();
        return diagram.getNodes();
    }

	protected IFigure createFigure() {
        return new DepartmentNodeFigure();
	}
	
	public IFigure getContentPane(){
		return ((DepartmentNodeFigure)getFigure()).getFigure();
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}
	
	public void activate() {
		super.activate();
		((DepartmentNode)getModel()).getListeners().addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((DepartmentNode)getModel()).getListeners().removePropertyChangeListener(this);
	}

    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DepartmentLayoutPolicy());
    }
    
	protected void addChildVisual(EditPart childEditPart, int index) {
		DepartmentNodeFigure figure = (DepartmentNodeFigure)getFigure();
		IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
		figure.getFigure().add(child);
	}
	
	protected void removeChildVisual(EditPart childEditPart) {
		IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
		child.getParent().remove(child);
	}
	
    protected void refreshVisuals() {
    	DepartmentNode department = (DepartmentNode) getModel();
    	DepartmentNodeFigure figure = (DepartmentNodeFigure)getFigure();
    	
       	figure.setName(department.getName(), department.getDescription());
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, department.getConstrain());
    }
}
