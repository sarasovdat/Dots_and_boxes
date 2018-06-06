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
	
	private Random r;
	
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
		this.r = new Random();
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
		
		
		int ocenaNajboljse = 0;
		List<Poteza> najboljse = new LinkedList<Poteza>();
		
		if (naPotezi == jaz) {
			double v = Double.NEGATIVE_INFINITY;
			for (Poteza p : igra.poteze()) {
				Igra kopijaIgre = new Igra(igra);
				kopijaIgre.odigraj(p);
				OcenjenaPoteza oPoteza = minimax((igra.getNaPotezi() == kopijaIgre.getNaPotezi() ? k : k + 1), 
			             kopijaIgre, alpha, beta);
				int ocenaP = oPoteza.vrednost;
				//System.out.println(ocenaP);
				if ((double) ocenaP > v) {
					v = ocenaP;
					najboljse.clear();
					najboljse.add(p);
					alpha = Math.max(alpha, v);
					ocenaNajboljse = ocenaP;
					if (alpha >= beta) break;
					//System.out.println(v);
					//System.out.println(ocenaP);
				} else if (ocenaP == v) {
					najboljse.add(p);
				} 
			}

		} else {
			double v = Double.POSITIVE_INFINITY;
			for (Poteza p : igra.poteze()) {
				Igra kopijaIgre = new Igra(igra);
				kopijaIgre.odigraj(p);
				OcenjenaPoteza oPoteza = minimax((igra.getNaPotezi() == kopijaIgre.getNaPotezi() ? k : k + 1), 
			             kopijaIgre, alpha, beta);
				int ocenaP = oPoteza.vrednost;
				if ((double) ocenaP < v) {
					v = ocenaP;
					najboljse.clear();
					najboljse.add(p);
					beta = Math.min(beta, v);
					ocenaNajboljse = ocenaP;
					if (alpha >= beta) break;
				} else if (ocenaP == v) {
					najboljse.add(p);
				}
				
			}
		}
		
		// Vrnemo najboljso najdeno potezo in njeno oceno
		for (int i = 0; i < najboljse.size(); i++) {
			System.out.println(najboljse.get(i) + " " + ocenaNajboljse);
		}
		System.out.println("");
		assert (!najboljse.isEmpty());
		Poteza p = najboljse.get(r.nextInt(najboljse.size()));
		return new OcenjenaPoteza(p, ocenaNajboljse);
	}
}

