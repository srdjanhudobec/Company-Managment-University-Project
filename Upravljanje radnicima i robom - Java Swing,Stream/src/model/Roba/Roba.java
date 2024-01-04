package model.Roba;

public abstract class Roba {
	
	private String naziv;
	private Double cena;
	private String model;
	private String proizvodjac;
	private String opis;
	
	public Roba() {
		super();
	}
	
	public Roba(String naziv, Double cena, String model, String proizvodjac, String opis) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.model = model;
		this.proizvodjac = proizvodjac;
		this.opis = opis;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public Double getCena() {
		return cena;
	}
	
	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getProizvodjac() {
		return proizvodjac;
	}
	
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	
	public String getOpis() {
		return opis;
	}
	
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	@Override
	public String toString() {
		return "Roba [naziv=" + naziv + ", cena=" + cena + ", model=" + model + ", proizvodjac=" + proizvodjac
				+ ", opis=" + opis + "]";
	}
	
}
