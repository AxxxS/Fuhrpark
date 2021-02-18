package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class App {
	private static boolean beendet = false;

	public static void main(String[] args) {
		Standort standortA, standortB, standortC, standortD, standortE, standortF, zentralerParkplatz;
		standortA = standortB = standortC = new Standort(10, 3);
		standortD = standortE = standortF = new Standort(25, 5);
		zentralerParkplatz = new Standort(75, 25);		
		ArrayList<Standort> standorte = new ArrayList<Standort>(Arrays.asList(standortA, standortB, standortC, standortD, standortE, standortF, zentralerParkplatz));
		for (Standort standort : standorte) {
			fahrzeugMitPS sportWagen1, sportWagen2;
			fahrzeugMitFarbe kompaktWagen1, kompaktWagen2, kompaktWagen3, luxusWagen1;
			fahrzeugMitSitzPlatzZahl kleinBus1;
			
			Random myRandom = new Random();
			
			sportWagen1 = new fahrzeugMitPS(myRandom.nextInt(99999) + 1, "ajksld49jkdfsa", myRandom.nextInt(400) + 200, fahrzeugKlasse.SP);
			sportWagen2 = new fahrzeugMitPS(myRandom.nextInt(99999) + 1, "afsidu09334fda", myRandom.nextInt(400) + 200, fahrzeugKlasse.SP);
			kompaktWagen1 = new fahrzeugMitFarbe(myRandom.nextInt(99999) + 1, "fasdkoöak32", "rot", fahrzeugKlasse.KO);
			kompaktWagen2 = new fahrzeugMitFarbe(myRandom.nextInt(99999) + 1, "adfs21e43f", "blau", fahrzeugKlasse.KO);
			kompaktWagen3 = new fahrzeugMitFarbe(myRandom.nextInt(99999) + 1, "fadsf43afdas", "gelb", fahrzeugKlasse.KO);
			luxusWagen1 = new fahrzeugMitFarbe(myRandom.nextInt(99999) + 1, "653zfsd332", "gold", fahrzeugKlasse.LU);
			kleinBus1 = new fahrzeugMitSitzPlatzZahl(myRandom.nextInt(99999) + 1, "asklou3lk", myRandom.nextInt(10) + 6, fahrzeugKlasse.KL);
		}
		Firma firma = new Firma(standorte);
		
		Scanner sc = new Scanner(System.in);
		while(!beendet) {
			switch(sc.next()) {
				case "uebersicht":
					uebersichtWoWelcheFahrzeugArt(firma);
			}
		}
	}
	
	public static void uebersichtWoWelcheFahrzeugArt(Firma firma) {
		HashMap<Character, HashMap<fahrzeugKlasse, Integer>> result = firma.getUerbersichtWoWelcheFahrzeugArt();
		
		System.out.println("An den Standorten sind folgende Fahrzeuge geparkt:");
		for (Character standortBuchstabe : result.keySet()) {
			System.out.println("Standort " + standortBuchstabe + ":");
			HashMap<fahrzeugKlasse, Integer> values = result.get(standortBuchstabe);
			for (fahrzeugKlasse fahrzeugKlasse : values.keySet()) {
				int anzahl = values.get(fahrzeugKlasse);
				System.out.println(fahrzeugKlasse.getBezeichnung() + ": " + anzahl);
			}
		}
	}

}
