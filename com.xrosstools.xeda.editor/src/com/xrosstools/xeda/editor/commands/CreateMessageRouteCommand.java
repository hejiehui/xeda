package com.xrosstools.xeda.editor.commands;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.model.BaseNode;
import com.xrosstools.xeda.editor.model.MessageRoute;
import com.xrosstools.xeda.editor.model.RouteStyle;

public class CreateMessageRouteCommand extends Command {
	private RouteStyle style;
	private MessageRoute route;
	private BaseNode source;
	private BaseNode target;

	public CreateMessageRouteCommand(RouteStyle style) {
		this.style = style;
	}
	
	public void execute() {
		String defaultNamePrefix = ":" + target.getId() == null? "" : target.getId();

		Set<String> existingNames = new HashSet<>();
		for(MessageRoute route: source.getOutputs()) {
			existingNames.add(route.getRouteId());
		}

		int i = 1;
		String defaultName = defaultNamePrefix;
		while(existingNames.contains(defaultName)) {
			defaultName = defaultNamePrefix + i++;
		}
		
		route = new MessageRoute(source, target, style);
		route.setRouteId(defaultName);
	}

	public void redo() {
		source.addOutput(route);
		target.addInput(route);
	}

	public void setSource(BaseNode source) {
		this.source = source;
	}

	public void setTarget(BaseNode target) {
		this.target = target;
	}
	
	public void undo() {
		source.removeOutput(route);
		target.removeInput(route);
	}
}