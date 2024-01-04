package model.Roba;

public class Dimenzije {

	private Double duzina;
	private Double sirina;
	private Double visina;
	
	public Dimenzije() {
		super();
	}
	
	public Dimenzije(Double sirina,Double visina,Double duzina) {
		super();
		this.sirina = sirina;
		this.visina = visina;
		this.duzina = duzina;
	}
	
	public Double getDuzina() {
		return duzina;
	}
	
	public void setDuzina(Double duzina) {
		this.duzina = duzina;
	}
	
	public Double getSirina() {
		return sirina;
	}
	
	public void setSirina(Double sirina) {
		this.sirina = sirina;
	}
	
	public Double getVisina() {
		return visina;
	}
	
	public void setVisina(Double visina) {
		this.visina = visina;
	}
	
	@Override
	public String toString() {
		return sirina + "," + visina + "," + duzina;
	}
	
	
}
