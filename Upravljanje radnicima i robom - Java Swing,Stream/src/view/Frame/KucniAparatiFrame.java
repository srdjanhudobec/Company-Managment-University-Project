package view.Frame;

import java.awt.Dimension;
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

import controller.KucniAparatiController;
import interfaces.IFrame;
import interfaces.IInit;
import model.Roba.Dimenzije;
import model.Roba.KucniAparati;
import view.TableModels.KucniAparatiTableModel;

public class KucniAparatiFrame extends JFrame implements IFrame<KucniAparati>,IInit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KucniAparatiFrame() throws HeadlessException {
		super();
	}

	public KucniAparatiFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public KucniAparatiFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public KucniAparatiFrame(String title) throws HeadlessException {
		super(title);
	}
	
	private SpringLayout layout = new SpringLayout();
	private JLabel nazivLabel = new JLabel("Naziv: ");
	private JLabel cenaLabel = new JLabel("Cena: ");
	private JLabel modelLabel = new JLabel("Model: ");
	private JLabel proizvodjacLabel = new JLabel("Proizvodjac: ");
	private JLabel opisLabel = new JLabel("Opis: ");
	private JLabel dimenzijeLabel = new JLabel("Dimenzije");
	private JLabel sirinaLabel = new JLabel("Sirina: ");
	private JLabel visinaLabel = new JLabel("Visina: ");
	private JLabel duzinaLabel = new JLabel("Duzina: ");
	private JLabel radniNaponLabel = new JLabel("Radni napon: ");
	private JLabel nominalnaSnagaLabel = new JLabel("Nominalna snaga: ");
	
	private JTextField nazivInput = new JTextField(30);
	private JSpinner cenaSpinner = new JSpinner(new SpinnerNumberModel(0,0,10000.0,0.1)); //ovim smo osigurali da se unosi broj
	private JTextField modelInput = new JTextField(30);
	private JTextField proizvodjacInput = new JTextField(30);
	private JTextField opisInput = new JTextField(30);
	private JSpinner sirinaSpinner = new JSpinner(new SpinnerNumberModel(0,0,100,0.1)); //ovim smo osigurali da se unosi broj
	private JSpinner visinaSpinner = new JSpinner(new SpinnerNumberModel(0,0,100,0.1)); //ovim smo osigurali da se unosi broj
	private JSpinner duzinaSpinner = new JSpinner(new SpinnerNumberModel(0,0,100,0.1)); //ovim smo osigurali da se unosi broj
	private JSpinner radniNaponSpinner = new JSpinner(new SpinnerNumberModel(0,0,100,1)); //ovim smo osigurali da se unosi broj
	private JSpinner nominalnaSnagaSpinner = new JSpinner(new SpinnerNumberModel(0,0,100,1)); //ovim smo osigurali da se unosi broj
	
	private JButton dodajButton = new JButton("Dodaj");
	private JButton ukloniButton =  new JButton("Ukloni");
	private JButton izmeniButton = new JButton("Izmeni");
	
	private KucniAparatiTableModel kucniAparatiModel = new KucniAparatiTableModel();
	private JTable tabela = new JTable(kucniAparatiModel);
	private JScrollPane scrollPane = new JScrollPane(tabela);
	
	public void addActionListener(ActionListener listener) {
		this.dodajButton.addActionListener(listener);
	}
	
	public KucniAparati kreiraj() {
		return new KucniAparati(nazivInput.getText(),(double)cenaSpinner.getValue(),modelInput.getText(),proizvodjacInput.getText(),opisInput.getText(),new Dimenzije((double)sirinaSpinner.getValue(),(double)visinaSpinner.getValue(),(double)duzinaSpinner.getValue()),(int)radniNaponSpinner.getValue(),(int)nominalnaSnagaSpinner.getValue());
	}
	
	public void removeActionListener(ActionListener listener) {
		this.ukloniButton.addActionListener(listener);
	}
	
	public void updateActionListener(ActionListener listener) {
		this.izmeniButton.addActionListener(listener);
	}
	
	public void ucitaj() {
		KucniAparatiController kac = new KucniAparatiController();
		List<KucniAparati> kucniAparati = kac.ucitaj();
		for(KucniAparati k:kucniAparati) {
			kucniAparatiModel.dodajPostojece(k);
		}
	}

	public void init() {
		
		this.setLayout(layout);
		
		this.setSize(1130,820);
		this.setTitle("Kucni aparati:");
		
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
		this.add(dimenzijeLabel);
		this.add(sirinaLabel);
		this.add(sirinaSpinner);
		this.add(visinaLabel);
		this.add(visinaSpinner);
		this.add(duzinaLabel);
		this.add(duzinaSpinner);
		this.add(radniNaponLabel);
		this.add(radniNaponSpinner);
		this.add(nominalnaSnagaLabel);
		this.add(nominalnaSnagaSpinner);
		
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
		
		this.layout.putConstraint(SpringLayout.WEST, dimenzijeLabel, 290,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, dimenzijeLabel, 20,SpringLayout.SOUTH, opisLabel);

		this.layout.putConstraint(SpringLayout.WEST, sirinaLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, sirinaLabel, 20,SpringLayout.SOUTH, dimenzijeLabel);
		this.layout.putConstraint(SpringLayout.WEST, sirinaSpinner, 10,SpringLayout.EAST, sirinaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, sirinaSpinner, 20,SpringLayout.SOUTH, dimenzijeLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, visinaLabel, 50,SpringLayout.EAST, sirinaSpinner);
		this.layout.putConstraint(SpringLayout.NORTH, visinaLabel, 20,SpringLayout.SOUTH, dimenzijeLabel);
		this.layout.putConstraint(SpringLayout.WEST, visinaSpinner, 10,SpringLayout.EAST, visinaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, visinaSpinner, 20,SpringLayout.SOUTH, dimenzijeLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, duzinaLabel, 50,SpringLayout.EAST, visinaSpinner);
		this.layout.putConstraint(SpringLayout.NORTH, duzinaLabel, 20,SpringLayout.SOUTH, dimenzijeLabel);
		this.layout.putConstraint(SpringLayout.WEST, duzinaSpinner, 10,SpringLayout.EAST, duzinaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, duzinaSpinner, 20,SpringLayout.SOUTH, dimenzijeLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, radniNaponLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, radniNaponLabel, 40,SpringLayout.SOUTH, sirinaLabel);
		this.layout.putConstraint(SpringLayout.WEST, radniNaponSpinner, 3,SpringLayout.EAST, radniNaponLabel);
		this.layout.putConstraint(SpringLayout.NORTH, radniNaponSpinner, 36,SpringLayout.SOUTH, sirinaSpinner);
		
		this.layout.putConstraint(SpringLayout.WEST, nominalnaSnagaLabel, 118,SpringLayout.EAST, radniNaponSpinner);
		this.layout.putConstraint(SpringLayout.NORTH, nominalnaSnagaLabel, 41,SpringLayout.SOUTH, visinaLabel);
		this.layout.putConstraint(SpringLayout.WEST, nominalnaSnagaSpinner, 5,SpringLayout.EAST, nominalnaSnagaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, nominalnaSnagaSpinner, 36,SpringLayout.SOUTH, duzinaSpinner);
		
		this.layout.putConstraint(SpringLayout.WEST, dodajButton, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, dodajButton, 20,SpringLayout.SOUTH, nominalnaSnagaLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, izmeniButton, 20,SpringLayout.EAST, dodajButton);
		this.layout.putConstraint(SpringLayout.NORTH, izmeniButton, 19,SpringLayout.SOUTH, nominalnaSnagaLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, ukloniButton, 20,SpringLayout.EAST, izmeniButton);
		this.layout.putConstraint(SpringLayout.NORTH, ukloniButton, 19,SpringLayout.SOUTH, nominalnaSnagaLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, scrollPane, 80,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, scrollPane, 20,SpringLayout.SOUTH, dodajButton);	
		
		scrollPane.setPreferredSize(new Dimension(950, 370)); 
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ucitaj(); //ucitava sve postojece kucne aparate iz fajla i smesta ih u tabelu
		
		//dodavanje kucnih aparata 
		this.addActionListener(e -> {
			kucniAparatiModel.dodaj(this.kreiraj());
		});
				
		//uklanjanje kucnih aparata 
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
				this.removeActionListener(er ->{
					int[] selektovaniRedovi = tabela.getSelectedRows();
		            
		            for (int i = selektovaniRedovi.length - 1; i >= 0; i--) {
		            	kucniAparatiModel.ukloni(selektovaniRedovi[i]);
		            }
				});
			}else if(tabela.getSelectedRow() < 0) {
				nazivInput.setText("");			//ako je sve obrisano,postavi vrednosti polja na default vrednosti
				cenaSpinner.setValue(0.0);
				modelInput.setText("");
				proizvodjacInput.setText("");
				opisInput.setText("");
				sirinaSpinner.setValue(0.0);
				visinaSpinner.setValue(0.0);
				duzinaSpinner.setValue(0.0);
				radniNaponSpinner.setValue(0);
				nominalnaSnagaSpinner.setValue(0.0);
			}
		});
				
		//izmena kucnih aparata
		tabela.getSelectionModel().addListSelectionListener(e -> {	//prilikom selekta,popunjavaju se polja u formi sa vrednostima selektovanog
			if(!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
				KucniAparati selektovan = kucniAparatiModel.dobavi(tabela.getSelectedRow());
				nazivInput.setText(selektovan.getNaziv());
				cenaSpinner.setValue(selektovan.getCena());
				modelInput.setText(selektovan.getModel());
				proizvodjacInput.setText(selektovan.getProizvodjac());
				opisInput.setText(selektovan.getOpis());
				sirinaSpinner.setValue(selektovan.getDimenzije().getSirina());
				visinaSpinner.setValue(selektovan.getDimenzije().getVisina());
				duzinaSpinner.setValue(selektovan.getDimenzije().getDuzina());
				radniNaponSpinner.setValue(selektovan.getRadniNapon());
				nominalnaSnagaSpinner.setValue(selektovan.getNominalnaSnaga());
				this.updateActionListener(eu -> {
					kucniAparatiModel.izmeni(tabela.getSelectedRow(), this.kreiraj());
				});
			}
		});

		this.setVisible(true);
	}
}
