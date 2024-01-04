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
import java.util.stream.Collectors;

import interfaces.IController;
import model.Radnik.Magacioner;
import model.Radnik.Menadzer;
import model.Radnik.Radnik;

public class MenadzerController implements IController<Menadzer>{

	public List<Menadzer> ucitaj(){	
		try {
			String[] menadzeri = Files.readString(Paths.get("src/data/podaci.csv")).split("\\$");
			List<String> lista = Arrays.asList(menadzeri[1].split("\n"));
			List<Menadzer> listaMenadzera = lista.stream()
				    .skip(1)
				    .filter(linija -> !linija.trim().isEmpty())
				    .map(line -> line.split("\\|"))
				    .map(instanca -> {
				    	String[] magacioneriInfo = instanca[4].split(",");
				    	ArrayList<Radnik> magacioneri;
				    	if(instanca[4].contains("/")) {
				    		magacioneri = new ArrayList<Radnik>();
				    	}
				    	else{//jedino ovde se kao atribut nalazi lista neke druge klase,zato ovde mapiramo svakog iz departmana na instancu magacionera koju kasnije vracamo
				    		magacioneri = Arrays.stream(magacioneriInfo)
				                .map(magacionerStr -> {
				                    String[] magacionerInfoArr = magacionerStr.split("-");
				                    return new Magacioner(
				                            magacionerInfoArr[0],
				                            magacionerInfoArr[1],
				                            magacionerInfoArr[2],
				                            Double.parseDouble(magacionerInfoArr[3]),
				                            magacionerInfoArr[4],
				                            Integer.parseInt(magacionerInfoArr[5])
				                    );
				                })
				                .collect(Collectors.toCollection(ArrayList::new));
				    	}
				        return new Menadzer(	//tek nakon prebacivanja u magacionere mozemo da vratimo listu menadzera
				                instanca[0],
				                instanca[1],
				                instanca[2],
				                Double.parseDouble(instanca[3]),
				                magacioneri
				        );
				    })
				    .toList();
			return listaMenadzera;
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public void upisi(Menadzer m) {
		String zaUpis = "" + m.getIme() + "|" + m.getPrezime() + "|" + m.getBiografija() + "|" + m.getVisinaPlate() + "|";
		if(m.getDepartman().size() > 0) {
			for(int i=0;i<m.getDepartman().size();i++) {
				Magacioner trenutni = (Magacioner) m.getDepartman().get(i);
				if(i == (m.getDepartman().size() - 1)) {
					zaUpis += trenutni.getIme() + "-" + trenutni.getPrezime() + "-" + trenutni.getBiografija() + "-" + trenutni.getVisinaPlate() + "-" + trenutni.getRadnoMesto() + "-" + trenutni.getOdmori() + "|";
				}else {
					zaUpis += trenutni.getIme() + "-" + trenutni.getPrezime() + "-" + trenutni.getBiografija() + "-" + trenutni.getVisinaPlate() + "-" + trenutni.getRadnoMesto() + "-" + trenutni.getOdmori() + ",";
				}
			}
		}else {
			zaUpis += "/|";
		}
		
        int dolarCounter = 0;
        int brojMenadzera = ucitaj().size();
        int ucitanoMenadzera = 0;
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
                    if(dolarCounter == 1) {
                    	if(ucitanoMenadzera < brojMenadzera) {
                    		ucitanoMenadzera++;
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
        int ucitanoMenadzera = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 1) {
                    	if (ucitanoMenadzera == index + 1) {
                    		ucitanoMenadzera++;
                            continue;
                        } else {
                            linije.add(linija);
                            ucitanoMenadzera++;
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
	
	public void izmeni(int index, Menadzer m) {
		String zaUpis = "";
		int dolarCounter = 0;
        int ucitanoMenadzera = 0;
        try {
            File fajl = new File("src/data/podaci.csv");
            ArrayList<String> linije = new ArrayList<String>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
                String linija;
                while ((linija = reader.readLine()) != null) {
                    if (linija.contains("$")) {
                    	dolarCounter++;
                    }
                    if(dolarCounter == 1) {
                    	if(ucitanoMenadzera == index + 1) {
                    		zaUpis += m.getIme() + "|" + m.getPrezime() + "|" + m.getBiografija() + "|" + m.getVisinaPlate() + "|";
                    		if(m.getDepartman().size() > 0) {
	                    		for(int i=0;i<m.getDepartman().size();i++) {
	                    			Magacioner trenutni = (Magacioner) m.getDepartman().get(i);
	                    			if(i == (m.getDepartman().size() - 1)) {
	                    				zaUpis += trenutni.getIme() + "-" + trenutni.getPrezime() + "-" + trenutni.getBiografija() + "-" + trenutni.getVisinaPlate() + "-" + trenutni.getRadnoMesto() + "-" + trenutni.getOdmori() + "|";
	                    			}else {
	                    				zaUpis += trenutni.getIme() + "-" + trenutni.getPrezime() + "-" + trenutni.getBiografija() + "-" + trenutni.getVisinaPlate() + "-" + trenutni.getRadnoMesto() + "-" + trenutni.getOdmori() + ",";
	                    			}
	                    		}
                    		}else {
                    			zaUpis += "/|";
                    		}
                    		linije.add(zaUpis);
                    		ucitanoMenadzera++;
                    	}
                    	else {
                    		linije.add(linija);
                    		ucitanoMenadzera++;
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
