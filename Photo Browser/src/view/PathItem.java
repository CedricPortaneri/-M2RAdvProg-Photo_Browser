package view;

import java.awt.Color;
import java.awt.geom.Path2D;

public class PathItem extends CanvasItem {
	
	private static final long serialVersionUID = 1L;
	private Path2D path;
	
	public PathItem(Color interieur , Color contour,Path2D chemin) {
    	super(interieur);
    	this.path = chemin;
    }
	
	public Path2D getPath(){
		return path;
	}
}