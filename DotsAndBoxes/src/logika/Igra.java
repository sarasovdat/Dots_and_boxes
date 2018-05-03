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
	 * @return True, ce crta zapre box
	 */
	public boolean polnBox (Poteza p) {
		//ce je crta na robu, preverimo za en box
		if (p.getI() == 0 && p.getSmer() == Smer.DESNO) {
			if (plosca.navpicneCrte[p.getI()][p.getJ()] != Crta.PRAZNO 
					&& plosca.navpicneCrte[p.getI()][p.getJ() + 1] != Crta.PRAZNO
					&& plosca.vodoravneCrte[p.getI() + 1][p.getJ()] != Crta.PRAZNO) {
				return true;
			} else {
				return false;
			}
		} else if (p.getI() == Plosca.VISINA && p.getSmer() == Smer.DESNO) {
			if (plosca.navpicneCrte[p.getI() - 1][p.getJ()] != Crta.PRAZNO 
					&& plosca.navpicneCrte[p.getI() - 1][p.getJ() + 1] != Crta.PRAZNO
					&& plosca.vodoravneCrte[p.getI() - 1][p.getJ()] != Crta.PRAZNO) {
				return true;
			} else {
				return false;
			}
		} else if (p.getJ() == 0 && p.getSmer() == Smer.DOL) {
			if (plosca.vodoravneCrte[p.getI()][p.getJ()] != Crta.PRAZNO 
					&& plosca.vodoravneCrte[p.getI() + 1][p.getJ()] != Crta.PRAZNO
					&& plosca.navpicneCrte[p.getI()][p.getJ() + 1] != Crta.PRAZNO) {
				return true;
			} else {
				return false;
			}
		} else if (p.getJ() == Plosca.SIRINA && p.getSmer() == Smer.DOL) {
			if (plosca.vodoravneCrte[p.getI()][p.getJ() - 1] != Crta.PRAZNO 
					&& plosca.vodoravneCrte[p.getI() + 1][p.getJ() - 1] != Crta.PRAZNO
					&& plosca.navpicneCrte[p.getI()][p.getJ() - 1] != Crta.PRAZNO) {
				return true;
			} else {
				return false;
			}
		//Sicer preverimo dva boxa
		} else {
			if (p.getSmer() == Smer.DESNO) {
				//ZGORNJI
				if (plosca.navpicneCrte[p.getI() - 1][p.getJ()] != Crta.PRAZNO 
						&& plosca.navpicneCrte[p.getI() - 1][p.getJ() + 1] != Crta.PRAZNO
						&& plosca.vodoravneCrte[p.getI() - 1][p.getJ()] != Crta.PRAZNO) {
					return true;
				//SPODNJI
				} else if (plosca.navpicneCrte[p.getI()][p.getJ()] != Crta.PRAZNO 
						&& plosca.navpicneCrte[p.getI()][p.getJ() + 1] != Crta.PRAZNO
						&& plosca.vodoravneCrte[p.getI() + 1][p.getJ()] != Crta.PRAZNO) {
					return true;
				}
			} else if (p.getSmer() == Smer.DOL) {
				//DESNI
				if (plosca.vodoravneCrte[p.getI()][p.getJ()] != Crta.PRAZNO 
						&& plosca.vodoravneCrte[p.getI() + 1][p.getJ()] != Crta.PRAZNO
						&& plosca.navpicneCrte[p.getI()][p.getJ() + 1] != Crta.PRAZNO) {
					return true;
				} else if (plosca.vodoravneCrte[p.getI()][p.getJ() - 1] != Crta.PRAZNO 
						&& plosca.vodoravneCrte[p.getI() + 1][p.getJ() - 1] != Crta.PRAZNO
						&& plosca.navpicneCrte[p.getI()][p.getJ() - 1] != Crta.PRAZNO) {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return True, èe je polje na poziciji [i][j] zapolnjeno.
	 */
	public boolean kateriBox(int i, int j) {
		if (plosca.navpicneCrte[i][j] != Crta.PRAZNO 
				&& plosca.navpicneCrte[i][j] != Crta.PRAZNO
				&& plosca.vodoravneCrte[i][j] != Crta.PRAZNO
				&& plosca.vodoravneCrte[i][j] != Crta.PRAZNO) {
			return true;
		} else {
			return false;
		}
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
