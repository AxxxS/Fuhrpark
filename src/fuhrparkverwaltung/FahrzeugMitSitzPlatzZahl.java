package fuhrparkverwaltung;

/**
 * Unterklasse von Fahrzeug, die eine Sitzplatz-Zahl als zusätzliches Attribut enthält
 */
public class FahrzeugMitSitzPlatzZahl extends Fahrzeug {
	private int sitzPlatzZahl;

	/**
	 * Erstellt ein Objekt der Klasse Fahrzeug mit Sitzplatzzahl.
	 * @param kmStand aktueller Kilometerstand
	 * @param kennzeichen das Kennzeichen 
	 * @param sitzPlatzZahl	die Sitzplatzzahl
	 * @param klasse die Fahrzeugklasse
	 */
	public FahrzeugMitSitzPlatzZahl(int kmStand, String kennzeichen, int sitzPlatzZahl, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.sitzPlatzZahl = sitzPlatzZahl;
	}

	public int getSitzPlatzZahl() {
		return sitzPlatzZahl;
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
				+ "\t- Sitzplaetze: " + this.getSitzPlatzZahl() + "\n"
				+ "\t- Defekt: " + this.isDefekt() 
		);
	}
}
