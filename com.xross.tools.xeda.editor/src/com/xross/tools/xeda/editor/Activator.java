package com.xross.tools.xeda.editor;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID 
	public static final String PLUGIN_ID = "com.xross.tools.xeda.editor"; //$NON-NLS-1$

	public static final String HOME = "icons/";
	public static final String ICO = ".ico";
	public static final String JPG = ".jpg";

	public static final String STATE_MACHINE_DIAGRAM = "state_machine_diagram";
	public static final String STATE_MACHINE = "department_24px_1";
	public static final String ACTOR_NODE = "actor_24px_5";
	public static final String QUEUE_NODE = "queue_node";
	public static final String TOPIC_NODE = "radio_24px_1";
	public static final String TRANSITION = "transition";
	public static final String ENTRY_ACTION = "end_point";
	public static final String EXIT_ACTION = "start_point";
	public static final String TRANSITION_ACTION = "transition_action";
	
	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
    protected void initializeImageRegistry(ImageRegistry reg) {
    	put(reg, STATE_MACHINE_DIAGRAM);
    	put(reg, STATE_MACHINE);
    	put(reg, ACTOR_NODE);
    	put(reg, QUEUE_NODE);
    	put(reg, TOPIC_NODE);
    	put(reg, TRANSITION);
    	put(reg, ENTRY_ACTION);
		put(reg, EXIT_ACTION);
		put(reg, TRANSITION_ACTION);
    	
//    	initByClass(reg);
    }
    
    private void put(ImageRegistry reg, String id){
    	reg.put(id, imageDescriptorFromPlugin(PLUGIN_ID, getIconPath(id)));
    }
//
//    public Image getImage(Class clazz){
//    	return this.getImageRegistry().get(getName(clazz));
//    }
    
    private static String getIconPath(String iconId){
    	return HOME + iconId + ICO;
    }

    public Image getImage(String id){
    	return getImageRegistry().get(id);
    }
}
