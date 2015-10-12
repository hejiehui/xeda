package com.xross.tools.xeda.editor.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;

import com.xross.tools.xeda.editor.Activator;

public class TopicNodeFigure extends RoundedRectangle {
    private Label nameLabel;

    public TopicNodeFigure() {
    	ToolbarLayout layout= new ToolbarLayout();
    	layout.setHorizontal(false);
    	layout.setSpacing(1);
    	layout.setStretchMinorAxis(false);
    	layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
    	setLayoutManager(layout);
    	this.setBorder(new MarginBorder(5));
    	
    	Label entry = new Label();
    	entry.setLabelAlignment(PositionConstants.CENTER);
    	entry.setForegroundColor(ColorConstants.darkGreen);
    	entry.setIcon(Activator.getDefault().getImageRegistry().get(Activator.TOPIC_NODE));
    	entry.setText("Topic");
    	add(entry);
    	
        Figure line = new RectangleFigure();
        line.setBackgroundColor(ColorConstants.lightGray);
        line.setForegroundColor(ColorConstants.lightGray);
        line.setPreferredSize(1000, 1);
        add(line);
        
    	nameLabel = new Label();
        nameLabel.setLabelAlignment(PositionConstants.CENTER);
        nameLabel.setForegroundColor(ColorConstants.darkGreen);
        add(nameLabel);
    }
    
    public Dimension getPreferredSize(int wHint, int hHint) {
        return new Dimension(Math.max(100, nameLabel.getTextBounds().width + 10), 50);
    }

    public void setName(String name) {
    	nameLabel.setText(name);
    	nameLabel.setToolTip(new Label(name));
    	repaint();
    }
}
