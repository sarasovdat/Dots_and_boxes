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
		for (int i = 0; i < VISINA; i++) {
			for (int j = 0; j < SIRINA; j++) {
				getPolje()[i][j] = Box.PRAZNO;
			}
		}
		
		// Na zacetku na plosci ni crt (vodoravnih in navpicnih)
		for (int i = 0; i < (VISINA + 1); i++) {
			for (int j = 0; j < SIRINA; j++) {
				getVodoravneCrte()[i][j] = Crta.PRAZNO;
			}
		}
		
		for (int i = 0; i < VISINA; i++) {
			for (int j = 0; j < (SIRINA + 1); j++) {
				getNavpicneCrte()[i][j] = Crta.PRAZNO;
			}
		}
	}
	
	
//Metoda, ki preveri, ce so crte ze narisane
	
	public Crta crtaLevo (int i, int j) {
		return getNavpicneCrte()[i][j];
	}
	
	public Crta crtaDesno (int i, int j) {
		return getNavpicneCrte()[i][j+1];
	}
	
	public Crta crtaZgoraj (int i, int j) {
		return getVodoravneCrte()[i][j];
	}
	
	public Crta crtaSpodaj (int i, int j) {
		return getVodoravneCrte()[i+1][j];
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
