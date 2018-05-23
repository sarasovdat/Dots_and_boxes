package logika;

public class Plosca {
	
	/**
	 * Velikost igralne plosce je N x M
	 */
    public static final int SIRINA = 4;
	public static final int VISINA = 4;
	public static final int VELIKOST_POLJA = SIRINA * VISINA;
	
	private Box [][] polje;
	private Crta [][] vodoravneCrte;
	private Crta [][] navpicneCrte;
	
	public Plosca() {
		polje = new Box [VISINA][SIRINA];
		vodoravneCrte = new Crta [VISINA + 1][SIRINA];
		navpicneCrte = new Crta [VISINA][SIRINA + 1];
		
		// Polja nastavimo na prazno
		for (int vis = 0; vis < VISINA; vis++) {
			for (int sir = 0; sir < SIRINA; sir++) {
				getPolje()[vis][sir] = Box.PRAZNO;
			}
		}
		
		// Na zacetku na plosci ni crt (vodoravnih in navpicnih)
		for (int vis = 0; vis < (VISINA + 1); vis++) {
			for (int sir = 0; sir < SIRINA; sir++) {
				getVodoravneCrte()[vis][sir] = Crta.PRAZNO;
			}
		}
		
		for (int vis = 0; vis < VISINA; vis++) {
			for (int sir = 0; sir < (SIRINA + 1); sir++) {
				getNavpicneCrte()[vis][sir] = Crta.PRAZNO;
			}
		}
	}
	
	
// Metoda, ki preveri, ce so crte ze narisane
	
	public Crta crtaLevo (int vis, int sir) {
		return getNavpicneCrte()[vis][sir];
	}
	
	public Crta crtaDesno (int vis, int sir) {
		return getNavpicneCrte()[vis][sir+1];
	}
	
	public Crta crtaZgoraj (int vis, int sir) {
		return getVodoravneCrte()[vis][sir];
	}
	
	public Crta crtaSpodaj (int vis, int sir) {
		return getVodoravneCrte()[vis+1][sir];
	}


	public Crta [][] getNavpicneCrte() {
		return navpicneCrte;
	}
	public Crta [][] getVodoravneCrte() {
		return vodoravneCrte;
	}

	public Box [][] getPolje() {
		return polje;
	}

}
