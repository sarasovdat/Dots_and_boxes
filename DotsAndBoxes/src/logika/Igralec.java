package logika;

public enum Igralec {
	RDEC, MODER;
	
	public Igralec nasprotnik() {
		return (this == RDEC ? MODER : RDEC);
	}
	
}
