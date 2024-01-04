package interfaces;

import java.awt.event.ActionListener;

public interface IFrame<T> {
	
	public T kreiraj();
	
	public void ucitaj();
	
	public void addActionListener(ActionListener listener);
	
	public void removeActionListener(ActionListener listener);
	
	public void updateActionListener(ActionListener listener);
}
