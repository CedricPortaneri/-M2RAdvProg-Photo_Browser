package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;

import view.PhotoComponent;
import model.MainModel;

public class StatueSelectionListener implements SelectionListener {

	private MainModel model;

	public StatueSelectionListener(MainModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newStatue = new String();
		if (e.getSource().getClass().equals(JRadioButton.class)) {
			JRadioButton jr = (JRadioButton) e.getSource();
			newStatue = jr.getName().toString();
		}
		if (e.getSource().getClass().equals(JMenuItem.class)) {
			JMenuItem jr = (JMenuItem) e.getSource();
			newStatue = jr.getName().toString();
			if (jr.getText().equals("Quit")) {
				System.exit(0);
			} else if (jr.getText().equals("Import")) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int retour = jfc.showOpenDialog(model.getVue());
				if (retour == JFileChooser.APPROVE_OPTION) {
					// un fichier a été choisi (sortie par OK)
					// nom du fichier choisi
					System.out.println(jfc.getSelectedFile().getName());
					PhotoComponent newPc = new PhotoComponent(jfc
							.getSelectedFile().getName());
					model.getVue().getPhotoViewer().getScroll()
							.setCurrentPhoto(newPc);

					model.getVue().getPhotoViewer().getScroll()
							.setViewportView(newPc);

					model.getVue().getPhotoViewer().getScroll()
							.setPreferredSize(newPc.getPreferredSize());
					model.getVue().getPhotoViewer().getScroll()
							.setSize(newPc.getSize());
					model.getVue()
							.getPhotoViewer()
							.getScroll()
							.setMaximumSize(
									new Dimension((int) newPc.getSize()
											.getWidth() + 15, (int) newPc
											.getSize().getHeight() + 15));

					model.getVue()
							.getPhotoViewer()
							.getScroll()
							.setVerticalScrollBarPolicy(
									ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					model.getVue()
							.getPhotoViewer()
							.getScroll()
							.setHorizontalScrollBarPolicy(
									ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					model.getVue().getPhotoViewer().getScroll().setBorder(null);
	

					model.getVue().getPhotoViewer().getScroll()
							.setVisible(true);
					model.getVue().getPhotoViewer().getScroll()
					.getCurrentPhoto().repaint();
					model.getVue().getPhotoViewer().getScroll().repaint();
					model.getVue().getPhotoViewer().repaint();
					model.getVue().repaint();
				}

			}
			 else if (jr.getText().equals("Delete")) {
//				 model.getVue().getPhotoViewer().getScroll()
//					.setCurrentPhoto(new PhotoComponent(null));
				 model.getVue().getPhotoViewer().getScroll().setVisible(false);
			 }
		}
		model.setStatu(newStatue);

	}
}
