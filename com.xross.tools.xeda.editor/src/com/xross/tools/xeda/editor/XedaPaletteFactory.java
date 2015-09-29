package com.xross.tools.xeda.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;

import com.xross.tools.xeda.editor.model.TopicNode;
import com.xross.tools.xeda.editor.model.QueueNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.ActorNode;

public class XedaPaletteFactory {
	private Class<XedaPaletteFactory> imageClass = XedaPaletteFactory.class;
    public PaletteRoot createPalette() {
        PaletteRoot paletteRoot = new PaletteRoot();
        paletteRoot.addAll(createCategories(paletteRoot));    	
        return paletteRoot;
    }
    
    private List<PaletteContainer> createCategories(PaletteRoot root) {
        List<PaletteContainer> categories = new ArrayList<PaletteContainer>();

        categories.add(createControlGroup(root));

        return categories;
    }
    
    private static Object[][] ENTRIES = new Object[][]{
    	{"State Machine", DepartmentNode.class, Activator.STATE_MACHINE},
    	{"Actor Node", ActorNode.class, Activator.ACTOR_NODE},
    	{"Queue Node", QueueNode.class, Activator.QUEUE_NODE},
    	{"Topic Node", TopicNode.class, Activator.TOPIC_NODE},
    };

    private PaletteContainer createControlGroup(PaletteRoot root) {
        PaletteGroup controlGroup = new PaletteGroup("Control Group");
        List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

        ToolEntry tool = new SelectionToolEntry();
        entries.add(tool);
        root.setDefaultEntry(tool);

    	tool = new MarqueeToolEntry();
    	entries.add(tool);

    	PaletteSeparator sep = new PaletteSeparator();
    	sep.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
    	entries.add(sep);

    	for(Object[] entry: ENTRIES){
	    	entries.add(new CombinedTemplateCreationEntry(
	    			(String)entry[0],
	    			(String)entry[0],
	    			entry[1],
	    			new SimpleFactory((Class)entry[1]),
	    			Activator.getDefault().getImageRegistry().getDescriptor(((String)entry[2])),    			
	    			Activator.getDefault().getImageRegistry().getDescriptor(((String)entry[2]))));
    	}

    	entries.add(new PaletteSeparator());
    	entries.add(new ConnectionCreationToolEntry(
    			"Transition",
    			"Transition",
    			null,
    			Activator.getDefault().getImageRegistry().getDescriptor(Activator.TRANSITION),    			
    			Activator.getDefault().getImageRegistry().getDescriptor(Activator.TRANSITION)));
    	controlGroup.addAll(entries);
        return controlGroup;
    }    

}
