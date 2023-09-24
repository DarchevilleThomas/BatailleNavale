package fr.univartois.butinfo.ihm;

public enum BoatType{
	DESTROYEUR(1),CROISEUR(2),TORPILLEUR(3),PORTE_AVION(4);
	private int taille;
	
	BoatType(int taille) {
		this.taille=taille;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
}
