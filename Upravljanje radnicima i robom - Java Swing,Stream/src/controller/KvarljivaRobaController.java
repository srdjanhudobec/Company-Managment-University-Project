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
import model.Roba.KvarljivaRoba;

public class KvarljivaRobaController implements IController<KvarljivaRoba>{
	
	public KvarljivaRobaController() {
		
	}
	
	public List<KvarljivaRoba> ucitaj(){   //u ovom kontroleru je sve identicno kao u prethodnom,samo se menja model sa kojim se manipulise
		try {
			String[] kvarljivaRoba = Files.readString(Paths.get("src/data/podaci.csv")).split("\\$");
			List<String> lista = Arrays.asList(kvarljivaRoba[3].split("\n"));
			return lista.stream()
					.skip(1)
					.filter(linija -> !linija.trim().isEmpty())
					.map(linija -> linija.split("\\|"))
					.map(instanca -> new KvarljivaRoba(instanca[0],Double.parseDouble(instanca[1]),instanca[2],instanca[3],instanca[4],Integer.parseInt(instanca[5]),instanca[6])).toList();	
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public void upisi(KvarljivaRoba kr) {
		String zaUpis = "" + kr.getNaziv() + "|" + kr.getCena() + "|" + kr.getModel() + "|" + kr.getProizvodjac() + "|" + kr.getOpis() + "|" + kr.getRokTrajanja() + "|" + kr.getUputstvo() + "|";
        int dolarCounter = 0;
        int brojKvarljiveRobe = ucitaj().size();
        int ucitanoKvarljiveRobe = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {
                    linije.add(linija);
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 3) {
                    	if(ucitanoKvarljiveRobe < brojKvarljiveRobe) {
                    		ucitanoKvarljiveRobe++;
                    	}
                    	else {
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
	
	public void izmeni(int index,KvarljivaRoba kr) {
		String zaUpis = "";
		int dolarCounter = 0;
        int ucitanoKvarljiveRobe = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 3) {
                    	if(ucitanoKvarljiveRobe == index + 1) {
                    		zaUpis += kr.getNaziv() + "|" + kr.getCena() + "|" + kr.getModel() + "|" + kr.getProizvodjac() + "|" + kr.getOpis() + "|" + kr.getRokTrajanja() + "|" + kr.getUputstvo() + "|";
                    		linije.add(zaUpis);
                    		ucitanoKvarljiveRobe++;
                    	}
                    	else {
                    		linije.add(linija);
                    		ucitanoKvarljiveRobe++;
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
        int ucitanoKvarljiveRobe = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 3) {
                    	if(ucitanoKvarljiveRobe == index + 1) {
                    		ucitanoKvarljiveRobe++;
                    		continue;
                    	}
                    	else {
                    		linije.add(linija);
                    		ucitanoKvarljiveRobe++;
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
