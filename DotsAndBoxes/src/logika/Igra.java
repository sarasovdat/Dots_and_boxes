package logika;

import java.util.LinkedList;


public class Igra {
	protected Plosca plosca;
	protected Igralec naPotezi;
	
	public Igra() {
		plosca = new Plosca();
		// Naj vedno zacne rdec
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
		for (int vis = 0; vis < Plosca.VISINA; vis ++) {
			for (int sir = 0; sir < Plosca.SIRINA; sir ++) {
				if (plosca.getPolje()[vis][sir] == Box.RDEC) {
					steviloBoxovRdec ++;
				}
				else if (plosca.getPolje()[vis][sir] == Box.MODER) {
					steviloBoxovModer ++;
				}
			}
		}
		// KONEC IGRE (ko so zapolnjeni vsi boxi)
		if (steviloBoxovRdec + steviloBoxovModer == Plosca.VISINA * Plosca.SIRINA) {
			// Kateri igralec ima vec zapoljnenih boxov?
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
		// NI SE KONEC, povemo kdo je na potezi
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
	 * @return Seznam vseh moznih potez
	 */
	public LinkedList<Poteza> poteze(){
		LinkedList<Poteza> moznePoteze = new LinkedList<Poteza>();
		//Preverimo vodoravne crte
		for (int vis = 0; vis < (Plosca.VISINA + 1); vis ++) {
			for (int sir = 0; sir < Plosca.SIRINA; sir ++) {
				if(plosca.getVodoravneCrte()[vis][sir] == Crta.PRAZNO) {
					moznePoteze.add(new Poteza(Smer.DESNO, vis, sir));
				}
			}
		}
		// Preverimo navpicne crte
		for (int vis = 0; vis < Plosca.VISINA; vis ++) {
			for (int sir = 0; sir < (Plosca.SIRINA + 1); sir ++) {
				if(plosca.getNavpicneCrte()[vis][sir] == Crta.PRAZNO) {
					moznePoteze.add(new Poteza(Smer.DOL, vis, sir));
				}
			}
		}
		return moznePoteze;	
	}
	/**
	 * 
	 * @param p
	 * @return Seznam lokacij vseh sosednjih kvadratov poteze p
	 */
	public LinkedList<Lokacija> sosednjiBoxi(Poteza p){
		LinkedList<Lokacija> sosednji = new LinkedList<Lokacija>();			
		Lokacija l = new Lokacija();
		Lokacija k = new Lokacija();
		if (p.getVis() == 0 || p.getSir() == 0 || p.getVis() == Plosca.VISINA || p.getSir() == Plosca.SIRINA) {	
		// Primeri, ko dodamo le en box na seznam lokacij
			if (p.getVis() == 0 && p.getSmer() == Smer.DESNO) {
				l.vis = 0;
				l.sir = p.getSir();
			} else if (p.getVis() == Plosca.VISINA && p.getSmer() == Smer.DESNO) {
				l.vis = (p.getVis() - 1);
				l.sir = p.getSir();
			} else if (p.getSir() == 0 && p.getSmer() == Smer.DOL) {
				l.vis = p.getVis();
				l.sir = 0;
			} else if (p.getSir() == Plosca.SIRINA && p.getSmer() == Smer.DOL) {
				l.vis = p.getVis();
				l.sir = (p.getSir() - 1);
			}
			sosednji.add(l);
		} else {
		// Primeri, ko dodamo dva boxa na seznam lokacij
			if (p.getSmer() == Smer.DESNO) {
				l.vis = (p.getVis() - 1);
				l.sir = p.getSir();
				k.vis = p.getVis();
				k.sir = p.getSir();
			} else if (p.getSmer() == Smer.DOL) {
				l.vis = p.getVis();
				l.sir = (p.getSir() - 1);
				k.vis = p.getVis();
				k.sir = p.getSir();			
			}
			sosednji.add(l);
			sosednji.add(k);
		}
		return sosednji;
	}
	
	
	/**
	 * 
	 * @param l
	 * @return true, ce so vse crte, ki omejujejo kvadrat na lokaciji l ze zapolnjene
	 */
	public boolean jeObkrozen(Lokacija l) {
		if (plosca.getVodoravneCrte()[l.vis][l.sir] != Crta.PRAZNO
				&& plosca.getVodoravneCrte()[l.vis + 1][l.sir] != Crta.PRAZNO
				&& plosca.getNavpicneCrte()[l.vis][l.sir] != Crta.PRAZNO
				&& plosca.getNavpicneCrte()[l.vis][l.sir +1] != Crta.PRAZNO) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean zapolniKvadrate(Poteza p) {
		boolean smo_zapolnili = false;
		Box b = naPotezi.box();
		for (Lokacija l : sosednjiBoxi(p)) {
			if (jeObkrozen(l) && plosca.getPolje()[l.vis][l.sir] == Box.PRAZNO) {
				smo_zapolnili = true;
		        plosca.getPolje()[l.vis][l.sir] = b;
		    }
		}
		return smo_zapolnili;
	}
	
	
	/**
	 * 
	 * @param p
	 * @return True, ce je bila poteza uspesno odigrana
	 * 
	 */
	public boolean odigraj(Poteza p) {
		Crta c = naPotezi.crta();
		int vis = p.getVis();
		int sir = p.getSir();
		if (p.getSmer() == Smer.DOL) {
			if (plosca.getNavpicneCrte()[vis][sir] != Crta.PRAZNO) {
				return false;
			} else {
				plosca.getNavpicneCrte()[vis][sir] = c;
				boolean smo_zapolnili_kvadrat = zapolniKvadrate(p);
				if (!smo_zapolnili_kvadrat) {
					naPotezi = naPotezi.nasprotnik();
				}
				return true;
			}
		} else if (p.getSmer() == Smer.DESNO){
			if (plosca.getVodoravneCrte()[vis][sir] != Crta.PRAZNO) {
				return false;
			} else {
				plosca.getVodoravneCrte()[vis][sir] = c;
				boolean smo_zapolnili_kvadrat = zapolniKvadrate(p);
				if (!smo_zapolnili_kvadrat) {
					naPotezi = naPotezi.nasprotnik();
				}
				return true;
			}
		}
		// A je ok da tu vrne false? 
		return false;
	}
	
	public Plosca getPlosca() {
		return plosca;
	}

}
		