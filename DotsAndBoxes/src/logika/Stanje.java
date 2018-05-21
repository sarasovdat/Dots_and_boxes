package logika;

public enum Stanje {
	NA_POTEZI_RDEC, 
	NA_POTEZI_MODER, 
	ZMAGA_RDEC, 
	ZMAGA_MODER, 
	NEODLOCENO;
	
	private int rdeciKvadratki;
	private int modriKvadratki;
	

	public int getRdeciKvadratki() {
		return rdeciKvadratki;
	}

	public void setRdeciKvadratki(int rdeciKvadratki) {
		this.rdeciKvadratki = rdeciKvadratki;
	}

	public int getModriKvadratki() {
		return modriKvadratki;
	}

	public void setModriKvadratki(int modriKvadratki) {
		this.modriKvadratki = modriKvadratki;
	}
	
}

