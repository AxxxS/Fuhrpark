package fuhrparkverwaltung;
/**
 * Unterklasse von Fahrzeuge und Fahrzeug mit dem Attribut PS-Zahl
 */
public class FahrzeugMitPS extends Fahrzeug {
	private int ps;
	/**
	 * Erstellt ein Objekt der Klasse Fahrzeug mit PS-Zahl.
	 * @param kmStand aktueller Kilometerstand
	 * @param kennzeichen ein Kennzeichen des Fahrzeugs
	 * @param ps	die PS-Zahl des Fahrzeugs
	 * @param klasse die Klasse des Fahrzeugs
	 */
	public FahrzeugMitPS(int kmStand, String kennzeichen, int ps, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.ps = ps;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
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
				+ "		PS: " + this.getPs() + "\n"
				+ "		Defekt: " + this.isDefekt() 
		);
	}
}

