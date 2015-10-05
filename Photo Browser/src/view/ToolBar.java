package view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4268951943328073987L;
	private ArrayList<AbstractButton> listButton;
	
	public ToolBar(){
		super();
		this.listButton = new ArrayList<AbstractButton>();
		this.setFloatable(false);
		this.setPreferredSize(new Dimension(100, 400));
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
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
		
		this.add(familyButton);
		this.add(vacationButton);
		this.add(schoolButton);
		
	}

	public ArrayList<AbstractButton> getListButton() {
		return listButton;
	}
}
