package com.xross.tools.xeda.editor.requests;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.xross.tools.xeda.editor.model.DepartmentNode;

public class ActorLayoutRequest extends Request {
	private DepartmentNode department;
	private boolean isHorizantal;
	private float alignment;
	
	public ActorLayoutRequest(DepartmentNode department, boolean isHorizantal, float alignment){
		super(RequestConstants.REQ_ALIGN);
		this.department = department;
		this.alignment = alignment;
		this.isHorizantal = isHorizantal;
	}
	
	public boolean isHorizantal() {
		return isHorizantal;
	}

	public float getAlignment() {
		return alignment;
	}

	public DepartmentNode getDepartmentNode() {
		return department;
	}
}
