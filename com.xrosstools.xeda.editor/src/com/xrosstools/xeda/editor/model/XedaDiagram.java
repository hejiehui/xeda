package com.xrosstools.xeda.editor.model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class XedaDiagram implements XedaConstants, IPropertySource {
	private String name;
	private String description;

	private List<DepartmentNode> departments = new ArrayList<DepartmentNode>();
	
	private boolean isHorizantal;
	private int verticalSpace = 50;
	private int horizantalSpace = 50;
	private float alignment = 0;
	private int nodeWidth = 100;
	private int nodeHeight = 50;	
	
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	protected void firePropertyChange(String propertyName){
		listeners.firePropertyChange(propertyName, null, null);
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
	
	public List<String> validate() {
	    List<String> errorMessages = new ArrayList<>();
	    Set<String> ids = new HashSet<>();

	    for(DepartmentNode dept:  departments) {
	        if(ids.contains(dept.getId())) {
	            errorMessages.add("Duplicate department id is detected: " + dept.getId());
	        }
	        dept.validate(errorMessages);
	    }
	    
	    return errorMessages;
	}

	public List<DepartmentNode> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentNode> departments) {
		this.departments = departments;
	}

	public void addDepartment(DepartmentNode machine) {
		departments.add(machine);
		firePropertyChange(PROP_LAYOUT);
	}

	public void removeDepartment(DepartmentNode machine) {
		departments.remove(machine);
		firePropertyChange(PROP_LAYOUT);
	}
	
	public PropertyChangeSupport getListeners() {
		return listeners;
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

	public boolean isHorizantal() {
		return isHorizantal;
	}

	public void setHorizantal(boolean isHorizantal) {
		this.isHorizantal = isHorizantal;
	}

	public int getVerticalSpace() {
		return verticalSpace;
	}

	public void setVerticalSpace(int verticalSpace) {
		this.verticalSpace = verticalSpace;
	}

	public int getHorizantalSpace() {
		return horizantalSpace;
	}

	public void setHorizantalSpace(int horizantalSpace) {
		this.horizantalSpace = horizantalSpace;
	}

	public float getAlignment() {
		return alignment;
	}

	public void setAlignment(float alignment) {
		this.alignment = alignment;
	}

	public int getNodeWidth() {
		return nodeWidth;
	}

	public void setNodeWidth(int nodeWidth) {
		this.nodeWidth = nodeWidth;
	}

	public int getNodeHeight() {
		return nodeHeight;
	}

	public void setNodeHeight(int nodeHeight) {
		this.nodeHeight = nodeHeight;
	}	
}