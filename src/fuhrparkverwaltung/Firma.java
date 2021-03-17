package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Oberste Datenstruktur der Fuhrparkverwaltung, die alle Standorte und von der App aufzurufenden Methoden enthält
 */
public class Firma {
	private static ArrayList<Standort> alleStandorte;

	/**
	 * Erstellt eine Firma die Standorte besitzt.
	 * @param standorte ArrayList von Standorten, welche die Firma besitzt
	 */
	public Firma() {
		Standort standortA = new Standort(10, 3, 'A');
		Standort standortB = new Standort(10, 3, 'B');
		Standort standortC = new Standort(10, 3, 'C');
		Standort standortD = new Standort(25, 5, 'D');
		Standort standortE = new Standort(25, 5, 'E');
		Standort standortF = new Standort(25, 5, 'F');
		Standort zentralerParkplatz = new Standort(75, 25, 'P');
		ArrayList<Standort> standorte = new ArrayList<>(Arrays.asList(standortA, standortB, standortC, standortD, standortE, standortF, zentralerParkplatz));
		
		for (int i = 0; i<standorte.size(); i++) {
			Standort vorher = standorte.get(i);
			FahrzeugMitPS sportWagen1, sportWagen2;
			FahrzeugMitFarbe kompaktWagen1, kompaktWagen2, kompaktWagen3, luxusWagen1;
			FahrzeugMitSitzPlatzZahl kleinBus1;
			
			Random zufallsGenerator = new Random();
			
			sportWagen1 = new FahrzeugMitPS(zufallsGenerator.nextInt(99999) + 1, generiereKennzeichen(), zufallsGenerator.nextInt(400) + 200, fahrzeugKlasse.SP);
			sportWagen2 = new FahrzeugMitPS(zufallsGenerator.nextInt(99999) + 1, generiereKennzeichen(), zufallsGenerator.nextInt(400) + 200, fahrzeugKlasse.SP);
			kompaktWagen1 = new FahrzeugMitFarbe(zufallsGenerator.nextInt(99999) + 1, generiereKennzeichen(), "rot", fahrzeugKlasse.KO);
			kompaktWagen2 = new FahrzeugMitFarbe(zufallsGenerator.nextInt(99999) + 1, generiereKennzeichen(), "blau", fahrzeugKlasse.KO);
			kompaktWagen3 = new FahrzeugMitFarbe(zufallsGenerator.nextInt(99999) + 1, generiereKennzeichen(), "gelb", fahrzeugKlasse.KO);
			luxusWagen1 = new FahrzeugMitFarbe(zufallsGenerator.nextInt(99999) + 1, generiereKennzeichen(), "gold", fahrzeugKlasse.LU);
			kleinBus1 = new FahrzeugMitSitzPlatzZahl(zufallsGenerator.nextInt(99999) + 1, generiereKennzeichen(), zufallsGenerator.nextInt(10) + 6, fahrzeugKlasse.KL);
			
			vorher.fahrzeugParken(sportWagen1);
			vorher.fahrzeugParken(sportWagen2);
			vorher.fahrzeugParken(kompaktWagen1);
			vorher.fahrzeugParken(kompaktWagen2);
			vorher.fahrzeugParken(kompaktWagen3);
			vorher.fahrzeugParken(luxusWagen1);
			vorher.fahrzeugParken(kleinBus1);
			
			standorte.set(i, vorher);
		}
		this.alleStandorte = standorte;
	}
	
	/** Generiert ein (nicht der Realität entsprechendes) zufälliges Kennzeichen
	 * @return eine Kennzeichen-Zeichenkette nach dem Muster BB-BB-ZZZ, wobei B für Buchstabe und Z für Ziffer steht
	 */
	public  String generiereKennzeichen() {
		String abc = "abcdefghijklmnopqrstuvwxyz";
		Random zufallsGenerator = new Random();
		String kennzeichen = abc.charAt(zufallsGenerator.nextInt(26)) + "" + abc.charAt(zufallsGenerator.nextInt(26)) + "-" + abc.charAt(zufallsGenerator.nextInt(26)) + "" + abc.charAt(zufallsGenerator.nextInt(26)) + "-" + zufallsGenerator.nextInt(10) + "" + zufallsGenerator.nextInt(10) + "" +  zufallsGenerator.nextInt(10);
		return kennzeichen;
	}
	/**
	 * Durchsucht alle Standorte der Firma und erstellt eine HashMap mit allen Informationen zum Standort und den sich dort
	 * befindendlichen Fahrzeugen
	 * @return HashMap mit Standort-Buchstabe als Key und einer weiteren HashMap, die zu jeder Fahrzeugklasse die entsprechende Bestandsanzahl enthält, als Value
	 */
	
	public HashMap<Character, HashMap<fahrzeugKlasse, Integer>> getUerbersichtWoWelcheFahrzeugArt() {
		HashMap<Character, HashMap<fahrzeugKlasse, Integer>> ergebnis = new HashMap<>();
		for (Standort standort : alleStandorte) {
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
		for (Standort standort : alleStandorte) {
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
	public  ArrayList<Fahrzeug> getFahrzeugeMitAttributUndWert(Class gesuchteKlasse, String wert) {
		ArrayList<Fahrzeug> ergebnis = new ArrayList<Fahrzeug>();
		
		for (Standort standort : alleStandorte) {
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
	public  ArrayList<Fahrzeug> defektesFahrzeugAustauschen(String kennzeichen) {
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
	
	public  Standort getStandort(char name) {
		for(Standort standort : alleStandorte) {
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
	public  Standort getFahrzeugStandort(String kennzeichen) {
		for (Standort standort : alleStandorte) {
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
	/**
	 * Gibt in der Konsole den Namen des Standortes, an dem sich ein Fahrzeug befinden, aus
	 * @param firma eine Firma, in der nach dem Fahrzeug gesucht werden soll
	 * @param kennzeichen das Kennzeichen des Fahrzeuges
	 */
	public void findeFahrzeug(String kennzeichen) {
		Standort gefunden = getFahrzeugStandort(kennzeichen);
		if(gefunden != null) {
			System.out.println("Das Fahrzeug mit dem Kennzeichen " + kennzeichen + " befindet sich am Standort " + gefunden.getName());
		} else {
			System.out.println("Das Fahrzeug wurde nicht gefunden!");
		}
		
	}


	/**
	 * Gibt in der Konsole eine Übersicht der Standorte und der Anzahlen verschiedener Fahrzeuge aller Klassen aus
	 * @param firma eine Firma, von welcher die Übersicht ausgeben werden soll.
	 */
	public  void uebersichtWoWelcheFahrzeugArt() {
		HashMap<Character, HashMap<fahrzeugKlasse, Integer>> ergebnis = getUerbersichtWoWelcheFahrzeugArt();
		System.out.println("An den Standorten sind folgende Fahrzeuge geparkt:");
		for (Character standortBuchstabe : ergebnis.keySet()) {
			System.out.println("\n\tStandort " + standortBuchstabe + ":");
			HashMap<fahrzeugKlasse, Integer> werte = ergebnis.get(standortBuchstabe);
			for (fahrzeugKlasse fahrzeugKlasse : werte.keySet()) {
				int anzahl = werte.get(fahrzeugKlasse);
				System.out.println("\t- " + fahrzeugKlasse.getBezeichnung() + ": " + anzahl);
			}
		}
	}
	
	 /**
	  * Gibt in der Konsole eine Liste der 10 Autos der Firma mit dem größten Kilometerstand aus.
	  */
	public  void maxKilometer() {
		ArrayList<Fahrzeug> ergebnis = getAlleFahrzeuge();
		ergebnis.sort((fahrzeug1, fahrzeug2) -> Integer.compare((fahrzeug2.getKmStand()), fahrzeug1.getKmStand()));
		System.out.println("Die folgenden Fahrzeuge haben die größten Kilometerstände:");
		for(int i = 0; i<10; i++) {
			System.out.println(i+1 + ". ----------------------");
			System.out.println(ergebnis.get(i));
		}
	}
	
	/**
	 * Gibt in der Konsole eine Liste von Fahrzeugen mit einem bestimmten Wert im Sonderattribut aus
	 * @param firma eine Firma, von welcher die Liste ausgegeben werden soll
	 * @param gesuchteKlasse die Fahrzeugklasse, in deren Sonderattribut nach dem bestimmten Wert gesucht werden soll
	 * @param wert der Wert, den das Sonderattribut der Fahrzeuge haben soll
	 */
	public  void fahrzeugeMitAttributUndWert( Class gesuchteKlasse, String wert) {
		ArrayList<Fahrzeug> ergebnis = getFahrzeugeMitAttributUndWert(gesuchteKlasse, wert);
			if(ergebnis.size() > 0) {
			System.out.println("Die folgenden Fahrzeuge haben den gesuchten Wert:");
			for (Fahrzeug fahrzeug : ergebnis) {
				System.out.println("-------------------------");
				System.out.println(fahrzeug);
			}
		} else {
			System.out.println("Kein Fahrzeug hat den angegebenen Attributwert.");
		}
	}
	
	/**
	 * Markiert ein bestimmtes Fahrzeug als defekt und tauscht es mit einem Fahrzeug vom zentralen Parkplatz aus
	 * @param firma eine Firma, die das Fahrzeug besitzt
	 * @param kennzeichen das Kennzeichen des kaputten Fahrzeuges
	 */
	public  void defektUndAustauschen(String kennzeichen) {
		ArrayList<Fahrzeug> tauschpaar = defektesFahrzeugAustauschen(kennzeichen);
		
		switch(tauschpaar.size()) {
			case 0:
				System.out.println("Es wurde kein Fahrzeug mit dem Kennzeichen " + kennzeichen + " gefunden.");
				break;
			case 1:
				System.out.println("Das Fahrzeug ");
				System.out.println(tauschpaar.get(0));
				System.out.println("wurde als defekt markiert, konnte aber mangels vorhandenem Ersatzfahrzeug nicht ausgetauscht werden.");
				break;
			case 2:
				System.out.println("Das Fahrzeug ");
				System.out.println(tauschpaar.get(0));
				System.out.println("wurde als defekt markiert und mit dem Fahrzeug ");
				System.out.println(tauschpaar.get(1));
				System.out.println("vom zentralen Parkplatz ausgetauscht.");
				break;
			default:
				break;
			}
		
	}
	
	/**
	 * Verschiebt ein bestimmtes Fahrzeug an einen neuen Standort
	 * @param firma eine Firma, die das gesuchte Fahrzeug besitzt
	 * @param kennzeichen das Kennzeichen des gesuchten Fahrzeuges
	 * @param standort der Buchstabe / Name des Standortes, an den das Fahrzeug verschoben werden soll
	 */
	public void spezifschesFahrzeugverschieben(String kennzeichen, char standort) {
		int ergebnis = verschieben(kennzeichen, standort);
		switch(ergebnis) {
			case 0:
				System.out.println("Das Fahrzeug oder der angegebene Standort wurden nicht gefunden!");
				break;
			case 1:
				System.out.println("Das Fahrzeug mit dem Kennzeichen " + kennzeichen + " wurde an den gewünschten Standort verschoben.");
				break;
			case -1:
				System.out.println("Das Fahrzeug befindet sich bereits an diesem Standort!");
				break;
			case -2:
				System.out.println("Der Zielparkplatz kann keine weiteren Fahrzeuge dieses Typs aufnehmen.");
				break;
		}
	}
	
	
	/**
	 * Verschiebt ein Fahrzeug einer bestimmten Fahrzeugklasse von einem Standort an einen Anderen
	 * @param firma eine Firma, die ein solches Fahrzeug besitzt
	 * @param klasse eine Fahrzeugklasse, von der ein Fahrzeug verschoben werden soll
	 * @param vonStandort der Buchstabe / Name des Standortes, von dem das Fahrzeug kommen soll
	 * @param zuStandort der Buchstabe / Name des Standortes, an den das Fahrzeug verschoben werden soll
	 */
	public void zufälligesFahrzeugverschieben(fahrzeugKlasse klasse, char vonStandort, char zuStandort) {
		int ergebnis = verschieben(klasse, vonStandort, zuStandort);
		switch(ergebnis) {
			case 0:
				System.out.println("Es gibt entweder kein Fahrzeug dieser Klasse am Start-Standort, oder der Start-/Zielstandort existiert nicht!");
				break;
			case 1:
				System.out.println("Ein Fahrzeug der angegebenen Klasse wurde an den gewünschten Standort verschoben.");
				break;
			case -1:
				System.out.println("Start- und Zielstandort sind gleich!");
				break;
			case -2:
				System.out.println("Der Zielparkplatz kann keine weiteren Fahrzeuge dieses Typs aufnehmen.");
				break;
		}
	}
	
	
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

	public  int verschieben(fahrzeugKlasse klasse, char vonStandort, char zuStandort) {
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
