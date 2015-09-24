package com.xross.tools.xeda.editor.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.xross.tools.xeda.XedaDiagramConstants;
import com.xross.tools.xeda.editor.model.TopicNode;
import com.xross.tools.xeda.editor.model.MessageType;
import com.xross.tools.xeda.editor.model.QueueNode;
import com.xross.tools.xeda.editor.model.DepartmentNode;
import com.xross.tools.xeda.editor.model.XedaDiagram;
import com.xross.tools.xeda.editor.model.ActorNode;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class XedaDiagramReader implements XedaDiagramConstants {
	public XedaDiagram getFromDocument(Document doc){
		XedaDiagram model = new XedaDiagram();
		Element root = doc.getDocumentElement();

		model.setName(getChildNodeText(root, NAME));
		model.setDescription(getChildNodeText(root, DESCRIPTION));
		model.setMachines(readMachines(getChildNode(root, STATE_MACHINES)));
		
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
	
	private DepartmentNode readMachine(Node machineNode) {
		DepartmentNode machine = new DepartmentNode();
		machine.setName(getChildNodeText(machineNode, NAME));
		machine.setDescription(getChildNodeText(machineNode, DESCRIPTION));

		Node statesNode = getChildNode(machineNode, STATES);
		Node eventsNode = getChildNode(machineNode, EVENTS);
		Node transitionsNode = getChildNode(machineNode, TRANSITIONS);

		machine.setEvents(readEvents(eventsNode));
		machine.setNodes(readStates(statesNode));
		linkState(machine, transitionsNode);
		
		return machine;
	}
	
	private List<ActorNode> readStates(Node statesNode) {
		NodeList states = statesNode.getChildNodes();
		List<ActorNode> nodes = new ArrayList<ActorNode>();
		for(int i = 0; i < states.getLength(); i++) {
			nodes.add(readState(states.item(i)));
		}
		return nodes;
	}
	
	private ActorNode readState(Node stateNode) {
		ActorNode node = createStateNode(stateNode);
		node.setId(getAttribute(stateNode, ID));
		node.setDescription(getChildNodeText(stateNode, DESCRIPTION));
		
		node.setReference(getChildNodeText(stateNode, REFERENCE));
		node.setEntryAction(getChildNodeText(stateNode, ENTRY_ACTION));
		node.setExitAction(getChildNodeText(stateNode, EXIT_ACTION));

		node.setLocation(new Point(
				getIntAttribute(stateNode, X_LOC), 
				getIntAttribute(stateNode, Y_LOC)));
		
		return node;
	}
	
	private ActorNode createStateNode(Node stateNode) {
		if(stateNode.getNodeName().equals(START_STATE))
			return new QueueNode();
		else
			if(stateNode.getNodeName().equals(END_STATE))
			return new TopicNode();
		else
			return new ActorNode();

	}
	private void linkState(DepartmentNode machine, Node transitionsNode) {
		NodeList transitions = transitionsNode.getChildNodes();
		Map<String, ActorNode> states = new HashMap<String, ActorNode>();
		Map<String, MessageType> events = new HashMap<String, MessageType>();
		
		for(ActorNode node: machine.getNodes()) {
			states.put(node.getId(), node);
		}
		
		for(MessageType event: machine.getEvents()) {
			events.put(event.getId(), event);
		}

		for(int i = 0; i < transitions.getLength(); i++) {
			Node node = transitions.item(i);
			ActorNode source = states.get(getAttribute(node, SOURCE_ID));
			ActorNode target = states.get(getAttribute(node, TARGET_ID));
			MessageType event = events.get(getAttribute(node, EVENT_ID));
			MessageRoute transition = new MessageRoute(source, target, machine.getHelper());
			transition.setEvent(event);
			transition.setTransitAction(getAttribute(node, TRANSIT_ACTION));
		}
	}

	private List<MessageType> readEvents(Node eventsNode) {
		NodeList events = eventsNode.getChildNodes();
		List<MessageType> eventList = new ArrayList<MessageType>();
		for(int i = 0; i < events.getLength(); i++) {
			Node eventNode = events.item(i);
			MessageType event = new MessageType();
			event.setId(getAttribute(eventNode, ID));
			event.setDescription(eventNode.getTextContent());
			eventList.add(event);
		}
		return eventList;
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
