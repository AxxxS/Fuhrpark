package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Standard Standort Klasse
 */
public class Standort {
	private char name;
	private float x, y;
	private int anzahlStellplaetzeSPundKOundLU, anzahlStellplaetzeKLundTR;
	private ArrayList<Fahrzeug> stellplaetzeSPKOLU;
	private ArrayList<Fahrzeug> stellplaetzeKLTR;

	/**
	 * Erstellt einen Standort, auf welchem sich Fahrzeuge befindet.
	 * @param anzahlStellplaetzeSPKOLU Anzahl der Stellplätze für Fahrzeuge der Klasse Sportwagen, Kompaktklasse und Luxusklasse
	 * @param anzahlStellplaetzeKLTR Anzahl der Stellplätze für Fahrzeuge der Klasse
	 * @param name Character der den Namen eines Standortes darstellt
	 */
	public Standort(int anzahlStellplaetzeSPKOLU, int anzahlStellplaetzeKLTR, char name) {
		this.anzahlStellplaetzeKLundTR = anzahlStellplaetzeKLTR;
		this.anzahlStellplaetzeSPundKOundLU = anzahlStellplaetzeSPKOLU;
		this.stellplaetzeKLTR = new ArrayList<>();
		this.stellplaetzeSPKOLU = new ArrayList<>();
		this.name = name;
	}

	/**
	 * Parkt die Fahrzeuge auf den zugehörigen Parkplatz des Standorts, je nach der Klasse des Fahrzeugs.
	 * @param fahrzeug ein Fahrzeug welches auf dem Parkplatz abgestellt werden soll
	 */
	public void fahrzeugParken(Fahrzeug fahrzeug) {
		switch(fahrzeug.getKlasse()) {
			case SP, KO, LU:
				if(this.stellplaetzeSPKOLU.size() < this.anzahlStellplaetzeSPundKOundLU) {
					this.stellplaetzeSPKOLU.add(fahrzeug);
				}
				break;
			case KL, TR:
				if(this.stellplaetzeKLTR.size() < this.anzahlStellplaetzeKLundTR) {
					this.stellplaetzeKLTR.add(fahrzeug);
				}
				break;
		}
	}

	/**
	 * Erstellt eine Übersicht über die Anzahl von Fahrzeugen einer Fahrzeugklassen, die sich auf dem Standort befinden.
	 * @return gibt eine HashMap zurück, die als key die Klasse des Fahrzeugs hat und als value den zugehörigen Parkplatz
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
		
		HashMap<fahrzeugKlasse, Integer> result = new HashMap<>();
		result.put(fahrzeugKlasse.KL, anzahlKL);
		result.put(fahrzeugKlasse.TR, anzahlTR);
		result.put(fahrzeugKlasse.KO, anzahlKO);
		result.put(fahrzeugKlasse.LU, anzahlLU);
		result.put(fahrzeugKlasse.SP, anzahlSP);
		return result;
	}

	/**
	 * Erstellt eine Liste, welche alle Fahrzeuge enthält, die auf dem Standort geparkt sind.
	 * @return Gibt eine ArrayList von Fahrzeugen auf dem Standort zurück
	 */
	public ArrayList<Fahrzeug> getAlleFahrzeuge() {
		ArrayList<Fahrzeug> result = stellplaetzeKLTR;
		result.addAll(stellplaetzeSPKOLU);
		return result;
	}
	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getAnzahlStellplaetzeSPundKOundLU() {
		return anzahlStellplaetzeSPundKOundLU;
	}

	public void setAnzahlStellplaetzeSPundKOundLU(int anzahlStellplaetzeSPundKOundLU) {
		this.anzahlStellplaetzeSPundKOundLU = anzahlStellplaetzeSPundKOundLU;
	}

	public int getAnzahlStellplaetzeKLundTR() {
		return anzahlStellplaetzeKLundTR;
	}

	public void setAnzahlStellplaetzeKLundTR(int anzahlStellplaetzeKLundTR) {
		this.anzahlStellplaetzeKLundTR = anzahlStellplaetzeKLundTR;
	}

	public ArrayList<Fahrzeug> getStellplaetzeSPKOLU() {
		return stellplaetzeSPKOLU;
	}

	public void setStellplaetzeSPKOLU(ArrayList<Fahrzeug> stellplaetzeSPKOLU) {
		this.stellplaetzeSPKOLU = stellplaetzeSPKOLU;
	}

	public ArrayList<Fahrzeug> getStellplaetzeKLTR() {
		return stellplaetzeKLTR;
	}

	public void setStellplaetzeKLTR(ArrayList<Fahrzeug> stellplaetzeKLTR) {
		this.stellplaetzeKLTR = stellplaetzeKLTR;
	}

}
