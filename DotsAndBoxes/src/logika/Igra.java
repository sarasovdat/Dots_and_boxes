package logika;

import java.util.LinkedList;


public class Igra {
	protected Plosca plosca;
	protected Igralec naPotezi;
	
	public Igra() {
		plosca = new Plosca();
		//Naj vedno zacne rdec
		naPotezi = Igralec.RDEC;
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
				if (plosca.polje[i][j] == Box.RDEC) {
					steviloBoxovIgralec1++;
				}
				else if (plosca.polje[i][j] == Box.MODER) {
					steviloBoxovIgralec2++;
				}
			}
		}
		//KONEC IGRE (ko so zapolnjeni vsi boxi)
		if (steviloBoxovIgralec1 + steviloBoxovIgralec2 == Plosca.VISINA * Plosca.SIRINA) {
			//Kateri igralec ima vec zapoljnenih boxov?
			if (steviloBoxovIgralec1 > steviloBoxovIgralec2) {
				return Stanje.ZMAGA_RDEC;
				
			}
			else if (steviloBoxovIgralec1 < steviloBoxovIgralec2) {
				return Stanje.ZMAGA_MODER;
			}
			else {
				return Stanje.NEODLOCENO;
			}
		//NI SE KONEC, povemo kdo je na potezi
		} else {
			if (naPotezi == Igralec.RDEC) {
				return Stanje.NA_POTEZI_RDEC;
			} else {
				return Stanje.NA_POTEZI_MODER;
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
				if(plosca.vodoravneCrte[i][j] == Crta.PRAZNO) {
					moznePoteze.add(new Poteza(Smer.DESNO, i, j));
				}
			}
		}
		//Preverimo navpicne crte
		for (int i = 0; i < Plosca.VISINA; i++) {
			for (int j = 0; j < (Plosca.SIRINA + 1); j++) {
				if(plosca.navpicneCrte[i][j] == Crta.PRAZNO) {
					moznePoteze.add(new Poteza(Smer.DOL, i, j));
				}
			}
		}
		return moznePoteze;	
	}
	/**
	 * 
	 * @param p
	 * @return seznam lokacij vseh sosednjih kvadratov poteze p
	 */
	public LinkedList<Lokacija> sosednjiBoxi(Poteza p){
		LinkedList<Lokacija> sosednji = new LinkedList<Lokacija>();			
		Lokacija l = new Lokacija();
		Lokacija k = new Lokacija();
		if (p.getI() == 0 || p.getJ() == 0 || p.getI() == Plosca.VISINA || p.getJ() == Plosca.SIRINA) {	
		//primeri, ko dodamo le en box na seznam lokacij
			if (p.getI() == 0 && p.getSmer() == Smer.DESNO) {
				l.i = 0;
				l.j = p.getJ();
			} else if (p.getI() == Plosca.VISINA && p.getSmer() == Smer.DESNO) {
				l.i = (p.getI() - 1);
				l.j = p.getJ();
			} else if (p.getJ() == 0 && p.getSmer() == Smer.DOL) {
				l.i = p.getI();
				l.j = 0;
			} else if (p.getJ() == Plosca.SIRINA && p.getSmer() == Smer.DOL) {
				l.i = p.getI();
				l.j = (p.getJ() - 1);
			}
			sosednji.add(l);
		} else {
		//dodamo dva boxa
			if (p.getSmer() == Smer.DESNO) {
				l.i = (p.getI() -1);
				l.j = p.getJ();
				k.i = p.getI();
				k.j = p.getJ();
			} else if (p.getSmer() == Smer.DOL) {
				l.i = p.getI();
				l.j = (p.getJ() - 1);
				k.i = p.getI();
				k.j = p.getJ();			
			}
			sosednji.add(l);
			sosednji.add(k);
		}
		return sosednji;
	}
	
	
	/**
	 * 
	 * @param l
	 * @return true, èe so vse èrte, ki omejujejo kvadrat na lokaciji l že zapolnjene
	 */
	public boolean jeObkrozen(Lokacija l) {
		if (plosca.vodoravneCrte[l.i][l.j] != Crta.PRAZNO
				&& plosca.vodoravneCrte[l.i + 1][l.j] != Crta.PRAZNO
				&& plosca.navpicneCrte[l.i][l.j] != Crta.PRAZNO
				&& plosca.navpicneCrte[l.i][l.j +1] != Crta.PRAZNO) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean zapolniKvadrate(Poteza p) {
		boolean smo_zapolnili = false;
		Box b = naPotezi.box();
		for (Lokacija l : sosednjiBoxi(p)) {
			if (jeObkrozen(l)) {
				smo_zapolnili = smo_zapolnili || plosca.polje[l.i][l.j] == Box.PRAZNO  ;
		        plosca.polje[l.i][l.j] = b;
		    }
		}
		return smo_zapolnili;
	}
	
	
	/**
	 * 
	 * @param p
	 * @return True, ce je bila poteza uspesno odigrana.
	 * Zapomni si poteze, 'pobarva' box.
	 */
	public boolean odigraj(Poteza p) {
		if (plosca.polje[p.getI()][p.getJ()] != Box.PRAZNO) {
			return false;
		} else {
			if (p.getSmer() == Smer.DOL) {
				if (plosca.navpicneCrte[p.getI()][p.getJ()] == Crta.PRAZNO) {
					if (naPotezi == Igralec.RDEC){
						plosca.navpicneCrte[p.getI()][p.getJ()] = Crta.RDEC;
					} else {
						plosca.navpicneCrte[p.getI()][p.getJ()] = Crta.MODER;
					}
					if (polnBox(p) == false){
						naPotezi = naPotezi.nasprotnik();
					} else {
						if ()
					}
					return true;
				} else {
					return false;	
				}
			} else if (p.getSmer() == Smer.DESNO) {
				if (plosca.vodoravneCrte[p.getI()][p.getJ()] == Crta.PRAZNO) {
					if (naPotezi == Igralec.RDEC){
						plosca.vodoravneCrte[p.getI()][p.getJ()] = Crta.RDEC;
					} else {
						plosca.vodoravneCrte[p.getI()][p.getJ()] = Crta.MODER;
					}
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
