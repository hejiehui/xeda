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
        figure.setPreferredSize(new Dimension(700, 400));

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
}
