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
		XedaDiagram xeda = new XedaDiagram();
		xeda.setName("StateMachineDiagram");	
		
		xeda.getDepartments().add(createDepartment("Service", 3));
		
		return xeda;
	}
	
	private DepartmentNode createDepartment(String id, int num){
		DepartmentNode dept = new DepartmentNode();
		dept.setId(id);
		
		ActorNode a = new ActorNode();
		a.setLocation(new Point(0, 0));
		a.setId("frontend");
		dept.getNodes().add(a);

		for(int i = 0; i < num; i++)
		{
			ActorNode b = new ActorNode();
			b.setLocation(new Point((i+1)* 200, 0));
			b.setId("worker " + i);
			dept.getNodes().add(b);
			
			MessageRoute t = new MessageRoute(a, b, RouteStyle.heightFirst);
			t.setRouteId("route " + i);
			
			a = b;
		}

		dept.setConstrain(new Rectangle(10, 10, 100, 100));
		return dept;
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
