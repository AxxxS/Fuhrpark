package fuhrparkverwaltung;

/**
 * Enum welche Abstände der Standort beinhaltet.
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
	

}
