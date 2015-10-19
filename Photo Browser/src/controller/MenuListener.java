package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.MainModel;
import myComponents.PhotoComponent;

/* Listener for the Top Menu */
public class MenuListener implements ActionListener {

	private MainModel model;

	public MenuListener(MainModel model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().getClass().equals(JMenuItem.class)) {
			JMenuItem jr = (JMenuItem) e.getSource();
			if (jr.getText().equals("Quit")) {
				System.exit(0);
			} else if (jr.getText().equals("Import")) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int retour = jfc.showOpenDialog(model.getVue());
				if (retour == JFileChooser.APPROVE_OPTION) {
					
					PhotoComponent newPc = new PhotoComponent(jfc
							.getSelectedFile().getName());
					model.getVue().getPhotoViewer().getScroll()
							.setCurrentPhoto(newPc);
					model.getVue().getPhotoViewer().getScroll()
							.setVisible(true);
					model.getVue().getToolBar().getContourChooser().setBackground(Color.black);
					model.getVue().getToolBar().getInterieurChooser().setBackground(Color.LIGHT_GRAY);
					
				}

			}
			 else if (jr.getText().equals("Delete")) {
				 model.getVue().getPhotoViewer().getScroll().setVisible(false);
			 }
		}
		
	}

}
