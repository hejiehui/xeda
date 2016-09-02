package com.xrosstools.xeda.editor.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.xrosstools.xeda.editor.model.DepartmentNode;

public class MoveDepartmentCommand extends Command {
    private DepartmentNode node;
    private Rectangle oldConstrain;
    private Rectangle newConstrain;

    public void setConstrain(Rectangle constrain) {
    	newConstrain = constrain;
    	newConstrain.setLocation(Point.max(new Point(0, 0), constrain.getLocation()));
    }

    public void setNode(DepartmentNode node) {
        this.node = node;
    }

    public void execute() {
    	oldConstrain = node.getConstrain();
        node.setConstrain(newConstrain);
    }

    public String getLabel() {
        return "Move Node";
    }

    public void redo() {
        node.setConstrain(newConstrain);
    }

    public void undo() {
        node.setConstrain(oldConstrain);
    }
}
