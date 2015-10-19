package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

import model.MainModel;

/* Listener for both Interior and Outline Color Chooser Panel in the Tool Bar */
public class ColorChooserListener extends MouseAdapter {
	private JPanel type;
	private MainModel pc;

	public ColorChooserListener(JPanel type, MainModel pc) {
		this.type = type;
		this.pc = pc;
	}

	public void mousePressed(MouseEvent evt) {
		JColorChooser jcc = new JColorChooser();
		Color new_color = JColorChooser.showDialog(jcc, type.getName(),
				type.getBackground());
		if (new_color == null) {
			new_color = Color.white;
		}
		type.setBackground(new_color);
		if (type.getName().equals("Inside Color")) {

			pc.getVue().getPhotoViewer().getScroll().getCurrentPhoto().getModel()
					.setInteriorColor(new_color);
		} else if (type.getName().equals("Outline Color")) {
			pc.getVue().getPhotoViewer().getScroll().getCurrentPhoto().getModel()
					.setContourColor(new_color);
		}
	}
}
