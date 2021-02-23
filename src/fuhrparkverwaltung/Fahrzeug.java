package fuhrparkverwaltung;

/**
 * Standard Fahrzeug Klasse
 */
public class Fahrzeug {
	private int kmStand;
	private boolean defekt;
	private String kennzeichen;
	private fahrzeugKlasse klasse;

	/**
	 * Erstellt ein Fahrzeug, welches keine weitere Spezifikation als die Klasse hat.
	 *
	 * @param kmStand int den aktuellen Kilometerstand des Fahrzeugs
	 * @param kennzeichen  ein Kennzeichen
	 * @param klasse  Die Klasse des Fahrzeuges
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

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	public fahrzeugKlasse getKlasse() {
		return klasse;
	}

	public void setKlasse(fahrzeugKlasse klasse) {
		this.klasse = klasse;
	}
	
}
