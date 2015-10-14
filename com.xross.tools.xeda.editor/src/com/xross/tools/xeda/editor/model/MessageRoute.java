package com.xross.tools.xeda.editor.model;

import java.beans.PropertyChangeSupport;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class MessageRoute implements XedaConstants, IPropertySource {
	private String routeId;
	private BaseNode source;
	private BaseNode target;
	private RouteStyle style;

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public PropertyChangeSupport getListeners() {
		return listeners;
	}

	protected void firePropertyChange(String propertyName) {
		listeners.firePropertyChange(propertyName, null, null);
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		IPropertyDescriptor[] descriptors;
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(
				PROP_ROUTE_ID, PROP_ROUTE_ID), };
		return descriptors;
	}

	public Object getPropertyValue(Object propName) {
		if (PROP_ROUTE_ID.equals(propName))
			return getValue(routeId);

		return null;
	}

	public void setPropertyValue(Object propName, Object value) {
		if (PROP_ROUTE_ID.equals(propName))
			setRouteId((String) value);
	}

	public Object getEditableValue() {
		return this;
	}

	public boolean isPropertySet(Object propName) {
		return true;
	}

	public void resetPropertyValue(Object propName) {
	}

	private String getValue(String value) {
		return value == null ? "" : value;
	}

	public MessageRoute(BaseNode source, BaseNode target, RouteStyle style) {
		this.source = source;
		this.target = target;
		this.style = style;
		source.addOutput(this);
		target.addInput(this);
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
		firePropertyChange(PROP_ROUTE_ID);
	}

	public BaseNode getSource() {
		return source;
	}

	public void setSource(BaseNode source) {
		this.source = source;
		firePropertyChange(PROP_SOURCE);
	}

	public BaseNode getTarget() {
		return target;
	}

	public void setTarget(BaseNode target) {
		this.target = target;
		firePropertyChange(PROP_TARGET);
	}

	public RouteStyle getStyle() {
		return style;
	}

	public void setStyle(RouteStyle style) {
		this.style = style;
		firePropertyChange(PROP_STYLE);
	}
}
