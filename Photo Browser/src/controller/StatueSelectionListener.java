package controller;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

import utility.FileSelector;
import model.MainModel;

public class StatueSelectionListener implements SelectionListener {
	
	private MainModel model;
	
	public StatueSelectionListener(MainModel model){
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newStatue= new String();
		if (e.getSource().getClass().equals(JRadioButton.class)){
			JRadioButton jr = (JRadioButton) e.getSource();
			newStatue = jr.getName().toString();	
		}
		if (e.getSource().getClass().equals(JMenuItem.class)){
			JMenuItem jr = (JMenuItem) e.getSource();
			newStatue = jr.getName().toString();	
			if (jr.getText().equals("Quit")){
				System.exit(0);
			}
			else if (jr.getText().equals("Import")){
				FileSelector fs = new FileSelector("File open...",model);
				
			}
		}
		model.setStatu(newStatue);
		
	}

	
}
