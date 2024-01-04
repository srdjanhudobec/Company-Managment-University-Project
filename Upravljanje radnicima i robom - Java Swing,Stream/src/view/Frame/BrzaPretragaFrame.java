package view.Frame;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.BrzaPretragaController;
import interfaces.IInit;
import model.Radnik.Magacioner;
import model.Radnik.Menadzer;
import model.Roba.KucniAparati;
import model.Roba.KvarljivaRoba;
import view.TableModels.KucniAparatiTableModel;
import view.TableModels.KvarljivaRobaTableModel;
import view.TableModels.MagacioneriTableModel;
import view.TableModels.MenadzerTableModel;

public class BrzaPretragaFrame extends JFrame implements IInit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BrzaPretragaFrame() throws HeadlessException {
		super();
	}

	public BrzaPretragaFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public BrzaPretragaFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public BrzaPretragaFrame(String title) throws HeadlessException {
		super(title);
	}
	
	private JLabel filterLabel = new JLabel("Unesite filter za pretragu:");
	private JLabel menadzeriLabel = new JLabel("Menadzeri");
	private JLabel magacioneriLabel = new JLabel("Magacioneri");
	private JLabel kvarljivaRobaLabel = new JLabel("Kvarljiva roba");
	private JLabel kucniAparatiLabel = new JLabel("Kucni aparati");
	private JLabel radniciMenadzeraLabel = new JLabel("Menadzerovi radnici");
	private MagacioneriTableModel mtm = new MagacioneriTableModel();
	private JTable tabela = new JTable(mtm);
	private JScrollPane menadzeroviRadnici = new JScrollPane(tabela);
	private JTextField filterInput = new JTextField(30);
	private JButton pretraziButton = new JButton("Pretrazi");
	
	private MenadzerTableModel metb = new MenadzerTableModel();
	private JTable tabelaMenadzera = new JTable(metb);
	private JScrollPane menadzeriScroll = new JScrollPane(tabelaMenadzera);
	
	private MagacioneriTableModel mtb = new MagacioneriTableModel();
	private JTable tabelaMagacionera = new JTable(mtb);
	private JScrollPane magacioneriScroll = new JScrollPane(tabelaMagacionera);
	
	private KvarljivaRobaTableModel kvrtb = new KvarljivaRobaTableModel();
	private JTable tabelaKvarljiveRobe = new JTable(kvrtb);
	private JScrollPane kvarljivaRobaScroll = new JScrollPane(tabelaKvarljiveRobe);
	
	private KucniAparatiTableModel katb = new KucniAparatiTableModel();
	private JTable tabelaKucnihAparata = new JTable(katb);
	private JScrollPane kucniAparatiScroll = new JScrollPane(tabelaKucnihAparata);
	
	private BrzaPretragaController bpc = new BrzaPretragaController();
	
	private SpringLayout layout = new SpringLayout();
	
	public void pretraziMagacionere(String filter) {	//na osnovu kontrolera i metode provere,vraca Magacionere koji prolaze filtriranje i smesta ih u tabelu
		List<Magacioner> magacioneri = bpc.proveriMagacionere(filter);
		for(Magacioner m:magacioneri) {
			mtb.dodajPostojece(m);
		}
	}
	
	public void pretraziMenadzere(String filter) {
		List<Menadzer> menadzeri = bpc.proveriMenadzere(filter);
		for(Menadzer m:menadzeri) {
			metb.dodajPostojece(m);
		}
	}
	
	public void pretraziKvarljivuRobu(String filter) {
		List<KvarljivaRoba> kvarljivaRoba = bpc.proveriKvarljivuRobu(filter);
		for(KvarljivaRoba kr:kvarljivaRoba) {
			kvrtb.dodajPostojece(kr);
		}
	}
	
	public void pretraziKucneAparate(String filter) {
		List<KucniAparati> kucniAparati = bpc.proveriKucneAparate(filter);
		for(KucniAparati ka:kucniAparati) {
			katb.dodajPostojece(ka);
		}
	}
	
	public void init() {

		this.setLayout(layout);
		
		pretraziMagacionere(filterInput.getText());
		pretraziMenadzere(filterInput.getText());
		pretraziKvarljivuRobu(filterInput.getText());
		pretraziKucneAparate(filterInput.getText());
		
		this.setSize(970,720);
		this.setTitle("Brza pretraga:");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(filterLabel);
		this.add(filterInput);
		this.add(pretraziButton);
		pretraziButton.addActionListener(e -> {
			mtb.obrisiSve();
			metb.obrisiSve();    //prvo brisemo sve iz tabela,nakon cega popunjavamo tabele sa filtriranim podacima
			mtm.obrisiSve();
			kvrtb.obrisiSve();
			katb.obrisiSve();
			pretraziMagacionere(filterInput.getText());
			pretraziMenadzere(filterInput.getText());
			pretraziKvarljivuRobu(filterInput.getText());
			pretraziKucneAparate(filterInput.getText());
		});
		this.add(menadzeriLabel);
		this.add(menadzeriScroll);
		this.add(radniciMenadzeraLabel);
		this.add(menadzeroviRadnici);
		this.add(magacioneriLabel);
		this.add(magacioneriScroll);
		this.add(kvarljivaRobaLabel);
		this.add(kvarljivaRobaScroll);
		this.add(kucniAparatiLabel);
		this.add(kucniAparatiScroll);
		
		this.layout.putConstraint(SpringLayout.WEST, filterLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, filterLabel, 40,SpringLayout.NORTH, this);
		this.layout.putConstraint(SpringLayout.WEST, filterInput, 20,SpringLayout.EAST, filterLabel);
		this.layout.putConstraint(SpringLayout.NORTH, filterInput, 40,SpringLayout.NORTH, this);
		this.layout.putConstraint(SpringLayout.WEST, pretraziButton, 20,SpringLayout.EAST, filterInput);
		this.layout.putConstraint(SpringLayout.NORTH, pretraziButton, 35,SpringLayout.NORTH, this);
		
		this.layout.putConstraint(SpringLayout.WEST, menadzeriLabel, 20,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, menadzeriLabel, 20,SpringLayout.SOUTH, filterLabel);
		this.layout.putConstraint(SpringLayout.WEST, menadzeriScroll, 20,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, menadzeriScroll, 40,SpringLayout.SOUTH, filterLabel);
		this.layout.putConstraint(SpringLayout.WEST, radniciMenadzeraLabel, 20,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, radniciMenadzeraLabel, 10,SpringLayout.SOUTH, menadzeriScroll);
		this.layout.putConstraint(SpringLayout.WEST, menadzeroviRadnici, 20,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, menadzeroviRadnici, 4,SpringLayout.SOUTH, radniciMenadzeraLabel);
		
		this.layout.putConstraint(SpringLayout.WEST, magacioneriLabel, 410,SpringLayout.EAST, menadzeriLabel);
		this.layout.putConstraint(SpringLayout.NORTH, magacioneriLabel, 14,SpringLayout.SOUTH, pretraziButton);
		this.layout.putConstraint(SpringLayout.WEST, magacioneriScroll, 20,SpringLayout.EAST, menadzeriScroll);
		this.layout.putConstraint(SpringLayout.NORTH, magacioneriScroll, 35,SpringLayout.SOUTH, filterInput);
		
		this.layout.putConstraint(SpringLayout.WEST, kvarljivaRobaLabel, 20,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, kvarljivaRobaLabel, 17,SpringLayout.SOUTH, menadzeroviRadnici);
		this.layout.putConstraint(SpringLayout.WEST, kvarljivaRobaScroll, 20,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, kvarljivaRobaScroll, 35,SpringLayout.SOUTH, menadzeroviRadnici);
		
		this.layout.putConstraint(SpringLayout.WEST, kucniAparatiLabel, 392,SpringLayout.EAST, kvarljivaRobaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, kucniAparatiLabel, 11,SpringLayout.SOUTH, magacioneriScroll);
		this.layout.putConstraint(SpringLayout.WEST, kucniAparatiScroll, 20,SpringLayout.EAST, kvarljivaRobaScroll);
		this.layout.putConstraint(SpringLayout.NORTH, kucniAparatiScroll, 30,SpringLayout.SOUTH, magacioneriScroll);
		
		menadzeriScroll.setPreferredSize(new Dimension(450,150));
		magacioneriScroll.setPreferredSize(new Dimension(450,150));
		kvarljivaRobaScroll.setPreferredSize(new Dimension(450,250));
		kucniAparatiScroll.setPreferredSize(new Dimension(450,250));
		menadzeroviRadnici.setPreferredSize(new Dimension(450,100));
		
		//tabela u kojoj se nalaze menadzerovi radnici (ispis nakon selekcije reda)
		tabelaMenadzera.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting() && tabelaMenadzera.getSelectedRow() >= 0) {
				Menadzer selektovani = metb.dobavi(tabelaMenadzera.getSelectedRow());
				mtm.obrisiSve();
				for(int i=0;i<selektovani.getDepartman().size();i++) {
					mtm.dodajPostojece((Magacioner)selektovani.getDepartman().get(i));
				}
			}
		});
		
		this.setVisible(true);
	}
}
