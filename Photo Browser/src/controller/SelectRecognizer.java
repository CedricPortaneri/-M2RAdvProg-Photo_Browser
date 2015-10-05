package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;

public class SelectRecognizer{

	private ArrayList<SelectionListener> listeners = new ArrayList<SelectionListener>();
	private ArrayList<JMenuItem> menuItemObs = new ArrayList<JMenuItem>();
	private ArrayList<AbstractButton> abstractButtonObs = new ArrayList<AbstractButton>();
	
	public SelectRecognizer(ArrayList<JMenuItem> mIO,ArrayList<AbstractButton> mAB) {
		super();
		this.menuItemObs = mIO;
		this.abstractButtonObs = mAB;
		observableInit();
	}
	
	public void observableInit(){
		for (JMenuItem j : menuItemObs) {

			j.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					 handleSelection(((JComponent) (e.getSource())).getName().toString());
				}

			});
		}
		for (AbstractButton j : abstractButtonObs) {

			j.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					 handleSelection(((JComponent) (e.getSource())).getName().toString());
				}

			});
		}
	}
	
	public void handleSelection(String str){

		SelectEvent e = new SelectEvent(str);
		for (SelectionListener l : listeners){
			l.selectionRecognized(e);
		}
	}
	
	public void registerListener(SelectionListener l){
		listeners.add(l);
		// we check if check is in the list...
	}
	
	private void unregisterListener(SelectionListener l){
		listeners.remove(l);
	}
	
	private void unregisterAllListener(){
		listeners.clear();
	}


}
