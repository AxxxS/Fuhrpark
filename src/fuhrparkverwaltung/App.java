package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
		ArrayList<Standort> standorte = new ArrayList<Standort>(Arrays.asList(standortA, standortB, standortC, standortD, standortE, standortF, zentralerParkplatz));
		
		for (int i = 0; i<standorte.size(); i++) {
			Standort vorher = standorte.get(i);
			fahrzeugMitPS sportWagen1, sportWagen2;
			fahrzeugMitFarbe kompaktWagen1, kompaktWagen2, kompaktWagen3, luxusWagen1;
			fahrzeugMitSitzPlatzZahl kleinBus1;
			
			Random myRandom = new Random();
			
			sportWagen1 = new fahrzeugMitPS(myRandom.nextInt(99999) + 1, generiereKennzeichen(), myRandom.nextInt(400) + 200, fahrzeugKlasse.SP);
			sportWagen2 = new fahrzeugMitPS(myRandom.nextInt(99999) + 1, generiereKennzeichen(), myRandom.nextInt(400) + 200, fahrzeugKlasse.SP);
			kompaktWagen1 = new fahrzeugMitFarbe(myRandom.nextInt(99999) + 1, generiereKennzeichen(), "rot", fahrzeugKlasse.KO);
			kompaktWagen2 = new fahrzeugMitFarbe(myRandom.nextInt(99999) + 1, generiereKennzeichen(), "blau", fahrzeugKlasse.KO);
			kompaktWagen3 = new fahrzeugMitFarbe(myRandom.nextInt(99999) + 1, generiereKennzeichen(), "gelb", fahrzeugKlasse.KO);
			luxusWagen1 = new fahrzeugMitFarbe(myRandom.nextInt(99999) + 1, generiereKennzeichen(), "gold", fahrzeugKlasse.LU);
			kleinBus1 = new fahrzeugMitSitzPlatzZahl(myRandom.nextInt(99999) + 1, generiereKennzeichen(), myRandom.nextInt(10) + 6, fahrzeugKlasse.KL);
			
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
		System.out.println("Sie können die folgenden Befehle verwenden:");
		System.out.println("	Übersicht: Geben Sie alle Standorte sowie die Anzahl und Art der dort geparkten Fahrzeuge aus");
		System.out.println("	maxKilometer: Geben Sie die 10 Fahrzeuge mit den größten Kilometerständen aus");

		Scanner sc = new Scanner(System.in);
		while(!beendet) {
			String input = sc.next();
			if(input.equalsIgnoreCase("übersicht") || input.equalsIgnoreCase("0") || input.equalsIgnoreCase("ü")) {
				uebersichtWoWelcheFahrzeugArt(firma);
			} else if (input.equalsIgnoreCase("maxKilometer") || input.equalsIgnoreCase("1") || input.equalsIgnoreCase("m")){
				maxKilometer(firma);
			}
		}
	}
	
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
	
	public static void maxKilometer(Firma firma) {
		ArrayList<Fahrzeug> result = firma.getAlleFahrzeuge();
		result.sort((fahrzeug1, fahrzeug2) -> ((Integer) (fahrzeug2.getKmStand())).compareTo(fahrzeug1.getKmStand()));
		System.out.println("Die folgenden Fahrzeuge haben die größten Kilometerstände:");
		for(int i = 0; i<10; i++) {
			System.out.println(result.get(i));
		}
	}
	
	public static String generiereKennzeichen() {
		String abc = "abcdefghijklmnopqrstuvwxyz";
		Random r = new Random();
		String kennzeichen = abc.charAt(r.nextInt(26)) + "" + abc.charAt(r.nextInt(26)) + "-" + abc.charAt(r.nextInt(26)) + "" + abc.charAt(r.nextInt(26)) + "-" + r.nextInt(10) + "" + r.nextInt(10) + "" +  r.nextInt(10);
		return kennzeichen;
	}

}
