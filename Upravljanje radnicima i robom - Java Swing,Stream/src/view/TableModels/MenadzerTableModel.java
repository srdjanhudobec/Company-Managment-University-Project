package view.TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.MenadzerController;
import interfaces.ITableModel;
import model.Radnik.Menadzer;

public class MenadzerTableModel extends AbstractTableModel implements ITableModel<Menadzer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Menadzer> menadzeri = new ArrayList<Menadzer>();
	MenadzerController mc = new MenadzerController();
	
	@Override
	public int getRowCount() {
		return this.menadzeri.size();
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if(columnIndex == 0) {
			return "Ime";
		}
		if(columnIndex == 1) {
			return "Prezime";
		}
		if(columnIndex == 2) {
			return "Biografija";
		}
		if(columnIndex == 3) {
			return "Visina plate";
		}
		if(columnIndex == 4) {
			return "Radnici";
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Menadzer m = this.menadzeri.get(rowIndex);
		if(columnIndex == 0) {
			return m.getIme();
		}
		if(columnIndex == 1) {
			return m.getPrezime();
		}
		if(columnIndex == 2) {
			return m.getBiografija();
		}
		if(columnIndex == 3) {
			return m.getVisinaPlate() + " rsd";
		}
		if(columnIndex == 4) {
			return m.getDepartman().size() + " radnika";
		}
		return null;
	}
	
	public void dodajPostojece(Menadzer m) {
		this.menadzeri.add(m);
		this.fireTableRowsInserted(this.menadzeri.size() - 1, this.menadzeri.size());
	}
	
	public void dodaj(Menadzer m) {
		this.menadzeri.add(m);
		this.fireTableRowsInserted(this.menadzeri.size() - 1, this.menadzeri.size());
		mc.upisi(m);

	}
	
	public void ukloni(int index) {
		this.menadzeri.remove(index);
		this.fireTableRowsDeleted(index, index + 1);
		mc.ukloni(index);
	
	}
	
	public void izmeni(int index,Menadzer m) {
		this.menadzeri.set(index, m);
		this.fireTableRowsUpdated(index, index);
		mc.izmeni(index, m);
	}
	
	public Menadzer dobavi(int index) {
		return this.menadzeri.get(index);
	}
	
	public void obrisiSve() {
        this.menadzeri.clear();
        fireTableDataChanged();
    }
}
