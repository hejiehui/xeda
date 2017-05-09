package com.xrosstools.xeda.editor.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.xrosstools.xeda.XedaDiagramConstants;
import com.xrosstools.xeda.editor.model.ActorNode;
import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.model.MessageRoute;
import com.xrosstools.xeda.editor.model.QueueNode;
import com.xrosstools.xeda.editor.model.RouteStyle;
import com.xrosstools.xeda.editor.model.TopicNode;
import com.xrosstools.xeda.editor.model.XedaDiagram;

public class XedaDiagramReader implements XedaDiagramConstants {
	public XedaDiagram getFromDocument(Document doc){
		XedaDiagram diagram = new XedaDiagram();
		Element root = doc.getDocumentElement();

		diagram.setName(getChildNodeText(root, NAME));
		diagram.setDescription(getChildNodeText(root, DESCRIPTION));
		diagram.setDepartments(readDepartments(getChildNode(root, DEPARTMENTS)));
		
		linkNodes(diagram.getDepartments(), getChildNode(root, MESSAGE_ROUTES));
		
		return diagram;
	}
	
	private List<DepartmentNode> readDepartments(Node machinesNode) {
		NodeList nodes = machinesNode.getChildNodes();
		List<DepartmentNode> machineList = new ArrayList<DepartmentNode>();
		for(int i = 0;i < nodes.getLength(); i++) {
			if(isValidNode(nodes.item(i)))
				machineList.add(readDepartment(nodes.item(i)));
		}
		
		return machineList;
	}
	
	private DepartmentNode readDepartment(Node departmentNode) {
		DepartmentNode department = new DepartmentNode();
		department.setId(getChildNodeText(departmentNode, NAME));
		department.setDescription(getChildNodeText(departmentNode, DESCRIPTION));

		Node baseNodes = getChildNode(departmentNode, NODES);

		department.setNodes(readNodes(baseNodes));
		
		department.setConstrain(new Rectangle(
				getIntAttribute(departmentNode, X_LOC), 
				getIntAttribute(departmentNode, Y_LOC),
				getIntAttribute(departmentNode, WIDTH), 
				getIntAttribute(departmentNode, HEIGHT)));

		return department;
	}
	
	private List<BaseNode> readNodes(Node baseNodes) {
		NodeList nodesList = baseNodes.getChildNodes();
		List<BaseNode> nodes = new ArrayList<BaseNode>();
		for(int i = 0; i < nodesList.getLength(); i++) {
			if(isValidNode(nodesList.item(i)))
				nodes.add(readNode(nodesList.item(i)));
		}
		return nodes;
	}
	
	private BaseNode readNode(Node baseNode) {
		BaseNode node = createNode(baseNode);
		node.setId(getAttribute(baseNode, ID));
		node.setDescription(getChildNodeText(baseNode, DESCRIPTION));
		
		node.setLocation(new Point(
				getIntAttribute(baseNode, X_LOC), 
				getIntAttribute(baseNode, Y_LOC)));
		
		return node;
	}
	
	private BaseNode createNode(Node baseNode) {
		if(baseNode.getNodeName().equals(QUEUE)) {
			QueueNode node = new QueueNode();
			node.setAddress(getAttribute(baseNode, ADDRESS));
			return node;
		} else
			if(baseNode.getNodeName().equals(TOPIC)) {
				TopicNode node = new TopicNode();
				node.setAddress(getAttribute(baseNode, ADDRESS));
				return node;
		} else {
			ActorNode node = new ActorNode();
			
			node.setReference(getChildNodeText(baseNode, REFERENCE));
			node.setActorClassName(getChildNodeText(baseNode, ACTOR_CLASS));
			node.setErrorHandler(getChildNodeText(baseNode, ERROR_HANDLER));

			return node;
		}

	}
	
	private void linkNodes(List<DepartmentNode> machines, Node routesNode) {
		Map<String, DepartmentNode> deptMap = new HashMap<String, DepartmentNode>();
		
		for(DepartmentNode dept: machines) {
		    deptMap.put(dept.getId(), dept);
		}
		
		NodeList routes = routesNode.getChildNodes(); 
        for(int i = 0; i < routes.getLength(); i++) {
            Node node = routes.item(i);
            if(!isValidNode(node)) continue;
                
            String srcId = getAttribute(node, SOURCE_ID);
            String srcDeptId = getAttribute(node, SOURCE_DEPT_ID);
            String tgtId = getAttribute(node, TARGET_ID);
            String tgtDeptId = getAttribute(node, TARGET_DEPT_ID);
            RouteStyle style = RouteStyle.valueOf(getAttribute(node, STYLE));
            String id = getAttribute(node, ROUTE_ID);

			BaseNode source = deptMap.get(srcDeptId).getNodeById(srcId);
			BaseNode target = deptMap.get(tgtDeptId).getNodeById(tgtId);
			MessageRoute route = new MessageRoute(source, target, style);
			route.setRouteId(id);
		}
	}

	private String getChildNodeText(Node node, String childName) {
		Node child = getChildNode(node, childName);
		if(child == null)
			return null;

		return child.getTextContent();
	}
	
	private Node getChildNode(Node node, String name) {
		NodeList children = node.getChildNodes();
		Node found = null;
		for(int i = 0; i < children.getLength(); i++){
			if(!children.item(i).getNodeName().equalsIgnoreCase(name))
				continue;
			found = children.item(i);
			break;
		}
		return found;
	}
	
	private String getAttribute(Node node, String attributeName){
		NamedNodeMap map = node.getAttributes();
		for(int i = 0; i < map.getLength(); i++)
			if(attributeName.equals(map.item(i).getNodeName()))
				return map.item(i).getNodeValue();

		return null;
	}

	private int getIntAttribute(Node node, String attributeName) {
		return Integer.parseInt(getAttribute(node, attributeName));
	}
	
	private boolean isValidNode(Node node) {
		return !node.getNodeName().equals("#text");
	}
}
