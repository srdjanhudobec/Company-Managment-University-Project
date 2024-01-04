package view.TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.MagacionerController;
import interfaces.ITableModel;
import model.Radnik.Magacioner;

public class MagacioneriTableModel extends AbstractTableModel implements ITableModel<Magacioner>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Magacioner> magacioneri = new ArrayList<Magacioner>();
	MagacionerController mc = new MagacionerController();
	
	@Override
	public int getRowCount() {
		return this.magacioneri.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
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
			return "Radno mesto";
		}
		if(columnIndex == 5) {
			return "Odmor";
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Magacioner m = magacioneri.get(rowIndex);
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
			return m.getRadnoMesto();
		}
		if(columnIndex == 5) {
			return m.getOdmori() + " dana";
		}
		return null;
	}

	public void dodajPostojece(Magacioner m) {
		this.magacioneri.add(m);
		this.fireTableRowsInserted(this.magacioneri.size() - 1, this.magacioneri.size());
	}
	
	public void dodaj(Magacioner m) {
		this.magacioneri.add(m);
		this.fireTableRowsInserted(this.magacioneri.size() - 1, this.magacioneri.size());
		mc.upisi(m);
	}
	
	public void ukloni(int index) {
		this.magacioneri.remove(index);
		this.fireTableRowsDeleted(index, index + 1);
		mc.ukloni(index);
	
	}
	
	public void izmeni(int index,Magacioner m) {
		this.magacioneri.set(index, m);
		this.fireTableRowsUpdated(index, index);
		mc.izmeni(index, m);
	}
	
	public Magacioner dobavi(int index) {
		return this.magacioneri.get(index);
	}
	
	public void obrisiSve() {
        this.magacioneri.clear();
        fireTableDataChanged();
    }
}
