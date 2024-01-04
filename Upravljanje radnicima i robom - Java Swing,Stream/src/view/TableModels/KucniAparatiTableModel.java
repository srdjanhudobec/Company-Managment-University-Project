package view.TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.KucniAparatiController;
import interfaces.ITableModel;
import model.Roba.KucniAparati;

public class KucniAparatiTableModel extends AbstractTableModel implements ITableModel<KucniAparati>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<KucniAparati> kucniAparati = new ArrayList<KucniAparati>();
	KucniAparatiController kac = new KucniAparatiController();
	
	@Override
	public int getRowCount() {
		return this.kucniAparati.size();
	}

	@Override
	public int getColumnCount() {
		return 10;
	}
	
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
			return "Sirina";
		}
		if(columnIndex == 6) {
			return "Visina";
		}
		if(columnIndex == 7) {
			return "Duzina";
		}
		if(columnIndex == 8) {
			return "Radni napon";
		}
		if(columnIndex == 9) {
			return "Nominalna snaga";
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		KucniAparati k = kucniAparati.get(rowIndex);
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
			return k.getDimenzije().getSirina();
		}
		if(columnIndex == 6) {
			return k.getDimenzije().getVisina();
		}
		if(columnIndex == 7) {
			return k.getDimenzije().getDuzina();
		}
		if(columnIndex == 8) {
			return k.getRadniNapon();
		}
		if(columnIndex == 9) {
			return k.getNominalnaSnaga();
		}
		return null;
	}
	
	public void dodajPostojece(KucniAparati ka) {
		this.kucniAparati.add(ka);
		this.fireTableRowsInserted(this.kucniAparati.size() - 1, this.kucniAparati.size());
	}
	
	public void dodaj(KucniAparati ka) {
		this.kucniAparati.add(ka);
		this.fireTableRowsInserted(this.kucniAparati.size() - 1, this.kucniAparati.size());
		kac.upisi(ka);
	}
	
	public void ukloni(int index) {
		this.kucniAparati.remove(index);
		this.fireTableRowsDeleted(index, index + 1);
		kac.ukloni(index);
	}
	
	public void izmeni(int index,KucniAparati ka) {
		this.kucniAparati.set(index, ka);
		this.fireTableRowsUpdated(index, index);
		kac.izmeni(index, ka);
	}
	
	public KucniAparati dobavi(int index) {
		return this.kucniAparati.get(index);
	}
	
	public void obrisiSve() {
        this.kucniAparati.clear();
        fireTableDataChanged();
    }
}
