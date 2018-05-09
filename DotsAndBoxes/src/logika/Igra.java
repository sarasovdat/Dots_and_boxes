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
	 * Ko je igre konec, nastavi stevilo rdecih in modrih kvadratkov.
	 */
	public Stanje stanje() {
		int steviloBoxovRdec = 0;
		int steviloBoxovModer = 0;
		for (int i = 0; i < Plosca.VISINA; i++) {
			for (int j = 0; j < Plosca.SIRINA; j++) {
				if (plosca.polje[i][j] == Box.RDEC) {
					steviloBoxovRdec++;
				}
				else if (plosca.polje[i][j] == Box.MODER) {
					steviloBoxovModer++;
				}
			}
		}
		//KONEC IGRE (ko so zapolnjeni vsi boxi)
		if (steviloBoxovRdec + steviloBoxovModer == Plosca.VISINA * Plosca.SIRINA) {
			//Kateri igralec ima vec zapoljnenih boxov?
			if (steviloBoxovRdec > steviloBoxovModer) {
				Stanje s = Stanje.ZMAGA_RDEC;
				s.setRdeciKvadratki(steviloBoxovRdec);
				s.setModriKvadratki(steviloBoxovModer);
				return s;	
			}
			else if (steviloBoxovRdec < steviloBoxovModer) {
				Stanje s = Stanje.ZMAGA_MODER;
				s.setRdeciKvadratki(steviloBoxovRdec);
				s.setModriKvadratki(steviloBoxovModer);
				return s;
			}
			else {
				Stanje s = Stanje.NEODLOCENO;
				s.setRdeciKvadratki(steviloBoxovRdec);
				s.setModriKvadratki(steviloBoxovModer);
				return s;
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
			if (jeObkrozen(l) && plosca.polje[l.i][l.j] == Box.PRAZNO) {
				smo_zapolnili = true;
		        plosca.polje[l.i][l.j] = b;
		    }
		}
		return smo_zapolnili;
	}
	
	
	/**
	 * 
	 * @param p
	 * @return True, ce je bila poteza uspesno odigrana.
	 * 
	 */
	public boolean odigraj(Poteza p) {
		Crta c = naPotezi.crta();
		int i = p.getI();
		int j = p.getJ();
		if (p.getSmer() == Smer.DOL) {
			if (plosca.navpicneCrte[i][j] != Crta.PRAZNO) {
				return false;
			} else {
				plosca.navpicneCrte[i][j] = c;
				boolean smo_zapolnili_kvadrat = zapolniKvadrate(p);
				if (!smo_zapolnili_kvadrat) {
					naPotezi = naPotezi.nasprotnik();
				}
				return true;
			}
		} else if (p.getSmer() == Smer.DESNO){
			if (plosca.vodoravneCrte[i][j] != Crta.PRAZNO) {
				return false;
			} else {
				plosca.vodoravneCrte[i][j] = c;
				boolean smo_zapolnili_kvadrat = zapolniKvadrate(p);
				if (!smo_zapolnili_kvadrat) {
					naPotezi = naPotezi.nasprotnik();
				}
				return true;
			}
		}
		//a je ok da tu vrne false? 
		return false;
	}

}
		