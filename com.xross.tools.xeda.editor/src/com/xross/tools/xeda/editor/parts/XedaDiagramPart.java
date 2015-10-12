package com.xross.tools.xeda.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.xross.tools.xeda.editor.figures.XedaDiagramFigure;
import com.xross.tools.xeda.editor.model.XedaDiagram;
import com.xross.tools.xeda.editor.policies.XedaDiagramLayoutPolicy;

public class XedaDiagramPart extends AbstractGraphicalEditPart implements PropertyChangeListener{
	protected List getModelChildren() {
		return ((XedaDiagram)getModel()).getDepartments();
	}
	
	protected IFigure createFigure() {
        return new XedaDiagramFigure();
	}

	public void propertyChange(PropertyChangeEvent evt) {
//		String prop = evt.getPropertyName();
		refreshChildren();
	}
	
	public void activate() {
		super.activate();
		((XedaDiagram)getModel()).getListeners().addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((XedaDiagram)getModel()).getListeners().removePropertyChangeListener(this);
	}

	protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new XedaDiagramLayoutPolicy());
	}
}
