 package view.TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.KvarljivaRobaController;
import interfaces.ITableModel;
import model.Roba.*;

public class KvarljivaRobaTableModel extends AbstractTableModel implements ITableModel<KvarljivaRoba>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<KvarljivaRoba> kvarljivaRoba = new ArrayList<KvarljivaRoba>();
	KvarljivaRobaController krc = new KvarljivaRobaController();
	
	@Override
	public int getRowCount() {
		return this.kvarljivaRoba.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		KvarljivaRoba k = kvarljivaRoba.get(rowIndex);
		if(columnIndex == 0) {
			return k.getNaziv();
		}
		if(columnIndex == 1) {
			return k.getCena() + " rsd";
		}
		if(columnIndex == 2) {
			return k.getModel();
		}
		if(columnIndex == 3) {
			return k.getProizvodjac();
		}
		if(columnIndex == 4) {
			return k.getOpis();
		}
		if(columnIndex == 5) {
			return k.getRokTrajanja() + " dana";
		}
		if(columnIndex == 6) {
			return k.getUputstvo();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		if(columnIndex == 0) {
			return "Naziv";
		}
		if(columnIndex == 1) {
			return "Cena";
		}
		if(columnIndex == 2) {
			return "Model";
		}
		if(columnIndex == 3) {
			return "Proizvodjac";
		}
		if(columnIndex == 4) {
			return "Opis";
		}
		if(columnIndex == 5) {
			return "Rok trajanja";
		}
		if(columnIndex == 6) {
			return "Uputstvo";
		}
		return null;
	}

	public void dodajPostojece(KvarljivaRoba k) {
		this.kvarljivaRoba.add(k);
		this.fireTableRowsInserted(this.kvarljivaRoba.size() - 1, this.kvarljivaRoba.size());
	}
	
	public void dodaj(KvarljivaRoba k) {
		this.kvarljivaRoba.add(k);
		this.fireTableRowsInserted(this.kvarljivaRoba.size() - 1, this.kvarljivaRoba.size());
		krc.upisi(k);
	}
	
	public void ukloni(int index) {
		this.kvarljivaRoba.remove(index);
		this.fireTableRowsDeleted(index, index + 1);
		krc.ukloni(index);
	}
	
	public void izmeni(int index,KvarljivaRoba k) {
		this.kvarljivaRoba.set(index, k);
		this.fireTableRowsUpdated(index, index);
		krc.izmeni(index, k);
	}
	
	public KvarljivaRoba dobavi(int index) {
		return this.kvarljivaRoba.get(index);
	}
	
	public void obrisiSve() {
        this.kvarljivaRoba.clear();
        fireTableDataChanged();
    }
}
