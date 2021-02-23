package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.HashMap;

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
	 * Durchsucht alle Standorte der Firma und erstellt eine HashMap mit allen Informationen zum Standort und den darauf
	 * sich befindenden Fahrzeugen
	 * @return HashMap mit Standort als Key und einer HashMap mit den Fahrzeugen an der Position auf dem Standort als values
	 */
	public HashMap<Character, HashMap<fahrzeugKlasse, Integer>> getUerbersichtWoWelcheFahrzeugArt() {
		HashMap<Character, HashMap<fahrzeugKlasse, Integer>> result = new HashMap<>();
		for (Standort standort : standorte) {
			 result.put(standort.getName(), standort.getUebersichtWelcheFahrzeuge());
		}
		return result;
	}

	/**
	 * Zählt alle Fahrzeuge die auf den Standorten der Firma verteilt sind zusammen.
	 * @return gibt eine ArrayList mit allen Fahrzeugen
	 */
	public ArrayList<Fahrzeug> getAlleFahrzeuge() {
		ArrayList<Fahrzeug> result = new ArrayList<>(); 
		for (Standort standort : standorte) {
			for(Fahrzeug fahrzeug: standort.getAlleFahrzeuge()) {
				result.add(fahrzeug);
			}
		}
		return result;
	}
	
	public ArrayList<Fahrzeug> getFahrzeugeMitAttributUndWert(Class gesuchteKlasse, String wert) {
		ArrayList<Fahrzeug> result = new ArrayList<Fahrzeug>();
		
		for (Standort standort : standorte) {
			result.addAll(standort.getFahrzeugeMitAttributUndWert(gesuchteKlasse, wert));
		}
		
		return result;
	}

	public ArrayList<Fahrzeug> defektUndAustauschen(String kennzeichen) {
		ArrayList<Fahrzeug> result = new ArrayList<>();
		Standort standortMitGesuchtemFahrzeug = null;
		Fahrzeug zuTauschen = null;
		Standort zentralerParkplatz = null;
		
		for (Standort standort : standorte) {
			Fahrzeug eventuellesFahrzeug = standort.parkeAus(kennzeichen, true);
			if(eventuellesFahrzeug != null) {
				standortMitGesuchtemFahrzeug = standort;
				zuTauschen = eventuellesFahrzeug;
				result.add(zuTauschen);
			}
			if(standort.getName() == 'P') {
				zentralerParkplatz = standort;
			}
		}
		
		if(standortMitGesuchtemFahrzeug == null || zuTauschen == null) {
			return result;
		}
		
		
		if(standortMitGesuchtemFahrzeug.getName() != 'P') {
			zentralerParkplatz.fahrzeugParken(zuTauschen);
			
			String kennzeichenErsatzFahrzeug = zentralerParkplatz.getZufaelligesFahrzeug(zuTauschen.getKlasse());
			if(kennzeichenErsatzFahrzeug == null) {
				Fahrzeug ersatz = zentralerParkplatz.parkeAus(kennzeichenErsatzFahrzeug, false);
				standortMitGesuchtemFahrzeug.fahrzeugParken(ersatz);
				result.add(ersatz);
			}
		}
		
		return result;
	}
	
}
