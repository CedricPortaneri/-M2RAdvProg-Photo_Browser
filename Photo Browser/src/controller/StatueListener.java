package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

import model.MainModel;

/* Listener for the Bottom Statue Bar */
public class StatueListener implements ActionListener {

	private MainModel model;

	public StatueListener(MainModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newStatue = new String();
		if (e.getSource().getClass().equals(JRadioButton.class)) {
			JRadioButton jr = (JRadioButton) e.getSource();
			newStatue = jr.getName().toString();
		} else if (e.getSource().getClass().equals(JMenuItem.class)) {
			JMenuItem mi = (JMenuItem) e.getSource();
			newStatue = mi.getName().toString();
		}
		model.setStatu(newStatue);

	}
}
