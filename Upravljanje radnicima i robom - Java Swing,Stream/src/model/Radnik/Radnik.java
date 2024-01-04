package model.Radnik;

public abstract class Radnik {
	
	private String ime;
	private String prezime;
	private String biografija;
	private Double visinaPlate;
	
	public Radnik(String ime, String prezime, String biografija, Double visinaPlate) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.biografija = biografija;
		this.visinaPlate = visinaPlate;
	}

	public Radnik() {
		super();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getBiografija() {
		return biografija;
	}

	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}

	public Double getVisinaPlate() {
		return visinaPlate;
	}

	public void setVisinaPlate(Double visinaPlate) {
		this.visinaPlate = visinaPlate;
	}

	@Override
	public String toString() {
		return "Radnik [ime=" + ime + ", prezime=" + prezime + ", biografija=" + biografija + ", visinaPlate="
				+ visinaPlate + "]";
	}
	
	
}
