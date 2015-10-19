package model;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.UIManager;

import myComponents.PhotoUI;
import view.MainView;

/* Main Class which contain the top properties */ 
public class MainModel extends Observable {
	
	private MainView vue;
	
	private ArrayList<String> gallery;
	private String statu;

	public MainModel(ArrayList<String> gallery) {
		this.gallery = gallery;
		this.statu = "Nothing";
		
		this.vue = new MainView(this);

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


	public ArrayList<String> getGallery() {
		return gallery;
	}

	public void setGallery(ArrayList<String> gallery) {
		this.gallery = gallery;
	}

	public static void main (String[] args){
		
		ArrayList<String> defaultPhotos = new ArrayList<String>();
		defaultPhotos.add("isaac.png");
		
		UIManager.put(PhotoUI.UI_CLASS_ID, "BasicPhotoUI"); 
		MainModel photoBrowser = new MainModel(defaultPhotos);
	}
	
	
}
