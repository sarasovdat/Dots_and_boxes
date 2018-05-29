package inteligenca;


import logika.Box;
import logika.Igra;
import logika.Igralec;
import logika.Plosca;

/**
 * Ocena trenutne pozicije
 * 
 * @author Sara
 *
 */
public class Ocena {
	
	/**
	 * 
	 * @param jaz : igralec, ki zeli oceno
	 * @param igra : trenutno stanje igre
	 * @return ocena vrednosti pozicije
	 * 
	 */
	public static int oceniPozicijo(Igralec jaz, Igra igra) {
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
		return (jaz == Igralec.RDEC ? steviloBoxovRdec - steviloBoxovModer : steviloBoxovModer - steviloBoxovRdec);	
	}
}
