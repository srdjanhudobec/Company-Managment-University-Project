package model.Radnik;

import java.util.ArrayList;

public class Menadzer extends Radnik {
	
	private ArrayList<Radnik> departman;

	
	public Menadzer() {
		super();
	}

	public Menadzer(String ime, String prezime, String biografija, Double visinaPlate,
			ArrayList<Radnik> departman) {
		super(ime, prezime, biografija, visinaPlate);
		this.departman = departman;
	}

	public ArrayList<Radnik> getDepartman() {
		return departman;
	}

	public void setDepartman(ArrayList<Radnik> departman) {
		this.departman = departman;
	}

	@Override
	public String toString() {
		String menadzer = "Menadzer [ime=" + super.getIme() + ", prezime=" + super.getPrezime() + ", biografija=" + super.getBiografija() + ", visinaPlate=" + super.getVisinaPlate() + ", departman=";
		if(this.departman.size() <= 0) {
			menadzer += "[]]";
			return menadzer;
		}
		for(int i=0;i<this.departman.size();i++) {
			if(i == (this.departman.size() - 1)) {
				menadzer += departman.get(i).toString() + "]";
			}
			else {
				menadzer += departman.get(i).toString() + ",";
			}
		}
		return menadzer;
	}
	
	public void dodajZaposlenog(Radnik r) {
		this.departman.add(r);
	}
	
	public void ukloniZaposlenog(Radnik r) {
		if(this.departman.size() > 0) {
			this.departman.remove(r);
		}
	}
	
	public void ukloniZaposlenog() {
		if(this.departman.size() > 0) {
			this.departman.remove(0);
		}
	}
}
