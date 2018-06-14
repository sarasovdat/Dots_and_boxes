package inteligenca;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;

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
		Thread.sleep(300);
		OcenjenaPoteza p = minimax(0, igra, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		assert (p.poteza != null);
		System.out.println("Minimax: " + p);
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
	private OcenjenaPoteza minimax(int k, Igra igra, double alpha, double beta) {
		Igralec naPotezi = null;
		switch (igra.stanje()) {
		case NA_POTEZI_RDEC: naPotezi = Igralec.RDEC; break;
		case NA_POTEZI_MODER: naPotezi = Igralec.MODER; break;
		// Igre je konec, ne moremo vrniti poteze, vrnemo vrednost pozicije
		case ZMAGA_RDEC:
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		case ZMAGA_MODER:
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		case NEODLOCENO:
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		}
		assert (naPotezi != null);
		if (k >= globina) {
			return new OcenjenaPoteza(null, Ocena.oceniPozicijo(jaz, igra));
		}
		
		double ocenaNajboljse = 0.0;
		List<Poteza> najboljse = new LinkedList<Poteza>();
		for (Poteza p : igra.poteze()) {
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigraj(p);
			
			// TODO POPRAVIIIIII!!!!!!!!
			double ocenaP = minimax((igra.getNaPotezi() == kopijaIgre.getNaPotezi() ? k : k + 1), 
					             kopijaIgre, alpha, beta).vrednost;
			// Ce je p boljsa poteza, si jo zabelezimo
			if (najboljse.isEmpty() // se nimamo kandidata za najboljso potezo
					|| ocenaP == ocenaNajboljse) { 
				najboljse.add(p);
				ocenaNajboljse = ocenaP;
			} else if ((naPotezi == jaz && ocenaP > ocenaNajboljse) // maksimiziramo
					|| (naPotezi != jaz && ocenaP < ocenaNajboljse)) // minimiziramo
			{
				najboljse = new LinkedList<Poteza>();
				najboljse.add(p);
				ocenaNajboljse = ocenaP;
			}
			// Popravimo alpha ali beta
			if (naPotezi == jaz) {
				alpha = Math.max(alpha, ocenaNajboljse);
			} else {
				beta = Math.min(beta, ocenaNajboljse);
			}
			if (beta <= alpha) {
				return new OcenjenaPoteza(null, ocenaNajboljse);
			}
		}
		// Vrnemo najboljso najdeno potezo in njeno oceno
		assert (!najboljse.isEmpty());
		Random r = new Random();
		Poteza p = najboljse.get(r.nextInt(najboljse.size()));
		return new OcenjenaPoteza(p, ocenaNajboljse);
	}
}

