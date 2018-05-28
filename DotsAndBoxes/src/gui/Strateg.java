package gui;

import logika.Smer;

/**
 * Strateg je objekt, ki zna odigrati potezo. Lahko je clovek ali racunalnik
 * 
 * @author Sara
 * 
 */

public abstract class Strateg {
	/**
	 * Glavno okno klice to potezo, ko je strateg na potezi
	 */
	public abstract void na_potezi();
	
	
	/**
	 * Strateg naj neha igrati potezo
	 */
	public abstract void prekini();
	
	/**
	 * Glavno okno klice to metodo, ko uporabnik klikne na polje (visina, sirina)
	 * @param vis
	 * @param sir
	 * 
	 */
	public abstract void klikni(Smer s, int vis, int sir);

}
