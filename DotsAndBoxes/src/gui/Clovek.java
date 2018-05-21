package gui;

import logika.Poteza;
import logika.Smer;

public class Clovek extends Strateg {
	private GlavnoOkno okno;
	
	public Clovek(GlavnoOkno master) {
		this.okno = master;
	}

	@Override
	public void na_potezi() {
	}

	@Override
	public void prekini() {	
	}

	// dodale smer
	@Override
	public void klikni(Smer s, int i, int j) {
		okno.odigraj(new Poteza(s, i, j));	
	}
	
	

}
