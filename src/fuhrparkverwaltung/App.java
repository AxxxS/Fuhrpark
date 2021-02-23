package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class App {
	private static boolean beendet = false;

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
			
			Random myRandom = new Random();
			
			sportWagen1 = new FahrzeugMitPS(myRandom.nextInt(99999) + 1, generiereKennzeichen(), myRandom.nextInt(400) + 200, fahrzeugKlasse.SP);
			sportWagen2 = new FahrzeugMitPS(myRandom.nextInt(99999) + 1, generiereKennzeichen(), myRandom.nextInt(400) + 200, fahrzeugKlasse.SP);
			kompaktWagen1 = new FahrzeugMitFarbe(myRandom.nextInt(99999) + 1, generiereKennzeichen(), "rot", fahrzeugKlasse.KO);
			kompaktWagen2 = new FahrzeugMitFarbe(myRandom.nextInt(99999) + 1, generiereKennzeichen(), "blau", fahrzeugKlasse.KO);
			kompaktWagen3 = new FahrzeugMitFarbe(myRandom.nextInt(99999) + 1, generiereKennzeichen(), "gelb", fahrzeugKlasse.KO);
			luxusWagen1 = new FahrzeugMitFarbe(myRandom.nextInt(99999) + 1, generiereKennzeichen(), "gold", fahrzeugKlasse.LU);
			kleinBus1 = new FahrzeugMitSitzPlatzZahl(myRandom.nextInt(99999) + 1, generiereKennzeichen(), myRandom.nextInt(10) + 6, fahrzeugKlasse.KL);
			
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
		System.out.println();
		System.out.println("Sie koennen die folgenden Befehle verwenden:");
		System.out.println("	Uebersicht: Geben Sie alle Standorte sowie die Anzahl und Art der dort geparkten Fahrzeuge aus");
		System.out.println("	maxKilometer: Geben Sie die 10 Fahrzeuge mit den größten Kilometerstaenden aus");
		System.out.println("	ausgabe <attribut> <wert>: Geben Sie alle Fahrzeuge mit einem bestimmten Wert im Sonderattribut aus");
		System.out.println("    defektSetzen <kennzeichen>: Setzen Sie ein Fahrzeug auf defekt und tauschen sie es mit einem Fahrzeug des gleichen Types vom zentralen Parkplatz aus.");
		Scanner sc = new Scanner(System.in);
		
		while(!beendet) {
			String input = sc.nextLine();
			if(input.equalsIgnoreCase("uebersicht") || input.equalsIgnoreCase("0") || input.equalsIgnoreCase("ü")) {
				uebersichtWoWelcheFahrzeugArt(firma);
			} else if (input.equalsIgnoreCase("maxKilometer") || input.equalsIgnoreCase("1") || input.equalsIgnoreCase("m")){
				maxKilometer(firma);
			} else if (input.startsWith("ausgabe") || input.startsWith("a") || input.startsWith("2")) {
				Class gesuchteKlasse = null;
				String[] parts = input.split(" ");
				switch(parts[1]) {
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
				fahrzeugeMitAttributUndWert(firma, gesuchteKlasse, parts[2]);
			} else if(input.startsWith("defektSetzen") || input.startsWith("defekt") || input.startsWith("d") || input.startsWith("3")) {
				String kennzeichen = input.split(" ")[1];
				defektUndAustauschen(firma, kennzeichen);
			}
		}
	}




	/**
	 * Gibt in der Konsole eine Übersicht über die Standorte der Fahrzeuge aus.
	 * @param firma eine Firma, von welcher eine Übersicht ausgeben werden soll.
	 *
	 */
	public static void uebersichtWoWelcheFahrzeugArt(Firma firma) {
		HashMap<Character, HashMap<fahrzeugKlasse, Integer>> result = firma.getUerbersichtWoWelcheFahrzeugArt();
		System.out.println("An den Standorten sind folgende Fahrzeuge geparkt:");
		for (Character standortBuchstabe : result.keySet()) {
			System.out.println("	Standort " + standortBuchstabe + ":");
			HashMap<fahrzeugKlasse, Integer> values = result.get(standortBuchstabe);
			for (fahrzeugKlasse fahrzeugKlasse : values.keySet()) {
				int anzahl = values.get(fahrzeugKlasse);
				System.out.println("		" + fahrzeugKlasse.getBezeichnung() + ": " + anzahl);
			}
		}
	}
	
	 /**
	  *  Gibt in der Konsole die Autos, mit dem größten Kilometerstand aus.
	  * Die Informationen der Autos, wie Kennzeichen, Klasse, Kilometerstand, Defektstatus und Sonderattribute werden mit
	  * angezeigt.
	  * @param firma eine Firma, von welcher die Autos mit dem größten Kilometerstand ausgeben werden soll.
	  */
	public static void maxKilometer(Firma firma) {
		ArrayList<Fahrzeug> result = firma.getAlleFahrzeuge();
		result.sort((fahrzeug1, fahrzeug2) -> Integer.compare((fahrzeug2.getKmStand()), fahrzeug1.getKmStand()));
		System.out.println("Die folgenden Fahrzeuge haben die größten Kilometerstände:");
		for(int i = 0; i<10; i++) {
			System.out.println(result.get(i));
		}
	}
	
	private static void fahrzeugeMitAttributUndWert(Firma firma, Class gesuchteKlasse, String wert) {
		ArrayList<Fahrzeug> result = firma.getFahrzeugeMitAttributUndWert(gesuchteKlasse, wert);
		
		System.out.println("Die folgenden Fahrzeuge haben den gesuchten Wert:");
		for (Fahrzeug fahrzeug : result) {
			System.out.println(fahrzeug);
		}
	}
	
	private static void defektUndAustauschen(Firma firma, String kennzeichen) {
		ArrayList<Fahrzeug> tauschpaar = firma.defektUndAustauschen(kennzeichen);
		switch(tauschpaar.size()) {
			case 0:
				System.out.println("Es wurde kein Fahrzeug mit dem Kennzeichen " + kennzeichen + " gefunden.");
				break;
			case 1:
				System.out.println("Das Fahrzeug ");
				System.out.println(tauschpaar.get(0));
				System.out.println("wurde als defekt markiert und auf den zentralen Parkplatz verschoben.");
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
	
	
	
	/** Generiert ein Kennzeichen mit Hilfe der Random Klasse.
	 * @return Returned ein Kennzeichen mit folgendem Muster -> BB-BB-ZZZ ,wobei B für Buchstabe und Z für Zahl steht
	 */
	public static String generiereKennzeichen() {
		String abc = "abcdefghijklmnopqrstuvwxyz";
		Random r = new Random();
		String kennzeichen = abc.charAt(r.nextInt(26)) + "" + abc.charAt(r.nextInt(26)) + "-" + abc.charAt(r.nextInt(26)) + "" + abc.charAt(r.nextInt(26)) + "-" + r.nextInt(10) + "" + r.nextInt(10) + "" +  r.nextInt(10);
		return kennzeichen;
	}

}
