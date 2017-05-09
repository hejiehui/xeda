package com.xrosstools.xeda.editor.model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public abstract class BaseNode implements XedaConstants, IPropertySource {
	private String id;
	private String departmentId;
	private String description;
	private List<MessageRoute> inputs = new ArrayList<MessageRoute>();
	private List<MessageRoute> outputs = new ArrayList<MessageRoute>();

	private Point location;

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public PropertyChangeSupport getListeners() {
		return listeners;
	}

	protected void firePropertyChange(String propertyName) {
		listeners.firePropertyChange(propertyName, null, null);
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
		listeners.firePropertyChange(PROP_LOCATION, null, location);
	}
	
	public IPropertyDescriptor[] combine(IPropertyDescriptor[] p1, IPropertyDescriptor[] p2) {
		IPropertyDescriptor[] descriptors = new IPropertyDescriptor[p1.length + p2.length];
		System.arraycopy(p1, 0, descriptors, 0, p1.length);
		System.arraycopy(p2, 0, descriptors, p1.length, p2.length);
		return descriptors;
	}
	
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID), 
				new TextPropertyDescriptor(PROP_DESRIPTION, PROP_DESRIPTION),};
	}

	public Object getPropertyValue(Object propName) {
		if (PROP_ID.equals(propName))
			return getValue(id);
		if (PROP_DESRIPTION.equals(propName))
			return getValue(description);

		return null;
	}

	public void setPropertyValue(Object propName, Object value) {
		if (PROP_ID.equals(propName))
			setId((String) value);
		if (PROP_DESRIPTION.equals(propName))
			setDescription((String) value);
	}

	public Object getEditableValue() {
		return this;
	}

	public boolean isPropertySet(Object propName) {
		return true;
	}

	public void resetPropertyValue(Object propName) {
	}

	public String getValue(String value) {
		return value == null ? "" : value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		firePropertyChange(PROP_ID);
	}

	public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
        firePropertyChange(PROP_ID);
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		firePropertyChange(PROP_DESRIPTION);
	}

	public List<MessageRoute> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<MessageRoute> outputs) {
		this.outputs = outputs;
	}

	public List<MessageRoute> getInputs() {
		return inputs;
	}

	public void setInputs(List<MessageRoute> inputs) {
		this.inputs = inputs;
		firePropertyChange(PROP_INPUTS);
	}

	public void removeOutput(MessageRoute output) {
		if (!outputs.contains(output))
			return;
		outputs.remove(output);
		firePropertyChange(PROP_OUTPUTS);
	}

	public void removeAllOutputs() {
		List<MessageRoute> tempOutputs = new ArrayList<MessageRoute>(outputs);
		for (MessageRoute output : tempOutputs)
			removeOutput(output);
		firePropertyChange(PROP_OUTPUTS);
	}

	public void removeInput(MessageRoute input) {
		if (!inputs.contains(input))
			return;
		inputs.remove(input);
		firePropertyChange(PROP_INPUTS);
	}

	public void removeAllInputs() {
		List<MessageRoute> tempInputs = new ArrayList<MessageRoute>(inputs);
		for (MessageRoute input : tempInputs)
			removeInput(input);
		firePropertyChange(PROP_INPUTS);
	}

	public void removeAllConnections() {
		removeAllInputs();
		removeAllOutputs();
	}

	public void addOutput(MessageRoute output) {
		outputs.add(output);
		firePropertyChange(PROP_OUTPUTS);
	}

	public void addInput(MessageRoute input) {
		inputs.add(input);
		firePropertyChange(PROP_INPUTS);
	}
}
