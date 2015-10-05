package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MainModel;

public class MainView extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1749341150772433050L;
	private MainModel model;
	private JPanel mainPanel;
	private Menu menu;
	private StatusBar statueBar;
	private ToolBar toolBar;

	public MainView(MainModel model) {

		this.setSize(new Dimension(600, 400));
		this.setTitle("Photos Browser");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.mainPanel = new JPanel();
		this.mainPanel.setPreferredSize(new Dimension(600, 400));
		this.model = model;
		this.menu = new Menu(); 
		StatusBar status = new StatusBar(new JLabel(this.model.getStatu()));
		this.statueBar = status;
		this.toolBar = new ToolBar();
		initView();
		this.setContentPane(mainPanel);
		this.setVisible(true);

	}


	public void initView() {

		this.setJMenuBar(menu);

		mainPanel.setLayout(new BorderLayout());

		PhotosViewer photoViewer = new PhotosViewer();
		
		mainPanel.add(toolBar,BorderLayout.WEST);	
		mainPanel.add(photoViewer,BorderLayout.CENTER);
		mainPanel.add(statueBar,BorderLayout.SOUTH);
	
	}

	public ToolBar getToolBar() {
		return toolBar;
	}


	public Menu getMenu() {
		return menu;
	}

	public StatusBar getStatueBar() {
		return statueBar;
	}
	
}
