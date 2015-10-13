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
	public ArrayList<TextComponent> listeTexte;
	public int indiceSelectedCanvas;
	public CanvasItem selectedCanvas;
	public CanvasItem currentCanvas;
	private int selected_x;
	private int selected_y;

	public PhotoComponent(String imgRef) {

		this.drawingMode = DModeEnum.Rectangle;
		if (imgRef == null) {
			this.setVisible(false);
		} else {
			this.imgRef = imgRef;

			this.listeCanvas = new ArrayList<CanvasItem>();
			this.listeTexte = new ArrayList<TextComponent>();

			try {
				this.img = ImageIO.read(new File(this.imgRef));
				this.setPreferredSize(new Dimension(this.img.getWidth(),
						this.img.getHeight()));
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
			DrawingMouseAdapterListener lst = new DrawingMouseAdapterListener(
					this);
			DrawingMouseMotionListener lst2 = new DrawingMouseMotionListener(
					this);
			this.addMouseListener(lst);
			this.addMouseMotionListener(lst2);
			this.setVisible(true);
		}
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

	public ArrayList<TextComponent> getListeTexte() {
		return listeTexte;
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

				}
				for (int j = 0; j < listeTexte.size(); j++) {
					if (this.listeTexte.get(j).getClass() == TextComponent.class) {

						TextItem r = (TextItem) this.listeTexte.get(j).getTi();
						g.setColor(this.listeTexte.get(j).getTi()
								.getInterieurColor());

						int lineSep = 0;
						String texte = r.getTxt();
						ArrayList<String> listStr = new ArrayList<String>();
						int width = g.getFontMetrics().stringWidth(
								this.listeTexte.get(j).getTxt());

						/* If the photo bound is reached the first time */
						if (width + r.getX() + 2 > this.img.getWidth()) {
							int fLSize = this.img.getWidth() - r.getX() - 2;
							int fLNbrChar = 0;
							int tmpWidth = 0;
							String tmp = "";
							int l = 1;
							int p = 2;
							/* Find the number of char before the first vspace */
							while (tmpWidth < fLSize - 2) {
								tmp = tmp.concat(texte.substring(l, p));
								tmpWidth = g.getFontMetrics().stringWidth(tmp);
								fLNbrChar++;
								l++;
								p++;
							}

							listStr.add(texte.substring(0, fLNbrChar));
							texte = texte.substring(fLNbrChar);
							width = g.getFontMetrics().stringWidth(texte);

							/* If the photo bound is reached for the other line */

							while (width >= this.img.getWidth()) {
								int LNbrChar = 0;
								tmpWidth = 0;
								tmp = "";
								l = 1;
								p = 2;
								while (tmpWidth < this.img.getWidth() - 8) {
									tmp = tmp.concat(texte.substring(l, p));
									tmpWidth = g.getFontMetrics().stringWidth(
											tmp);
									LNbrChar++;
									l++;
									p++;
								}

								listStr.add(texte.substring(0, LNbrChar));
								texte = texte.substring(LNbrChar);
								width = g.getFontMetrics().stringWidth(texte);
							}
							if (width < this.img.getWidth()) {
								listStr.add(texte);
							}
						} else {
							listStr.add(texte);
						}

						/* Drawing part */
						for (int m = 0; m < listStr.size(); m++) {
							if (m == 0) {
								g.drawString(listStr.get(m), r.getX(), r.getY());
							} else {
								g.drawString(listStr.get(m), 0, r.getY()
										+ lineSep);
							}
							lineSep += 15;
						}

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
