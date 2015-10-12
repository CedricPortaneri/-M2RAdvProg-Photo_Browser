package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import model.DModeEnum;
import controller.DrawingMouseAdapterListener;
import controller.DrawingMouseMotionListener;
import controller.TextInputListener;

public class PhotoComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imgRef;
	private BufferedImage img;
	private boolean isFlipped;
	private DModeEnum drawingMode;
	public ArrayList<CanvasItem> listeCanvas;
	public int indiceSelectedCanvas;
	public CanvasItem selectedCanvas;
	public CanvasItem currentCanvas;
    private int selected_x;
    private int selected_y;

	public PhotoComponent(String imgRef) {

		this.drawingMode = DModeEnum.Rectangle;
		this.imgRef = imgRef;
		this.listeCanvas = new ArrayList<CanvasItem>();

		try {
			this.img = ImageIO.read(new File(this.imgRef));
			this.setPreferredSize(new Dimension(this.img.getWidth(), this.img
					.getHeight()));
			this.setSize(new Dimension(this.img.getWidth(), this.img
					.getHeight()));
		} catch (IOException e) {
			try {
				this.img = ImageIO.read(new File("cantfind.png"));
				this.setPreferredSize(new Dimension(this.img.getWidth(),
						this.img.getHeight()));
				this.setSize(new Dimension(this.img.getWidth(), this.img
						.getHeight()));
			} catch (IOException e1) {
				System.err.print("Missing 'cantfind.png' in root file... ");
			}
		}
		this.isFlipped = false;

		DrawingMouseAdapterListener lst = new DrawingMouseAdapterListener(this);
		DrawingMouseMotionListener lst2 = new DrawingMouseMotionListener(
				this);
		this.addMouseListener(lst);
		this.addMouseMotionListener(lst2);

	}

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

	public void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);
		if (!isFlipped) {
			graphics.drawImage(img, 0, 0, null);
		} else {

			Graphics2D g = (Graphics2D) graphics;
			RenderingHints rh = new RenderingHints(
		             RenderingHints.KEY_ANTIALIASING,
		             RenderingHints.VALUE_ANTIALIAS_ON);
		    g.setRenderingHints(rh);
		    
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			if (listeCanvas != null) {
				for (int i = 0; i < listeCanvas.size(); i++) {
					if (this.listeCanvas.get(i) == null)
						return;
					else if (this.listeCanvas.get(i).getClass() == RectangleItem.class) {
						RectangleItem r = (RectangleItem) this.listeCanvas
								.get(i);
						g.setColor(listeCanvas.get(i).getInterieurColor());
						g.fill(r.getRect());
						g.setColor(listeCanvas.get(i).getContourColor());
						g.draw(r.getRect());
					} else if (this.listeCanvas.get(i).getClass() == EllipseItem.class) {
						EllipseItem e = (EllipseItem) this.listeCanvas.get(i);
						g.setColor(listeCanvas.get(i).getInterieurColor());
						g.fill(e.getEllipse());
						g.setColor(listeCanvas.get(i).getContourColor());
						g.draw(e.getEllipse());
					} else if (this.listeCanvas.get(i).getClass() == LineItem.class) {
						LineItem r = (LineItem) this.listeCanvas.get(i);
						g.setColor(this.listeCanvas.get(i).getInterieurColor());
						g.fill(r.getLine());
						g.setColor(this.listeCanvas.get(i).getContourColor());

						g.draw(r.getLine());
					} else if (this.listeCanvas.get(i).getClass() == PathItem.class) {
						PathItem r = (PathItem) this.listeCanvas.get(i);
						g.setColor(this.listeCanvas.get(i).getInterieurColor());
						g.draw(r.getPath());

					}
					else if (this.listeCanvas.get(i).getClass() == TextItem.class) {
						TextItem r = (TextItem) this.listeCanvas.get(i);
						
						g.setColor(this.listeCanvas.get(i).getInterieurColor());
						g.drawString(r.getTxt(),r.getX()+2 , r.getY()+5);
					}
				}
			}
			if (selectedCanvas != null) {
				if (selectedCanvas.getClass() == RectangleItem.class) {
					RectangleItem r = (RectangleItem) selectedCanvas;
					g.setColor(selectedCanvas.getInterieurColor());
					g.fill(r.getRect());
					g.setColor(selectedCanvas.getContourColor());
					g.setStroke(new BasicStroke(2f));
					g.draw(r.getRect());
				} else if (selectedCanvas.getClass() == EllipseItem.class) {
					EllipseItem r = (EllipseItem) selectedCanvas;
					g.setColor(selectedCanvas.getInterieurColor());
					g.fill(r.getEllipse());
					g.setColor(selectedCanvas.getContourColor());
					g.setStroke(new BasicStroke(2f));
					g.draw(r.getEllipse());
				} else if (selectedCanvas.getClass() == LineItem.class) {
					LineItem r = (LineItem) selectedCanvas;
					g.setColor(selectedCanvas.getInterieurColor());
					g.setStroke(new BasicStroke(2f));
					g.fill(r.getLine());
					g.setColor(selectedCanvas.getContourColor());

					g.draw(r.getLine());
				} else if (selectedCanvas.getClass() == PathItem.class) {
					PathItem r = (PathItem) selectedCanvas;
					g.setColor(selectedCanvas.getInterieurColor());
					g.setStroke(new BasicStroke(2f));
					g.draw(r.getPath());

				}
			}

			if (currentCanvas != null) {

				if (currentCanvas.getClass() == RectangleItem.class) {
					g.setColor(currentCanvas.getInterieurColor());
					RectangleItem r = (RectangleItem) currentCanvas;
					g.fill(r.getRect());
					g.setStroke(new BasicStroke(1f));
					g.setColor(currentCanvas.getContourColor());
					g.draw(r.getRect());
				} else if (currentCanvas.getClass() == EllipseItem.class) {
					g.setColor(currentCanvas.getInterieurColor());
					EllipseItem r = (EllipseItem) currentCanvas;
					g.fill(r.getEllipse());
					g.setStroke(new BasicStroke(1f));
					g.setColor(currentCanvas.getContourColor());
					g.draw(r.getEllipse());
				} else if (currentCanvas.getClass() == LineItem.class) {
					LineItem r = (LineItem) currentCanvas;
					g.setColor(currentCanvas.getInterieurColor());
					g.fill(r.getLine());
					g.setColor(currentCanvas.getContourColor());

					g.draw(r.getLine());
				} else if (currentCanvas.getClass() == PathItem.class) {
					PathItem r = (PathItem) currentCanvas;
					g.setColor(currentCanvas.getInterieurColor());
					g.draw(r.getPath());

				}
			}

		}

	}

	public CanvasItem getSelectedCanvas() {
		return selectedCanvas;
	}

	public CanvasItem getCurrentCanvas() {
		return currentCanvas;
	}
}
