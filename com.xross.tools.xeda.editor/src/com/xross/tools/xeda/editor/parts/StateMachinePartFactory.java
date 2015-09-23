package com.xross.tools.xeda.editor.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xross.tools.xeda.editor.model.MessageEndNode;
import com.xross.tools.xeda.editor.model.MessageStartNode;
import com.xross.tools.xeda.editor.model.ActorGroup;
import com.xross.tools.xeda.editor.model.XedaDiagram;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class StateMachinePartFactory implements EditPartFactory {
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		
		if(model == null)
			part = null;
		else
		if(model instanceof XedaDiagram)
			part = new StateMachineDiagramPart();
		else
		if(model instanceof ActorGroup)
			part = new StateMachinePart();
		else
		if(model instanceof MessageStartNode)
			part = new StartNodePart();
		else
		if(model instanceof MessageEndNode)
			part = new EndNodePart();
		else
		if(model instanceof ActorNode)
			part = new StateNodePart();
		else
		if(model instanceof MessageRoute)
			part = new StateTransitionPart();
		
		part.setModel(model);
		
		return part;
	}
}
