package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import model.MainModel;
import controller.ModeSelectionListener;
import controller.StatueSelectionListener;

public class ToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4268951943328073987L;
	private ArrayList<AbstractButton> listButton;
	
	public ToolBar(MainModel model){
		super();
		this.listButton = new ArrayList<AbstractButton>();
		ArrayList<AbstractButton> drawingListButton = new ArrayList<AbstractButton>();
		this.setFloatable(false);
		this.setPreferredSize(new Dimension(100, 400));
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		Label category = new Label("Category");
		category.setMaximumSize(new Dimension(60,20));
		
		ButtonGroup radioList = new ButtonGroup();
		JRadioButton familyButton = new  JRadioButton("Family");
		JRadioButton vacationButton = new  JRadioButton("Vacation");
		JRadioButton schoolButton = new  JRadioButton("School");
		familyButton.setName("Family photos");
		vacationButton.setName("Vacation photos");
		schoolButton.setName("School photos");
		
		radioList.add(schoolButton);
		radioList.add(vacationButton);
		radioList.add(familyButton);
		
		listButton.add(schoolButton);
		listButton.add(vacationButton);
		listButton.add(familyButton);
		
		Label drawing = new Label("Drawing");
		drawing.setMaximumSize(new Dimension(60,20));
		
		ButtonGroup radioList2 = new ButtonGroup();
		JRadioButton selectedButton = new  JRadioButton("Select");
		JRadioButton rectangleButton = new  JRadioButton("Rectangle");
		JRadioButton ellipseButton = new  JRadioButton("Ellipse");
		JRadioButton lineButton = new  JRadioButton("Line");
		JRadioButton pathButton = new  JRadioButton("Path");
		JRadioButton textButton = new  JRadioButton("Text");
		
		selectedButton.setName("You can select and drag and drop your drawings");
		rectangleButton.setName("Try to do a rectangle!");
		ellipseButton.setName("Try to do an ellipse!");
		lineButton.setName("Draw a line");
		pathButton.setName("Free drawing!");
		textButton.setName("Write something");
		
		radioList2.add(selectedButton);
		radioList2.add(lineButton);
		radioList2.add(rectangleButton);
		radioList2.add(ellipseButton);
		radioList2.add(pathButton);
		radioList2.add(textButton);
		
		listButton.add(selectedButton);
		listButton.add(lineButton);
		listButton.add(rectangleButton);
		listButton.add(ellipseButton);
		listButton.add(pathButton);
		listButton.add(textButton);
		
		drawingListButton.add(selectedButton);
		drawingListButton.add(lineButton);
		drawingListButton.add(rectangleButton);
		drawingListButton.add(ellipseButton);
		drawingListButton.add(pathButton);
		drawingListButton.add(textButton);

		
		this.add(category);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		familyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(familyButton);
		vacationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(vacationButton);
		schoolButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(schoolButton);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(drawing);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		selectedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(selectedButton);
		rectangleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(rectangleButton);
		ellipseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(ellipseButton);
		lineButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(lineButton);
		rectangleButton.setSelected(true);
		pathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(pathButton);
		textButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(textButton);
		
		JButton deleteButton = new JButton("Delete");
		JButton cloneButton = new JButton("Clone");
		deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(deleteButton);
		cloneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(cloneButton);
		
		StatueSelectionListener selectListener = new StatueSelectionListener(model);
		for (AbstractButton l :listButton){
			l.addActionListener(selectListener);
		}
		ModeSelectionListener modeListener = new ModeSelectionListener(model);
		for (AbstractButton l :drawingListButton){
			l.addActionListener(modeListener);
		}
		deleteButton.addActionListener(modeListener);
		cloneButton.addActionListener(modeListener);
	}

	public ArrayList<AbstractButton> getListButton() {
		return listButton;
	}
}
