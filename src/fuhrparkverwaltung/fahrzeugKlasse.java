package fuhrparkverwaltung;

/**
 * Enum für die Fahrzeugklassen Sportwagen, Kompaktklasse, LuxusKlasse, Kleinbus und Transporter
 */
public enum fahrzeugKlasse {
	SP("Sportwagen"), KO("Kompaktklasse"), LU("Luxusklasse"), KL("Kleinbus"), TR("Transporter");
	
	private String bezeichnung;
	
	fahrzeugKlasse(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}
	
	
}
