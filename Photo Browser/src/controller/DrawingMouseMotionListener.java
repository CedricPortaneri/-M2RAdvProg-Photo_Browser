package controller;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import view.EllipseItem;
import view.LineItem;
import view.PathItem;
import view.PhotoComponent;
import view.RectangleItem;

public class DrawingMouseMotionListener extends MouseMotionAdapter {

	private PhotoComponent pc;

	public DrawingMouseMotionListener(PhotoComponent pc) {

		this.pc = pc;
	}

	public void mouseDragged(MouseEvent evt) {
		if (pc.isFlipped()) {

			switch (pc.getDrawingMode()) {
			case Rectangle:

				if (pc.currentCanvas == null)
					return;
				if (evt.getX() - pc.getSelected_x() > 0) {

					if (evt.getY() - pc.getSelected_y() > 0) {
						pc.currentCanvas = new RectangleItem(
								pc.getInteriorColor(), pc.getContourColor(),
								new Rectangle(pc.getSelected_x(),
										pc.getSelected_y(), evt.getX()
												- pc.getSelected_x(),
										evt.getY() - pc.getSelected_y()));
					} else {
						pc.currentCanvas = new RectangleItem(
						pc.getInteriorColor(),
								pc.getContourColor(), new Rectangle(
										pc.getSelected_x(), evt.getY(),
										evt.getX() - pc.getSelected_x(),
										pc.getSelected_y() - evt.getY()));

					}
				}

				else {
					if (evt.getY() - pc.getSelected_y() > 0) {

						pc.currentCanvas = new RectangleItem(
						pc.getInteriorColor(),
								pc.getContourColor(), new Rectangle(evt.getX(),
										pc.getSelected_y(), pc.getSelected_x()
												- evt.getX(), evt.getY()
												- pc.getSelected_y()));

					} else {

						pc.currentCanvas = new RectangleItem(
						pc.getInteriorColor(),
								pc.getContourColor(), new Rectangle(evt.getX(),
										evt.getY(), pc.getSelected_x()
												- evt.getX(),
										pc.getSelected_y() - evt.getY()));

					}
				}
				pc.repaint();
				break;
			case Ellipse:
				if (pc.currentCanvas == null)
					return;
				if (evt.getX() - pc.getSelected_x() > 0) {

					if (evt.getY() - pc.getSelected_y() > 0) {
						pc.currentCanvas = new EllipseItem(
						pc.getInteriorColor(),
								pc.getContourColor(), new Ellipse2D.Double(
										pc.getSelected_x(), pc.getSelected_y(),
										evt.getX() - pc.getSelected_x(),
										evt.getY() - pc.getSelected_y()));
					} else {
						pc.currentCanvas = new EllipseItem(
						pc.getInteriorColor(),
								pc.getContourColor(), new Ellipse2D.Double(
										pc.getSelected_x(), evt.getY(),
										evt.getX() - pc.getSelected_x(),
										pc.getSelected_y() - evt.getY()));

					}
				}

				else {
					if (evt.getY() - pc.getSelected_y() > 0) {

						pc.currentCanvas = new EllipseItem(
						pc.getInteriorColor(),
								pc.getContourColor(), new Ellipse2D.Double(
										evt.getX(), pc.getSelected_y(),
										pc.getSelected_x() - evt.getX(),
										evt.getY() - pc.getSelected_y()));

					} else {

						pc.currentCanvas = new EllipseItem(
						pc.getInteriorColor(),
								pc.getContourColor(), new Ellipse2D.Double(
										evt.getX(), evt.getY(),
										pc.getSelected_x() - evt.getX(),
										pc.getSelected_y() - evt.getY()));

					}
				}
				pc.repaint();
				break;
			case Line:
				if (pc.currentCanvas == null)
					return;

				pc.currentCanvas = new LineItem(
				pc.getInteriorColor(),
						pc.getContourColor(), new Line2D.Double(
								pc.getSelected_x(), pc.getSelected_y(),
								evt.getX(), evt.getY()));

				pc.repaint();
				break;
			case Path:
				if (pc.currentCanvas == null)
					return;

				PathItem p = (PathItem) pc.currentCanvas;
				p.getPath().lineTo(evt.getX(), evt.getY());
				pc.currentCanvas = p;

				pc.repaint();
				break;
			case Select:
				if (pc.listeCanvas.size() != 0 && pc.selectedCanvas != null) {
					if (pc.selectedCanvas.getClass() == RectangleItem.class) {
						RectangleItem r = (RectangleItem) pc.selectedCanvas;
						if (evt.getX() > r.getRect().x
								&& evt.getX() < r.getRect().x
										+ r.getRect().width) {
							if (evt.getY() > r.getRect().y
									&& evt.getY() < r.getRect().y
											+ r.getRect().height) {
								int translate_x = evt.getX()
										- pc.getSelected_x();
								int translate_y = evt.getY()
										- pc.getSelected_y();
								r.getRect().x = r.getRect().x + translate_x;
								r.getRect().y = r.getRect().y + translate_y;
								pc.selectedCanvas = r;
								pc.setSelected_x(evt.getX());
								pc.setSelected_y(evt.getY());
								pc.repaint();
							}
						}
					} else if (pc.selectedCanvas.getClass() == EllipseItem.class) {
						EllipseItem e = (EllipseItem) pc.selectedCanvas;
						if (e.getEllipse().contains(
								new Point2D.Float(evt.getX(), evt.getY()))) {
							int translate_x = evt.getX() - pc.getSelected_x();
							int translate_y = evt.getY() - pc.getSelected_y();
							Rectangle2D r = e.getEllipse().getFrame();
							r.setRect(r.getX() + translate_x, r.getY()
									+ translate_y, r.getWidth(), r.getHeight());
							e.getEllipse().setFrame(r);
							pc.selectedCanvas = e;
							pc.setSelected_x(evt.getX());
							pc.setSelected_y(evt.getY());
							pc.repaint();

						}
					}
				}
				break;
			default:
				break;
			}

		} else {

		}

	}
}
