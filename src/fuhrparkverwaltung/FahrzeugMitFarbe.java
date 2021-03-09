package fuhrparkverwaltung;

/**
 * Unterklasse von Fahrzeug, die eine Farbe als zusätzliches Attribut enthält
 */
public class FahrzeugMitFarbe extends Fahrzeug {
	private String farbe;

	/**
	 * Erstellt ein Objekt der Klasse Fahrzeug mit Farbe.
	 * @param kmStand aktueller Kilometerstand
	 * @param kennzeichen das Kennzeichen
	 * @param farbe	die Farbe
	 * @param klasse die Fahrzeugklasse
	 */
	public FahrzeugMitFarbe(int kmStand, String kennzeichen, String farbe, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.farbe = farbe;
	}

	public String getFarbe() {
		return farbe;
	}

	/**
	 * Erstellt eine Zeichenketten-Darstellung mit allen Information zum Fahrzeug
	 * @return die Zeichenketten-Darstellung zum Fahrzeug
	 */
	@Override
	public String toString() {
		return("\t" + this.getKennzeichen() + "\n"
				+ "\t- Klasse: " + this.getKlasse().getBezeichnung() + "\n"
				+ "\t- Kilometerstand: " + this.getKmStand() + " \n"
				+ "\t- Farbe: " + this.getFarbe() + "\n"
				+ "\t- Defekt: " + this.isDefekt() 
		);
	}
}
