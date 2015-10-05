package controller;

import model.MainModel;

public class StatueSelectionListener implements SelectionListener {
	
	private MainModel model;
	
	public StatueSelectionListener(MainModel model){
		this.model = model;
	}
	
	@Override
	public void selectionRecognized(SelectEvent e) {
		
		String newStatue = e.getSource().toString();		
		model.setStatu(newStatue);
			
	}

	
}
