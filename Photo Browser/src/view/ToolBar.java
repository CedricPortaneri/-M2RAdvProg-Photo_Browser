package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import model.MainModel;
import controller.ColorChooserListener;
import controller.ModeListener;
import controller.StatueListener;

/* Left Tool Bar which contain different Drawing and View Mode */
public class ToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4268951943328073987L;
	private ArrayList<AbstractButton> listButton;
	private JPanel contourChooser;
	private JPanel interieurChooser;
	
	
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
		JRadioButton selectedButton = new  JRadioButton("");
		JRadioButton rectangleButton = new  JRadioButton("");
		JRadioButton ellipseButton = new  JRadioButton("");
		JRadioButton lineButton = new  JRadioButton("");
		JRadioButton pathButton = new  JRadioButton("");
		JRadioButton textButton = new  JRadioButton("");
		
		selectedButton.setName("You can select and drag and drop your drawings");
		rectangleButton.setName("Try to do a rectangle!");
		ellipseButton.setName("Try to do an ellipse!");
		lineButton.setName("Draw a line");
		pathButton.setName("Free drawing!");
		textButton.setName("Write something");
		
		JPanel v1 = new JPanel();
		v1.setLayout(new BoxLayout(v1,BoxLayout.X_AXIS));
		
		JPanel v2 = new JPanel();
		v2.setLayout(new BoxLayout(v2,BoxLayout.X_AXIS));
		
		JPanel v3 = new JPanel();
		v3.setLayout(new BoxLayout(v3,BoxLayout.X_AXIS));
		
		JPanel v4 = new JPanel();
		v4.setLayout(new BoxLayout(v4,BoxLayout.X_AXIS));
		
		JPanel v5 = new JPanel();
		v5.setLayout(new BoxLayout(v5,BoxLayout.X_AXIS));
		
		
		selectedButton.setPressedIcon(new ImageIcon("button/clicked/select_clicked.png"));
		selectedButton.setRolloverIcon(new ImageIcon("button/hover/select_hover.png"));
		selectedButton.setSelectedIcon(new ImageIcon("button/clicked/select_clicked.png"));
		selectedButton.setIcon(new ImageIcon("button/idle/select_idle.png"));
		selectedButton.setActionCommand("Select");
		v3.add(selectedButton);
		
		rectangleButton.setPressedIcon(new ImageIcon("button/clicked/rectangle_clicked.png"));
		rectangleButton.setRolloverIcon(new ImageIcon("button/hover/rectangle_hover.png"));
		rectangleButton.setSelectedIcon(new ImageIcon("button/clicked/rectangle_clicked.png"));
		rectangleButton.setIcon(new ImageIcon("button/idle/rectangle_idle.png"));
		rectangleButton.setActionCommand("Rectangle");
		v2.add(rectangleButton);
		
		ellipseButton.setPressedIcon(new ImageIcon("button/clicked/ellipse_clicked.png"));
		ellipseButton.setRolloverIcon(new ImageIcon("button/hover/ellipse_hover.png"));
		ellipseButton.setSelectedIcon(new ImageIcon("button/clicked/ellipse_clicked.png"));
		ellipseButton.setIcon(new ImageIcon("button/idle/ellipse_idle.png"));
		ellipseButton.setActionCommand("Ellipse");
		v2.add(ellipseButton);
		
		pathButton.setPressedIcon(new ImageIcon("button/clicked/path_clicked.png"));
		pathButton.setRolloverIcon(new ImageIcon("button/hover/path_hover.png"));
		pathButton.setSelectedIcon(new ImageIcon("button/clicked/path_clicked.png"));
		pathButton.setIcon(new ImageIcon("button/idle/path_idle.png"));
		pathButton.setActionCommand("Path");
		v1.add(pathButton);
		
		lineButton.setPressedIcon(new ImageIcon("button/clicked/line_clicked.png"));
		lineButton.setRolloverIcon(new ImageIcon("button/hover/line_hover.png"));
		lineButton.setSelectedIcon(new ImageIcon("button/clicked/line_clicked.png"));
		lineButton.setIcon(new ImageIcon("button/idle/line_idle.png"));
		lineButton.setActionCommand("Line");
		v1.add(lineButton);
		
		textButton.setPressedIcon(new ImageIcon("button/clicked/texte_clicked.png"));
		textButton.setRolloverIcon(new ImageIcon("button/hover/texte_hover.png"));
		textButton.setSelectedIcon(new ImageIcon("button/clicked/texte_clicked.png"));
		textButton.setIcon(new ImageIcon("button/idle/texte_idle.png"));
		textButton.setActionCommand("Text");
		v3.add(textButton);	
		
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
		familyButton.setFocusable(false);
		this.add(familyButton);
		vacationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		vacationButton.setFocusable(false);
		this.add(vacationButton);
		schoolButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		schoolButton.setFocusable(false);
		this.add(schoolButton);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(drawing);
		this.add(Box.createRigidArea(new Dimension(0,5)));
		selectedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectedButton.setFocusable(false);
		
		rectangleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rectangleButton.setFocusable(false);
		
		ellipseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		ellipseButton.setFocusable(false);
		lineButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		lineButton.setFocusable(false);
		rectangleButton.setSelected(true);
		pathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		pathButton.setFocusable(false);
		textButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		textButton.setFocusable(false);
		
		this.add(v1);
		this.add(v2);
		this.add(v3);
		
		contourChooser = new JPanel();
		interieurChooser = new JPanel();
		contourChooser.setBackground(Color.black);
		contourChooser.setName("Outline Color");
		contourChooser.setBorder(BorderFactory.createLineBorder(Color.black));
		interieurChooser.setBackground(Color.LIGHT_GRAY);
		interieurChooser.setName("Inside Color");
		interieurChooser.setBorder(BorderFactory.createLineBorder(Color.black));
		
		contourChooser.setMaximumSize(new Dimension(31,20));
		interieurChooser.setMaximumSize(new Dimension(31,20));
		contourChooser.addMouseListener(new ColorChooserListener(contourChooser,model));
		interieurChooser.addMouseListener(new ColorChooserListener(interieurChooser,model));
		
		v4.add(interieurChooser);
		v4.add(Box.createRigidArea(new Dimension(9,0)));
		v4.add(contourChooser);
		
		this.add(v5);
		
		JButton deleteButton = new JButton("");
		JButton cloneButton = new JButton("");
		deleteButton.setPressedIcon(new ImageIcon("button/clicked/delete_clicked.png"));
		deleteButton.setRolloverIcon(new ImageIcon("button/hover/delete_hover.png"));
		deleteButton.setSelectedIcon(new ImageIcon("button/clicked/delete_clicked.png"));
		deleteButton.setIcon(new ImageIcon("button/idle/delete_idle.png"));
		deleteButton.setActionCommand("Delete");
		deleteButton.setOpaque(false);
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorderPainted(false);
		deleteButton.setMargin(new Insets(0,0,0,0));
		
		cloneButton.setPressedIcon(new ImageIcon("button/clicked/clone_clicked.png"));
		cloneButton.setRolloverIcon(new ImageIcon("button/hover/clone_hover.png"));
		cloneButton.setSelectedIcon(new ImageIcon("button/clicked/clone_clicked.png"));
		cloneButton.setIcon(new ImageIcon("button/idle/clone_idle.png"));
		cloneButton.setActionCommand("Clone");
		cloneButton.setOpaque(false);
		cloneButton.setContentAreaFilled(false);
		cloneButton.setBorderPainted(false);
		cloneButton.setMargin(new Insets(0,0,0,0));
		
		deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		v5.add(deleteButton);
		deleteButton.setFocusable(false);
		cloneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		v5.add(Box.createRigidArea(new Dimension(2,0)));
		v5.add(cloneButton);
		cloneButton.setFocusable(false);
		
		this.add(Box.createRigidArea(new Dimension(0,5)));
		this.add(v4);
		
		StatueListener selectListener = new StatueListener(model);
		for (AbstractButton l :listButton){
			l.addActionListener(selectListener);
		}
		ModeListener modeListener = new ModeListener(model);
		for (AbstractButton l :drawingListButton){
			l.addActionListener(modeListener);
		}
		deleteButton.addActionListener(modeListener);
		cloneButton.addActionListener(modeListener);
	}

	public ArrayList<AbstractButton> getListButton() {
		return listButton;
	}

	public JPanel getContourChooser() {
		return contourChooser;
	}

	public JPanel getInterieurChooser() {
		return interieurChooser;
	}
}
