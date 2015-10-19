package myComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* Custom Component which represent a Photo inside the Photo Viewer*/
public class PhotoComponent extends JComponent implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6862664907384365324L;
	private BasicPhotoModel model;

	public PhotoComponent(String imgRef) {

		if (imgRef == null) {
			this.setVisible(false);
		} else {
			setModel(new BasicPhotoModel(imgRef));
			setUI(new BasicPhotoUI(this));
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			this.setPreferredSize(new Dimension(((BasicPhotoModel) this.model)
					.getImg().getWidth(), ((BasicPhotoModel) this.model)
					.getImg().getHeight()));
			this.setSize(new Dimension(((BasicPhotoModel) this.model).getImg()
					.getWidth(), ((BasicPhotoModel) this.model).getImg()
					.getHeight()));
			this.setVisible(true);
		}
	}

	public void setUI(PhotoUI ui) {
		super.setUI(ui);
	}

	public void updateUI() {
		setUI((PhotoUI) UIManager.getUI(this));
		invalidate();

	}

	public String getUIClassID() {
		return PhotoUI.UI_CLASS_ID;
	}

	public BasicPhotoModel getModel() {
		return this.model;
	}

	public void setModel(BasicPhotoModel m) {
		if (this.model != null) {
			this.model.unregisterListener(this);
		}
		this.model = m;
		this.model.registerListener(this);
	}

	public void addStroke(MouseEvent evt) {
		this.model.addStroke(evt);
	}

	public void addReleasedStroke(MouseEvent evt) {
		this.model.addRealeasedStroke(evt);
	}

	public void addMotion(MouseEvent evt) {
		this.model.addMotion(evt);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.repaint();

	}
}
