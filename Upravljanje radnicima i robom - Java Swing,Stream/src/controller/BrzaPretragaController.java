package controller;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Radnik.*;
import model.Roba.KucniAparati;
import model.Roba.KvarljivaRoba;

public class BrzaPretragaController {
	
	public ArrayList<Magacioner> proveriMagacionere(String filter){
		ArrayList<Magacioner> filtriraniMagacioneri = new ArrayList<Magacioner>(); //ucitavamo sve magacionere
		try {
			MagacionerController mc = new MagacionerController();
			List<Magacioner> sviMagacioneri = mc.ucitaj();
			
			filtriraniMagacioneri = sviMagacioneri.stream()    //pomocu lamba izraza i streama,filter metodom izdvajamo one magacionere ciji se atribut delimicno poklapa sa unetom kljucnom reci
					.filter(x -> x.getIme().toLowerCase()
							.contains(filter.toLowerCase()) || x.getPrezime().toLowerCase().contains(filter.toLowerCase()) ||  //pretraga nije case sensitive,zato koristimo toLowerCase() i za filter parametar i za svaki atribut
									x.getBiografija().toLowerCase().contains(filter.toLowerCase()) || 
									String.valueOf(x.getVisinaPlate()).contains(filter) ||  //koriscen je logicki operand ili,jer ako bilo koji atribut ima poklapanje ceo objekat se vraca
									x.getRadnoMesto().toLowerCase().contains(filter.toLowerCase()) || 
									String.valueOf(x.getOdmori()).contains(filter))   //String.valueOf je koriscen da bi mogli da proverimo da li vrednosti koje su int sadrze neki od unetih karaktera u filteru sa metodom contains()
									.collect(Collectors.toCollection(ArrayList::new)); //vracamo array listu magacionera
		}catch(Exception e) {
			System.out.println("Doslo je do greske." + e.getMessage());
		}
		
		return filtriraniMagacioneri;
	}
	
	public ArrayList<Menadzer> proveriMenadzere(String filter){
		ArrayList<Menadzer> filtriraniMenadzeri = new ArrayList<Menadzer>();
		try {
			MenadzerController mc = new MenadzerController();
			List<Menadzer> sviMenadzeri = mc.ucitaj();
			
			filtriraniMenadzeri = sviMenadzeri.stream()
					.filter(x -> x.getIme().toLowerCase()
							.contains(filter.toLowerCase()) || x.getPrezime().toLowerCase().contains(filter.toLowerCase()) ||
									x.getBiografija().toLowerCase().contains(filter.toLowerCase()) || 
									String.valueOf(x.getVisinaPlate()).contains(filter) || //sve isto kao kod prethodne metode
									x.getDepartman().stream().map(xe -> (Magacioner) xe).anyMatch(d -> //ovde pretvaramo departman u arraylistu magacionera i proveravamo sve atribute svakog radnika u departmanu
					                                d.getIme().toLowerCase().contains(filter.toLowerCase()) ||
					                                d.getPrezime().toLowerCase().contains(filter.toLowerCase()) ||
					                                d.getBiografija().toLowerCase().contains(filter.toLowerCase()) ||
					                                String.valueOf(d.getVisinaPlate()).contains(filter) ||
					                                d.getRadnoMesto().toLowerCase().contains(filter.toLowerCase()) ||
					                                String.valueOf(d.getOdmori()).contains(filter)
					                )).collect(Collectors.toCollection(ArrayList::new));
		}catch(Exception e) {
			System.out.println("Doslo je do greske." + e.getMessage());
		}				
		
		return filtriraniMenadzeri;
	}
	
	public ArrayList<KvarljivaRoba> proveriKvarljivuRobu(String filter) {
		ArrayList<KvarljivaRoba> filtriranaRoba = new ArrayList<KvarljivaRoba>();  //sve isto kao u prethodnim metodama
		try {
			KvarljivaRobaController krc = new KvarljivaRobaController();
			List<KvarljivaRoba> kvarljivaRoba = krc.ucitaj();
			
			filtriranaRoba = kvarljivaRoba.stream().filter(x -> x.getNaziv().toLowerCase().contains(filter.toLowerCase()) || 
					String.valueOf(x.getCena()).contains(filter) ||
					x.getModel().toLowerCase().contains(filter.toLowerCase()) || 
					x.getProizvodjac().toLowerCase().contains(filter.toLowerCase()) ||
					x.getOpis().toLowerCase().contains(filter.toLowerCase()) ||
					String.valueOf(x.getRokTrajanja()).contains(filter) ||
					x.getUputstvo().toLowerCase().contains(filter.toLowerCase()))
					.collect(Collectors.toCollection(ArrayList::new));
		}catch(Exception e) {
			System.out.println("Doslo je do greske." + e.getMessage());
		}
		return filtriranaRoba;
	}
	
	public ArrayList<KucniAparati> proveriKucneAparate(String filter) {
		ArrayList<KucniAparati> filtriraniKucniAparati = new ArrayList<KucniAparati>();
		try {
			KucniAparatiController kac = new KucniAparatiController();                   //takodje sve isto kao u prethodnim metodama
			List<KucniAparati> kucniAparati = kac.ucitaj();
			
			filtriraniKucniAparati = kucniAparati.stream().filter(x -> x.getNaziv().toLowerCase().contains(filter.toLowerCase()) || 
					String.valueOf(x.getCena()).contains(filter) ||
					x.getModel().toLowerCase().contains(filter.toLowerCase()) || 
					x.getProizvodjac().toLowerCase().contains(filter.toLowerCase()) ||
					x.getOpis().toLowerCase().contains(filter.toLowerCase()) ||
					String.valueOf(x.getDimenzije().getSirina()).contains(filter) ||
					String.valueOf(x.getDimenzije().getVisina()).contains(filter) ||
					String.valueOf(x.getDimenzije().getDuzina()).contains(filter) ||
					String.valueOf(x.getRadniNapon()).contains(filter) ||
					String.valueOf(x.getNominalnaSnaga()).contains(filter))
					.collect(Collectors.toCollection(ArrayList::new));
		}catch(Exception e) {
			System.out.println("Doslo je do greske." + e.getMessage());
		}
		return filtriraniKucniAparati;
	}
}
