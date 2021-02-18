package fuhrparkverwaltung;

public class fahrzeugMitSitzPlatzZahl extends Fahrzeug {
	private int sitzPlatzZahl;
	
	public fahrzeugMitSitzPlatzZahl(int kmStand, String kennzeichen, int sitzPlatzZahl, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.sitzPlatzZahl = sitzPlatzZahl;
	}

	public int getSitzPlatzZahl() {
		return sitzPlatzZahl;
	}

	public void setSitzPlatzZahl(int sitzPlatzZahl) {
		this.sitzPlatzZahl = sitzPlatzZahl;
	}
}
