package utility;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.PopupMenuListener;

import view.PhotoComponent;
import model.MainModel;

/**
 * 
 * @author Cédric
 *
 */
public class FileSelector extends JDialog {
	
	private MainModel model;
	private String directory = null;
	private String file = null;
	private String[] files;
	
	private Container pane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	
	private JList liste;
	private JScrollPane scrollpane;
	private JComboBox cb;
	
	private JButton buttonClose;
	private JButton buttonOpen;
	
	
	 private ActionListener comboBoxListener = new ActionListener() {
		 public void actionPerformed(ActionEvent e) { 
			 String path="";
			 JComboBox cb2 = (JComboBox)e.getSource();
			 
			 for (int i = 0; i<=(int) cb2.getSelectedIndex();i++){
				 path=path.concat((String) cb2.getItemAt(i)+File.separator);
				 
			 }
			 pane.removeAll();
			 show(path);
			 pack();
			 
		 }
	};
	
	private MouseAdapter listeListener = new MouseAdapter() {
	    public void mouseClicked(MouseEvent evt) {
	        JList list = (JList)evt.getSource();
	        if (evt.getClickCount() == 2) {
	        	 int i = liste.getSelectedIndex();
				 if (i != -1){
					 String s = files[i];
					 pane.removeAll();
					 show(directory+File.separator+s);
					 pack();
				 }
	        }
	    }
	};
	
	private ActionListener openListener = new ActionListener(){
		 public void actionPerformed(ActionEvent e) { 
			 
			 int i = liste.getSelectedIndex();
			 if (i != -1){
				 String s = files[i];
				 pane.removeAll();
				 show(directory+File.separator+s);
				 pack();
				 model.getVue().getPhotoViewer().getScroll().setCurrentPhoto(new PhotoComponent(s));
				 dispose();
			 }
			
		 }
	};
	
	private ActionListener cancelListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	public FileSelector(String title,MainModel model){
		
		super((JFrame)null,title,true);
		this.model = model;
		show(System.getProperty("user.dir"));

		pack();
		setVisible(true);
		
	}
	
	
	
	boolean show(String path){
		
		File dir = new File(path);
		if(!dir.exists() || !dir.isDirectory()) return false;
		
		directory=dir.getAbsolutePath();
		file = null;
		

		
		
		//Affichage
		super.setBounds(300,200,300,140);	
		
		buttonClose = new JButton("Cancel");
		buttonOpen = new JButton("Open");
		
		buttonOpen.addActionListener(openListener);
		buttonClose.addActionListener(cancelListener);
		
		pane = getContentPane();
		pane.setLayout(new FlowLayout());
		
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
	
		
		files = dir.list();
		 
		liste = new JList(files);
		liste.addMouseListener(listeListener);
		scrollpane = new JScrollPane(liste);
		if (files!=null){
			for(int i =0;i<files.length;i++){
				File f = new File(path,files[i]);
				if (f.isDirectory()) files[i] = files[i]+File.separator;
				System.out.format(" file: %s\n", files[i]);
				
			}
		}
		
		
		System.out.format("parentdir: %s\n", File.separator);
		String[] dirs = path.split("\\\\");
		

		cb = new JComboBox(dirs);
		cb.addActionListener(comboBoxListener);
		//scroll.add(bar);
		//scroll.setBounds(10, 10, 200, 300);
		for(String p : dirs){
			if (p.equals("")) continue;
			System.out.format("parentdir: %s\n", p);
			
		}
		
		//cb.add(scroll);
		//cb.addItem(liste);
		panel1.add(cb);
		
		panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		
		
		panel3.add(scrollpane);
		
		panel1.add(panel3);
		panel1.add(Box.createRigidArea(new Dimension(0,10)));
		
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// Ajout des boutons
		panel2.add(buttonOpen);
		// Ecartement entre les boutons
		panel2.add(Box.createRigidArea(new Dimension(10,0)));
		panel2.add(buttonClose);
		panel2.add(Box.createHorizontalGlue());
		panel1.add(panel2);
		pane.add(panel1);
		
		return true;
		
	}
	
	
	public String getFilePath() {
		
		if (directory == null || file == null) return null;
		return directory+File.separator+file;
		
	}

	
}
