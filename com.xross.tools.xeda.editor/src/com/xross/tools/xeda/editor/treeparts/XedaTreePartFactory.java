package com.xross.tools.xeda.editor.treeparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.MessageType;
import com.xross.tools.xeda.editor.model.XedaDiagram;

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