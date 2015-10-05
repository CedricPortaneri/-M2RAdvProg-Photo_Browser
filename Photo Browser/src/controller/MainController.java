package controller;

import model.MainModel;


public class MainController {
	
	private MainModel model;
	private SelectRecognizer sr;
	// Observer/Observable pattern implemented with listener
	public MainController(MainModel model){
		
		this.model = model;
		
		SelectRecognizer sr = new SelectRecognizer(this.model.getVue().getMenu().getListMenuItem(),this.model.getVue().getToolBar().getListButton());
		this.sr = sr;
		StatueSelectionListener menuListener = new StatueSelectionListener(model);
		sr.registerListener(menuListener);
		
	}
	public SelectRecognizer getSr() {
		return sr;
	}

	/*GestureRecognizer reco = new GestureRecognizer();
	//something interactive that is generated some input event
	
	reco.registerListener(new GestureListener() {

		@Override
		public void gestureRecognized(SelectEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	});
	// --> dirty way listenre are the controller, create az class will implement one or several listener interface One unique class for 
	reco.handleStroke(null);*/
	// null = liste of positions

}
