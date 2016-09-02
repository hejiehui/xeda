package com.xrosstools.xeda.editor.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class TopicNode extends BaseNode {
	private String address;

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return combine(super.getPropertyDescriptors(), new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ADDRESS, PROP_ADDRESS)});
	}

	public Object getPropertyValue(Object propName) {
		if (PROP_ADDRESS.equals(propName))
			return getValue(address);

		return super.getPropertyValue(propName);
	}

	public void setPropertyValue(Object propName, Object value) {
		super.setPropertyValue(propName, value);
		if (PROP_ADDRESS.equals(propName))
			setAddress((String) value);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		firePropertyChange(PROP_ADDRESS);
	}
}