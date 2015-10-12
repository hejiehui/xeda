package com.xross.tools.xeda.editor.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.xross.tools.xeda.XedaDiagramConstants;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.model.QueueNode;
import com.xross.tools.xeda.editor.model.TopicNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;

public class XedaDiagramReader implements XedaDiagramConstants {
	public XedaDiagram getFromDocument(Document doc){
		XedaDiagram model = new XedaDiagram();
		Element root = doc.getDocumentElement();

		model.setName(getChildNodeText(root, NAME));
		model.setDescription(getChildNodeText(root, DESCRIPTION));
		model.setDepartments(readMachines(getChildNode(root, DEPARTMENTS)));
		
		return model;
	}
	
	private List<DepartmentNode> readMachines(Node machinesNode) {
		NodeList machines = machinesNode.getChildNodes();
		List<DepartmentNode> machineList = new ArrayList<DepartmentNode>();
		for(int i = 0;i < machines.getLength(); i++) {
			machineList.add(readMachine(machines.item(i)));
		}
		return machineList;
	}
	
	private DepartmentNode readMachine(Node departmentNode) {
		DepartmentNode department = new DepartmentNode();
		department.setName(getChildNodeText(departmentNode, NAME));
		department.setDescription(getChildNodeText(departmentNode, DESCRIPTION));

		Node baseNodes = getChildNode(departmentNode, nodes);
		Node transitionsNode = getChildNode(departmentNode, MESSAGE_ROUTES);

		department.setNodes(readNodes(baseNodes));
		linkNodes(department, transitionsNode);
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
	private void linkNodes(DepartmentNode machine, Node transitionsNode) {
		NodeList transitions = transitionsNode.getChildNodes();
		Map<String, BaseNode> nodes = new HashMap<String, BaseNode>();
		
		for(BaseNode node: machine.getNodes()) {
			nodes.put(node.getId(), node);
		}
		
		for(int i = 0; i < transitions.getLength(); i++) {
			Node node = transitions.item(i);
			BaseNode source = nodes.get(getAttribute(node, SOURCE_ID));
			BaseNode target = nodes.get(getAttribute(node, TARGET_ID));
			MessageRoute route = new MessageRoute(source, target);
			route.setRouteId(getAttribute(node, ROUTE_ID));
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
}
