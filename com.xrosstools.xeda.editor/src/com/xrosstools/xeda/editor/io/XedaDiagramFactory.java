package com.xrosstools.xeda.editor.io;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.w3c.dom.Document;

import com.xrosstools.xeda.editor.model.ActorNode;
import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.model.MessageRoute;
import com.xrosstools.xeda.editor.model.RouteStyle;
import com.xrosstools.xeda.editor.model.XedaConstants;
import com.xrosstools.xeda.editor.model.XedaDiagram;

public class XedaDiagramFactory implements XedaConstants{
	public static final String LOCATION = "location";
	
	public XedaDiagram getEmptyDiagram(){
		XedaDiagram smd = new XedaDiagram();
		smd.setName("StateMachineDiagram");	
		
//		smd.getMachines().add(createStateMachine("state machine 1", 0));
		smd.getDepartments().add(createStateMachine("state machine 2", 1));
//		smd.getMachines().add(createStateMachine("state machine 3", 2));
		
		return smd;
	}
	
	private DepartmentNode createStateMachine(String name, int num){
		DepartmentNode sm = new DepartmentNode();
		sm.setName(name);
		
		ActorNode a = new ActorNode();
		a.setLocation(new Point(0, 0));
		a.setId("start");
		sm.getNodes().add(a);

		for(int i = 0; i < num; i++)
		{
			ActorNode b = new ActorNode();
			b.setLocation(new Point((i+1)* 200, 0));
			b.setId("state" + i);
			sm.getNodes().add(b);
			
			MessageRoute t = new MessageRoute(a, b, RouteStyle.heightFirst);
			t.setRouteId("route " + i);
			
			a = b;
		}		
		
		sm.setConstrain(new Rectangle(10, 10, 100, 100));
		return sm;
	}
	
	private XedaDiagramReader reader = new XedaDiagramReader();
	private XedaDiagramWriter writer = new XedaDiagramWriter();
	public XedaDiagram getFromDocument(Document doc){
		return reader.getFromDocument(doc);
	}
	
	public Document writeToDocument(XedaDiagram model){
		return writer.writeToDocument(model);
	}
}
