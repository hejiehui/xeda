package com.xross.tools.xeda.editor.requests;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.xross.tools.xeda.editor.model.XedaDiagram;

public class StateNodeResizeRequest  extends Request {
	private XedaDiagram diagram;
	private boolean horizantal;
	private boolean nodeSize;
	private boolean increase;
	
	public StateNodeResizeRequest(XedaDiagram diagram, boolean nodeSize, boolean horizantal, boolean increase){
		super(RequestConstants.REQ_RESIZE);
		this.diagram = diagram;
		this.horizantal = horizantal;
		this.nodeSize = nodeSize;
		this.increase = increase;
	}

	public XedaDiagram getDiagram() {
		return diagram;
	}

	public boolean isHorizantal() {
		return horizantal;
	}

	public boolean isIncrease() {
		return increase;
	}

	public boolean isNodeSize() {
		return nodeSize;
	}
}
