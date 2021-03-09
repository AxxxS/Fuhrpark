package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Enthält alle Informationen und Methoden zu einem einzelnen Standort der Firma mit Stellplätzen
 */
public class Standort {
	private char name;
	private int anzahlStellplaetzeSPundKOundLU, anzahlStellplaetzeKLundTR;
	private ArrayList<Fahrzeug> stellplaetzeSPKOLU;
	private ArrayList<Fahrzeug> stellplaetzeKLTR;

	public char getName() {
		return name;
	}

	/**
	 * Erstellt einen Standort, auf welchem sich Fahrzeuge befinden.
	 * @param anzahlStellplaetzeSPKOLU Anzahl der Stellplätze für Fahrzeuge der Klassen Sportwagen, Kompaktklasse und Luxusklasse
	 * @param anzahlStellplaetzeKLTR Anzahl der Stellplätze für Fahrzeuge der Klassen Kleinbus und Transporter
	 * @param name Buchstabe der den Namen eines Standortes darstellt
	 */
	public Standort(int anzahlStellplaetzeSPKOLU, int anzahlStellplaetzeKLTR, char name) {
		this.anzahlStellplaetzeKLundTR = anzahlStellplaetzeKLTR;
		this.anzahlStellplaetzeSPundKOundLU = anzahlStellplaetzeSPKOLU;
		this.stellplaetzeKLTR = new ArrayList<>();
		this.stellplaetzeSPKOLU = new ArrayList<>();
		this.name = name;
	}

	/**
	 * Parkt ein Fahrzeug auf dem zugehörigen Parkplatz des Standorts, je nach Klasse des Fahrzeugs.
	 * @param fahrzeug ein Fahrzeug welches auf dem Parkplatz abgestellt werden soll
	 * @return Ergebnis der Operation, true wenn alles funktioniert hat, false wenn kein Platz mehr frei war
	 */
	public boolean fahrzeugParken(Fahrzeug fahrzeug) {
		switch(fahrzeug.getKlasse()) {
			case SP, KO, LU:
				if(this.stellplaetzeSPKOLU.size() < this.anzahlStellplaetzeSPundKOundLU) {
					this.stellplaetzeSPKOLU.add(fahrzeug);
					return true;
				}
				break;
			case KL, TR:
				if(this.stellplaetzeKLTR.size() < this.anzahlStellplaetzeKLundTR) {
					this.stellplaetzeKLTR.add(fahrzeug);
					return true;
				}
				break;
		}
		return false;
	}

	/**
	 * Erstellt eine Übersicht über die Anzahl von Fahrzeugen jeder Fahrzeugklasse, die sich am Standort befinden.
	 * @return eine HashMap, die jeder Fahrzeugklasse die auf dem Parkplatz abgestellte Anzahl an Fahrzeugen zuordnet
	 */
	public HashMap<fahrzeugKlasse, Integer> getUebersichtWelcheFahrzeuge() {
		int anzahlKL, anzahlTR, anzahlSP, anzahlKO, anzahlLU;
		anzahlKL = anzahlTR = anzahlSP = anzahlKO = anzahlLU = 0;
		for (Fahrzeug fahrzeug : stellplaetzeKLTR) {
			switch(fahrzeug.getKlasse()) {
				case KL:
					anzahlKL++;
					break;
				case TR: 
					anzahlTR++;
					break;
				default:
					break;
			}
		}
		for (Fahrzeug fahrzeug : stellplaetzeSPKOLU) {
			switch(fahrzeug.getKlasse()) {
				case KO: 
					anzahlKO++;
					break;
				case LU: 
					anzahlLU++;
					break;
				case SP: 
					anzahlSP++;
					break;
				default:
					break;
			}
		}
		
		HashMap<fahrzeugKlasse, Integer> ergebnis = new HashMap<>();
		ergebnis.put(fahrzeugKlasse.KL, anzahlKL);
		ergebnis.put(fahrzeugKlasse.TR, anzahlTR);
		ergebnis.put(fahrzeugKlasse.KO, anzahlKO);
		ergebnis.put(fahrzeugKlasse.LU, anzahlLU);
		ergebnis.put(fahrzeugKlasse.SP, anzahlSP);
		return ergebnis;
	}

	/**
	 * Erstellt eine Liste, welche alle Fahrzeuge enthält, die am Standort geparkt sind.
	 * @return eine ArrayList von Fahrzeugen an dem Standort
	 */
	public ArrayList<Fahrzeug> getAlleFahrzeuge() {
		ArrayList<Fahrzeug> ergebnis = new ArrayList<Fahrzeug>();
		ergebnis.addAll(stellplaetzeKLTR);
		ergebnis.addAll(stellplaetzeSPKOLU);
		return ergebnis;
	}
	
	/**
	 * Gibt eine Liste von Fahrzeugen am Standort aus, die alle einen bestimmten Wert im Sonderattribut stehen haben
	 * @param gesuchteKlasse Klasse der gesuchten Fahrzeuge, je nach gewünschtem Sonderattribut
	 * @param wert Wert des Sonderattributes
	 * @return Liste von allen Fahrzeugen am Standort mit dem gewünschten Wert im Sonderattribut
	 */
	public ArrayList<Fahrzeug> getFahrzeugeMitAttributUndWert(Class gesuchteKlasse, String wert) {
		ArrayList<Fahrzeug> ergebnis = new ArrayList<Fahrzeug>();
		
		if(gesuchteKlasse == FahrzeugMitFarbe.class) {
			for (Fahrzeug fahrzeug : this.stellplaetzeSPKOLU) {
				if(fahrzeug instanceof FahrzeugMitFarbe) {
					FahrzeugMitFarbe fzg = (FahrzeugMitFarbe) fahrzeug;
					if(fzg.getFarbe().equalsIgnoreCase(wert)) {
						ergebnis.add(fahrzeug);
					}
				}
			}
		} else if(gesuchteKlasse == FahrzeugMitPS.class) {
			for (Fahrzeug fahrzeug : this.stellplaetzeSPKOLU) {
				if(fahrzeug instanceof FahrzeugMitPS) {
					FahrzeugMitPS fzg = (FahrzeugMitPS) fahrzeug;
					if(fzg.getPs() == Integer.parseInt(wert)) {
						ergebnis.add(fahrzeug);
					}
				}
			}
		} else if(gesuchteKlasse == FahrzeugMitSitzPlatzZahl.class) {
			for (Fahrzeug fahrzeug : this.stellplaetzeKLTR) {
				if(fahrzeug instanceof FahrzeugMitSitzPlatzZahl) {
					FahrzeugMitSitzPlatzZahl fzg = (FahrzeugMitSitzPlatzZahl) fahrzeug;
					if(fzg.getSitzPlatzZahl() == Integer.parseInt(wert)) {
						ergebnis.add(fahrzeug);
					}
				}
			}
		}
		
		return ergebnis;
	}

	/**
	 * Entfernt / Parkt ein bestimmtes Fahrzeug am Standort aus
	 * @param kennzeichen Kennzeichen des auszuparkenden Fahrzeuges
	 * @return das ausgeparkte Fahrzeug, oder null sofern das Fahrzeug mit dem Kennzeichen nicht gefunden wurde
	 */
	public Fahrzeug fahrzeugAusparken(String kennzeichen) {
		for (Fahrzeug fahrzeug : stellplaetzeKLTR) {
			if(fahrzeug.getKennzeichen().equalsIgnoreCase(kennzeichen)) {
				stellplaetzeKLTR.remove(stellplaetzeKLTR.indexOf(fahrzeug));
				return fahrzeug;
			}
		}
		
		for (Fahrzeug fahrzeug : stellplaetzeSPKOLU) {
			if(fahrzeug.getKennzeichen().equalsIgnoreCase(kennzeichen)) {
				stellplaetzeSPKOLU.remove(stellplaetzeSPKOLU.indexOf(fahrzeug));
				return fahrzeug;
			}
		}
		
		return null;
	}
	
	/**
	 * Setzt die Eigenschaft -defekt- eines bestimmten Fahrzeuges auf true
	 * @param kennzeichen Kennzeichen des Fahrzeuges
	 * @return Ergebnis der Operation, true wenn alles funktioniert hat, false wenn das Fahrzeug nicht gefunden wurde
	 */
	public boolean fahrzeugAufDefektSetzen(String kennzeichen) {
		for (Fahrzeug fahrzeug : stellplaetzeKLTR) {
			if(fahrzeug.getKennzeichen().equalsIgnoreCase(kennzeichen)) {
				fahrzeug.setDefekt(true);
				return true;
			}
		}
		
		for (Fahrzeug fahrzeug : stellplaetzeSPKOLU) {
			if(fahrzeug.getKennzeichen().equalsIgnoreCase(kennzeichen)) {
				fahrzeug.setDefekt(true);
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Findet das Fahrzeug einer bestimmten Klasse mit dem geringsten Kilometerstand am Standort und parkt es aus
	 * @param klasse Gewünschte Fahrzeugklasse
	 * @return Das ausgeparkte Fahrzeug falls alles funktioniert hat, null falls kein Solches Fahrzeug gefunden wurde
	 */
	public Fahrzeug klassenFahrzeugAusparkenMitGeringstemKilometerstand(fahrzeugKlasse klasse) {		
		Fahrzeug ergebnis = null;
		switch(klasse) {
			case SP, KO, LU:
				for (Fahrzeug fahrzeug : stellplaetzeSPKOLU) {
					if(fahrzeug.getKlasse() == klasse && !fahrzeug.isDefekt()) {
						if(ergebnis == null) ergebnis = fahrzeug;
						if(fahrzeug.getKmStand() < ergebnis.getKmStand()) {
							ergebnis = fahrzeug;
						}
					}
				}
				stellplaetzeSPKOLU.remove(stellplaetzeSPKOLU.indexOf(ergebnis));
				return ergebnis;
			case KL, TR:
				for (Fahrzeug fahrzeug : stellplaetzeKLTR) {
					if(fahrzeug.getKlasse() == klasse && !fahrzeug.isDefekt()) {
						if(ergebnis == null) ergebnis = fahrzeug;
						if(fahrzeug.getKmStand() < ergebnis.getKmStand()) {
							ergebnis = fahrzeug;
						}
					}
				}
				stellplaetzeKLTR.remove(stellplaetzeKLTR.indexOf(ergebnis));
				return ergebnis;
		}
		
		return null;
	}

	/**
	 * Findet ein Fahrzeug am Standort anhand des Kennzeichens
	 * @param kennzeichen Kennzeichen des gesuchten Fahrzeuges
	 * @return Das Fahrzeug wenn alles funktioniert hat, null wenn das Fahrzeug nicht gefunden wurde
	 */
	public Fahrzeug getFahrzeug(String kennzeichen) {
		for (Fahrzeug fahrzeug : stellplaetzeKLTR) {
			if(fahrzeug.getKennzeichen().equalsIgnoreCase(kennzeichen)) {
				return fahrzeug;
			}
		}
		
		for (Fahrzeug fahrzeug : stellplaetzeSPKOLU) {
			if(fahrzeug.getKennzeichen().equalsIgnoreCase(kennzeichen)) {
				return fahrzeug;
			}
		}
		
		return null;
	}

	
}
