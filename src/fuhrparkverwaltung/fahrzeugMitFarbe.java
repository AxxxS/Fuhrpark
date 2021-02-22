package fuhrparkverwaltung;

public class fahrzeugMitFarbe extends Fahrzeug {
	private String farbe;
	
	public fahrzeugMitFarbe(int kmStand, String kennzeichen, String farbe, fahrzeugKlasse klasse) {
		super(kmStand, kennzeichen, klasse);
		this.farbe = farbe;
		
		
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

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
