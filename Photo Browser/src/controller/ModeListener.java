package controller;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import model.DModeEnum;
import model.MainModel;
import view.EllipseItem;
import view.RectangleItem;

/* Listener for the all the Drawing Mode in the Tool Bar */
public class ModeListener implements ActionListener {
	private MainModel pc;

	public ModeListener(MainModel pc) {
		super();
		this.pc = pc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DModeEnum drawingMode = DModeEnum.Rectangle;
		if (e.getSource().getClass().equals(JRadioButton.class)) {
			JRadioButton jr = (JRadioButton) e.getSource();
			drawingMode = DModeEnum.valueOf(jr.getActionCommand().toString());
			this.pc.getVue().getPhotoViewer().getScroll().getCurrentPhoto().getModel()
					.setDrawingMode(drawingMode);
			if (drawingMode.equals(DModeEnum.Text)) {
				this.pc.getVue().setCursor(new Cursor(Cursor.TEXT_CURSOR));
			}
			else {
				this.pc.getVue().setCursor(Cursor.getDefaultCursor());
			}

		} else if (e.getSource().getClass().equals(JButton.class)) {
			JButton jb = (JButton) e.getSource();
			if (jb.getActionCommand().equals("Delete")) {
				if (this.pc.getVue().getPhotoViewer().getScroll()
						.getCurrentPhoto().getModel().getSelectedCanvas() != null
						&& this.pc.getVue().getPhotoViewer().getScroll()
						.getCurrentPhoto().getModel().getListeCanvas().size() != 0) {
					this.pc.getVue().getPhotoViewer().getScroll()
							.getCurrentPhoto().getModel().setSelectedCanvas(null);

					this.pc.getVue().getPhotoViewer().getScroll()
							.getCurrentPhoto().getModel().getListeCanvas().remove(this.pc
							.getVue().getPhotoViewer().getScroll()
							.getCurrentPhoto().getModel().getIndiceSelectedCanvas());

					this.pc.getVue().getPhotoViewer().getScroll()
							.getCurrentPhoto().repaint();
				}
			} else if (jb.getActionCommand().equals("Clone")) {
				if (this.pc.getVue().getPhotoViewer().getScroll()
						.getCurrentPhoto().getModel().getListeCanvas().size() != 0
						&& this.pc.getVue().getPhotoViewer().getScroll()
								.getCurrentPhoto().getModel().getSelectedCanvas() != null) {
					if (this.pc.getVue().getPhotoViewer().getScroll()
							.getCurrentPhoto().getModel().getSelectedCanvas().getClass() == RectangleItem.class) {
						RectangleItem r = (RectangleItem) this.pc.getVue()
								.getPhotoViewer().getScroll().getCurrentPhoto()
								.getModel().getSelectedCanvas();
						RectangleItem clone = new RectangleItem(this.pc
								.getVue().getPhotoViewer().getScroll()
								.getCurrentPhoto().getModel().getSelectedCanvas()
								.getInterieurColor(), this.pc.getVue()
								.getPhotoViewer().getScroll().getCurrentPhoto()
								.getModel().getSelectedCanvas().getContourColor(),
								new Rectangle(r.getRect().x + 5,
										r.getRect().y + 5, r.getRect().width,
										r.getRect().height));

						this.pc.getVue().getPhotoViewer().getScroll()
								.getCurrentPhoto().getModel().getListeCanvas().add(clone);
						this.pc.getVue().getPhotoViewer().getScroll()
								.getCurrentPhoto().repaint();
					}
					else if (this.pc.getVue().getPhotoViewer().getScroll()
							.getCurrentPhoto().getModel().getSelectedCanvas().getClass() == EllipseItem.class) {
						EllipseItem e1 = (EllipseItem) this.pc.getVue()
								.getPhotoViewer().getScroll().getCurrentPhoto()
								.getModel().getSelectedCanvas();
						EllipseItem clone = new EllipseItem(this.pc
								.getVue().getPhotoViewer().getScroll()
								.getCurrentPhoto().getModel().getSelectedCanvas()
								.getInterieurColor(), this.pc.getVue()
								.getPhotoViewer().getScroll().getCurrentPhoto()
								.getModel().getSelectedCanvas().getContourColor(),
								new Ellipse2D.Double(e1.getEllipse().getX() + 5,
										e1.getEllipse().getY()  + 5, e1.getEllipse().getWidth(),
										e1.getEllipse().getHeight()));

						this.pc.getVue().getPhotoViewer().getScroll()
								.getCurrentPhoto().getModel().getListeCanvas().add(clone);
						this.pc.getVue().getPhotoViewer().getScroll()
								.getCurrentPhoto().repaint();
					}
				}
			}
		}

	}

}
