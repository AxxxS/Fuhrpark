package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Anwendungslogik (Datengenerierung und Konsoleninteraktion) der Fuhrparkverwaltungs-App
 */
public class App {
	private static boolean beendet = false;

	/**
	 * Startet das Programm, generiert Daten und reagiert auf Konsoleneingaben
	 * @param args Nichts
	 */
	public static void main(String[] args) {
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
		
		Firma firma = new Firma(standorte);
		
		System.out.println("Herzlich Willkommen in Ihrer Fuhrparkverwaltung!");
		hilfeTextAusgeben();
		
		Scanner sc = new Scanner(System.in);
		while(!beendet) {
			String eingabe = sc.nextLine();
			
			if(eingabe.equalsIgnoreCase("uebersicht") || eingabe.equalsIgnoreCase("0") || eingabe.equalsIgnoreCase("ü")) {
				uebersichtWoWelcheFahrzeugArt(firma);
			} else if (eingabe.equalsIgnoreCase("maxKilometer") || eingabe.equalsIgnoreCase("1") || eingabe.equalsIgnoreCase("m")){
				maxKilometer(firma);
			} else if (eingabe.startsWith("ausgabe") || eingabe.startsWith("a") || eingabe.startsWith("2")) {
				Class gesuchteKlasse = null;
				String[] teile = eingabe.split(" ");
				switch(teile[1]) {
					case "PS-Zahl":
						gesuchteKlasse = FahrzeugMitPS.class;
						break;
					case "Farbe":
						gesuchteKlasse = FahrzeugMitFarbe.class;
						break;
					case "Sitzplatz-Zahl":
						gesuchteKlasse = FahrzeugMitSitzPlatzZahl.class;
						break;
				}
				fahrzeugeMitAttributUndWert(firma, gesuchteKlasse, teile[2]);
			} else if(eingabe.startsWith("defektSetzen") || eingabe.startsWith("defekt") || eingabe.startsWith("d") || eingabe.startsWith("3")) {
				String kennzeichen = eingabe.split(" ")[1];
				defektUndAustauschen(firma, kennzeichen);
			} else if(eingabe.startsWith("verschiebenKennzeichen") || eingabe.startsWith("vk")) {
				String[] teile = eingabe.split(" ");
				if(teile.length == 3) {
					String kennzeichen = eingabe.split(" ")[1];
					char standort = eingabe.split(" ")[2].charAt(0);
					verschieben(firma, kennzeichen, standort);
				} else {
					System.out.println("Bitte beachten Sie die Befehlsstruktur: vk <kennzeichen> <ZielStandort>");
				}
			} else if(eingabe.startsWith("verschieben") || eingabe.startsWith("v")) {
				String[] teile = eingabe.split(" ");
				if(teile.length == 4) {
					String klasse = eingabe.split(" ")[1];
					fahrzeugKlasse fzKlasse = null;
					if(klasse.equalsIgnoreCase("sp") || klasse.equalsIgnoreCase("sportwagen")) { 
						fzKlasse = fahrzeugKlasse.SP;
					} else if(klasse.equalsIgnoreCase("ko") || klasse.equalsIgnoreCase("kompaktklasse")) {
						fzKlasse = fahrzeugKlasse.KO;
					} else if(klasse.equalsIgnoreCase("lu") || klasse.equalsIgnoreCase("luxusklasse")) {
						fzKlasse = fahrzeugKlasse.LU;
					} else if(klasse.equalsIgnoreCase("kl") || klasse.equalsIgnoreCase("kleinbus")) {
						fzKlasse = fahrzeugKlasse.KL;
					} else if(klasse.equalsIgnoreCase("tr") || klasse.equalsIgnoreCase("transporter")) {
						fzKlasse = fahrzeugKlasse.TR;
					}
					
					if(fzKlasse != null) {
						char vonStandort = eingabe.split(" ")[2].charAt(0);
						char zuStandort = eingabe.split(" ")[3].charAt(0);
						verschieben(firma, fzKlasse, vonStandort, zuStandort);
					}
				} else {
					System.out.println("Bitte beachten Sie die Befehlsstruktur: v <fahrzeugKlasse> <StartStandort> <ZielStandort>");
				}
			} else {
				hilfeTextAusgeben();
			}
		}
	}


	/**
	 * Gibt in der Konsole eine Übersicht der Standorte und der Anzahlen verschiedener Fahrzeuge aller Klassen aus
	 * @param firma eine Firma, von welcher die Übersicht ausgeben werden soll.
	 */
	private static void uebersichtWoWelcheFahrzeugArt(Firma firma) {
		HashMap<Character, HashMap<fahrzeugKlasse, Integer>> ergebnis = firma.getUerbersichtWoWelcheFahrzeugArt();
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
	  * @param firma eine Firma, von welcher die Liste ausgegeben werden soll
	  */
	private static void maxKilometer(Firma firma) {
		ArrayList<Fahrzeug> ergebnis = firma.getAlleFahrzeuge();
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
	private static void fahrzeugeMitAttributUndWert(Firma firma, Class gesuchteKlasse, String wert) {
		ArrayList<Fahrzeug> ergebnis = firma.getFahrzeugeMitAttributUndWert(gesuchteKlasse, wert);
		
		System.out.println("Die folgenden Fahrzeuge haben den gesuchten Wert:");
		for (Fahrzeug fahrzeug : ergebnis) {
			System.out.println("-------------------------");
			System.out.println(fahrzeug);
		}
	}
	
	/**
	 * Markiert ein bestimmtes Fahrzeug als defekt und tauscht es mit einem Fahrzeug vom zentralen Parkplatz aus
	 * @param firma eine Firma, die das Fahrzeug besitzt
	 * @param kennzeichen das Kennzeichen des kaputten Fahrzeuges
	 */
	private static void defektUndAustauschen(Firma firma, String kennzeichen) {
		ArrayList<Fahrzeug> tauschpaar = firma.defektesFahrzeugAustauschen(kennzeichen);
		
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
	private static void verschieben(Firma firma, String kennzeichen, char standort) {
		int ergebnis = firma.verschieben(kennzeichen, standort);
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
	private static void verschieben(Firma firma, fahrzeugKlasse klasse, char vonStandort, char zuStandort) {
		int ergebnis = firma.verschieben(klasse, vonStandort, zuStandort);
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
	
	
	
	/** Generiert ein (nicht der Realität entsprechendes) zufälliges Kennzeichen
	 * @return eine Kennzeichen-Zeichenkette nach dem Muster BB-BB-ZZZ, wobei B für Buchstabe und Z für Ziffer steht
	 */
	public static String generiereKennzeichen() {
		String abc = "abcdefghijklmnopqrstuvwxyz";
		Random zufallsGenerator = new Random();
		String kennzeichen = abc.charAt(zufallsGenerator.nextInt(26)) + "" + abc.charAt(zufallsGenerator.nextInt(26)) + "-" + abc.charAt(zufallsGenerator.nextInt(26)) + "" + abc.charAt(zufallsGenerator.nextInt(26)) + "-" + zufallsGenerator.nextInt(10) + "" + zufallsGenerator.nextInt(10) + "" +  zufallsGenerator.nextInt(10);
		return kennzeichen;
	}

	/**
	 * Gibt die Kurzanleitung für das Programm in der Konsole aus
	 */
	public static void hilfeTextAusgeben() {
		System.out.println();
		System.out.println("Sie können die folgenden Befehle verwenden:");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("1. uebersicht (ü): Geben Sie alle Standorte sowie die Anzahl und Art der dort geparkten Fahrzeuge aus");
		System.out.println("2. maxKilometer (m): Geben Sie die 10 Fahrzeuge mit den größten Kilometerstaenden aus");
		System.out.println("3. ausgabe (a) <attribut> <wert>: Geben Sie alle Fahrzeuge mit einem bestimmten Wert im Sonderattribut aus");
		System.out.println("4. defektSetzen (d) <kennzeichen>: Setzen Sie ein Fahrzeug auf defekt und tauschen sie es mit einem Fahrzeug des gleichen Types vom zentralen Parkplatz aus.");
		System.out.println("5. verschiebenKennzeichen (vk) <kennzeichen> <neuerStandort>: Verschieben sie ein bestimmtes Fahrzeug an einen anderen Standort oder auf den zentralen Parkplatz.\n\tEs stehen 'A' bis 'F' und 'P' (zentraler Parkplatz) als Standorte zur Verfügung.");
		System.out.println("5. verschieben (v) <fahrzeugKlasse> <alterStandort> <neuerStandort>: Verschieben das Fahrzeug mit dem geringsten Kilometerstand einer Klasse an einem Standort an einen anderen Standort oder auf den zentralen Parkplatz.\n\tMögliche Fahrzeugklassen sind: sp bzw. sportwagen, ko bzw. kompaktwagen, lu bzw. luxusklasse, kl bzw. kleinbus und tr bzw. transporter\n\tEs stehen 'A' bis 'F' und 'P' (zentraler Parkplatz) als Standorte zur Verfügung.");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}
}
