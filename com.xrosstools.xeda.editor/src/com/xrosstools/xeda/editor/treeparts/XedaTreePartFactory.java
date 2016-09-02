package com.xrosstools.xeda.editor.treeparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.model.MessageType;
import com.xrosstools.xeda.editor.model.XedaDiagram;

public class XedaTreePartFactory  implements EditPartFactory {
	public EditPart createEditPart(EditPart context, Object model) {
		if(model instanceof XedaDiagram)
			return new XedaDiagramTreePart(model);

		if(model instanceof DepartmentNode)
			return new DepartmentTreePart(model);

		if(model instanceof BaseNode)
			return new BaseNodeTreePart(model);
		
		if(model instanceof MessageType)
			return new EventTreePart(model);
		
		return null;
	}
}
