package fuhrparkverwaltung;

/**
 * Enth�lt alle Informationen zu einem Fahrzeug
 */
public abstract class Fahrzeug {
	private int kmStand;
	private boolean defekt;
	private String kennzeichen;
	private fahrzeugKlasse klasse;

	/**
	 * Erstellt ein neues Fahrzeug anhand der �bergebenen Eigenschaften
	 *
	 * @param kmStand der aktuelle Kilometerstand
	 * @param kennzeichen  das Kennzeichen
	 * @param klasse  die Fahrzeugklasse
	 */
	public Fahrzeug(int kmStand, String kennzeichen, fahrzeugKlasse klasse) {
		this.kmStand = kmStand;
		this.defekt = false;
		this.kennzeichen = kennzeichen;
		this.klasse = klasse;
	}

	public int getKmStand() {
		return kmStand;
	}

	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}

	public boolean isDefekt() {
		return defekt;
	}

	public void setDefekt(boolean defekt) {
		this.defekt = defekt;
	}

	public String getKennzeichen() {
		return kennzeichen;
	}

	public fahrzeugKlasse getKlasse() {
		return klasse;
	}

}
