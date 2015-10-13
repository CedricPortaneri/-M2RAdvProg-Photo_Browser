package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import view.TextComponent;
import model.MainModel;

public class TextInputListener extends KeyAdapter {
	
	private MainModel m;
	
	public TextInputListener(MainModel m){
		this.m = m;
	}
	public void keyPressed(KeyEvent k) {
		char character = k.getKeyChar();
		if (m.getVue().getPhotoViewer().getScroll().getCurrentPhoto() != null){
			ArrayList<TextComponent> liste = m.getVue().getPhotoViewer().getScroll().getCurrentPhoto().getListeTexte();
			if (!liste.isEmpty()){
				liste.get(liste.size()-1).setTxt(liste.get(liste.size()-1).getTxt()+Character.toString(character));
				m.getVue().getPhotoViewer().getScroll().getCurrentPhoto().repaint();
			}
		}
			
	}

}
