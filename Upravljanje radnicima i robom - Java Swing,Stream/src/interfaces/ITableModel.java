package interfaces;

public interface ITableModel<T>{
	
	public void dodajPostojece(T instanca);
	
	public void dodaj(T instanca);
	
	public void ukloni(int index);
	
	public void izmeni(int index,T instanca);
	
	public T dobavi(int index);
	
	public void obrisiSve();
}
