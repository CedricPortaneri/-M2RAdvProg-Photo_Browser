package view;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class EllipseItem extends CanvasItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ellipse2D ellipse;

    
    public EllipseItem(Color interieur , Color contour,Ellipse2D.Double ellipse) {
    	super(interieur,contour);
    	this.ellipse = ellipse;
    }


	public Ellipse2D getEllipse() {
		return ellipse;
	}
    
    
	   
}