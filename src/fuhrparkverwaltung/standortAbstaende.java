package fuhrparkverwaltung;

/**
 * Enum, das die Abstände zwischen allen Standorten beinhaltet.
 */
public enum standortAbstaende {
	
	AB(50),
	AC(100),
	AD(200),
	AE(300),
	AF(400),
	AP(125),
	BC(70),
	BD(100),
	BE(250),
	BF(300),
	BP(100),
	CD(125),
	CE(150),
	CF(220),
	CP(300),
	DE(130),
	DF(140),
	DP(200),
	EF(160),
	EP(220),
	FP(170);
	
	private int abstand;
	
	standortAbstaende(int abstand) {
		this.abstand = abstand;
	}

	public int getAbstand() {
		return abstand;
	}
	
	/**
	 * Gibt den Abstand zwischen zwei Standorten aus
	 * @param Standort1 der Name/Buchstabe eines Standortes
	 * @param Standort2 der Name/Buchstabe eines Standortes
	 * @return der Abstand zwischen den Standorten
	 */
	public static int berechneAbstand(char Standort1, char Standort2) {
		if(Standort1 != Standort2) {
			String kombination = "" + Standort1 + Standort2;
			String kombination2 = "" + Standort2 + Standort1;
			for (standortAbstaende abstaendeBuchstabe : standortAbstaende.values()) {
				if(abstaendeBuchstabe.name().equalsIgnoreCase(kombination) || abstaendeBuchstabe.name().equalsIgnoreCase(kombination2)) {
					return abstaendeBuchstabe.getAbstand();
				}
			}
		}
		return 0;
	}
	

}
