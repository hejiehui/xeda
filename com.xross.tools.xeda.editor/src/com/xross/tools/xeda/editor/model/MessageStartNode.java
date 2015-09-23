package com.xross.tools.xeda.editor.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class MessageStartNode extends ActorNode {
	public MessageStartNode() {
		setId("start");
	}
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
			};
	}
}
