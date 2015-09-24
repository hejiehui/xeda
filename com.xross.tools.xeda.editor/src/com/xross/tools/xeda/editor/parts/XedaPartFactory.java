package com.xross.tools.xeda.editor.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xross.tools.xeda.editor.model.TopicNode;
import com.xross.tools.xeda.editor.model.QueueNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class XedaPartFactory implements EditPartFactory {
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		
		if(model == null)
			part = null;
		else
		if(model instanceof XedaDiagram)
			part = new XedaDiagramPart();
		else
		if(model instanceof DepartmentNode)
			part = new DepartmentPart();
		else
		if(model instanceof QueueNode)
			part = new QueueNodePart();
		else
		if(model instanceof TopicNode)
			part = new TopicNodePart();
		else
		if(model instanceof ActorNode)
			part = new ActorNodePart();
		else
		if(model instanceof MessageRoute)
			part = new MessageRoutePart();
		
		part.setModel(model);
		
		return part;
	}
}
