package view;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.MainModel;

public class PhotoScrollPanel extends JScrollPane implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PhotoComponent currentPhoto;
	
	public PhotoScrollPanel(MainModel model){
		
		currentPhoto = new PhotoComponent(model.getGallery().get(0));
		this.setViewportView(currentPhoto);

		this.setPreferredSize(currentPhoto.getPreferredSize());
		this.setSize(currentPhoto.getSize());
		this.setMaximumSize(new Dimension((int)currentPhoto.getSize().getWidth()+10,(int)currentPhoto.getSize().getHeight()+10));
		
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setBorder(null);
		
	}
	
	public PhotoScrollPanel(MainModel model,PhotoComponent currentPhoto ){
		this.currentPhoto = currentPhoto;
		this.setViewportView(currentPhoto);

		this.setPreferredSize(currentPhoto.getPreferredSize());
		this.setSize(currentPhoto.getSize());
		this.setMaximumSize(new Dimension((int)currentPhoto.getSize().getWidth()+10,(int)currentPhoto.getSize().getHeight()+10));
		
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

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof MainModel){
			this.currentPhoto.repaint();
		}
		
	}
	

}
