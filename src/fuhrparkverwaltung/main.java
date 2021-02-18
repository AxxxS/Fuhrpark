package fuhrparkverwaltung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class main {

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

	}

}
