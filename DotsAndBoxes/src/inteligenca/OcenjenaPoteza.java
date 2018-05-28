package inteligenca;

import logika.Poteza;

/**
 * Poteza z oceno, kako dobra je ocena.
 * Poteza je lahko tudi null (ce je konec igre), v tem primeru pove, kako dobra je pozicija.
 * 
 * @author Sara
 *
 */
public class OcenjenaPoteza {
	Poteza poteza;
	int vrednost;
	
	public OcenjenaPoteza(Poteza poteza, int vrednost) {
		super();
		this.poteza = poteza;
		this.vrednost = vrednost;
	}
}
