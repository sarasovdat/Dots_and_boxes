package logika;

import java.util.LinkedList;

public class Igra {
	protected Plosca plosca;
	protected Igralec naPotezi;
	
	public Igra() {
		plosca = new Plosca();
		//naj vedno zaène igralec 1
		naPotezi = Igralec.IGRALEC1;
	}
	
	public Stanje stanje() {
		int steviloBoxovIgralec1 = 0;
		int steviloBoxovIgralec2 = 0;
		for (int i = 0; i < Plosca.VISINA; i++) {
			for (int j = 0; j < Plosca.SIRINA; j++) {
				if (plosca.polje[i][j] == Box.IGRALEC1) {
					steviloBoxovIgralec1++;
				}
				else if (plosca.polje[i][j] == Box.IGRALEC2) {
					steviloBoxovIgralec2++;
				}
			}
		}
		//KONEC IGRE (ko so zapolnjeni vsi boxi)
		if (steviloBoxovIgralec1 + steviloBoxovIgralec2 == Plosca.VISINA * Plosca.SIRINA) {
			//Kateri igralec ima veè zapoljnenih boxov?
			if (steviloBoxovIgralec1 > steviloBoxovIgralec2) {
				return Stanje.ZMAGA_IGRALEC1;
				
			}
			else if (steviloBoxovIgralec1 < steviloBoxovIgralec2) {
				return Stanje.ZMAGA_IGRALEC2;
			}
			else {
				return Stanje.NEODLOCENO;
			}
		} else {
			//treba je narest èe ni konec in je nekdo na potezi, kako narest to da se poteze ne menjajo?
			return null;
		}
	}
	
	//seznam vseh možnih potez
	public LinkedList<Poteza> poteze(){
		LinkedList<Poteza> moznePoteze = new LinkedList<Poteza>();
		//preverimo vodoravne crte
		for (int i = 0; i < (Plosca.VISINA + 1); i++) {
			for (int j = 0; j < Plosca.SIRINA; j++) {
				if(plosca.vodoravneCrte[i][j] == false) {
					moznePoteze.add(new Poteza(Smer.VODORAVNO, i, j));
				}
			}
		}
		//preverimo se navpicne crte
		for (int i = 0; i < Plosca.VISINA; i++) {
			for (int j = 0; j < (Plosca.SIRINA + 1); j++) {
				if(plosca.navpicneCrte[i][j] == false) {
					moznePoteze.add(new Poteza(Smer.NAVPICNO, i, j));
				}
			}
		}
		return moznePoteze;	
	}
}
