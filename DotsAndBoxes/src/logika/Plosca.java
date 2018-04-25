package logika;

public class Plosca {
	
	/**
	 * velikost igralne plošèe NxM
	 */
	protected static final int SIRINA = 4;
	protected static final int VISINA = 4;
	public static final int VELIKOST_POLJA = SIRINA * VISINA;
	
	protected Box [][] polje;
	protected boolean [][] vodoravneCrte;
	protected boolean [][] navpicneCrte;
	
	public Plosca() {
		polje = new Box [VISINA][SIRINA];
		vodoravneCrte = new boolean [VISINA + 1][SIRINA];
		navpicneCrte = new boolean [VISINA][SIRINA + 1];
		
		//Polja nastavimo na prazno.
		for (int i = 0; i < VISINA; i++) {
			for (int j = 0; j < SIRINA; j++) {
				polje[i][j] = Box.PRAZNO;
			}
		}
		
		//Na zaèetku na plošèi ni èrt (vodoravnih in navpiènih).
		for (int i = 0; i < (VISINA + 1); i++) {
			for (int j = 0; j < SIRINA; j++) {
				vodoravneCrte[i][j] = false;
			}
		}
		
		for (int i = 0; i < VISINA; i++) {
			for (int j = 0; j < (SIRINA + 1); j++) {
				navpicneCrte[i][j] = false;
			}
		}
	}
	
	
//Metoda, ki preveri, èe so èrte že narisane.
	
	public boolean crtaLevo (int i, int j) {
		return navpicneCrte[i][j];
	}
	
	public boolean crtaDesno (int i, int j) {
		return navpicneCrte[i][j+1];
	}
	
	public boolean crtaZgoraj (int i, int j) {
		return vodoravneCrte[i][j];
	}
	
	public boolean crtaSpodaj (int i, int j) {
		return vodoravneCrte[i+1][j];
	}
	
	
	
	
	
		
	

}
