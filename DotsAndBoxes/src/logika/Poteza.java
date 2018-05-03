package logika;

public class Poteza {
	protected Smer smer;
	protected int i;
	protected int j;
	
	public Poteza(Smer smer, int i, int j) {
		this.smer = smer;
		this.i = i;
		this.j = j;
	}

	public Smer getSmer() {
		return smer;
	}


	public int getI() {
		return i;
	}

	
	public int getJ() {
		return j;
	}
}
