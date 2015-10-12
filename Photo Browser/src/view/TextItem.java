package view;

import java.awt.Color;

public class TextItem extends CanvasItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String txt;
	private int x;
	private int y;
	
	public TextItem(Color interieur, Color contour, String txt,int x, int y) {
		super(interieur, contour);
		this.txt = txt;
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
