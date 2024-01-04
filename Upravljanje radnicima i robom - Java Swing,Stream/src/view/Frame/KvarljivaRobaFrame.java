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

import controller.KvarljivaRobaController;
import interfaces.IFrame;
import interfaces.IInit;
import model.Roba.KvarljivaRoba;
import view.TableModels.KvarljivaRobaTableModel;

public class KvarljivaRobaFrame extends JFrame implements IFrame<KvarljivaRoba>,IInit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KvarljivaRobaFrame() throws HeadlessException {
		super();
	}

	public KvarljivaRobaFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public KvarljivaRobaFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public KvarljivaRobaFrame(String title) throws HeadlessException {
		super(title);
	}
	
	private SpringLayout layout = new SpringLayout();
	private JLabel nazivLabel = new JLabel("Naziv: ");
	private JLabel cenaLabel = new JLabel("Cena: ");
	private JLabel modelLabel = new JLabel("Model: ");
	private JLabel proizvodjacLabel = new JLabel("Proizvodjac: ");
	private JLabel opisLabel = new JLabel("Opis: ");
	private JLabel rokTrajanjaLabel = new JLabel("Rok trajanja: ");
	private JLabel uputstvoLabel = new JLabel("Uputstvo: ");
	
	private JTextField nazivInput = new JTextField(30);
	private JSpinner cenaSpinner = new JSpinner(new SpinnerNumberModel(0,0,10000.0,0.1)); //ovim smo osigurali da se unosi broj
	private JTextField modelInput = new JTextField(30);
	private JTextField proizvodjacInput = new JTextField(30);
	private JTextField opisInput = new JTextField(30);
	private JSpinner rokTrajanjaSpinner = new JSpinner(new SpinnerNumberModel(0,0,100,1)); //ovim smo osigurali da se unosi broj
	private JTextField uputstvoInput = new JTextField(30);
	
	private JButton dodajButton = new JButton("Dodaj");
	private JButton ukloniButton =  new JButton("Ukloni");
	private JButton izmeniButton = new JButton("Izmeni");
	
	private KvarljivaRobaTableModel kvarljivaRobaModel = new KvarljivaRobaTableModel();
	private JTable tabela = new JTable(kvarljivaRobaModel);
	private JScrollPane scrollPane = new JScrollPane(tabela);
	
	public void addActionListener(ActionListener listener) {
		this.dodajButton.addActionListener(listener);
	}
	
	public KvarljivaRoba kreiraj() {
		return new KvarljivaRoba(nazivInput.getText(),(double)cenaSpinner.getValue(),modelInput.getText(),proizvodjacInput.getText(),opisInput.getText(),(int)rokTrajanjaSpinner.getValue(),uputstvoInput.getText());
	}
	
	public void removeActionListener(ActionListener listener) {
		this.ukloniButton.addActionListener(listener);
	}
	
	public void updateActionListener(ActionListener listener) {
		this.izmeniButton.addActionListener(listener);
	}
	
	public void ucitaj() {
		KvarljivaRobaController krc = new KvarljivaRobaController();
		List<KvarljivaRoba> kvarljivaRoba = krc.ucitaj();
		for(KvarljivaRoba k:kvarljivaRoba) {
			kvarljivaRobaModel.dodajPostojece(k);
		}
	}
	
	public void init() {

		this.setLayout(layout);
		
		this.setSize(650,820);
		this.setTitle("Kvarljiva roba");
		
		this.add(nazivLabel);
		this.add(nazivInput);
		this.add(cenaLabel);
		this.add(cenaSpinner);
		this.add(modelLabel);
		this.add(modelInput);
		this.add(proizvodjacLabel);
		this.add(proizvodjacInput);
		this.add(opisLabel);
		this.add(opisInput);
		this.add(rokTrajanjaLabel);
		this.add(rokTrajanjaSpinner);
		this.add(uputstvoLabel);
		this.add(uputstvoInput);
		this.add(dodajButton);
		this.add(ukloniButton);
		this.add(izmeniButton);
		tabela.setFillsViewportHeight(true);
		this.add(scrollPane);
		
		this.layout.putConstraint(SpringLayout.WEST, nazivLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, nazivLabel, 40,SpringLayout.NORTH, this);
		this.layout.putConstraint(SpringLayout.WEST, nazivInput, 37,SpringLayout.EAST, nazivLabel);
		this.layout.putConstraint(SpringLayout.NORTH, nazivInput, 40,SpringLayout.NORTH, this);

		this.layout.putConstraint(SpringLayout.WEST, cenaLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, cenaLabel, 20,SpringLayout.SOUTH, nazivLabel);
		this.layout.putConstraint(SpringLayout.WEST, cenaSpinner, 40,SpringLayout.EAST, cenaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, cenaSpinner, 15,SpringLayout.SOUTH, nazivInput);
		
		this.layout.putConstraint(SpringLayout.WEST, modelLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, modelLabel, 20,SpringLayout.SOUTH, cenaLabel);
		this.layout.putConstraint(SpringLayout.WEST, modelInput, 35,SpringLayout.EAST, modelLabel);
		this.layout.putConstraint(SpringLayout.NORTH, modelInput, 17,SpringLayout.SOUTH, cenaSpinner);
		
		this.layout.putConstraint(SpringLayout.WEST, proizvodjacLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, proizvodjacLabel, 20,SpringLayout.SOUTH, modelLabel);
		this.layout.putConstraint(SpringLayout.WEST, proizvodjacInput, 3,SpringLayout.EAST, proizvodjacLabel);
		this.layout.putConstraint(SpringLayout.NORTH, proizvodjacInput, 17,SpringLayout.SOUTH, modelInput);
		
		this.layout.putConstraint(SpringLayout.WEST, opisLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, opisLabel, 20,SpringLayout.SOUTH, proizvodjacLabel);
		this.layout.putConstraint(SpringLayout.WEST, opisInput, 45,SpringLayout.EAST, opisLabel);
		this.layout.putConstraint(SpringLayout.NORTH, opisInput, 16,SpringLayout.SOUTH, proizvodjacInput);
		
		this.layout.putConstraint(SpringLayout.WEST, rokTrajanjaLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, rokTrajanjaLabel, 20,SpringLayout.SOUTH, opisLabel);
		this.layout.putConstraint(SpringLayout.WEST, rokTrajanjaSpinner, 5,SpringLayout.EAST, rokTrajanjaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, rokTrajanjaSpinner, 14,SpringLayout.SOUTH, opisInput);
		
		this.layout.putConstraint(SpringLayout.WEST, uputstvoLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, uputstvoLabel, 20,SpringLayout.SOUTH, rokTrajanjaLabel);
		this.layout.putConstraint(SpringLayout.WEST, uputstvoInput, 21,SpringLayout.EAST, uputstvoLabel);
		this.layout.putConstraint(SpringLayout.NORTH, uputstvoInput, 16,SpringLayout.SOUTH, rokTrajanjaSpinner);
		
		this.layout.putConstraint(SpringLayout.WEST, dodajButton, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, dodajButton, 20,SpringLayout.SOUTH, uputstvoLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, izmeniButton, 20,SpringLayout.EAST, dodajButton);
		this.layout.putConstraint(SpringLayout.NORTH, izmeniButton, 19,SpringLayout.SOUTH, uputstvoLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, ukloniButton, 20,SpringLayout.EAST, izmeniButton);
		this.layout.putConstraint(SpringLayout.NORTH, ukloniButton, 19,SpringLayout.SOUTH, uputstvoLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, scrollPane, 80,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, scrollPane, 20,SpringLayout.SOUTH, dodajButton);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ucitaj();
		
		//dodavanje kvarljive robe
		this.addActionListener(e -> {
			kvarljivaRobaModel.dodaj(this.kreiraj());
		});
				
		//uklanjanje kvarljive robe
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
				this.removeActionListener(er ->{
					int[] selektovaniRedovi = tabela.getSelectedRows();
		            
		            for (int i = selektovaniRedovi.length - 1; i >= 0; i--) {
		            	kvarljivaRobaModel.ukloni(selektovaniRedovi[i]);
		            }
				});
			}else if(tabela.getSelectedRow() < 0) {
				nazivInput.setText("");
				cenaSpinner.setValue(0.0);
				modelInput.setText("");
				proizvodjacInput.setText("");
				opisInput.setText("");
				rokTrajanjaSpinner.setValue(0);
				uputstvoInput.setText("");
			}
		});
				
		//izmena kvarljive robe
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
				KvarljivaRoba selektovana = kvarljivaRobaModel.dobavi(tabela.getSelectedRow());
				nazivInput.setText(selektovana.getNaziv());
				cenaSpinner.setValue(selektovana.getCena());
				modelInput.setText(selektovana.getModel());
				proizvodjacInput.setText(selektovana.getProizvodjac());
				opisInput.setText(selektovana.getOpis());
				rokTrajanjaSpinner.setValue(selektovana.getRokTrajanja());
				uputstvoInput.setText(selektovana.getUputstvo());
				this.updateActionListener(eu -> {
					kvarljivaRobaModel.izmeni(tabela.getSelectedRow(), this.kreiraj());
				});
			}
		});

		this.setVisible(true);
	}
}
