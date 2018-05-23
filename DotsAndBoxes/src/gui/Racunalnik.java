package gui;

import logika.Igralec;
import logika.Poteza;
import logika.Smer;

import javax.swing.SwingWorker;

import inteligenca.NakljucnaInteligenca;

/**
 * Objekt, ki igra poteze racunalnika glede na izbiro 'inteligence'
 * @author Sara
 *
 */
public class Racunalnik extends Strateg {
	private GlavnoOkno okno;
	private Igralec racunalnik;
	private SwingWorker<Poteza, Object> mislec;
//	private boolean prekini;
	
	public Racunalnik(GlavnoOkno okno, Igralec racunalnik) {
		this.okno = okno;
		this.racunalnik = racunalnik;
	}

	@Override
	public void na_potezi() {
		// Zacnemo razmisljati
		mislec = new NakljucnaInteligenca(okno); 
		mislec.execute();	
	}

	@Override
	public void prekini() {
		if (mislec != null) {
			mislec.cancel(true);
		}
	}

	@Override
	public void klikni(Smer s, int vis, int sir) {
	}
}
