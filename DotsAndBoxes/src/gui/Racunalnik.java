package gui;

import logika.Poteza;
import logika.Smer;

import javax.swing.SwingWorker;

import inteligenca.NakljucnaInteligenca;


public class Racunalnik extends Strateg {
	private GlavnoOkno okno;
	private SwingWorker<Poteza, Object> mislec;
//	private boolean prekini;
	
	public Racunalnik(GlavnoOkno okno) {
		this.okno = okno;
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
