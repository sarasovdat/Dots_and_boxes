package inteligenca;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.SwingWorker;

import gui.GlavnoOkno;
import logika.Igra;
import logika.Poteza;

public class NakljucnaInteligenca extends SwingWorker<Poteza, Object> {
	private GlavnoOkno okno;
	
	public NakljucnaInteligenca(GlavnoOkno okno) {
		this.okno = okno;
		
	}

	@Override
	protected Poteza doInBackground() throws Exception {
		Igra igra = okno.kopirajIgro();
		Thread.sleep(100);
		Random r = new Random();
		LinkedList<Poteza> poteze = igra.poteze();
		Poteza p = poteze.get(r.nextInt(poteze.size()));
		return p;
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
}
