package logika;

public class Poteza {
	protected Smer smer;
	protected int vis;
	protected int sir;
	
	public Poteza(Smer smer, int vis, int sir) {
		this.smer = smer;
		this.vis = vis;
		this.sir = sir;
	}

	public Smer getSmer() {
		return smer;
	}


	public int getVis() {
		return vis;
	}
	

	
	public int getSir() {
		return sir;
	}
}
