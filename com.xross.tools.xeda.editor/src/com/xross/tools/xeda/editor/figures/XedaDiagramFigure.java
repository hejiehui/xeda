package com.xross.tools.xeda.editor.figures;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;

import com.xross.tools.xeda.editor.model.XedaConstants;


public class XedaDiagramFigure extends FreeformLayer implements XedaConstants{
    public XedaDiagramFigure() {
    	setLayoutManager(new FreeformLayout());
    }
}
