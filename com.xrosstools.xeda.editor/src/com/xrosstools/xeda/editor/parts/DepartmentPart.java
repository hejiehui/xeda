package com.xrosstools.xeda.editor.parts;

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

import com.xrosstools.xeda.editor.ContextMenuBuilder;
import com.xrosstools.xeda.editor.actions.DepartmentLayoutAction;
import com.xrosstools.xeda.editor.figures.DepartmentNodeFigure;
import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.policies.DepartmentLayoutPolicy;
import com.xrosstools.xeda.editor.policies.DepartmentNodeComponentEditPolicy;

public class DepartmentPart extends AbstractGraphicalEditPart implements PropertyChangeListener, ContextMenuBuilder {
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
    	installEditPolicy(EditPolicy.COMPONENT_ROLE, new DepartmentNodeComponentEditPolicy());
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
    	
       	figure.setId(department.getId(), department.getDescription());
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, department.getConstrain());
    }

	@Override
	public void buildContextMenu(IMenuManager menu, IWorkbenchPart editor, ImplementationFinder finder) {
    	menu.add(new Separator());
    	menu.add(new DepartmentLayoutAction(editor, (DepartmentNode)getModel(), getFigure().getPreferredSize()));
	}
}
