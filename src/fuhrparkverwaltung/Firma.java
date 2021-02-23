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
	 * ZÃ¤hlt alle Fahrzeuge die auf den Standorten der Firma verteilt sind zusammen.
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
		
		char aktuellerStandortBuchtstabe = fahrzeugStandortFinden(kennzeichen);
		if(aktuellerStandortBuchtstabe != 'N') {
			Standort aktuellerStandort = getStandortNachName(aktuellerStandortBuchtstabe);
			if(aktuellerStandort != null) {
				if(aktuellerStandort.fahrzeugAufDefektSetzen(kennzeichen)) {
					fahrzeugVerschieben(kennzeichen, 'P');
					//ZENTRALEN PARKPLATZ FINDEN und Zufälliges FZG der selben Klasse abrufen, danach verschieben
				}
			}
		}
		return result;
	}
	
	public boolean fahrzeugVerschieben(String kennzeichen, char zielStandort) {
		boolean result = false;	
		char jetzigerStandort = fahrzeugStandortFinden(kennzeichen);
		
		Standort vorher = getStandortNachName(jetzigerStandort);
		Fahrzeug fahrzeug = vorher.fahrzeugAusparken(kennzeichen);
		if(!fahrzeug.isDefekt()) {
			int entfernung = standortAbstaende.berechneAbstand(jetzigerStandort, zielStandort);
			fahrzeug.setKmStand(fahrzeug.getKmStand() + entfernung);
		}
		Standort nachher = getStandortNachName(zielStandort);
		nachher.fahrzeugParken(fahrzeug);
		
		return result;
	}
	
	public char fahrzeugStandortFinden(String kennzeichen) {
		for (Standort standort : standorte) {
			if(standort.getFahrzeug(kennzeichen) != null) {
				return standort.getName();
			}
		}
		return 'N';
	}
	
	public Standort getStandortNachName(char name) {
		for (Standort standort : standorte) {
			if(standort.getName() == name) {
				return standort;
			}
		}
		return null;
	}
	
	public fahrzeugKlasse getFahrzeugKlasse(String kennzeichen) {
		char standortBuchstabe = fahrzeugStandortFinden(kennzeichen);
		if(standortBuchstabe != 'N') {
			Standort standort = getStandortNachName(standortBuchstabe);
			if(standort != null) {
				Fahrzeug fahrzeug = standort.getFahrzeug(kennzeichen);
				if(fahrzeug != null) {
					return fahrzeug.getKlasse();
				}
			}
		}
		return null;
	}
	
}
