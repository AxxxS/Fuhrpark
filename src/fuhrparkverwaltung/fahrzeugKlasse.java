package fuhrparkverwaltung;

/**
 * Enum für die Fahrzeugklassen Sportwagen, Kompaktklasse, LuxusKlasse, Kleinbus und Transporter
 */
public enum fahrzeugKlasse {
	SP("Sportwagen"), KO("Kompaktklasse"), LU("Luxusklasse"), KL("Kleinbus"), TR("Transporter");
	
	private String bezeichnung;
	
	/**
	 * Erstellt ein neues fahrzeugKlasse-Objekt anhand der Bezeichnung der Klasse
	 * @param bezeichnung Bezeichnung der Fahrzeugklasse
	 */
	fahrzeugKlasse(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}
	
	
}
