package gui;

import logika.Igralec;
import logika.Poteza;
import logika.Smer;

/**
 * Objekt, ki igra clovekove poteze
 * @author Sara
 *
 */
public class Clovek extends Strateg {
	private GlavnoOkno okno;
	private Igralec clovek;
	
	public Clovek(GlavnoOkno okno, Igralec clovek) {
		this.okno = okno;
		this.clovek = clovek;
	}

	@Override
	public void na_potezi() {
	}

	@Override
	public void prekini() {	
	}

	// dodale smer
	@Override
	public void klikni(Smer s, int vis, int sir) {
		okno.odigraj(new Poteza(s, vis, sir));	
	}
	
	

}
