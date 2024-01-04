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
import model.Radnik.Magacioner;

public class MagacionerController implements IController<Magacioner>{
	
	public MagacionerController () {
		
	}
	
	public List<Magacioner> ucitaj(){	
		try {
			String[] magacioneri = Files.readString(Paths.get("src/data/podaci.csv")).split("\\$");
			List<String> lista = Arrays.asList(magacioneri[2].split("\n"));
			return lista.stream()
					.skip(1)
					.filter(linija -> !linija.trim().isEmpty())
					.map(line -> line.split("\\|"))
					.map(instanca -> new Magacioner(instanca[0],instanca[1],instanca[2],Double.parseDouble(instanca[3]),instanca[4],Integer.parseInt(instanca[5]))).toList();	
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	
	
	public void upisi(Magacioner m) {
		String zaUpis = "" + m.getIme() + "|" + m.getPrezime() + "|" + m.getBiografija() + "|" + m.getVisinaPlate() + "|" + m.getRadnoMesto() + "|" + m.getOdmori() + "|";
        int dolarCounter = 0;
        int brojMagacionera = ucitaj().size();
        int ucitanoMagacionera = 0;
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
                    if(dolarCounter == 2) {
                    	if(ucitanoMagacionera < brojMagacionera) {
                    		ucitanoMagacionera++;
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

	public void ukloni(int index) {
        int dolarCounter = 0;
        int ucitanoMagacionera = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 2) {
                    	if(ucitanoMagacionera == index + 1) {
                    		ucitanoMagacionera++;
                    		continue;
                    	}
                    	else {
                    		linije.add(linija);
                    		ucitanoMagacionera++;
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

	public void izmeni(int index, Magacioner m) {
		String zaUpis = "";
		int dolarCounter = 0;
        int ucitanoMagacionera = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {
                	
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 2) {
                    	if(ucitanoMagacionera == index + 1) {
                    		zaUpis += m.getIme() + "|" + m.getPrezime() + "|" + m.getBiografija() + "|" + m.getVisinaPlate() + "|" + m.getRadnoMesto() + "|" + m.getOdmori() + "|";
                    		linije.add(zaUpis);
                    		ucitanoMagacionera++;
                    	}
                    	else {
                    		linije.add(linija);
                    		ucitanoMagacionera++;
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