package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TextInputListener;
import model.MainModel;

/* Main View which regroup every component of the Photo Browser */
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
	private PhotosViewer photoViewer;

	public MainView(MainModel model) {

		this.setSize(new Dimension(600, 500));
		this.setTitle("Photos Browser");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.mainPanel = new JPanel();
		this.setPreferredSize(new Dimension(600, 500));
		this.model = model;
		this.menu = new Menu(model); 
		StatusBar status = new StatusBar(new JLabel(this.model.getStatu()));
		this.statueBar = status;
		photoViewer = new PhotosViewer(model);
		this.toolBar = new ToolBar(this.model);
		
		initView();
		this.setContentPane(mainPanel);
		this.setVisible(true);

		this.addKeyListener(new TextInputListener(model));
		this.setFocusable(true);
		this.requestFocus();
		this.setFocusTraversalKeysEnabled(false);
	}



	public PhotosViewer getPhotoViewer() {
		return photoViewer;
	}



	public void initView() {

		this.setJMenuBar(menu);

		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(photoViewer,BorderLayout.CENTER);
		mainPanel.add(toolBar,BorderLayout.WEST);	
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
