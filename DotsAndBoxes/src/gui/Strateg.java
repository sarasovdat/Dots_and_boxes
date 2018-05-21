package gui;

import logika.Smer;

/**
 * 
 * @author Sara
 * Strateg je objekt, ki zna odigrati potezo. Lahko je clovek ali racunalnik.
 */

public abstract class Strateg {
	/**
	 * Glavno okno klice to potezo, ko je strateg na potezi.
	 */
	public abstract void na_potezi();
	
	
	/**
	 * Strateg naj neha igrati potezo.
	 */
	public abstract void prekini();
	
	// Dodale smer
	/**
	 * 
	 * @param i
	 * @param j
	 * Glavno okno klice to metodo, ko uporabnik klikne na polje (i, j).
	 */
	public abstract void klikni(Smer s, int i, int j);

}
