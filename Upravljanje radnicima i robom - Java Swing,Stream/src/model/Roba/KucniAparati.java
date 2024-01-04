package model.Roba;

public class KucniAparati extends Roba {
	
	private Dimenzije dimenzije;
	private int radniNapon;
	private int nominalnaSnaga;
	
	public KucniAparati(String naziv,Double cena,String model,String proizvodjac,String opis,Dimenzije dimenzije, int radniNapon, int nominalnaSnaga) {
		super(naziv,cena,model,proizvodjac,opis);
		this.dimenzije = dimenzije;
		this.radniNapon = radniNapon;
		this.nominalnaSnaga = nominalnaSnaga;
	}
	
	public KucniAparati() {
		super();
	}
	
	public Dimenzije getDimenzije() {
		return dimenzije;
	}
	
	public void setDimenzije(Dimenzije dimenzije) {
		this.dimenzije = dimenzije;
	}
	
	public int getRadniNapon() {
		return radniNapon;
	}
	
	public void setRadniNapon(int radniNapon) {
		this.radniNapon = radniNapon;
	}
	
	public int getNominalnaSnaga() {
		return nominalnaSnaga;
	}
	
	public void setNominalnaSnaga(int nominalnaSnaga) {
		this.nominalnaSnaga = nominalnaSnaga;
	}
	
	@Override
	public String toString() {
		return "KucniAparati [naziv=" + super.getNaziv() + ", cena=" + super.getCena() + ", model=" + super.getModel() + ", proizvodjac=" + super.getProizvodjac() + ", opis=" + super.getOpis() + ", dimenzije=" + dimenzije + ", radniNapon=" + radniNapon + ", nominalnaSnaga="
				+ nominalnaSnaga + "]";
	}
	
}
