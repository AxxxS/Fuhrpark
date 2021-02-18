package fuhrparkverwaltung;

import java.util.ArrayList;

public class Standort {
	private char name;
	private float x, y;
	private int anzahlStellplaetzeSPundKOundLU, anzahlStellplaetzeKLundTR;
	private ArrayList<Fahrzeug> stellplaetzeSPKOLU;
	private ArrayList<Fahrzeug> stellplaetzeKLTR;
	
	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getAnzahlStellplaetzeSPundKOundLU() {
		return anzahlStellplaetzeSPundKOundLU;
	}

	public void setAnzahlStellplaetzeSPundKOundLU(int anzahlStellplaetzeSPundKOundLU) {
		this.anzahlStellplaetzeSPundKOundLU = anzahlStellplaetzeSPundKOundLU;
	}

	public int getAnzahlStellplaetzeKLundTR() {
		return anzahlStellplaetzeKLundTR;
	}

	public void setAnzahlStellplaetzeKLundTR(int anzahlStellplaetzeKLundTR) {
		this.anzahlStellplaetzeKLundTR = anzahlStellplaetzeKLundTR;
	}

	public ArrayList<Fahrzeug> getStellplaetzeSPKOLU() {
		return stellplaetzeSPKOLU;
	}

	public void setStellplaetzeSPKOLU(ArrayList<Fahrzeug> stellplaetzeSPKOLU) {
		this.stellplaetzeSPKOLU = stellplaetzeSPKOLU;
	}

	public ArrayList<Fahrzeug> getStellplaetzeKLTR() {
		return stellplaetzeKLTR;
	}

	public void setStellplaetzeKLTR(ArrayList<Fahrzeug> stellplaetzeKLTR) {
		this.stellplaetzeKLTR = stellplaetzeKLTR;
	}

	public Standort(int anzahlStellplaetzeSPKOLU, int anzahlStellplaetzeKLTR) {
		this.anzahlStellplaetzeKLundTR = anzahlStellplaetzeKLTR;
		this.anzahlStellplaetzeSPundKOundLU = anzahlStellplaetzeSPKOLU;
	}
	
	public boolean fahrzeugParken(Fahrzeug fahrzeug) {
		switch(fahrzeug.getKlasse()) {
		case SP, KO, LU:
			if(this.stellplaetzeSPKOLU.size() < this.anzahlStellplaetzeSPundKOundLU) {
				this.stellplaetzeSPKOLU.add(fahrzeug);
				return true;
			} else {
				return false;
			}
		case KL, TR:
			if(this.stellplaetzeKLTR.size() < this.anzahlStellplaetzeKLundTR) {
				this.stellplaetzeKLTR.add(fahrzeug);
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}
}
