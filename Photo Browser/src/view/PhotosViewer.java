package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PhotosViewer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PhotosViewer(){
		this.setPreferredSize(new Dimension(400, 300));
		this.setBackground(Color.BLUE);
	}

}
