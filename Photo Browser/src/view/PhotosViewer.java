package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.MainModel;

/* Main part of the application, the Viewer of the photos */
public class PhotosViewer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage bg;
	private PhotoScrollPanel scroll;
	
	public PhotosViewer(MainModel model) {

		this.setLayout(new BoxLayout( this, BoxLayout.X_AXIS ));
		
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout( jp, BoxLayout.Y_AXIS ));
		
		try {
			this.bg = ImageIO.read(new File("background1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		scroll = new PhotoScrollPanel(model);

		jp.add(Box.createVerticalGlue());
		jp.add(Box.createVerticalGlue());
		jp.add(scroll);
		jp.setBackground(new Color(0,0,0,0));
		jp.add(Box.createVerticalGlue());
		jp.add(Box.createVerticalGlue());
		
		this.add(Box.createHorizontalGlue());
		this.add(Box.createHorizontalGlue());
		this.add(jp);
		this.add(Box.createHorizontalGlue());
		this.add(Box.createHorizontalGlue());
		

	}

	public void setScroll(PhotoScrollPanel scroll) {
		this.scroll = scroll;
	}

	public PhotoScrollPanel getScroll() {
		return scroll;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, 1500, 1000, this);

	}
}
