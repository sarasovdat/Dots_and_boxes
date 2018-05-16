package gui;

public class Clovek extends Strateg {
	private GlavnoOkno okno;
	
	public Clovek(GlavnoOkno master) {
		this.okno = master;
	}

	@Override
	public void na_potezi() {
	}

	@Override
	public void prekini() {	
	}

	@Override
	public void klikni(int i, int j) {
		okno.odigraj(new Poteza(smer, i, j));	
	}
	
	

}
