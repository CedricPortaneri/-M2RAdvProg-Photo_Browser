package view;

import java.awt.Color;
import java.awt.geom.Line2D;

public class LineItem extends CanvasItem {
	
	private static final long serialVersionUID = 1L;
	private Line2D ligne;
	
	public LineItem(Color interieur , Color contour,Line2D ligne) {
    	super(interieur);
    	this.ligne = ligne;
    }
	
	public Line2D getLine(){
		return ligne;
	}
}