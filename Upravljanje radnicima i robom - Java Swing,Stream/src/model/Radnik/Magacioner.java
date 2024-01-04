package model.Radnik;

public class Magacioner extends Radnik {
	
	private String radnoMesto;
	private int odmori; //broj dana godisnjeg koji mu je ostao
	
	public String getRadnoMesto() {
		return radnoMesto;
	}
	
	public void setRadnoMesto(String radnoMesto) {
		this.radnoMesto = radnoMesto;
	}
	
	public int getOdmori() {
		return odmori;
	}
	
	public void setOdmori(int odmori) {
		this.odmori = odmori;
	}
	
	public Magacioner(String ime, String prezime, String biografija, Double visinaPlate, String radnoMesto,
			int odmori) {
		super(ime, prezime, biografija, visinaPlate);
		this.radnoMesto = radnoMesto;
		this.odmori = odmori;
	}
	
	public Magacioner() {
		super();
	}
	
	@Override
	public String toString() {
		return this.getIme() + "-" + this.getPrezime() + "-" + this.getBiografija() + "-" + this.getVisinaPlate() + "-" + this.getRadnoMesto() + "-" + this.getOdmori();
	}
	
	public void dodajDaneOdmora(int brojDana) {
		this.setOdmori(this.getOdmori() + brojDana);
	}
	
	public void ukloniDaneOdmora(int brojDana) {
		this.setOdmori(this.getOdmori() - brojDana);
	}
	
}
