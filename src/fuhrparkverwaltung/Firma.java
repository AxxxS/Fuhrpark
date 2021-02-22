package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.HashMap;

public class Firma {
	private ArrayList<Standort> standorte;
	
	public Firma(ArrayList<Standort> standorte) {
		this.standorte = standorte;
	}

	public HashMap<Character, HashMap<fahrzeugKlasse, Integer>> getUerbersichtWoWelcheFahrzeugArt() {
		HashMap<Character, HashMap<fahrzeugKlasse, Integer>> result = new HashMap<>();
		for (Standort standort : standorte) {
			 result.put(standort.getName(), standort.getUebersichtWelcheFahrzeuge());
		}
		return result;
	}

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
