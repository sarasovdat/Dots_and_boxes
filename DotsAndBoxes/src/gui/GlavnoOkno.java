package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import logika.Igra;
import logika.Plosca;
import logika.Poteza;
import logika.Smer;

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
		
		// statusna vrstica za sporocila 
		status = new JLabel();
		status.setFont(new Font(status.getFont().getName(),
				status.getFont().getStyle(), 20));
		
		// zacnemo novo igro
		novaIgra();
		
	}
	
	/**
	 * Metoda, ki ustvari novo igro
	 */
	private void novaIgra() {
		this.igra = new Igra();
		rdec = new Clovek(this);
		moder = new Clovek(this);
	}
	
	/**
	 * 
	 * @param p
	 * odigra potezo
	 */
	public void odigraj(Poteza p) {
		igra.odigraj(p);
		osveziGUI();
		switch (igra.stanje()) {
		case NA_POTEZI_RDEC: rdec.na_potezi(); break;
		case NA_POTEZI_MODER: moder.na_potezi(); break;
		case ZMAGA_RDEC: break;
		case ZMAGA_MODER: break;
		case NEODLOCENO: break;
		}
	}
	
	/**
	 * Osvezuje celotni GUI (statusna vrstica)
	 */
	public void osveziGUI() {
		if (igra == null) {
			status.setText("Igra se ne izvaja");
		} else {
			switch(igra.stanje()) {
			case NA_POTEZI_RDEC: status.setText("Na potezi: RDEC"); break;
			case NA_POTEZI_MODER: status.setText("Na potezi: MODER"); break;
			case ZMAGA_RDEC: status.setText("Zmaga: RDEC"); break;
			case ZMAGA_MODER: status.setText("Zmaga: MODER"); break;
			case NEODLOCENO: status.setText("Nimata za burek!"); break;
			
			}
		}
		polje.repaint();
	}
	
	/**
	 * 
	 * @return trenutna igralna plosca (null, ce se igra ne izvaja)
	 */
	public Plosca getPlosca() {
		return (igra == null ? null : igra.getPlosca());	
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * Metoda, ki ob kliku na (i, j) pravilno ukrepa 
	 */
	
	// POPRAVI NEKAJ S KLIKOM NA CRTO
	public void klikniPolje (Smer s, int i, int j) {
		if (igra != null) {
			switch (igra.stanje()) {
			case NA_POTEZI_RDEC: rdec.klikni(s, i, j); break;
			case NA_POTEZI_MODER: moder.klikni(s, i, j); break;
			default: break;
			}
		}
	}
	



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
