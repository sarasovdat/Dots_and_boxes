package logika;

public class Poteza {
	protected Smer smer;
	protected int visina;
	protected int sirina;
	
	public Poteza(Smer smer, int vis, int sir) {
		this.smer = smer;
		this.visina = vis;
		this.sirina = sir;
	}

	public Smer getSmer() {
		return smer;
	}


	public int getVis() {
		return visina;
	}
	

	
	public int getSir() {
		return sirina;
	}

	@Override
	public String toString() {
		return "Poteza [smer=" + smer + ", vis=" + visina + ", sir=" + sirina + "]";
	}
	
	
}
