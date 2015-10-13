package view;

import java.awt.Color;

import javax.swing.JComponent;

public class TextComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private TextItem ti ;
	private String txt;
	private Color color;
	
	
	public TextComponent(Color interieur , Color contour,int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.txt = " ";
		this.color = interieur;
		this.ti = new TextItem(interieur,contour,txt,x,y);
		this.add(ti);
		this.setBackground(Color.BLUE);
		this.setVisible(true);
		this.setFocusable(true);
	}
	
//	public void paintComponent(Graphics graphics) {
//		System.out.println("?");
//		Graphics2D g = (Graphics2D) graphics;
//		RenderingHints rh = new RenderingHints(
//	             RenderingHints.KEY_TEXT_ANTIALIASING,
//	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//	    g.setRenderingHints(rh);
//	    
//		g.setColor(Color.YELLOW);
//		g.fillRect(x, y, 10, 10);
//	}
	
	public String getTxt() {
		return txt;
	}

	
	public void setTxt(String txt) {
		this.txt = txt;
		this.ti = new TextItem(color,color,txt,x,y);
	}
	
	

	public void setTi(TextItem ti) {
		this.ti = ti;
	}

	public TextItem getTi() {
		return ti;
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
