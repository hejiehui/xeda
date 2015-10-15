package com.xross.tools.xeda.editor.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.xross.tools.xeda.editor.Activator;
import com.xross.tools.xeda.editor.model.XedaConstants;

public class DepartmentNodeFigure extends Figure implements XedaConstants{
	private Label label;
	private IFigure figure;
    public DepartmentNodeFigure() {
    	ToolbarLayout layout= new ToolbarLayout();
    	setLayoutManager(layout);
    	
        label = new Label();

        label.setLabelAlignment(PositionConstants.LEFT);
        label.setForegroundColor(ColorConstants.blue);
        label.setIcon(Activator.getDefault().getImageRegistry().get(Activator.STATE_MACHINE));
        label.setPreferredSize(700, 50);
        label.setBorder(new MarginBorder(TOP_LEVEL_SPACE));
        
        add(label);

        Figure line = new RectangleFigure();
        line.setBackgroundColor(ColorConstants.lightGray);
        line.setForegroundColor(ColorConstants.lightGray);
        line.setPreferredSize(700, 1);
        add(line);
        
        figure = new Figure();
        figure.setLayoutManager(new XYLayout());

        add(figure);
        setBorder(new LineBorder(ColorConstants.lightGray, 1));
    }

    public void setName(String name, String toolTip) {
    	label.setText(name);
    	label.setToolTip(new Label(toolTip));
        repaint();
    }
    
    public IFigure getFigure(){
    	return figure;
    }
    
    public Dimension getPreferredSize(int wHint, int hHint) {
    	Point loc = getLocation();
		int width = Math.max(300, label.getTextBounds().width);
		int height = 200;
		int margin = 50;
		Dimension size = new Dimension(width, height);

		for(Object node: getFigure().getChildren()){
			Figure child = (Figure)node;
			width = Math.max(width, (child.getLocation().x - loc.x) + child.getPreferredSize().width);
			height = Math.max(height, (child.getLocation().y - loc.y)+ child.getPreferredSize().height);
		}
		
		size = new Dimension(width + margin, height + margin);
		return size;
    }
}
