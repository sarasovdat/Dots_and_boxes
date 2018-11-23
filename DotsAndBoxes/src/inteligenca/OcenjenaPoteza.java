package inteligenca;

import logika.Poteza;

/**
 * Poteza z oceno, kako dobra je ocena.
 * Poteza je lahko tudi null (ce je konec igre), v tem primeru pove, kako dobra je pozicija
 * 
 * @author Sara
 *
 */
public class OcenjenaPoteza {
	Poteza poteza;
	double vrednost;
	
	public OcenjenaPoteza(Poteza poteza, double vrednost) {
		super();
		this.poteza = poteza;
		this.vrednost = vrednost;
	}

	@Override
	public String toString() {
		return "OcenjenaPoteza [poteza=" + poteza + ", vrednost=" + vrednost + "]";
	}
	
	
}
