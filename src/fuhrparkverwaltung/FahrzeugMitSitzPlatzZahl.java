package fuhrparkverwaltung;

/**
 * Unterklasse von Fahrzeuge und Fahrzeug mit dem Attribut Sitzplatzzahl
 */
public class FahrzeugMitSitzPlatzZahl extends Fahrzeug {
	private int sitzPlatzZahl;

	/**
	 * Erstellt ein Objekt der Klasse Fahrzeug mit Sitzplatzzahl.
	 * @param kmStand aktueller Kilometerstand
	 * @param kennzeichen ein Kennzeichen des Fahrzeugs
	 * @param sitzPlatzZahl	die Sitzplatzzahl des Fahrzeugs
	 * @param klasse die Klasse des Fahrzeugs
	 */
	public FahrzeugMitSitzPlatzZahl(int kmStand, String kennzeichen, int sitzPlatzZahl, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.sitzPlatzZahl = sitzPlatzZahl;
	}

	public int getSitzPlatzZahl() {
		return sitzPlatzZahl;
	}

	public void setSitzPlatzZahl(int sitzPlatzZahl) {
		this.sitzPlatzZahl = sitzPlatzZahl;
	}
	/**
	 * Nimmt alle Atribute des Fahrzeugs und genereriert einen String mit allen Information zum zugehörigen Fahrzeugs.
	 * Beinhaltet Informationen, wie Klasse, Kilometerstand, Farbe und den aktuellen Defektstatus.
	 * @return Gibt einen String mit den Information zum Fahrzeug zurück
	 */
	@Override
	public String toString() {
		return(this.getKennzeichen() + "\n"
				+ "		Klasse: " + this.getKlasse().getBezeichnung() + "\n"
				+ "		Kilometerstand: " + this.getKmStand() + " \n"
				+ "		Sitzpl�tze: " + this.getSitzPlatzZahl() + "\n"
				+ "		Defekt: " + this.isDefekt() 
		);
	}
}
