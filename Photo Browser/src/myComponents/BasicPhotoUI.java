package myComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import view.EllipseItem;
import view.LineItem;
import view.PathItem;
import view.RectangleItem;
import view.TextItem;

/* Implementation of the specific look and feel UI for the PhotoComponent */
public class BasicPhotoUI extends PhotoUI implements MouseListener,
		MouseMotionListener {

	public PhotoComponent component;
	
	public BasicPhotoUI(JComponent c){
		this.component = (PhotoComponent) c;
	}
	
	/* UI Managment */
	public static ComponentUI createUI(JComponent c) {
		return new BasicPhotoUI(c);

	}

	public void installUI(JComponent c) {
		((PhotoComponent) c).addMouseListener(this);
		((PhotoComponent) c).addMouseMotionListener(this);
	}

	public void uninstallUI(JComponent c) {
		((PhotoComponent) c).removeMouseListener(this);
		((PhotoComponent) c).removeMouseMotionListener(this);
	}

	/* Drawing */
	public void paint(Graphics graphics, JComponent c) {

		PhotoComponent p = (PhotoComponent) c;
		BasicPhotoModel model = (BasicPhotoModel) p.getModel();
		// super.paintComponent(graphics);

		if (!model.isFlipped()) {
			graphics.drawImage(model.getImg(), 0, 0, null);
		} else {

			Graphics2D g = (Graphics2D) graphics;
			RenderingHints rh = new RenderingHints(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHints(rh);

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, p.getWidth(), p.getHeight());
			if (model.getListeCanvas() != null) {
				for (int i = 0; i < model.getListeCanvas().size(); i++) {
					if (model.getListeCanvas().get(i) == null)
						return;
					else if (model.getListeCanvas().get(i).getClass() == RectangleItem.class) {
						RectangleItem r = (RectangleItem) model
								.getListeCanvas().get(i);
						g.setColor(model.getListeCanvas().get(i)
								.getInterieurColor());
						g.fill(r.getRect());
						g.setColor(model.getListeCanvas().get(i)
								.getContourColor());
						g.draw(r.getRect());
					} else if (model.getListeCanvas().get(i).getClass() == EllipseItem.class) {
						EllipseItem e = (EllipseItem) model.getListeCanvas()
								.get(i);
						g.setColor(model.getListeCanvas().get(i)
								.getInterieurColor());
						g.fill(e.getEllipse());
						g.setColor(model.getListeCanvas().get(i)
								.getContourColor());
						g.draw(e.getEllipse());
					} else if (model.getListeCanvas().get(i).getClass() == LineItem.class) {
						LineItem r = (LineItem) model.getListeCanvas().get(i);
						g.setColor(model.getListeCanvas().get(i)
								.getInterieurColor());
						g.fill(r.getLine());
						g.setColor(model.getListeCanvas().get(i)
								.getContourColor());

						g.draw(r.getLine());
					} else if (model.getListeCanvas().get(i).getClass() == PathItem.class) {
						PathItem r = (PathItem) model.getListeCanvas().get(i);
						g.setColor(model.getListeCanvas().get(i)
								.getInterieurColor());
						g.draw(r.getPath());

					}

				}
				for (int j = 0; j < model.getListeTexte().size(); j++) {
					if (model.getListeTexte().get(j).getClass() == TextItem.class) {

						TextItem r = (TextItem) model.getListeTexte().get(j);
						g.setColor(model.getListeTexte().get(j).getInterieurColor());

						int lineSep = 0;
						String texte = r.getTxt();
						ArrayList<String> listStr = new ArrayList<String>();
						int width = g.getFontMetrics().stringWidth(
								model.getListeTexte().get(j).getTxt());

						/* If the photo bound is reached the first time */
						if (width + r.getX() + 2 > model.getImg().getWidth()) {
							int fLSize = model.getImg().getWidth() - r.getX()
									- 2;
							int fLNbrChar = 0;
							int tmpWidth = 0;
							String tmp = "";
							int l = 1;
							int o = 2;
							/* Find the number of char before the first vspace */
							while (tmpWidth < fLSize - 2) {
								tmp = tmp.concat(texte.substring(l, o));
								tmpWidth = g.getFontMetrics().stringWidth(tmp);
								fLNbrChar++;
								l++;
								o++;
							}

							listStr.add(texte.substring(0, fLNbrChar));
							texte = texte.substring(fLNbrChar);
							width = g.getFontMetrics().stringWidth(texte);

							/* If the photo bound is reached for the other line */

							while (width >= model.getImg().getWidth()) {
								int LNbrChar = 0;
								tmpWidth = 0;
								tmp = "";
								l = 1;
								o = 2;
								while (tmpWidth < model.getImg().getWidth() - 8) {
									tmp = tmp.concat(texte.substring(l, o));
									tmpWidth = g.getFontMetrics().stringWidth(
											tmp);
									LNbrChar++;
									l++;
									o++;
								}

								listStr.add(texte.substring(0, LNbrChar));
								texte = texte.substring(LNbrChar);
								width = g.getFontMetrics().stringWidth(texte);
							}
							if (width < model.getImg().getWidth()) {
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
			
			if (model.getSelectedCanvas() != null) {
				if (model.getSelectedCanvas().getClass() == RectangleItem.class) {
					RectangleItem r = (RectangleItem) model.getSelectedCanvas();
					g.setColor(model.getSelectedCanvas().getInterieurColor());
					g.fill(r.getRect());
					g.setColor(model.getSelectedCanvas().getContourColor());
					g.setStroke(new BasicStroke(2f));
					g.draw(r.getRect());
				} else if (model.getSelectedCanvas().getClass() == EllipseItem.class) {
					EllipseItem r = (EllipseItem) model.getSelectedCanvas();
					g.setColor(model.getSelectedCanvas().getInterieurColor());
					g.fill(r.getEllipse());
					g.setColor(model.getSelectedCanvas().getContourColor());
					g.setStroke(new BasicStroke(2f));
					g.draw(r.getEllipse());
				} else if (model.getSelectedCanvas().getClass() == LineItem.class) {
					LineItem r = (LineItem) model.getSelectedCanvas();
					g.setColor(model.getSelectedCanvas().getInterieurColor());
					g.setStroke(new BasicStroke(2f));
					g.fill(r.getLine());
					g.setColor(model.getSelectedCanvas().getContourColor());

					g.draw(r.getLine());
				} else if (model.getSelectedCanvas().getClass() == PathItem.class) {
					PathItem r = (PathItem) model.getSelectedCanvas();
					g.setColor(model.getSelectedCanvas().getInterieurColor());
					g.setStroke(new BasicStroke(2f));
					g.draw(r.getPath());

				}
			}

			if (model.getCurrentCanvas() != null) {

				if (model.getCurrentCanvas().getClass() == RectangleItem.class) {
					g.setColor(model.getCurrentCanvas().getInterieurColor());
					RectangleItem r = (RectangleItem) model.getCurrentCanvas();
					g.fill(r.getRect());
					g.setStroke(new BasicStroke(1f));
					g.setColor(model.getCurrentCanvas().getContourColor());
					g.draw(r.getRect());
				} else if (model.getCurrentCanvas().getClass() == EllipseItem.class) {
					g.setColor(model.getCurrentCanvas().getInterieurColor());
					EllipseItem r = (EllipseItem) model.getCurrentCanvas();
					g.fill(r.getEllipse());
					g.setStroke(new BasicStroke(1f));
					g.setColor(model.getCurrentCanvas().getContourColor());
					g.draw(r.getEllipse());
				} else if (model.getCurrentCanvas().getClass() == LineItem.class) {
					LineItem r = (LineItem) model.getCurrentCanvas();
					g.setColor(model.getCurrentCanvas().getInterieurColor());
					g.fill(r.getLine());
					g.setColor(model.getCurrentCanvas().getContourColor());

					g.draw(r.getLine());
				} else if (model.getCurrentCanvas().getClass() == PathItem.class) {
					PathItem r = (PathItem) model.getCurrentCanvas();
					g.setColor(model.getCurrentCanvas().getInterieurColor());
					g.draw(r.getPath());

				}
			}

		}

	}

	/* Listeners actions */

	@Override
	public void mousePressed(MouseEvent evt) {
		this.component.addStroke(evt);

	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		this.component.addReleasedStroke(evt);

	}
	
	@Override
	public void mouseDragged(MouseEvent evt) {
		this.component.addMotion(evt);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
