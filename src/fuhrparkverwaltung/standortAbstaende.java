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
	
	public static int berechneAbstand(char Standort1, char Standort2) {
		if(Standort1 != Standort2) {
			String kombination = "" + Standort1 + Standort2;
			String kombination2 = "" + Standort2 + Standort1;
			for (standortAbstaende abstaendeBuchstabe : standortAbstaende.values()) {
				if(abstaendeBuchstabe.name().equalsIgnoreCase(kombination) || abstaendeBuchstabe.name().equalsIgnoreCase(kombination2)) {
					return abstaendeBuchstabe.getAbstand();
				}
			}
			
			/*switch(kombination) {
				case "AB", "BA":
					return standortAbstaende.AB.getAbstand();
				case "AC", "CA":
					return standortAbstaende.AC.getAbstand();
				case "AD", "DA":
					return standortAbstaende.AD.getAbstand();
				case "AE", "EA":
					return standortAbstaende.AE.getAbstand();
				case "AF", "FA":
					return standortAbstaende.AF.getAbstand();
				case "AP", "PA":
					return standortAbstaende.AP.getAbstand();
				case "BC", "CB":
					return standortAbstaende.BC.getAbstand();
				case "BD", "DB":
					return standortAbstaende.BD.getAbstand();
				case "BE", "EB":
					return standortAbstaende.BE.getAbstand();
				case "BF", "FB":
					return standortAbstaende.BF.getAbstand();
				case "BP", "PB":
					return standortAbstaende.BP.getAbstand();
				case "CD", "DC":
					return standortAbstaende.CD.getAbstand();
				case "CE", "EC":
					return standortAbstaende.CE.getAbstand();
				case "CF", "FC":
					return standortAbstaende.CF.getAbstand();
				case "CP", "PC":
					return standortAbstaende.CP.getAbstand();
				case "DE", "ED":
					return standortAbstaende.DE.getAbstand();
				case "DF", "FD":
					return standortAbstaende.DF.getAbstand();
				case "DP", "PD":
					return standortAbstaende.DP.getAbstand();
				case "EF", "FE":
					return standortAbstaende.EF.getAbstand();
				case "EP", "PE":
					return standortAbstaende.EP.getAbstand();
				case "FP", "PF":
					return standortAbstaende.FP.getAbstand();
			}*/
		}
		return 0;
	}
	

}
