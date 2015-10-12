package controller;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import model.DModeEnum;
import view.EllipseItem;
import view.LineItem;
import view.PathItem;
import view.PhotoComponent;
import view.PhotosViewer;
import view.RectangleItem;
import view.TextComponent;

public class DrawingMouseAdapterListener extends MouseAdapter {

	private PhotoComponent pc;

	public DrawingMouseAdapterListener(PhotoComponent pc) {
		this.pc = pc;
	}

	public void mousePressed(MouseEvent evt) {
		System.out.println(pc.getDrawingMode().toString());
		if (evt.getClickCount() == 2) {
			if (pc.isFlipped()) {
				pc.setFlipped(false);
				pc.repaint();
			} else {
				pc.setFlipped(true);
				pc.repaint();
			}
		} else if (pc.isFlipped()) {
			int width = 0, height = 0;

			switch (pc.getDrawingMode()) {

			case Rectangle:

				pc.setSelected_x(evt.getX());
				pc.setSelected_y(evt.getY());
				pc.currentCanvas = new RectangleItem(
				/* interieurChooser.getBackground() */Color.BLUE,
				/* contourChooser.getBackground() */Color.BLACK, new Rectangle(
						pc.getSelected_x(), pc.getSelected_y(), width, height));
				break;
			case Ellipse:

				pc.setSelected_x(evt.getX());
				pc.setSelected_y(evt.getY());
				pc.currentCanvas = new EllipseItem(/*
													 * interieurChooser.
													 * getBackground()
													 */Color.BLUE,
				/* contourChooser.getBackground() */Color.BLACK,
						new Ellipse2D.Double(pc.getSelected_x(),
								pc.getSelected_y(), width, height));
				break;
			case Line:
				pc.setSelected_x(evt.getX());
				pc.setSelected_y(evt.getY());
				pc.currentCanvas = new LineItem(
				/* interieurChooser.getBackground() */Color.BLUE,
				/* contourChooser.getBackground() */Color.BLACK,
						new Line2D.Double(pc.getSelected_x(),
								pc.getSelected_y(), pc.getSelected_x(),
								pc.getSelected_y()));
				break;
			case Path:
				pc.setSelected_x(evt.getX());
				pc.setSelected_y(evt.getY());
				pc.currentCanvas = new PathItem(
				/* interieurChooser.getBackground() */Color.BLUE,
				/* contourChooser.getBackground() */Color.BLACK,
						new Path2D.Double());
				PathItem p = (PathItem) pc.currentCanvas;
				p.getPath().moveTo(pc.getSelected_x(), pc.getSelected_y());
				pc.currentCanvas = p;
				break;
			case Select:
				pc.setSelected_x(evt.getX());
				pc.setSelected_y(evt.getY());
				if (pc.listeCanvas.size() != 0) {
					for (int i = pc.listeCanvas.size() - 1; i >= 0; i--) {
						if (pc.listeCanvas.get(i).getClass() == RectangleItem.class) {
							RectangleItem r = (RectangleItem) pc.listeCanvas
									.get(i);
							if (evt.getX() > r.getRect().x
									&& evt.getX() < r.getRect().x
											+ r.getRect().width) {
								if (evt.getY() > r.getRect().y
										&& evt.getY() < r.getRect().y
												+ r.getRect().height) {

									pc.selectedCanvas = pc.listeCanvas.get(i);
									pc.indiceSelectedCanvas = i;
									pc.repaint();
									return;
								}
							}
						} else if (pc.listeCanvas.get(i).getClass() == EllipseItem.class) {
							EllipseItem e = (EllipseItem) pc.listeCanvas.get(i);
							if (e.getEllipse().contains(
									new Point2D.Float(evt.getX(), evt.getY()))) {
								pc.selectedCanvas = (EllipseItem) pc.listeCanvas
										.get(i);
								pc.indiceSelectedCanvas = i;
								pc.repaint();
								return;
							}
						}

					}
				}
				break;
			case Text:
				TextComponent txt = new TextComponent(/*
													 * interieurChooser.
													 * getBackground()
													 */Color.BLUE,
				/* contourChooser.getBackground() */Color.BLACK, evt.getX(),
						evt.getY());
			
				pc.add(txt);
				pc.currentCanvas = txt.getTi();
				pc.repaint();
				break;
			default:
				break;
			}
			pc.repaint();
		} else {

		}

	}

	public void mouseReleased(MouseEvent evt) {
		if (evt.getClickCount() == 1) {
			if (pc.isFlipped()) {
				if (pc.getDrawingMode() == DModeEnum.Select) {
					if (pc.listeCanvas.size() != 0) {
						pc.repaint();
					}
				} else {
					pc.listeCanvas.add(pc.currentCanvas);
					pc.currentCanvas = null;
					pc.repaint();
				}

			} else {

			}
		}

	}
}
