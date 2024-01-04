package view.Frame;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import model.Radnik.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import controller.DepartmanController;
import controller.MagacionerController;
import controller.MenadzerController;
import interfaces.IFrame;
import interfaces.IInit;
import view.TableModels.MenadzerTableModel;

public class MenadzerFrame extends JFrame implements IFrame<Menadzer>,IInit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenadzerFrame() throws HeadlessException {
		super();
	}

	public MenadzerFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public MenadzerFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public MenadzerFrame(String title) throws HeadlessException {
		super(title);
	}
	
	private JLabel imeLabel = new JLabel("Ime: ");
	private JLabel prezimeLabel = new JLabel("Prezime: ");
	private JLabel biografijaLabel = new JLabel("Biografija: ");
	private JLabel visinaPlateLabel = new JLabel("Visina plate: ");
	private JLabel departmanLabel = new JLabel("Departman: ");
	private JLabel izabraniDepartmanLabel = new JLabel("Izabrani departman:");
	
	private JTextField imeInput = new JTextField(30);
	private JTextField prezimeInput = new JTextField(30);
	private JTextField biografijaInput = new JTextField(30);
	private JSpinner visinaPlateSpinner = new JSpinner(new SpinnerNumberModel(0,0,10000.0,0.1)); //ovim smo osigurali da se unosi broj
	
	private DefaultListModel<Radnik> postojeciRadniciModel = new DefaultListModel<Radnik>();
	private JList<Radnik> postojeciRadnici = new JList<Radnik>(postojeciRadniciModel);
	private JScrollPane scrollPaneDepartman = new JScrollPane(postojeciRadnici);
	
	private DefaultListModel<Radnik> izabraniRadniciModel = new DefaultListModel<Radnik>();
	private JList<Radnik> izabraniRadnici = new JList<Radnik>(izabraniRadniciModel);
	private JScrollPane scrollPaneIzabrani = new JScrollPane(izabraniRadnici);

	private SpringLayout layout = new SpringLayout();
	
	private JButton dodajDepartmanButton = new JButton("Dodaj u departman");
	private JButton ukloniDepartmanButton = new JButton("Ukloni iz departmana");
	private JButton dodajButton = new JButton("Dodaj");
	private JButton ukloniButton = new JButton("Ukloni");
	private JButton izmeniButton = new JButton("Izmeni");

	private MenadzerTableModel menadzeriModel = new MenadzerTableModel();
	private JTable tabela = new JTable(menadzeriModel);
	private JScrollPane scrollPaneMenadzeri = new JScrollPane(tabela);
	
	public void dodajDepActionListener(ActionListener listener) {
		this.dodajDepartmanButton.addActionListener(listener);
	}
	
	public void ukloniDepActionListener(ActionListener listener) {
		this.ukloniDepartmanButton.addActionListener(listener);
	}
	
	public void dodajUDepartman() {
		
		postojeciRadnici.getSelectionModel().addListSelectionListener(e -> {	
            if (!e.getValueIsAdjusting()) {
                Radnik selektovani = postojeciRadnici.getSelectedValue();
                DepartmanController dc = new DepartmanController();
                if (selektovani != null) {
                	ActionListener[] actionListeneri = dodajDepartmanButton.getActionListeners();
                	
                    // Obrisemo listenere koje smo pre pokrenuli sa selektom i klikom na dugme dodaj
                    for (ActionListener listener : actionListeneri) {
                        dodajDepartmanButton.removeActionListener(listener);
                    }
	
                    this.dodajDepActionListener(ed -> {	//nakon klika na dugme,selektovani clan se dodaje u drugu listu u kom se nalaze izabrani magacioneri
                    	if(dc.postojiRadnik(izabraniRadniciModel, selektovani) == false) {//proveravamo da li vec postoji takav radnik u listi,ako postoji,nece se dodati da ne bi doslo do dupliranja magacionera
                    		izabraniRadniciModel.addElement(selektovani);
	                	}
                    });
	            }
                }
            });
	}
	
	public void ukloniIzDepartmana() {
		izabraniRadnici.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Radnik selektovani = izabraniRadnici.getSelectedValue();
                if (selektovani != null) {
                	ActionListener[] actionListeneri = ukloniDepartmanButton.getActionListeners();
                	
                    // Obrisemo listenere koje smo pre pokrenuli sa selektom i klikom na dugme ukloni
                    for (ActionListener listener : actionListeneri) {
                    	ukloniDepartmanButton.removeActionListener(listener);
                    }
	
                    this.ukloniDepActionListener(ed -> {	//identicna metodi sa za dodavanje,samo se ovde bez provere uklanja selektovani element iz liste izabranih magacionera
                    	izabraniRadniciModel.removeElement(selektovani);
                    });
	            }
                }
            });
	}
	
	public void addActionListener(ActionListener listener) {
		this.dodajButton.addActionListener(listener);
	}
	
	public Menadzer kreiraj() {
		return new Menadzer(imeInput.getText(),prezimeInput.getText(),biografijaInput.getText(),(double) visinaPlateSpinner.getValue(),Collections.list(izabraniRadniciModel.elements()));
	}
	
	public void removeActionListener(ActionListener listener) {
		this.ukloniButton.addActionListener(listener);
	}
	
	public void updateActionListener(ActionListener listener) {
		this.izmeniButton.addActionListener(listener);
	}
	
	public void ucitaj() {
		MenadzerController mc = new MenadzerController();
		List<Menadzer> menadzeri = mc.ucitaj();
		for(Menadzer m:menadzeri) {
			menadzeriModel.dodajPostojece(m);
		}
	}
	
	public void init() {
		this.setSize(660,800);
		this.setTitle("Menadzeri");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(layout);
		this.add(imeLabel);
		this.add(imeInput);
		this.add(prezimeLabel);
		this.add(prezimeInput);
		this.add(biografijaLabel);
		this.add(biografijaInput);
		this.add(visinaPlateLabel);
		this.add(visinaPlateSpinner);
		this.add(departmanLabel);
		this.add(scrollPaneDepartman);
		MagacionerController mc = new MagacionerController(); //postojeci magacioneri se ucitavaju,da bi korisnik mogao da bira i dodaje
		List<Magacioner> postojeciMagacioneri = mc.ucitaj();
		for(Magacioner m:postojeciMagacioneri) {
			postojeciRadniciModel.addElement(m);
		}
		this.add(dodajDepartmanButton);
		this.dodajUDepartman();
		this.ukloniIzDepartmana();
		this.add(izabraniDepartmanLabel);
		this.add(scrollPaneIzabrani);
		this.add(ukloniDepartmanButton);
		this.add(dodajButton);
		this.add(ukloniButton);
		this.add(izmeniButton);
		this.add(scrollPaneMenadzeri);
		
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
		
		this.layout.putConstraint(SpringLayout.WEST, departmanLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, departmanLabel, 20,SpringLayout.SOUTH, visinaPlateLabel);
		this.layout.putConstraint(SpringLayout.WEST, scrollPaneDepartman, 55,SpringLayout.EAST, departmanLabel);
		this.layout.putConstraint(SpringLayout.NORTH, scrollPaneDepartman, 17,SpringLayout.SOUTH, visinaPlateSpinner);
		
		this.layout.putConstraint(SpringLayout.WEST, dodajDepartmanButton, 300,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, dodajDepartmanButton, 10,SpringLayout.SOUTH, scrollPaneDepartman);
		
		this.layout.putConstraint(SpringLayout.WEST, ukloniDepartmanButton, 294,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, ukloniDepartmanButton, 10,SpringLayout.SOUTH, scrollPaneIzabrani);
		
		this.layout.putConstraint(SpringLayout.WEST, izabraniDepartmanLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, izabraniDepartmanLabel, 125,SpringLayout.SOUTH, departmanLabel);
		this.layout.putConstraint(SpringLayout.WEST, scrollPaneIzabrani, 55,SpringLayout.EAST, departmanLabel);
		this.layout.putConstraint(SpringLayout.NORTH, scrollPaneIzabrani, 50,SpringLayout.SOUTH, scrollPaneDepartman);
		
		scrollPaneDepartman.setPreferredSize(new Dimension(300, 90));
		scrollPaneIzabrani.setPreferredSize(new Dimension(300, 90)); 
		
		this.layout.putConstraint(SpringLayout.WEST, dodajButton, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, dodajButton, 30,SpringLayout.SOUTH, ukloniDepartmanButton);
		
		this.layout.putConstraint(SpringLayout.WEST, izmeniButton, 20,SpringLayout.EAST, dodajButton);
		this.layout.putConstraint(SpringLayout.NORTH, izmeniButton, 30,SpringLayout.SOUTH, ukloniDepartmanButton);
		
		this.layout.putConstraint(SpringLayout.WEST, ukloniButton, 20,SpringLayout.EAST, izmeniButton);
		this.layout.putConstraint(SpringLayout.NORTH, ukloniButton, 30,SpringLayout.SOUTH, ukloniDepartmanButton);
		
		this.layout.putConstraint(SpringLayout.WEST, scrollPaneMenadzeri, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, scrollPaneMenadzeri, 10,SpringLayout.SOUTH, ukloniButton);
		
		scrollPaneMenadzeri.setPreferredSize(new Dimension(450, 150));
		
		ucitaj();
		
		//dodavanje menadzera
		this.addActionListener(e -> {
			menadzeriModel.dodaj(this.kreiraj());
		});
		
		//uklanjanje menadzera
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
				this.removeActionListener(er ->{
					int[] selektovaniRedovi = tabela.getSelectedRows();
		    
		            for (int i = selektovaniRedovi.length - 1; i >= 0; i--) {
		                menadzeriModel.ukloni(selektovaniRedovi[i]);
		            }
				});
			}else if(tabela.getSelectedRow() < 0) {
				imeInput.setText("");
				prezimeInput.setText("");
				biografijaInput.setText("");
				visinaPlateSpinner.setValue(0.0);
				izabraniRadniciModel.clear();
			}
		});
		
		//izmena menadzera
		tabela.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
				Menadzer selektovani = menadzeriModel.dobavi(tabela.getSelectedRow());
				imeInput.setText(selektovani.getIme());
				prezimeInput.setText(selektovani.getPrezime());
				biografijaInput.setText(selektovani.getBiografija());
				visinaPlateSpinner.setValue(selektovani.getVisinaPlate());
				izabraniRadniciModel.clear();
				for(int i=0;i<selektovani.getDepartman().size();i++) {
					izabraniRadniciModel.addElement(selektovani.getDepartman().get(i));
				}
				this.updateActionListener(eu -> {
					menadzeriModel.izmeni(tabela.getSelectedRow(), this.kreiraj());
				});
			}
		});
		
		this.setVisible(true);
	}
}
