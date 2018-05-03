package logika;

import java.util.LinkedList;


public class Igra {
	protected Plosca plosca;
	protected Igralec naPotezi;
	
	public Igra() {
		plosca = new Plosca();
		//Naj vedno zacne igralec 1
		naPotezi = Igralec.IGRALEC1;
	}
	
	/**
	 * 
	 * @return Trenutno stanje igre.
	 */
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
			//Kateri igralec ima vec zapoljnenih boxov?
			if (steviloBoxovIgralec1 > steviloBoxovIgralec2) {
				return Stanje.ZMAGA_IGRALEC1;
				
			}
			else if (steviloBoxovIgralec1 < steviloBoxovIgralec2) {
				return Stanje.ZMAGA_IGRALEC2;
			}
			else {
				return Stanje.NEODLOCENO;
			}
		//NI SE KONEC, povemo kdo je na potezi.
		} else {
			if (naPotezi == Igralec.IGRALEC1) {
				return Stanje.NA_POTEZI_IGRALEC1;
			} else {
				return Stanje.NA_POTEZI_IGRALEC2;
			}
		}
	}
	
	/**
	 * 
	 * @return Seznam vseh moznih potez.
	 */
	public LinkedList<Poteza> poteze(){
		LinkedList<Poteza> moznePoteze = new LinkedList<Poteza>();
		//Preverimo vodoravne crte
		for (int i = 0; i < (Plosca.VISINA + 1); i++) {
			for (int j = 0; j < Plosca.SIRINA; j++) {
				if(plosca.vodoravneCrte[i][j] == false) {
					moznePoteze.add(new Poteza(Smer.VODORAVNO, i, j));
				}
			}
		}
		//Preverimo navpicne crte
		for (int i = 0; i < Plosca.VISINA; i++) {
			for (int j = 0; j < (Plosca.SIRINA + 1); j++) {
				if(plosca.navpicneCrte[i][j] == false) {
					moznePoteze.add(new Poteza(Smer.NAVPICNO, i, j));
				}
			}
		}
		return moznePoteze;	
	}
	
	/**
	 * 
	 * @param p
	 * @return True, èe je bila poteza uspešno odigrana.
	 */
	public boolean odigraj(Poteza p) {
		if (plosca.polje[p.getI()][p.getJ()] != Box.PRAZNO) {
			return false;
		} else {
			if (p.getSmer() == Smer.NAVPICNO) {
				if (plosca.navpicneCrte[p.getI()][p.getJ()] == false) {
					plosca.navpicneCrte[p.getI()][p.getJ()] = true;
					return true;
				} else {
					return false;	
				}
			} else if (p.getSmer() == Smer.VODORAVNO) {
				if (plosca.vodoravneCrte[p.getI()][p.getJ()] == false) {
					plosca.vodoravneCrte[p.getI()][p.getJ()] = true;
					return true;
				} else {
					return false;	
				}
			} else {
				return false;
			}
		}		
	}
}
