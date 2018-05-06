package logika;

public enum Igralec {
	RDEC, MODER;
	
	public Igralec nasprotnik() {
		return (this == RDEC ? MODER : RDEC);
	}
	
	public Crta crta() {
		return (this == RDEC ? Crta.RDEC : Crta.MODER);
	}
	
	public Box box() {
		return (this == RDEC ? Box.RDEC : Box.MODER);
	}
}