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

		Firma firma = new Firma();
		
		System.out.println("Herzlich Willkommen bei FUPA!");
		hilfeTextAusgeben();
		
		Scanner sc = new Scanner(System.in);
		while(!beendet) {
			String eingabe = sc.nextLine();
			String[] teile = eingabe.split(" ");
			if(teile.length == 1 && (teile[0].equalsIgnoreCase("uebersicht") || teile[0].equalsIgnoreCase("ü"))) {
				firma.uebersichtWoWelcheFahrzeugArt();
			} else if (teile.length == 1 && (teile[0].equalsIgnoreCase("maxKilometer") || teile[0].equalsIgnoreCase("m"))){
				firma.maxKilometer();
			} else if (teile.length == 3 && (teile[0].equalsIgnoreCase("ausgabe") || teile[0].equalsIgnoreCase("a"))) {
				Class gesuchteKlasse = null;
				boolean unmoeglicherWert = false;
				switch(teile[1]) {
					case "PS-Zahl":
						if(teile[2].matches("\\d+")) {
							gesuchteKlasse = FahrzeugMitPS.class;
						} else {
							System.out.println("Der angegebenen Suchwert ist keine Ganzzahl!");
							unmoeglicherWert = true;
						}
						break;
					case "Farbe":
						gesuchteKlasse = FahrzeugMitFarbe.class;
						break;
					case "Sitzplatz-Zahl":
						if(teile[2].matches("\\d+")) {
							gesuchteKlasse = FahrzeugMitSitzPlatzZahl.class;
						} else {
							System.out.println("Der angegebenen Suchwert ist keine Ganzzahl!");
							unmoeglicherWert = true;
						}
						break;
				}
				if(gesuchteKlasse != null) {
					firma.fahrzeugeMitAttributUndWert(gesuchteKlasse, teile[2]);
				} else {
					if(!unmoeglicherWert) {
						System.out.println("Das gesuchte Attribut konnte keiner Fahrzeugklasse zugeordnet werden.");
					}
				}
			} else if(teile.length == 2 && (teile[0].equalsIgnoreCase("defektSetzen") || teile[0].equalsIgnoreCase("defekt") || teile[0].equalsIgnoreCase("d"))) {
				String kennzeichen = teile[1];
				firma.defektUndAustauschen(kennzeichen);
			} else if(teile.length == 3 && (teile[0].equalsIgnoreCase("verschiebenKennzeichen") || teile[0].equalsIgnoreCase("vk"))) {
				String kennzeichen = teile[1];
				char standort = teile[2].charAt(0);
				firma.spezifschesFahrzeugverschieben(kennzeichen, standort);
			} else if(teile.length == 4 && (teile[0].equalsIgnoreCase("verschieben") || teile[0].equalsIgnoreCase("v"))) {
					String klasse = teile[1];
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
						char vonStandort = teile[2].charAt(0);
						char zuStandort = teile[3].charAt(0);
						firma.zufälligesFahrzeugverschieben( fzKlasse, vonStandort, zuStandort);
					} else {
						System.out.println("Die angegebene Fahrzeugklasse existiert nicht!");
					}
			} else if(teile.length == 2 && (teile[0].equalsIgnoreCase("finde") || teile[0].equalsIgnoreCase("f"))){
				String kennzeichen = teile[1];
				firma.findeFahrzeug(kennzeichen);
			} else if(teile.length == 1 && (teile[0].equalsIgnoreCase("b") || teile[0].equalsIgnoreCase("beenden"))){
				beendet = true;
			} else {
				hilfeTextAusgeben();
			}
		}
	}





	/**
	 * Gibt die Kurzanleitung für das Programm in der Konsole aus
	 */
	public static void hilfeTextAusgeben() {
		System.out.println();
		System.out.println("Sie können die folgenden Befehle verwenden, um das Programm zu beenden verwenden Sie 'beenden' (b):");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("1. uebersicht (ü): Geben Sie alle Standorte sowie die Anzahl und Art der dort geparkten Fahrzeuge aus");
		System.out.println("2. maxKilometer (m): Geben Sie die 10 Fahrzeuge mit den größten Kilometerstaenden aus");
		System.out.println("3. ausgabe (a) <attribut> <wert>: Geben Sie alle Fahrzeuge mit einem bestimmten Wert im Sonderattribut aus");
		System.out.println("4. defektSetzen (d) <kennzeichen>: Setzen Sie ein Fahrzeug auf defekt und tauschen sie es mit einem Fahrzeug des gleichen Types vom zentralen Parkplatz aus.");
		System.out.println("5. verschiebenKennzeichen (vk) <kennzeichen> <neuerStandort>: Verschieben sie ein bestimmtes Fahrzeug an einen anderen Standort oder auf den zentralen Parkplatz.\n\tEs stehen 'A' bis 'F' und 'P' (zentraler Parkplatz) als Standorte zur Verfügung.");
		System.out.println("5. verschieben (v) <fahrzeugKlasse> <alterStandort> <neuerStandort>: Verschieben das Fahrzeug mit dem geringsten Kilometerstand einer Klasse an einem Standort an einen anderen Standort oder auf den zentralen Parkplatz.\n\tMögliche Fahrzeugklassen sind: sp bzw. sportwagen, ko bzw. kompaktwagen, lu bzw. luxusklasse, kl bzw. kleinbus und tr bzw. transporter\n\tEs stehen 'A' bis 'F' und 'P' (zentraler Parkplatz) als Standorte zur Verfügung.");
		System.out.println("6. finden (f) <kennzeichen>: Finden Sie den Standort eines bestimmten Fahrzeuges");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}
}
