package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import logika.Igra;

@SuppressWarnings("serial")
public class GlavnoOkno extends JFrame implements ActionListener{
	
	/**
	 * JPanel, v katerega risemo crte
	 */
	private IgralnoPolje polje;
	
	/**
	 * Statusna vrstica v spodnjem delu okna
	 */
	private JLabel status;
	
	/**
	 * Logika igre
	 * null, èe se trenutno ne igra
	 */
	private Igra igra;
	
	/**
	 * Strateg, ki rise rdece crte
	 */
	private Strateg rdec;
	
	/**
	 * Strateg, ki rise modre crte
	 */
	private Strateg moder;
	
	// Izbire v menujih #TODO
	private JMenuItem novaIgra;
	
	
	public GlavnoOkno() {
		this.setTitle("Dots and Boxes");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		// menu #TODO
		
		// igralno polje
		polje = new IgralnoPolje(this);
		GridBagConstraints izgledPolja = new GridBagConstraints();
		izgledPolja.gridx = 0;
		izgledPolja.gridy = 0;
		izgledPolja.fill = GridBagConstraints.BOTH;
		izgledPolja.weightx = 1.0;
		izgledPolja.weighty = 1.0;
		getContentPane().add(polje, izgledPolja);
		
		// statusna vrstica za sporocila #TODO
		
		// zacnemo novo igro
		// novaIgra();
		
	}
	
	public 
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
