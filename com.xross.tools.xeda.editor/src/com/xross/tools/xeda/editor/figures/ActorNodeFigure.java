package com.xross.tools.xeda.editor.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;

import com.xross.tools.xeda.editor.Activator;

public class ActorNodeFigure extends RoundedRectangle {
    private Label nameLabel;
    private ImageFigure entry;
    private ImageFigure exit;

    public ActorNodeFigure() {
    	ToolbarLayout layout= new ToolbarLayout();
    	layout.setHorizontal(false);
    	layout.setSpacing(1);
    	layout.setStretchMinorAxis(false);
    	layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
    	setLayoutManager(layout);
    	this.setBorder(new MarginBorder(5));
    	
    	nameLabel = new Label();
        nameLabel.setLabelAlignment(PositionConstants.CENTER);
        nameLabel.setForegroundColor(ColorConstants.darkGreen);
        add(nameLabel);
    	
        Figure line = new RectangleFigure();
        line.setBackgroundColor(ColorConstants.lightGray);
        line.setForegroundColor(ColorConstants.lightGray);
        line.setPreferredSize(1000, 1);
        add(line);
        
        Figure actionsPanel = new Panel();
    	add(actionsPanel);
    	
    	ToolbarLayout actionsPanelLayout= new ToolbarLayout();
    	actionsPanelLayout.setHorizontal(true);
    	actionsPanelLayout.setSpacing(20);
    	actionsPanelLayout.setStretchMinorAxis(true);
    	actionsPanelLayout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
    	actionsPanel.setLayoutManager(actionsPanelLayout);
    	
    	entry = new ImageFigure(Activator.getDefault().getImageRegistry().get(Activator.ENTRY_ACTION));
    	actionsPanel.add(entry);

    	Figure actionLine = new RectangleFigure();
    	actionLine.setBackgroundColor(ColorConstants.lightGray);
    	actionLine.setForegroundColor(ColorConstants.lightGray);
    	actionLine.setPreferredSize(1, 20);
        actionsPanel.add(actionLine);
    	
    	exit = new ImageFigure(Activator.getDefault().getImageRegistry().get(Activator.EXIT_ACTION));
    	actionsPanel.add(exit);
    }
    
    public boolean isEntryAction(IFigure figure) {
    	return figure == entry;
    }

    public Dimension getGoodSize() {
        return new Dimension(Math.max(100, nameLabel.getTextBounds().width + 10), 50);
    }

    public void setName(String name) {
    	nameLabel.setText(name);
    	nameLabel.setToolTip(new Label(name));
    	repaint();
    }
    
    public void setEntryAction(String entryAction) {
		entry.setToolTip(new Label(entryAction));
        repaint();
    }
    
    public void setExistAction(String existAction) {
		exit.setToolTip(new Label(existAction));
        repaint();
    }
}
