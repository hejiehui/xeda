package com.xross.tools.xeda.editor.treeparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xross.tools.xeda.editor.model.MessageType;
import com.xross.tools.xeda.editor.model.ActorGroup;
import com.xross.tools.xeda.editor.model.XedaDiagram;
import com.xross.tools.xeda.editor.model.ActorNode;

public class StateMachineTreePartFactory  implements EditPartFactory {
	public EditPart createEditPart(EditPart context, Object model) {
		if(model instanceof XedaDiagram)
			return new StateMachineDiagramTreePart(model);

		if(model instanceof ActorGroup)
			return new StateMachineTreePart(model);

		if(model instanceof ActorNode)
			return new StateNodeTreePart(model);
		
		if(model instanceof MessageType)
			return new EventTreePart(model);
		
		return null;
	}
}
