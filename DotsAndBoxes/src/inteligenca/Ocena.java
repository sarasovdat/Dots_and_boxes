package inteligenca;

import logika.Box;
import logika.Igra;
import logika.Igralec;
import logika.Plosca;
import logika.Stanje;

/**
 * Ocena trenutne pozicije
 * @author Sara
 *
 */
public class Ocena {
	public static final int ZMAGA = (1 << 20);
	public static final int PORAZ = - ZMAGA;
	public static final int NEODLOCENO = 0;
	
	public static int vrednostBoxa(int vrednost) {
		int k = Math.max(Plosca.VISINA, Plosca.SIRINA);
		assert (vrednost < k);
		return (ZMAGA >> (4 * (k - vrednost))); // hevristicna ocena
	}
	
	/**
	 * 
	 * @param jaz igralec, ki zeli oceno
	 * @param igra trenutno stanje igre
	 * @return ocena vrednosti pozicije
	 */
	public static int oceniPozicijo(Igralec jaz, Igra igra) {
		Igralec naPotezi = null;
		switch (igra.stanje()) {
		case ZMAGA_RDEC:
			return (jaz == Igralec.RDEC ? ZMAGA : PORAZ);
		case ZMAGA_MODER:
			return (jaz == Igralec.MODER ? ZMAGA : PORAZ);
		case NEODLOCENO:
			return NEODLOCENO;
		case NA_POTEZI_RDEC:
			naPotezi = jaz.RDEC;
		case NA_POTEZI_MODER:
			naPotezi = jaz.MODER;
			
		Plosca plosca = igra.getPlosca();
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
		if (naPotezi == Igralec.RDEC) { steviloBoxovModer /= 2; }
		if (naPotezi == Igralec.MODER) { steviloBoxovRdec /= 2; }
		return (jaz == Igralec.RDEC ? steviloBoxovModer - steviloBoxovRdec : steviloBoxovModer - steviloBoxovRdec);
			
		}
		return 0;
	}
}
