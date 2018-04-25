package logika;


public class Igra {
	protected Plosca plosca;
	protected Igralec naPotezi;
	
	public Igra() {
		plosca = new Plosca();
	}
	
	public Stanje stanje() {
		int steviloBoxovIgralec1 = 0;
		int steviloBoxovIgralec2 = 0;
		for (int i = 0; i < Plosca.VISINA; i++) {
			for (int j = 0; j < Plosca.SIRINA; j++) {
				if (Plosca.polje[i][j] == Box.IGRALEC1) {
					steviloBoxovIgralec1++;
				}
				else if (Plosca.polje[i][j] == Box.IGRALEC2) {
					steviloBoxovIgralec2++;
				}
			}
		}
		//KONEC IGRE (ko so zapolnjeni vsi boxi)
		if (steviloBoxovIgralec1 + steviloBoxovIgralec2 == Plosca.VISINA * Plosca.SIRINA) {
			//Kateri igralec ima veè zapoljnenih boxov?
			if (steviloBoxovIgralec1 > steviloBoxovIgralec2) {
				Stanje s = Stanje.ZMAGA_IGRALEC1;
			}
			else if (steviloBoxovIgralec1 < steviloBoxovIgralec2) {
				Stanje s = Stanje.ZMAGA_IGRALEC2;
			}
			else {
				Stanje s = Stanje.NEODLOCENO;
			}
		}
		return s;
	}
}
