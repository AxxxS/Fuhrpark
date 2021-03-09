package fuhrparkverwaltung;
/**
 * Unterklasse von Fahrzeug, die eine PS-Zahl als zusätzliches Attribut enthält
 */
public class FahrzeugMitPS extends Fahrzeug {
	private int ps;
	/**
	 * Erstellt ein Objekt der Klasse Fahrzeug mit PS-Zahl.
	 * @param kmStand aktueller Kilometerstand
	 * @param kennzeichen das Kennzeichen
	 * @param ps	die PS-Zahl 
	 * @param klasse die Fahrzeugklasse
	 */
	public FahrzeugMitPS(int kmStand, String kennzeichen, int ps, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.ps = ps;
	}

	public int getPs() {
		return ps;
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
				+ "\t- PS: " + this.getPs() + "\n"
				+ "\t- Defekt: " + this.isDefekt() 
		);
	}
}

