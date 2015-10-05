package model;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;

import view.MainView;
import controller.MainController;


public class MainModel extends Observable {
	
	private MainView vue;
	private MainController controller;
	
	private ArrayList<BufferedImage> gallery;
	private String statu;

	public MainModel(ArrayList<BufferedImage> gallery) {
		this.gallery = gallery;
		this.statu = "Nothing";
		
		this.vue = new MainView(this);
		this.controller = new MainController(this);

		this.addObserver(this.vue.getStatueBar());
	}
	

	public String getStatu(){
		return this.statu;
	}
	
	public void setStatu(String newStatu){
		this.statu = newStatu;
		setChanged();
		notifyObservers(this.statu);
	}
	
	public MainView getVue() {
		return vue;
	}

	public void setVue(MainView vue) {
		this.vue = vue;
	}

	public MainController getController() {
		return controller;
	}

	public void setController(MainController controller) {
		this.controller = controller;
	}

	public ArrayList<BufferedImage> getGallery() {
		return gallery;
	}

	public void setGallery(ArrayList<BufferedImage> gallery) {
		this.gallery = gallery;
	}

	public static void main (String[] args){
		MainModel photoBrowser = new MainModel(null);
	}
	
	
}
