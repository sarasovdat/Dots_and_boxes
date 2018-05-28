package inteligenca;

import javax.swing.SwingWorker;

import gui.GlavnoOkno;
import logika.Igra;
import logika.Igralec;
import logika.Poteza;

/**
 * Inteligenca, ki uporabi algoritem minimax
 * 
 * @author Sara
 *
 */
public class MiniMax extends SwingWorker<Poteza, Object> {
	
	/** 
	 * Glavno okno, v katerem poteka ta igra
	 */
	private GlavnoOkno okno;
	
	/**
	 * Globina, do katere pregleduje minimax
	 */
	private int globina;
	
	/**
	 * Ali je racunalnik rdec ali moder
	 */
	private Igralec jaz;
	
	/**
	 * 
	 * @param okno : glavno okno, v katerem vlecemo poteze
	 * @param globina : koliko potez naprej gledamo
	 * @param jaz : koga igramo
	 * 
	 */
	public MiniMax (GlavnoOkno okno, int globina, Igralec jaz) {
		this.okno = okno;
		this.globina = globina;
		this.jaz = jaz;	
	}

	@Override
	protected Poteza doInBackground() throws Exception {
		Igra igra = okno.kopirajIgro();
		Thread.sleep(700);
		OcenjenaPoteza p = minimax(0, igra);
		assert (p.poteza != null);
		return p.poteza;
	}
	
	@Override
	public void done() {
		try {
			Poteza p = this.get();
			if (p != null) { 
				okno.odigraj(p);
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 
	 * @param k : stevec globine
	 * @param igra
	 * @return najboljsa poteza (ali null, ce je ni) in ocena najboljse poteze
	 * 
	 */
	private OcenjenaPoteza minimax(int k, Igra igra) {
		Igralec naPotezi = null;
		// Ugotovimo, ali je konec, ali je kdo na potezi
		switch (igra.stanje()) {
		case NA_POTEZI_RDEC: naPotezi = Igralec.RDEC; break;
		case NA_POTEZI_MODER: naPotezi = Igralec.MODER; break;
		// Igre je konec, ne moremo vrniti poteze, vrnemo vrednost pozicije
		case ZMAGA_RDEC:
			return new OcenjenaPoteza(null, (jaz == Igralec.RDEC ? Ocena.ZMAGA : Ocena.PORAZ));
		case ZMAGA_MODER:
			return new OcenjenaPoteza(null, (jaz == Igralec.MODER ? Ocena.ZMAGA : Ocena.PORAZ));
		case NEODLOCENO:
			return new OcenjenaPoteza(null, Ocena.NEODLOCENO);
		}
		assert (naPotezi != null);
		// Nekdo je na potezi, ugotovimo, kaj se splaca igrati
		if (k >= globina) {
			// dosegli smo najvecjo dovoljeno globino, zato
			// ne vrnemo poteze, ampak samo oceno pozicije
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		}
		// Hranimo najboljso do sedaj videno potezo in njeno oceno.
		// Tu bi bilo bolje imeti seznam do sedaj videnih najboljsih potez, ker je lahko
		// v neki poziciji vec enakovrednih najboljsih potez. Te bi lahko zbrali
		// v seznam, potem pa vrnili nakljucno izbrano izmed najboljsih potez, kar bi
		// popestrilo igro racunalnika.
		Poteza najboljsa = null;
		int ocenaNajboljse = 0;
		for (Poteza p : igra.poteze()) {
			// V kopiji igre odigramo potezo p
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigraj(p);
			// Izracunamo vrednost pozicije po odigrani potezi p
			int ocenaP = minimax(k+1, kopijaIgre).vrednost;
			// Ce je p boljsa poteza, si jo zabelezimo
			if (najboljsa == null // se nimamo kandidata za najboljso potezo
				|| (naPotezi == jaz && ocenaP > ocenaNajboljse) // maksimiziramo
				|| (naPotezi != jaz && ocenaP < ocenaNajboljse) // minimiziramo
				) {
				najboljsa = p;
				ocenaNajboljse = ocenaP;
			}
		}
		// Vrnemo najboljso najdeno potezo in njeno oceno
		assert (najboljsa != null);
		return new OcenjenaPoteza(najboljsa, ocenaNajboljse);
	}
}

