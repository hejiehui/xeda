package com.xrosstools.xeda.editor.model;

import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

//TODO allow additional properties?
public class ActorNode extends BaseNode {
	private String reference;
	private String actorClassName;
	private String errorHandler;

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return combine(super.getPropertyDescriptors(), new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ACTOR_CLASS_NAME, PROP_ACTOR_CLASS_NAME),
				new TextPropertyDescriptor(PROP_ERROR_HANDLER, PROP_ERROR_HANDLER),
				new TextPropertyDescriptor(PROP_REFERENCE, PROP_REFERENCE), });
	}

	public Object getPropertyValue(Object propName) {
		if (PROP_ACTOR_CLASS_NAME.equals(propName))
			return getValue(actorClassName);
		if (PROP_ERROR_HANDLER.equals(propName))
			return getValue(errorHandler);
		if (PROP_REFERENCE.equals(propName))
			return getValue(reference);

		return super.getPropertyValue(propName);
	}

	public void setPropertyValue(Object propName, Object value) {
		super.setPropertyValue(propName, value);
		if (PROP_ACTOR_CLASS_NAME.equals(propName))
			setActorClassName((String) value);
		if (PROP_ERROR_HANDLER.equals(propName))
			setErrorHandler((String) value);
		if (PROP_REFERENCE.equals(propName))
			setReference((String) value);
	}
	
	public void validate(List<String> errorMessages) {
        if(isEmpty(getId())) {
            errorMessages.add("Actor Id is notset");
        }
        
        if(isEmpty(actorClassName)) {
            errorMessages.add("Actor \"" + getId() + "\" actorClassName is not set");
        }
	}
	
	public String getActorClassName() {
		return actorClassName;
	}

	public void setActorClassName(String actorClassName) {
		this.actorClassName = actorClassName;
		firePropertyChange(PROP_ACTOR_CLASS_NAME);
	}

	public String getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(String errorHandler) {
		this.errorHandler = errorHandler;
		firePropertyChange(PROP_ERROR_HANDLER);
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
		firePropertyChange(PROP_REFERENCE);
	}
}
