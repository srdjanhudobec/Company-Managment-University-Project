package view.Frame;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import controller.MagacionerController;
import interfaces.IFrame;
import interfaces.IInit;
import model.Radnik.Magacioner;
import view.TableModels.MagacioneriTableModel;


public class MagacionerFrame extends JFrame implements IFrame<Magacioner>,IInit{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MagacionerFrame() throws HeadlessException {
		super();
	}

	public MagacionerFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public MagacionerFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public MagacionerFrame(String title) throws HeadlessException {
		super(title);
	}

	private JLabel imeLabel = new JLabel("Ime: ");
	private JLabel prezimeLabel = new JLabel("Prezime: ");
	private JLabel biografijaLabel = new JLabel("Biografija: ");
	private JLabel visinaPlateLabel = new JLabel("Visina plate: ");
	private JLabel radnoMestoLabel = new JLabel("Radno mesto: ");
	private JLabel odmoriLabel = new JLabel("Dani odmora: ");
	
	private JTextField imeInput = new JTextField(30);
	private JTextField prezimeInput = new JTextField(30);
	private JTextField biografijaInput = new JTextField(30);
	private JSpinner visinaPlateSpinner = new JSpinner(new SpinnerNumberModel(0,0,10000.0,0.1)); //ovim smo osigurali da se unosi broj
	private JTextField radnoMestoInput = new JTextField(30);
	private JSpinner odmoriSpinner = new JSpinner(new SpinnerNumberModel(0,0,30,1)); //ovim smo osigurali da se unosi broj


	private SpringLayout layout = new SpringLayout();
	
	private JButton dodajButton = new JButton("Dodaj");
	private JButton ukloniButton = new JButton("Ukloni");
	private JButton izmeniButton = new JButton("Izmeni");

	private MagacioneriTableModel magacioneriModel = new MagacioneriTableModel();
	private JTable tabela = new JTable(magacioneriModel);
	private JScrollPane scrollPane = new JScrollPane(tabela);
	
	
	public void addActionListener(ActionListener listener) {
		this.dodajButton.addActionListener(listener);
	}
	
	public Magacioner kreiraj() {
		return new Magacioner(imeInput.getText(),prezimeInput.getText(),biografijaInput.getText(),(double) visinaPlateSpinner.getValue(),radnoMestoInput.getText(),(int)odmoriSpinner.getValue());
	}
	
	public void removeActionListener(ActionListener listener) {
		this.ukloniButton.addActionListener(listener);
	}
	
	public void updateActionListener(ActionListener listener) {
		this.izmeniButton.addActionListener(listener);
	}
	
	public void ucitaj() {
		MagacionerController mc = new MagacionerController();
		List<Magacioner> magacioneri = mc.ucitaj();
		for(Magacioner m:magacioneri) {
			magacioneriModel.dodajPostojece(m);
		}
	}
	
	public void init() {
		this.setSize(600,800);
		this.setTitle("Magacioneri");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(layout);
		tabela.setFillsViewportHeight(true);
		this.add(imeLabel);
		this.add(imeInput);
		this.add(prezimeLabel);
		this.add(prezimeInput);
		this.add(biografijaLabel);
		this.add(biografijaInput);
		this.add(visinaPlateLabel);
		this.add(visinaPlateSpinner);
		this.add(radnoMestoLabel);
		this.add(radnoMestoInput);
		this.add(odmoriLabel);
		this.add(odmoriSpinner);
		this.add(dodajButton);
		this.add(ukloniButton);
		this.add(izmeniButton);
		this.add(scrollPane);
		
		this.layout.putConstraint(SpringLayout.WEST, imeLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, imeLabel, 40,SpringLayout.NORTH, this);
		this.layout.putConstraint(SpringLayout.WEST, imeInput, 60,SpringLayout.EAST, imeLabel);
		this.layout.putConstraint(SpringLayout.NORTH, imeInput, 40,SpringLayout.NORTH, this);

		this.layout.putConstraint(SpringLayout.WEST, prezimeLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, prezimeLabel, 20,SpringLayout.SOUTH, imeLabel);
		this.layout.putConstraint(SpringLayout.WEST, prezimeInput, 33,SpringLayout.EAST, prezimeLabel);
		this.layout.putConstraint(SpringLayout.NORTH, prezimeInput, 15,SpringLayout.SOUTH, imeInput);
		
		this.layout.putConstraint(SpringLayout.WEST, biografijaLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, biografijaLabel, 20,SpringLayout.SOUTH, prezimeLabel);
		this.layout.putConstraint(SpringLayout.WEST, biografijaInput, 25,SpringLayout.EAST, biografijaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, biografijaInput, 17,SpringLayout.SOUTH, prezimeInput);
		
		this.layout.putConstraint(SpringLayout.WEST, visinaPlateLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, visinaPlateLabel, 20,SpringLayout.SOUTH, biografijaLabel);
		this.layout.putConstraint(SpringLayout.WEST, visinaPlateSpinner, 16,SpringLayout.EAST, visinaPlateLabel);
		this.layout.putConstraint(SpringLayout.NORTH, visinaPlateSpinner, 17,SpringLayout.SOUTH, biografijaInput);
		
		this.layout.putConstraint(SpringLayout.WEST, radnoMestoLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, radnoMestoLabel, 20,SpringLayout.SOUTH, visinaPlateLabel);
		this.layout.putConstraint(SpringLayout.WEST, radnoMestoInput, 6,SpringLayout.EAST, radnoMestoLabel);
		this.layout.putConstraint(SpringLayout.NORTH, radnoMestoInput, 16,SpringLayout.SOUTH, visinaPlateSpinner);
		
		this.layout.putConstraint(SpringLayout.WEST, odmoriLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, odmoriLabel, 20,SpringLayout.SOUTH, radnoMestoLabel);
		this.layout.putConstraint(SpringLayout.WEST, odmoriSpinner, 8,SpringLayout.EAST, odmoriLabel);
		this.layout.putConstraint(SpringLayout.NORTH, odmoriSpinner, 17,SpringLayout.SOUTH, radnoMestoInput);
		
		this.layout.putConstraint(SpringLayout.WEST, dodajButton, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, dodajButton, 20,SpringLayout.SOUTH, odmoriLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, izmeniButton, 20,SpringLayout.EAST, dodajButton);
		this.layout.putConstraint(SpringLayout.NORTH, izmeniButton, 19,SpringLayout.SOUTH, odmoriLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, ukloniButton, 20,SpringLayout.EAST, izmeniButton);
		this.layout.putConstraint(SpringLayout.NORTH, ukloniButton, 19,SpringLayout.SOUTH, odmoriLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, scrollPane, 80,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, scrollPane, 20,SpringLayout.SOUTH, dodajButton);
		
		ucitaj();
		
		//dodavanje magacionera
		this.addActionListener(e -> {
			magacioneriModel.dodaj(this.kreiraj());
		});
		
		//uklanjanje magacionera
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
				this.removeActionListener(er ->{
					int[] selektovaniRedovi = tabela.getSelectedRows();
		    
		            for (int i = selektovaniRedovi.length - 1; i >= 0; i--) {
		                magacioneriModel.ukloni(selektovaniRedovi[i]);
		            }
				});
			}else if(tabela.getSelectedRow() < 0) {
				imeInput.setText("");
				prezimeInput.setText("");
				biografijaInput.setText("");
				visinaPlateSpinner.setValue(0.0);
				radnoMestoInput.setText("");
				odmoriSpinner.setValue(0);
			}
		});
		
		//izmena magacionera
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
				Magacioner selektovani = magacioneriModel.dobavi(tabela.getSelectedRow());
				imeInput.setText(selektovani.getIme());
				prezimeInput.setText(selektovani.getPrezime());
				biografijaInput.setText(selektovani.getBiografija());
				visinaPlateSpinner.setValue(selektovani.getVisinaPlate());
				radnoMestoInput.setText(selektovani.getRadnoMesto());
				odmoriSpinner.setValue(selektovani.getOdmori());
				this.updateActionListener(eu -> {
					magacioneriModel.izmeni(tabela.getSelectedRow(), this.kreiraj());
				});
			}
		});
		
		this.setVisible(true);
	}
}
