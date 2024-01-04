package interfaces;

import java.util.List;

public interface IController<T>{
	
	public List<T> ucitaj();
	
	public void upisi(T instanca);
	
	public void ukloni(int index);
	
	public void izmeni(int index,T instanca);
}
