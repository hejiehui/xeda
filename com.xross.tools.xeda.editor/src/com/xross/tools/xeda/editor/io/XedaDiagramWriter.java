package com.xross.tools.xeda.editor.io;

import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xross.tools.xeda.XedaDiagramConstants;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.BaseNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.MessageRoute;
import com.xross.tools.xeda.editor.model.QueueNode;
import com.xross.tools.xeda.editor.model.TopicNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;

public class XedaDiagramWriter implements XedaDiagramConstants {
	public Document writeToDocument(XedaDiagram model){
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = (Element)doc.createElement(XEDA_DIAGRAM);
			doc.appendChild(root);

			createNameDesc(doc, root, model.getName(), model.getDescription());			
			
			Element machinesNode = createNode(doc, root, DEPARTMENTS);
			writeMachines(doc, machinesNode, model);

			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void writeMachines(Document doc, Element machinesNode, XedaDiagram model) {
		for(DepartmentNode machine: model.getMachines()) {
			Element machineNode = createNode(doc, machinesNode, DEPARTMENT);
			writeMachine(doc, machineNode, machine);
		}
	}
	
	private void writeMachine(Document doc, Element machineNode, DepartmentNode machine) {
		createNameDesc(doc, machineNode, machine.getName(), machine.getDescription());
		
		Element statesNode = createNode(doc, machineNode, nodes);
		Element transitionsNode = createNode(doc, machineNode, MESSAGE_ROUTES);
		machineNode.appendChild(statesNode);
		machineNode.appendChild(transitionsNode);

		writeStatesAndTransitions(doc, statesNode, machine.getNodes(), transitionsNode);
	}
	
	private void writeStatesAndTransitions(Document doc, Element statesNode, List<BaseNode> nodes, Element transitionsNode) {
		for(BaseNode node: nodes) {
			Element baseNode = (Element)doc.createElement(getNodeType(node));
			statesNode.appendChild(baseNode);
			writeState(doc, baseNode, node);
			writeTransitions(doc, transitionsNode, node.getOutputs());
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
	
	private void writeState(Document doc, Element baseNode, BaseNode node) {
		createIdDesc(doc, baseNode, node.getId(), node.getDescription());
		
		if(node instanceof QueueNode) {
			QueueNode queue = (QueueNode)node;
			createTextNode(doc, baseNode, ADDRESS, queue.getAddress());
		} else
		if(node instanceof TopicNode) {
			TopicNode topic = (TopicNode)node;
			createTextNode(doc, baseNode, ADDRESS, topic.getAddress());
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