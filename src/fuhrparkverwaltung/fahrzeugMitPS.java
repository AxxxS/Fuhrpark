package fuhrparkverwaltung;

public class fahrzeugMitPS extends Fahrzeug {
	private int ps;

	public fahrzeugMitPS(int kmStand, String kennzeichen, int ps, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.ps = ps;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}
}

