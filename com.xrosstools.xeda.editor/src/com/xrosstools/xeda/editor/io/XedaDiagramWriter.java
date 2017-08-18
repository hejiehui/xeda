package com.xrosstools.xeda.editor.io;

import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.draw2d.geometry.Rectangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xrosstools.xeda.XedaDiagramConstants;
import com.xrosstools.xeda.editor.model.ActorNode;
import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.DepartmentNode;
import com.xrosstools.xeda.editor.model.MessageRoute;
import com.xrosstools.xeda.editor.model.QueueNode;
import com.xrosstools.xeda.editor.model.TopicNode;
import com.xrosstools.xeda.editor.model.XedaDiagram;

public class XedaDiagramWriter implements XedaDiagramConstants {
	public Document writeToDocument(XedaDiagram model){
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = (Element)doc.createElement(XEDA_DIAGRAM);
			doc.appendChild(root);

			createNameDesc(doc, root, model.getName(), model.getDescription());			
			
			Element deptsNode = createNode(doc, root, DEPARTMENTS);
			Element routesNode = createNode(doc, root, MESSAGE_ROUTES);
			
			writeDepartments(doc, deptsNode, model.getDepartments());
			writeRoutes(doc, routesNode, model.getDepartments());

			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void writeDepartments(Document doc, Element departmentsNode, List<DepartmentNode> depts) {
		for(DepartmentNode dept: depts) {
			Element departmentNode = createNode(doc, departmentsNode, DEPARTMENT);
			writeDepartment(doc, departmentNode, dept);
		}
	}
	
	private void writeDepartment(Document doc, Element departmentNode, DepartmentNode department) {
		createNameDesc(doc, departmentNode, department.getId(), department.getDescription());
		Rectangle constrain = department.getConstrain();
		departmentNode.setAttribute(X_LOC, String.valueOf(constrain.x));
		departmentNode.setAttribute(Y_LOC, String.valueOf(constrain.y));
		departmentNode.setAttribute(WIDTH, String.valueOf(constrain.width));
		departmentNode.setAttribute(HEIGHT, String.valueOf(constrain.height));
		
		Element actorsNode = createNode(doc, departmentNode, NODES);
		
		for(BaseNode node: department.getNodes()) {
			Element baseNode = (Element)doc.createElement(getNodeType(node));
			actorsNode.appendChild(baseNode);
			writeNode(doc, baseNode, node);
		}
	}
	
	private void writeRoutes(Document doc, Element routesNode, List<DepartmentNode> depts) {
		for(DepartmentNode machine: depts) {
			for(BaseNode node: machine.getNodes()) {
				writeTransitions(doc, routesNode, node.getOutputs());
			}
		}
	}

	private String getNodeType(BaseNode node) {
		if(node instanceof QueueNode)
			return QUEUE;
		else
		if(node instanceof TopicNode)
			return TOPIC;
		else
			return ACTOR;
	}
	
	private void writeNode(Document doc, Element baseNode, BaseNode node) {
		createIdDesc(doc, baseNode, node.getId(), node.getDescription());
		
		if(node instanceof QueueNode) {
			QueueNode queue = (QueueNode)node;
			baseNode.setAttribute(ADDRESS, queue.getAddress());
		} else
		if(node instanceof TopicNode) {
			TopicNode topic = (TopicNode)node;
			baseNode.setAttribute(ADDRESS, topic.getAddress());
		} else {
			ActorNode actor = (ActorNode)node;
			createTextNode(doc, baseNode, REFERENCE, actor.getReference());
			createTextNode(doc, baseNode, ACTOR_CLASS, actor.getActorClassName());
			createTextNode(doc, baseNode, ERROR_HANDLER, actor.getErrorHandler());
		}

		baseNode.setAttribute(X_LOC, String.valueOf(node.getLocation().x));
		baseNode.setAttribute(Y_LOC, String.valueOf(node.getLocation().y));
	}
	
	private void writeTransitions(Document doc, Element transitionsNode, List<MessageRoute> outputs) {
		for(MessageRoute transition: outputs) {
			Element node = createNode(doc, transitionsNode, ROUTE);
			if(transition.getRouteId() != null)
				node.setAttribute(ROUTE_ID, transition.getRouteId());
			node.setAttribute(SOURCE_ID, transition.getSource().getId());
			node.setAttribute(TARGET_ID, transition.getTarget().getId());
			node.setAttribute(SOURCE_DEPT_ID, transition.getSource().getDepartmentId());
            node.setAttribute(TARGET_DEPT_ID, transition.getTarget().getDepartmentId());
            node.setAttribute(STYLE, transition.getStyle().name());
		}
	}
	
	private void createNameDesc(Document doc, Element node, String name, String desc) {
		createTextNode(doc, node, NAME, name);
		createTextNode(doc, node, DESCRIPTION, desc);
	}
	
	private void createIdDesc(Document doc, Element node, String id, String desc) {
		node.setAttribute(ID, id);
		createTextNode(doc, node, DESCRIPTION, desc);
	}
	
	private Element createNode(Document doc, Element parent, String name) {
		Element node = (Element)doc.createElement(name);
		parent.appendChild(node);
		return node;
	}
	
	private Element createTextNode(Document doc, Element node, String name, String value) {
		Element textNode = (Element)doc.createElement(name);
		node.appendChild(textNode);
		if(value == null)
			return textNode;
		
		textNode.appendChild(doc.createTextNode(value));
		return textNode;
	}
}
