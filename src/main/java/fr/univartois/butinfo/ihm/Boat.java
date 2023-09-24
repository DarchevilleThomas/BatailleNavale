package fr.univartois.butinfo.ihm;

import java.util.Arrays;

public class Boat {
	// Attributs
	public BoatType boatType;
	public int taille;
	public int nbImpactsRecu;
	public boolean[] impacts;
	
	// Constructeurs
	public Boat(BoatType boatType) {
		this.boatType = boatType;
		this.taille = boatType.getTaille();
		this.nbImpactsRecu = 0;
		this.impacts = new boolean[taille];
		for (int i=0;i<taille;i++) {
			this.impacts[i]=false;
		}
	}
	
	
	// Getters & Setters | toString
	public int getTaille() {
		return this.taille;
	}
	
	public BoatType getBoatType() {
		return this.boatType;
	}
	
	public int getNbImpactsRecu() {
		return nbImpactsRecu;
	}


	public boolean[] getImpacts() {
		return impacts;
	}

	
	@Override
	public String toString() {
		return "\n---------\nBoatType = " + boatType + "\nTaille = " + taille + "\nnbImpactsReçu = " + nbImpactsRecu + "\nimpacts = "
				+ Arrays.toString(impacts)+ "\n";
	}
	
	public String BoatTypetoString() {
		return ""+boatType;
	}


	// Méthodes
	public boolean boatCoule() {
		for (int i=0;i<taille;i++) {
			if (this.impacts[i]==false) {
				return false;
			}
		}
		return true;
	}
	
	public void shootBoat(int position) {
		impacts[position]=true;
		nbImpactsRecu++;
	}
}
