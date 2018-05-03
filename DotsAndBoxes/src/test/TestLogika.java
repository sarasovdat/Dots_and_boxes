package test;

import junit.framework.TestCase;
import logika.Igra;
import logika.Stanje;

public class TestLogika  extends TestCase {
	
	public void testIgra() {
		Igra igra = new Igra();
		//Na zacetku je na potezi igralec 1
		assertEquals(Stanje.NA_POTEZI_RDEC, igra.stanje());
		
	}

}
