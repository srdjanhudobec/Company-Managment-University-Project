package model.Roba;


public class KvarljivaRoba extends Roba {
	
	private int rokTrajanja; //broj dana npr.
	private String uputstvo;
	

	public KvarljivaRoba(String naziv, Double cena, String model, String proizvodjac, String opis,int rokTrajanja,String uputstvo) {
		super(naziv, cena, model, proizvodjac, opis);
		this.rokTrajanja = rokTrajanja;
		this.uputstvo = uputstvo;
	}

	public KvarljivaRoba() {
		super();
	}

	public int getRokTrajanja() {
		return rokTrajanja;
	}

	public void setRokTrajanja(int rokTrajanja) {
		this.rokTrajanja = rokTrajanja;
	}

	public String getUputstvo() {
		return uputstvo;
	}

	public void setUputstvo(String uputstvo) {
		this.uputstvo = uputstvo;
	}

	@Override
	public String toString() {
		return "KvarljivaRoba [naziv=" + super.getNaziv() + ", cena=" + super.getCena() + ", model=" + super.getModel() + ", proizvodjac=" + super.getProizvodjac() + ", opis=" + super.getOpis() +  ", rokTrajanja=" + rokTrajanja + ", uputstvo=" + uputstvo + "]";
	}

}
