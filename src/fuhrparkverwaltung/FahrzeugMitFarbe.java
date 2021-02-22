package fuhrparkverwaltung;

/**
 * Unterklasse von Fahrzeuge und Fahrzeug mit dem Attribut Farbe
 */
public class FahrzeugMitFarbe extends Fahrzeug {
	private String farbe;

	/**
	 * Erstellt ein Objekt der Klasse Fahrzeug mit Farbe.
	 * @param kmStand aktueller Kilometerstand
	 * @param kennzeichen ein Kennzeichen des Fahrzeugs
	 * @param farbe	die Farbe des Fahrzeugs
	 * @param klasse die Klasse des Fahrzeugs
	 */
	public FahrzeugMitFarbe(int kmStand, String kennzeichen, String farbe, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.farbe = farbe;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	/**
	 * Nimmt alle Atribute des Objekts und genereriert einen String mit allen Information zum zugehörigen Objekt.
	 * Beinhaltet Informationen, wie Klasse, Kilometerstand, Farbe und den aktuellen Defektstatus.
	 * @return Gibt einen String mit den Information zum Fahrzeug zurück
	 */
	@Override
	public String toString() {
		return(this.getKennzeichen() + "\n"
				+ "		Klasse: " + this.getKlasse().getBezeichnung() + "\n"
				+ "		Kilometerstand: " + this.getKmStand() + " \n"
				+ "		Farbe: " + this.getFarbe() + "\n"
				+ "		Defekt: " + this.isDefekt() 
		);
	}
}
