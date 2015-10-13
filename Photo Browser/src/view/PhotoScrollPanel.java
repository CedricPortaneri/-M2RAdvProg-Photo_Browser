package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.MainModel;

public class PhotoScrollPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PhotoComponent currentPhoto;

	public PhotoScrollPanel(MainModel model) {
		
		if (model.getGallery() != null ) {
		currentPhoto = new PhotoComponent(model.getGallery().get(0));
		this.setViewportView(currentPhoto);

		this.setPreferredSize(currentPhoto.getPreferredSize());
		this.setSize(currentPhoto.getSize());
		this.setMaximumSize(new Dimension((int) currentPhoto.getSize()
				.getWidth() + 15, (int) currentPhoto.getSize().getHeight() + 15));

		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setBorder(null);
		this.setVisible(true);
		}
		else {
			this.setVisible(false);
		}
	}

	public PhotoScrollPanel(MainModel model, PhotoComponent currentPhoto) {
		this.currentPhoto = currentPhoto;
		this.setViewportView(currentPhoto);

		this.setPreferredSize(currentPhoto.getPreferredSize());
		this.setSize(currentPhoto.getSize());
		this.setMaximumSize(new Dimension((int) currentPhoto.getSize()
				.getWidth() + 10, (int) currentPhoto.getSize().getHeight() + 10));

		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setBorder(null);
	}

	public PhotoComponent getCurrentPhoto() {
		return currentPhoto;
	}

	public void setCurrentPhoto(PhotoComponent currentPhoto) {
		this.currentPhoto = currentPhoto;
	}



}
