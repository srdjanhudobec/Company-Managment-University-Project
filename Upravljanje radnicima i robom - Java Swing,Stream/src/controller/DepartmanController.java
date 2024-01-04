package controller;

import javax.swing.DefaultListModel;

import model.Radnik.*;

public class DepartmanController {
		
	public DepartmanController() {}
	
	public boolean postojiRadnik(DefaultListModel<Radnik> model, Radnik radnik) { //metoda proverava da li prosledjeni radnik postoji u list modelu koju mu je takodje prosledjen
	    if(model.getSize() > 0) {
			for (int i = 0; i < model.getSize(); i++) {
		        Radnik existingRadnik = model.getElementAt(i);
		        if (existingRadnik.getIme().equals(radnik.getIme()) && existingRadnik.getPrezime().equals(radnik.getPrezime())) {
		            return true;						//ukoliko radnik postoji,vracamo true,ako ne postoji u listi vracamo false
		        }
		    }
	    }
	    
	    return false;
	}
}
