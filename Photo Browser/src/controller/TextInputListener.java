package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import view.TextItem;
import model.MainModel;

/* Key Listener for the Text Mode */
public class TextInputListener extends KeyAdapter {
	
	private MainModel m;
	
	public TextInputListener(MainModel m){
		this.m = m;
	}
	public void keyPressed(KeyEvent k) {
		char character = k.getKeyChar();
		if (m.getVue().getPhotoViewer().getScroll().getCurrentPhoto() != null){
			ArrayList<TextItem> liste = m.getVue().getPhotoViewer().getScroll().getCurrentPhoto().getModel().getListeTexte();
			if (!liste.isEmpty()){
				liste.get(liste.size()-1).setTxt(liste.get(liste.size()-1).getTxt()+Character.toString(character));
				m.getVue().getPhotoViewer().getScroll().getCurrentPhoto().repaint();
			}
		}
			
	}

}
