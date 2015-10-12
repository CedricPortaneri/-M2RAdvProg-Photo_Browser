package view;

import java.awt.Color;
import java.awt.Rectangle;

public class RectangleItem extends CanvasItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle rect;

    public RectangleItem(Color interieur , Color contour,Rectangle r) {
    	super(interieur,contour);
    	this.rect=r;
    }

	public Rectangle getRect() {
		return rect;
	}
    
}