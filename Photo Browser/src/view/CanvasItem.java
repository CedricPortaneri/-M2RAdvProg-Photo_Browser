package view;

import java.awt.Color;
import java.awt.Component;

/* Abstract class that represent every drawing object on the back of the photo */
public abstract class CanvasItem extends Component {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color interieurColor;
	private Color contourColor;
	
  
    public Color getInterieurColor() {
		return interieurColor;
	}

	public Color getContourColor() {
		return contourColor;
	}

	public CanvasItem(Color interieur , Color contour) {
		this.interieurColor = interieur;
		this.contourColor = contour;
    }

	public CanvasItem(Color couleur) {
		this.interieurColor = couleur;
    }
	
}