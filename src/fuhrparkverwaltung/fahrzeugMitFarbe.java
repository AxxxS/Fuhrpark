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
}
