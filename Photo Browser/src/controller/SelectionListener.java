package controller;

import java.util.EventListener;



public interface SelectionListener extends EventListener {
	
		public void selectionRecognized(SelectEvent e);
}
