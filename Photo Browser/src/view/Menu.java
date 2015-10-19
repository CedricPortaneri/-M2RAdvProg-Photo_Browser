package view;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.MainModel;
import controller.MenuListener;
import controller.StatueListener;

/* Top Menu */
public class Menu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2401343171737574518L;

	private ArrayList<JMenuItem> listMenuItem;

	public Menu(MainModel model){
		listMenuItem = new ArrayList<JMenuItem>();

		JMenu fileMenu = new JMenu("File");

		JMenuItem importItem = new JMenuItem("Import");
		importItem.setName("Import ...");
		JMenuItem deleteItem = new JMenuItem("Delete");
		deleteItem.setName("Delete ...");
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.setName("Quit");

		fileMenu.add(importItem);
		fileMenu.add(deleteItem);
		fileMenu.add(quitItem);

		listMenuItem.add(importItem);
		listMenuItem.add(deleteItem);
		listMenuItem.add(quitItem);

		JMenu viewMenu = new JMenu("View");

		JMenuItem photoViewerItem = new JMenuItem("Photo Viewer");
		photoViewerItem.setName("Photo Viewer Mode");
		JMenuItem browserItem = new JMenuItem("Browser");
		browserItem.setName("Browser Mode");
		JMenuItem spitItem = new JMenuItem("Split Mode");
		spitItem.setName("Split Mode");

		viewMenu.add(photoViewerItem);
		viewMenu.add(browserItem);
		viewMenu.add(spitItem);

		listMenuItem.add(photoViewerItem);
		listMenuItem.add(browserItem);
		listMenuItem.add(spitItem);

		this.add(fileMenu);
		this.add(viewMenu);
		
		StatueListener selectListener = new StatueListener(model);
		MenuListener menuListener = new MenuListener(model);
		
		for (JMenuItem l :listMenuItem){
			l.addActionListener(selectListener);
			l.addActionListener(menuListener);
		}
		

	}
	

	public ArrayList<JMenuItem> getListMenuItem() {
		return this.listMenuItem;
	}
}
