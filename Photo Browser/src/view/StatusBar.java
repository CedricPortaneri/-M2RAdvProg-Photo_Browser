package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MainModel;

/* Bottom Status Bar */
public class StatusBar extends JPanel implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel statu;
	
	public StatusBar (JLabel defaultLabel){
		this.setPreferredSize(new Dimension(600,25));
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		this.statu = defaultLabel;
		this.add(statu);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof MainModel){
			this.statu.setText((String) arg.toString());
		}
	}
}
