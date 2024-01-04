package view.MainFrame;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import interfaces.IInit;
import view.Frame.BrzaPretragaFrame;
import view.Frame.KucniAparatiFrame;
import view.Frame.KvarljivaRobaFrame;
import view.Frame.MagacionerFrame;
import view.Frame.MenadzerFrame;

public class PocetniProzor extends JFrame implements IInit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PocetniProzor() throws HeadlessException {
		super();
	}

	public PocetniProzor(GraphicsConfiguration gc) {
		super(gc);
	}

	public PocetniProzor(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public PocetniProzor(String title) throws HeadlessException {
		super(title);
	}
	
	private JLabel menadzeriLabel = new JLabel("Upravljaj menadzerima: ");
	private JLabel magacioneriLabel = new JLabel("Upravljaj magacionerima: ");
	private JLabel kvarljivaRobaLabel = new JLabel("Upravljaj kvarljivom robom: ");
	private JLabel kucniAparatiLabel = new JLabel("Upravljaj kucnim aparatima: ");
	private JLabel brzaPretragaLabel = new JLabel("Pristupi brzoj pretrazi: ");
	
	private JButton menadzeriButton = new JButton("Menadzeri");
	private JButton magacioneriButton = new JButton("Magacioneri");
	private JButton kvarljivaRobaButton = new JButton("Kvarljiva roba");
	private JButton kucniAparatiButton = new JButton("Kucni Aparati");
	private JButton brzaPretragaButton = new JButton("Pretraga");
	
	private SpringLayout layout = new SpringLayout();
	
	public void init() {
		this.setTitle("Pocetna");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(550, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(layout);
		
		this.add(menadzeriLabel);
		this.add(menadzeriButton);
		menadzeriButton.addActionListener(e -> {
			MenadzerFrame mf = new MenadzerFrame();
			mf.init();
		});
		this.add(magacioneriLabel);
		this.add(magacioneriButton);
		magacioneriButton.addActionListener(e -> {
			MagacionerFrame mp = new MagacionerFrame();
			mp.init();
		});
		this.add(kvarljivaRobaLabel);
		this.add(kvarljivaRobaButton);
		kvarljivaRobaButton.addActionListener(e -> {
			KvarljivaRobaFrame kvf = new KvarljivaRobaFrame();
			kvf.init();
		});
		this.add(kucniAparatiLabel);
		this.add(kucniAparatiButton);
		kucniAparatiButton.addActionListener(e -> {
			KucniAparatiFrame kaf = new KucniAparatiFrame();
			kaf.init();
		});
		this.add(brzaPretragaLabel);
		this.add(brzaPretragaButton);
		brzaPretragaButton.addActionListener(e -> {
			BrzaPretragaFrame bpf = new BrzaPretragaFrame();
			bpf.init();
		});
		
		this.layout.putConstraint(SpringLayout.WEST, menadzeriLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, menadzeriLabel, 100,SpringLayout.NORTH, this);
		this.layout.putConstraint(SpringLayout.WEST, menadzeriButton, 43,SpringLayout.EAST, menadzeriLabel);
		this.layout.putConstraint(SpringLayout.NORTH, menadzeriButton, 96,SpringLayout.NORTH, this);

		this.layout.putConstraint(SpringLayout.WEST, magacioneriLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, magacioneriLabel, 20,SpringLayout.SOUTH, menadzeriLabel);
		this.layout.putConstraint(SpringLayout.WEST, magacioneriButton, 33,SpringLayout.EAST, magacioneriLabel);
		this.layout.putConstraint(SpringLayout.NORTH, magacioneriButton, 10,SpringLayout.SOUTH, menadzeriButton);
		
		this.layout.putConstraint(SpringLayout.WEST, kvarljivaRobaLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, kvarljivaRobaLabel, 20,SpringLayout.SOUTH, magacioneriLabel);
		this.layout.putConstraint(SpringLayout.WEST, kvarljivaRobaButton, 20,SpringLayout.EAST, kvarljivaRobaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, kvarljivaRobaButton, 10,SpringLayout.SOUTH, magacioneriButton);

		
		this.layout.putConstraint(SpringLayout.WEST, kucniAparatiLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, kucniAparatiLabel, 20,SpringLayout.SOUTH, kvarljivaRobaLabel);
		this.layout.putConstraint(SpringLayout.WEST, kucniAparatiButton, 20,SpringLayout.EAST, kucniAparatiLabel);
		this.layout.putConstraint(SpringLayout.NORTH, kucniAparatiButton, 10,SpringLayout.SOUTH, kvarljivaRobaButton);

		this.layout.putConstraint(SpringLayout.WEST, brzaPretragaLabel, 100,SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, brzaPretragaLabel, 20,SpringLayout.SOUTH, kucniAparatiLabel);
		this.layout.putConstraint(SpringLayout.WEST, brzaPretragaButton, 48,SpringLayout.EAST, brzaPretragaLabel);
		this.layout.putConstraint(SpringLayout.NORTH, brzaPretragaButton, 10,SpringLayout.SOUTH, kucniAparatiButton);
		
		this.setVisible(true);
	}
}
