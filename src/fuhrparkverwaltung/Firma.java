package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Oberste Datenstruktur der Fuhrparkverwaltung, die alle Standorte und von der App aufzurufenden Methoden enthält
 */
public class Firma {
	private ArrayList<Standort> standorte;

	/**
	 * Erstellt eine Firma die Standorte besitzt.
	 * @param standorte ArrayList von Standorten, welche die Firma besitzt
	 */
	public Firma(ArrayList<Standort> standorte) {
		this.standorte = standorte;
	}

	/**
	 * Durchsucht alle Standorte der Firma und erstellt eine HashMap mit allen Informationen zum Standort und den sich dort
	 * befindendlichen Fahrzeugen
	 * @return HashMap mit Standort-Buchstabe als Key und einer weiteren HashMap, die zu jeder Fahrzeugklasse die entsprechende Bestandsanzahl enthält, als Value
	 */
	public HashMap<Character, HashMap<fahrzeugKlasse, Integer>> getUerbersichtWoWelcheFahrzeugArt() {
		HashMap<Character, HashMap<fahrzeugKlasse, Integer>> ergebnis = new HashMap<>();
		for (Standort standort : standorte) {
			 ergebnis.put(standort.getName(), standort.getUebersichtWelcheFahrzeuge());
		}
		return ergebnis;
	}

	/**
	 * Gibt alle Fahrzeuge die auf die Standorte der Firma verteilt sind zurück.
	 * @return Die Liste aller Fahrzeuge
	 */
	public ArrayList<Fahrzeug> getAlleFahrzeuge() {
		ArrayList<Fahrzeug> ergebnis = new ArrayList<>(); 
		for (Standort standort : standorte) {
			for(Fahrzeug fahrzeug: standort.getAlleFahrzeuge()) {
				ergebnis.add(fahrzeug);
			}
		}
		return ergebnis;
	}
	
	/**
	 * Gibt eine Liste von Fahrzeugen aller Standorte aus, die alle einen bestimmten Wert im Sonderattribut stehen haben
	 * @param gesuchteKlasse Klasse der gesuchten Fahrzeuge, je nach gewünschtem Sonderattribut
	 * @param wert Wert des Sonderattributes
	 * @return Liste von allen Fahrzeugen aller Standorte mit dem gewünschten Wert im Sonderattribut
	 */
	public ArrayList<Fahrzeug> getFahrzeugeMitAttributUndWert(Class gesuchteKlasse, String wert) {
		ArrayList<Fahrzeug> ergebnis = new ArrayList<Fahrzeug>();
		
		for (Standort standort : standorte) {
			ergebnis.addAll(standort.getFahrzeugeMitAttributUndWert(gesuchteKlasse, wert));
		}
		
		return ergebnis;
	}

	/**
	 * Tauscht ein bestimmtes defektes Fahrzeug mit einem Fahrzeug des Selben Types vom zentralen Parkplatz aus
	 * @param kennzeichen Kennzeichen des Fahrzeuges
	 * @return Das ausgetauschte und das Ersatzfahrzeug in einer Liste, sofern alles geklappt hat. Wenn kein Ersatzfahrzeug gefunden wurde,
	 * 			enthält die Liste nur das zum zentralen Parkplatz verschobene defekte Fahrzeug, wenn das angegebene Kennzeichen nicht gefunden werden konnte,
	 * 			ist die Liste leer.
	 */
	public ArrayList<Fahrzeug> defektesFahrzeugAustauschen(String kennzeichen) {
		ArrayList<Fahrzeug> tauschpaar = new ArrayList<>();
		Standort standortDefektesFahrzeug, standortTauschPartner;
		Fahrzeug defektesFahrzeug, tauschPartner;
		
		standortDefektesFahrzeug = getFahrzeugStandort(kennzeichen);
		
		if(standortDefektesFahrzeug != null) {
			defektesFahrzeug = standortDefektesFahrzeug.fahrzeugAusparken(kennzeichen);
			defektesFahrzeug.setDefekt(true);
			tauschpaar.add(defektesFahrzeug);
			
			standortTauschPartner = getStandort('P');
			
			if(standortTauschPartner != null) {
				tauschPartner = standortTauschPartner.klassenFahrzeugAusparkenMitGeringstemKilometerstand(defektesFahrzeug.getKlasse());
				if(tauschPartner != null) {
					tauschpaar.add(tauschPartner);
					
					int distanz = standortAbstaende.berechneAbstand(standortDefektesFahrzeug.getName(), standortTauschPartner.getName());
					tauschPartner.setKmStand(tauschPartner.getKmStand() + distanz);
					
					standortDefektesFahrzeug.fahrzeugParken(tauschPartner);
					standortTauschPartner.fahrzeugParken(defektesFahrzeug);
				} else {
					standortDefektesFahrzeug.fahrzeugParken(defektesFahrzeug);
				}
			}
			else {
				standortDefektesFahrzeug.fahrzeugParken(defektesFahrzeug);
			}
		}
		
		return tauschpaar;
	}
	
	private Standort getStandort(char name) {
		for(Standort standort : standorte) {
			if(standort.getName() == name) {
				return standort;
			}
		}
		
		return null;
	}	
	
	/**
	 * Gibt den Standort eines bestimmten Fahrzeuges zurück
	 * @param kennzeichen Kennzeichen des Fahrzeuges
	 * @return Standort des Fahrzeuges
	 */
	public Standort getFahrzeugStandort(String kennzeichen) {
		for (Standort standort : standorte) {
			if(standort.getFahrzeug(kennzeichen) != null) {
				return standort;
			}
		}
		
		return null;
	}
	
	/**
	 * Verschiebt ein bestimmtes Fahrzeug an einen neuen Standort
	 * @param kennzeichen Kennzeichen des zu verschiebenden Fahrzeuges
	 * @param zielStandortName Buchstabe des neuen Standortes
	 * @return Ergebnis des Verschiebens:
	 * 		   0 wenn das Fahrzeug oder einer der Standorte nicht gefunden wurde;
	 * 		   1 wenn alles geklappt hat;
	 * 		  -1 wenn beide Standorte gleich sind;
	 * 		  -2 wenn am Zielstandort kein Platz ist
	 */
	public int verschieben(String kennzeichen, char zielStandortName) {
		
		Standort standortVorher, standortNachher;
		Fahrzeug fahrzeug;
		
		standortVorher = getFahrzeugStandort(kennzeichen);
		standortNachher = getStandort(zielStandortName);
		
		if(standortVorher == standortNachher) return -1; //Steht schon richtig
		
		if(standortVorher != null && standortNachher != null) {
			fahrzeug = standortVorher.fahrzeugAusparken(kennzeichen);
			int distanz = standortAbstaende.berechneAbstand(standortVorher.getName(), standortNachher.getName());
			
			if(standortNachher.fahrzeugParken(fahrzeug)) {
				fahrzeug.setKmStand(fahrzeug.getKmStand() + distanz);
				return 1; //Hat geklappt
			}
			
			else {
				standortVorher.fahrzeugParken(fahrzeug);
				return -2; //Kein Platz
			}
		}
		
		return 0; //Standort nicht gefunden
	}

	/**
	 * Verschiebt ein Fahrzeug einer bestimmten Klasse von einem Standort an einen Anderen
	 * @param klasse Klasse des Fahrzeuges
	 * @param vonStandort Vorheriger Standort
	 * @param zuStandort neuer Standort
	 * @return Ergebnis des Verschiebens:
	 * 		   0 wenn entweder kein Fahrzeug oder einer der Standorte nicht gefunden wurde;
	 * 		   1 wenn alles geklappt hat;
	 * 		  -1 wenn beide Standorte gleich sind;
	 * 		  -2 wenn am Zielstandort kein Platz ist
	 */
	public int verschieben(fahrzeugKlasse klasse, char vonStandort, char zuStandort) {
		Standort standortVorher, standortNachher;
		Fahrzeug fahrzeug;
		
		standortVorher = getStandort(vonStandort);
		standortNachher = getStandort(zuStandort);
		
		if(standortVorher == standortNachher) return -1; //Steht schon richtig
		
		if(standortVorher != null && standortNachher != null) {
			fahrzeug = standortVorher.klassenFahrzeugAusparkenMitGeringstemKilometerstand(klasse);
			if(fahrzeug != null) {
				int distanz = standortAbstaende.berechneAbstand(standortVorher.getName(), standortNachher.getName());
				
				if(standortNachher.fahrzeugParken(fahrzeug)) {
					fahrzeug.setKmStand(fahrzeug.getKmStand() + distanz);
					return 1; //Hat geklappt
				} else {
					standortVorher.fahrzeugParken(fahrzeug);
					return -2; //Kein Platz
				}
			}
		}
		return 0; //Standort nicht gefunden
	}
	
}
