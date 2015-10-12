package com.xross.tools.xeda.editor.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.xross.tools.xeda.editor.model.BaseNode;

public class MoveNodeCommand extends Command {
    private BaseNode node;

    private Point oldLocation;

    private Point newLocation;

    public void setLocation(Point location) {
    	newLocation = Point.max(new Point(0, 0), location);
    }

    public void setNode(BaseNode node) {
        this.node = node;
    }

    public void execute() {
    	oldLocation = node.getLocation();
        node.setLocation(newLocation);
    }

    public String getLabel() {
        return "Move Node";
    }

    public void redo() {
        node.setLocation(newLocation);
    }

    public void undo() {
        node.setLocation(oldLocation);
    }
}
