package com.xross.tools.xeda.editor.model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class DepartmentNode implements XedaConstants, IPropertySource {
	private String name;
	private String description;

	private List<BaseNode> nodes = new ArrayList<BaseNode>();
	private List<MessageType> events = new ArrayList<MessageType>();
	private XedaHelper helper = new XedaHelper(this);
	
	private Point location;
	
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	protected void firePropertyChange(String propertyName){
		listeners.firePropertyChange(propertyName, null, null);
	}

	public PropertyChangeSupport getListeners() {
		return listeners;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		IPropertyDescriptor[] descriptors;
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_DESRIPTION, PROP_DESRIPTION),
			};
		return descriptors;
	}
	
	public Object getPropertyValue(Object propName) {
		if (PROP_ID.equals(propName))
			return getValue(name);
		if (PROP_DESRIPTION.equals(propName))
			return getValue(description);
		
		return null;
	}

	public void setPropertyValue(Object propName, Object value){
		if (PROP_ID.equals(propName))
			setName((String)value);
		if (PROP_DESRIPTION.equals(propName))
			setDescription((String)value);
	}
	
	public Object getEditableValue(){
		return this;
	}

	public boolean isPropertySet(Object propName){
		return true;
	}

	public void resetPropertyValue(Object propName){
	}
		
	private String getValue(String value) {
		return value == null? "" : value;
	}

	public void removeNode(BaseNode node){
		nodes.remove(node);
		firePropertyChange(STATE_NODE);
	}

	public void addNode(BaseNode node){
		nodes.add(node);
		firePropertyChange(STATE_NODE);
	}

	public void setLocation(Point location) {
		this.location = location;
		listeners.firePropertyChange(PROP_LOCATION, null, location);
	}

	public Point getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		firePropertyChange(PROP_ID);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		firePropertyChange(PROP_DESRIPTION);
	}

	public List<BaseNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<BaseNode> nodes) {
		this.nodes = nodes;
		firePropertyChange(STATE_NODE);
	}
	
	public List<MessageType> getEvents() {
		return events;
	}
	
	public XedaHelper getHelper() {
		return helper;
	}
}
