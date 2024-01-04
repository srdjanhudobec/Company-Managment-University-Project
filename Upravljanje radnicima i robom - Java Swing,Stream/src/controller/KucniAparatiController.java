package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import interfaces.IController;
import model.Roba.Dimenzije;
import model.Roba.KucniAparati;

public class KucniAparatiController implements IController<KucniAparati>{
	
	public KucniAparatiController() {
		
	}
	
	public List<KucniAparati> ucitaj(){
		try {
			String[] kucniAparati = Files.readString(Paths.get("src/data/podaci.csv")).split("\\$");
			List<String> lista = Arrays.asList(kucniAparati[4].split("\n"));   //iscitavamo sve kucne aparate iz csv fajla,i uz pomoc streama i map metode,kreiramo instance klase i vracamo kao array listu kucnih aparata
			return lista.stream()
					.skip(1)
					.filter(linija -> !linija.trim().isEmpty())
					.map(linija -> linija.split("\\|"))
					.map(instanca -> new KucniAparati(instanca[0],Double.parseDouble(instanca[1]),instanca[2],instanca[3],instanca[4],new Dimenzije(Double.parseDouble(instanca[5].split(",")[0]),Double.parseDouble(instanca[5].split(",")[1]),Double.parseDouble(instanca[5].split(",")[2])),Integer.parseInt(instanca[6]),Integer.parseInt(instanca[7]))).toList();	
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public void upisi(KucniAparati ka) {
		String zaUpis = "" + ka.getNaziv() + "|" + ka.getCena() + "|" + ka.getModel() + "|" + ka.getProizvodjac() + "|" + ka.getOpis() + "|" + ka.getDimenzije().toString() + "|" + ka.getRadniNapon() + "|" + ka.getNominalnaSnaga() + "|";
        int dolarCounter = 0;
        int brojKucnihAparata = ucitaj().size();
        int ucitanoKucnihAparata = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) { //koriscen je BufferedReader za citanje,kojem kontrolisemo liniju po liniju sta ce se dodati u iscitanu listu
                    linije.add(linija);
                    // Dodajem novog korisnika nakon cetvrtog dolara i nakon zadnjeg kucnog aparata
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 4) {
                    	if(ucitanoKucnihAparata < brojKucnihAparata) {
                    		ucitanoKucnihAparata++;
                    	}
                    	else {//tek kad su prosli svi kucni aparati,tek tad dodajem novi
                    		linije.add(zaUpis);
                    	}
                    }
                }
            }
            //upisujem izmenjenu listu u fajl
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fajl))) {
                for (String linija : linije) {
                    writer.write(linija);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void izmeni(int index,KucniAparati ka) {
		String zaUpis = "";
		int dolarCounter = 0;
        int ucitanoKucnihAparata = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {			//sve isto kao i kod upisa,samo se ovde upisuje izmenjeni sadrzaj kad se stigne do unesenog indexa umesto da se uklanja
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 4) {
                    	if(ucitanoKucnihAparata == index + 1) {
                    		zaUpis = "" + ka.getNaziv() + "|" + ka.getCena() + "|" + ka.getModel() + "|" + ka.getProizvodjac() + "|" + ka.getOpis() + "|" + ka.getDimenzije().toString() + "|" + ka.getRadniNapon() + "|" + ka.getNominalnaSnaga() + "|";
                    		linije.add(zaUpis);
                    		ucitanoKucnihAparata++;
                    	}
                    	else {
                    		linije.add(linija);
                    		ucitanoKucnihAparata++;
                    	}
                    }
                    else {
                    	linije.add(linija);
                    }
                }
            }
            //upisujem izmenjenu listu u fajl
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fajl))) {
                for (String linija : linije) {
                    writer.write(linija);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void ukloni(int index) {
		int dolarCounter = 0;
        int ucitanoKucnihAparata = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 4) {
                    	if(ucitanoKucnihAparata == index + 1) {
                    		ucitanoKucnihAparata++;
                    		continue;
                    	}
                    	else {
                    		linije.add(linija);
                    		ucitanoKucnihAparata++;
                    	}
                    }
                    else {
                    	linije.add(linija);
                    }
                }
            }
            //upisujem izmenjenu listu u fajl
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fajl))) {
                for (String linija : linije) {
                    writer.write(linija);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
