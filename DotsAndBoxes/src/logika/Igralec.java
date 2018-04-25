package logika;

public enum Igralec {
	IGRALEC1, IGRALEC2;
	
	public Igralec nasprotnik() {
		return (this == IGRALEC1 ? IGRALEC2 : IGRALEC1);
	}
	
}
