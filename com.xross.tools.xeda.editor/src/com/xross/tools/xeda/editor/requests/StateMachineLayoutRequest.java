package com.xross.tools.xeda.editor.requests;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.xross.tools.xeda.editor.model.XedaDiagram;

public class StateMachineLayoutRequest extends Request {
	private XedaDiagram diagram;
	private boolean isHorizantal;
	private float alignment;
	
	public StateMachineLayoutRequest(XedaDiagram diagram, boolean isHorizantal, float alignment){
		super(RequestConstants.REQ_ALIGN);
		this.diagram = diagram;
		this.alignment = alignment;
		this.isHorizantal = isHorizantal;
	}
	
	public boolean isHorizantal() {
		return isHorizantal;
	}

	public float getAlignment() {
		return alignment;
	}

	public XedaDiagram getDiagram() {
		return diagram;
	}
}
