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
	
}
