package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextInputListener extends KeyAdapter {
	public void keyPressed(KeyEvent k) {
		char character = k.getKeyChar();
		System.out.println(character);
	}

}
