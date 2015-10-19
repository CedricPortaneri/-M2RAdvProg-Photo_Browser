package myComponents;

import java.awt.event.MouseEvent;

import javax.swing.event.ChangeListener;


public interface PhotoModel {

	public void registerListener(ChangeListener l);

	public void unregisterListener(ChangeListener l);

	public void unregisterAllListener();
	
	public void addStroke(MouseEvent evt);

	public void addRealeasedStroke(MouseEvent evt);
	
	public void addMotion(MouseEvent evt);
	
}
