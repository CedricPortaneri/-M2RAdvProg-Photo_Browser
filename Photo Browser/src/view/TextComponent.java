package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import controller.TextInputListener;

public class TextComponent extends JPanel implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private TextItem ti ;
	private String txt;
	
	
	public TextComponent(Color interieur , Color contour,int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.txt = "test";
		this.ti = new TextItem(interieur,contour,txt,x,y);
		this.add(ti);
		this.setBackground(Color.BLUE);
		this.setVisible(true);
		this.addKeyListener(this);
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

	@Override
	public void keyPressed(KeyEvent k) {
		System.out.println("!!!");
		char character = k.getKeyChar();
		System.out.println(character);
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("!!!");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println("!!!");
		// TODO Auto-generated method stub
		
	}

}
