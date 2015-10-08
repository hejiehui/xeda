package com.xross.tools.xeda.editor.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;

import com.xross.tools.xeda.editor.model.XedaConstants;


public class XedaDiagramFigure extends Figure implements XedaConstants{
    public XedaDiagramFigure() {
    	setLayoutManager(new XYLayout());
    }
}
