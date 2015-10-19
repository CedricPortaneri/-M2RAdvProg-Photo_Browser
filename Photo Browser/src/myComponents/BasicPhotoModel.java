package myComponents;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.DModeEnum;
import view.CanvasItem;
import view.EllipseItem;
import view.LineItem;
import view.PathItem;
import view.RectangleItem;
import view.TextItem;

/* Implementation of the specific model for the PhotoComponent */
public class BasicPhotoModel implements PhotoModel {

	private String imgRef;
	private BufferedImage img;
	private boolean isFlipped;
	private DModeEnum drawingMode;
	private ArrayList<CanvasItem> listeCanvas;
	private ArrayList<TextItem> listeTexte;
	private int indiceSelectedCanvas;
	private CanvasItem selectedCanvas;
	private CanvasItem currentCanvas;
	private Color interiorColor;
	private Color contourColor;
	private int selected_x;
	private int selected_y;

	private ArrayList<ChangeListener> listeners;

	public BasicPhotoModel(String imgRef) {
		
		this.listeners = new ArrayList<ChangeListener>();
		this.drawingMode = DModeEnum.Rectangle;

		this.imgRef = imgRef;

		this.listeCanvas = new ArrayList<CanvasItem>();
		this.listeTexte = new ArrayList<TextItem>();

		try {
			this.img = ImageIO.read(new File(this.imgRef));

		} catch (IOException e) {
			try {
				this.img = ImageIO.read(new File("cantfind.png"));
			} catch (IOException e1) {
				System.err.print("Missing 'cantfind.png' in root file... ");
			}
		}
		this.isFlipped = false;
		this.interiorColor = Color.LIGHT_GRAY;
		this.contourColor = Color.BLACK;
	}

	/* Listener manager */

	@Override
	public void registerListener(ChangeListener l) {
		listeners.add(l);
	}

	@Override
	public void unregisterListener(ChangeListener l) {
		listeners.remove(l);
	}

	@Override
	public void unregisterAllListener() {
		listeners.clear();
	}

	protected void fireStateChanged() {
		if (listeners != null && listeners.size() > 0) {
			ChangeEvent evt = new ChangeEvent(this);
			for (ChangeListener listener : listeners) {
				listener.stateChanged(evt);
			}
		}
	}

	/* Event Manager */
	public void addStroke(MouseEvent evt) {
		if (evt.getClickCount() == 2) {
			if (isFlipped()) {
				setFlipped(false);
			} else {
				setFlipped(true);
			}
			fireStateChanged();
		} else if (isFlipped()) {
			int width = 0, height = 0;

			switch (getDrawingMode()) {

			case Rectangle:

				setSelected_x(evt.getX());
				setSelected_y(evt.getY());
				currentCanvas = new RectangleItem(getInteriorColor(),
						getContourColor(), new Rectangle(getSelected_x(),
								getSelected_y(), width, height));
				break;
			case Ellipse:

				setSelected_x(evt.getX());
				setSelected_y(evt.getY());
				currentCanvas = new EllipseItem(getInteriorColor(),
						getContourColor(),
						new Ellipse2D.Double(getSelected_x(), getSelected_y(),
								width, height));
				break;
			case Line:
				setSelected_x(evt.getX());
				setSelected_y(evt.getY());
				currentCanvas = new LineItem(getInteriorColor(),
						getContourColor(), new Line2D.Double(getSelected_x(),
								getSelected_y(), getSelected_x(),
								getSelected_y()));
				break;
			case Path:
				setSelected_x(evt.getX());
				setSelected_y(evt.getY());
				currentCanvas = new PathItem(getInteriorColor(),
						getContourColor(), new Path2D.Double());
				PathItem p = (PathItem) currentCanvas;
				p.getPath().moveTo(getSelected_x(), getSelected_y());
				currentCanvas = p;
				break;
			case Select:
				setSelected_x(evt.getX());
				setSelected_y(evt.getY());
				if (listeCanvas.size() != 0) {
					for (int i = listeCanvas.size() - 1; i >= 0; i--) {
						if (listeCanvas.get(i).getClass() == RectangleItem.class) {
							RectangleItem r = (RectangleItem) listeCanvas
									.get(i);
							if (evt.getX() > r.getRect().x
									&& evt.getX() < r.getRect().x
											+ r.getRect().width) {
								if (evt.getY() > r.getRect().y
										&& evt.getY() < r.getRect().y
												+ r.getRect().height) {

									selectedCanvas = listeCanvas.get(i);
									indiceSelectedCanvas = i;
									fireStateChanged();
									return;
								}
							}
						} else if (listeCanvas.get(i).getClass() == EllipseItem.class) {
							EllipseItem e = (EllipseItem) listeCanvas.get(i);
							if (e.getEllipse().contains(
									new Point2D.Float(evt.getX(), evt.getY()))) {
								selectedCanvas = (EllipseItem) listeCanvas
										.get(i);
								indiceSelectedCanvas = i;
								fireStateChanged();
								return;
							}
						}

					}
				}
				break;
			case Text:
				TextItem txt = new TextItem(getInteriorColor(),
						getContourColor()," ", evt.getX(), evt.getY());
				getListeTexte().add(txt);
				currentCanvas = txt;
				fireStateChanged();
				break;
			default:
				break;
			}
			fireStateChanged();
		} else {

		}
	}

	@Override
	public void addRealeasedStroke(MouseEvent evt) {
		if (evt.getClickCount() == 1) {
			if (isFlipped()) {
				if (getDrawingMode() == DModeEnum.Select) {
					if (listeCanvas.size() != 0) {
						fireStateChanged();
					}
				} else {
					listeCanvas.add(currentCanvas);
					currentCanvas = null;
					fireStateChanged();
				}

			}
		}

	}

	@Override
	public void addMotion(MouseEvent evt) {
		if (isFlipped()) {
			switch (getDrawingMode()) {
			case Rectangle:

				if (currentCanvas == null)
					return;
				if (evt.getX() - getSelected_x() > 0) {

					if (evt.getY() - getSelected_y() > 0) {
						currentCanvas = new RectangleItem(getInteriorColor(),
								getContourColor(), new Rectangle(
										getSelected_x(), getSelected_y(),
										evt.getX() - getSelected_x(),
										evt.getY() - getSelected_y()));
					} else {
						currentCanvas = new RectangleItem(getInteriorColor(),
								getContourColor(), new Rectangle(
										getSelected_x(), evt.getY(), evt.getX()
												- getSelected_x(),
										getSelected_y() - evt.getY()));

					}
				}

				else {
					if (evt.getY() - getSelected_y() > 0) {

						currentCanvas = new RectangleItem(getInteriorColor(),
								getContourColor(), new Rectangle(evt.getX(),
										getSelected_y(), getSelected_x()
												- evt.getX(), evt.getY()
												- getSelected_y()));

					} else {

						currentCanvas = new RectangleItem(getInteriorColor(),
								getContourColor(), new Rectangle(evt.getX(),
										evt.getY(), getSelected_x()
												- evt.getX(), getSelected_y()
												- evt.getY()));

					}
				}
				fireStateChanged();
				break;
			case Ellipse:
				if (currentCanvas == null)
					return;
				if (evt.getX() - getSelected_x() > 0) {

					if (evt.getY() - getSelected_y() > 0) {
						currentCanvas = new EllipseItem(getInteriorColor(),
								getContourColor(), new Ellipse2D.Double(
										getSelected_x(), getSelected_y(),
										evt.getX() - getSelected_x(),
										evt.getY() - getSelected_y()));
					} else {
						currentCanvas = new EllipseItem(getInteriorColor(),
								getContourColor(), new Ellipse2D.Double(
										getSelected_x(), evt.getY(), evt.getX()
												- getSelected_x(),
										getSelected_y() - evt.getY()));

					}
				}

				else {
					if (evt.getY() - getSelected_y() > 0) {

						currentCanvas = new EllipseItem(getInteriorColor(),
								getContourColor(), new Ellipse2D.Double(
										evt.getX(), getSelected_y(),
										getSelected_x() - evt.getX(),
										evt.getY() - getSelected_y()));

					} else {

						currentCanvas = new EllipseItem(getInteriorColor(),
								getContourColor(), new Ellipse2D.Double(
										evt.getX(), evt.getY(), getSelected_x()
												- evt.getX(), getSelected_y()
												- evt.getY()));

					}
				}
				fireStateChanged();
				break;
			case Line:
				if (currentCanvas == null)
					return;

				currentCanvas = new LineItem(getInteriorColor(),
						getContourColor(), new Line2D.Double(getSelected_x(),
								getSelected_y(), evt.getX(), evt.getY()));

				fireStateChanged();
				break;
			case Path:
				if (currentCanvas == null)
					return;

				PathItem p = (PathItem) currentCanvas;
				p.getPath().lineTo(evt.getX(), evt.getY());
				currentCanvas = p;

				fireStateChanged();
				break;
			case Select:
				if (listeCanvas.size() != 0 && selectedCanvas != null) {
					if (selectedCanvas.getClass() == RectangleItem.class) {
						RectangleItem r = (RectangleItem) selectedCanvas;
						if (evt.getX() > r.getRect().x
								&& evt.getX() < r.getRect().x
										+ r.getRect().width) {
							if (evt.getY() > r.getRect().y
									&& evt.getY() < r.getRect().y
											+ r.getRect().height) {
								int translate_x = evt.getX() - getSelected_x();
								int translate_y = evt.getY() - getSelected_y();
								r.getRect().x = r.getRect().x + translate_x;
								r.getRect().y = r.getRect().y + translate_y;
								selectedCanvas = r;
								setSelected_x(evt.getX());
								setSelected_y(evt.getY());
								fireStateChanged();
							}
						}
					} else if (selectedCanvas.getClass() == EllipseItem.class) {
						EllipseItem e = (EllipseItem) selectedCanvas;
						if (e.getEllipse().contains(
								new Point2D.Float(evt.getX(), evt.getY()))) {
							int translate_x = evt.getX() - getSelected_x();
							int translate_y = evt.getY() - getSelected_y();
							Rectangle2D r = e.getEllipse().getFrame();
							r.setRect(r.getX() + translate_x, r.getY()
									+ translate_y, r.getWidth(), r.getHeight());
							e.getEllipse().setFrame(r);
							selectedCanvas = e;
							setSelected_x(evt.getX());
							setSelected_y(evt.getY());
							fireStateChanged();

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

	/* Getter and Setter */
	public boolean isFlipped() {
		return isFlipped;
	}

	public void setFlipped(boolean isFlipped) {
		this.isFlipped = isFlipped;
	}

	public DModeEnum getDrawingMode() {
		return drawingMode;
	}

	public void setDrawingMode(DModeEnum drawingMode) {
		this.drawingMode = drawingMode;
	}

	public ArrayList<CanvasItem> getListeCanvas() {
		return listeCanvas;
	}

	public int getSelected_x() {
		return selected_x;
	}

	public void setSelected_x(int selected_x) {
		this.selected_x = selected_x;
	}

	public int getSelected_y() {
		return selected_y;
	}

	public void setSelected_y(int selected_y) {
		this.selected_y = selected_y;
	}

	public ArrayList<TextItem> getListeTexte() {
		return listeTexte;
	}

	public Color getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(Color interiorColor) {
		this.interiorColor = interiorColor;
	}

	public Color getContourColor() {
		return contourColor;
	}

	public void setContourColor(Color contourColor) {
		this.contourColor = contourColor;
	}

	public String getImgRef() {
		return imgRef;
	}

	public void setImgRef(String imgRef) {
		this.imgRef = imgRef;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getIndiceSelectedCanvas() {
		return indiceSelectedCanvas;
	}

	public void setIndiceSelectedCanvas(int indiceSelectedCanvas) {
		this.indiceSelectedCanvas = indiceSelectedCanvas;
	}

	public CanvasItem getSelectedCanvas() {
		return selectedCanvas;
	}

	public void setSelectedCanvas(CanvasItem selectedCanvas) {
		this.selectedCanvas = selectedCanvas;
	}

	public CanvasItem getCurrentCanvas() {
		return currentCanvas;
	}

	public void setCurrentCanvas(CanvasItem currentCanvas) {
		this.currentCanvas = currentCanvas;
	}

	public ArrayList<ChangeListener> getListeners() {
		return listeners;
	}

	public void setListeners(ArrayList<ChangeListener> listeners) {
		this.listeners = listeners;
	}

	public void setListeCanvas(ArrayList<CanvasItem> listeCanvas) {
		this.listeCanvas = listeCanvas;
	}

	public void setListeTexte(ArrayList<TextItem> listeTexte) {
		this.listeTexte = listeTexte;
	}

}
