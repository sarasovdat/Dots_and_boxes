package gui;

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
	
	/**
	 * 
	 * @param i
	 * @param j
	 * Glavno okno klice to metodo, ko uporabnik klikne na polje (i, j).
	 */
	public abstract void klikni(int i, int j);

}
