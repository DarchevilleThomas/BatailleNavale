package fr.univartois.butinfo.ihm;

public class GridJeu {
	// Attributs
	private static final int HAUTEUR = 10;
	private static final int LARGEUR = 10;
	private BoatPart[][] grille = new BoatPart[HAUTEUR][LARGEUR];
	
	// Constructeurs
	public GridJeu() {
		for (int i=0;i<HAUTEUR;i++) {
			for (int j=0;j<LARGEUR;j++) {
				this.grille[i][j] = null;
			}
		}
	}
	
	
	// Affichage
	public void afficherGrille() {
		for (int i=0;i<HAUTEUR;i++) {
			System.out.print("| ");
			for (int j=0;j<LARGEUR;j++) {
				if (this.grille[i][j] == null ) {
					System.out.print(null+" ");
				}
				else {
				System.out.print(this.grille[i][j].typeDuBoat()+" ");
				}
			}
			System.out.print("|\n");
		}
	}



	// Méthodes
	public BoatPart caseGrille(int i,int j) {
		return this.grille[i][j];
	}
	
	public boolean placerBoatHorizontal(Boat boat,int i, int j) {
		int taille = boat.getTaille();
		if ((j+taille)<=LARGEUR && (i>=0 && i<=HAUTEUR-1) && (j>=0 && j<=LARGEUR-1) && grille[i][j]==null) {
			boolean continuer = true;
			for(int depart = j;depart<j+taille;depart++) {
				if (this.grille[i][depart] != null) {
					continuer = false;
				}
			}
		
			if (continuer==true) {
				int numPosition=0;
				for(int k = j;k<j+taille;k++)  {
					this.grille[i][k] = new BoatPart(boat,numPosition);
					numPosition++;
				}
				System.out.println("Réussi");
				return true;
			}
			else {
				System.out.println("Un autre bateau se trouve déjà ici");
				return false;
			}
		}
		else {
			System.out.println("Error");
			return false;
		}
		
	}
	
	
	public void placerBoatRandom(boolean sens, Boat boat, int i, int j) {
		if (sens == true) {
			placerBoatHorizontal(boat, i, j);
		}
		else {
			placerBoatVertical(boat, i, j);
		}
	}
	
	public boolean placerBoatVertical(Boat boat,int i, int j) {
		int taille = boat.getTaille();
		if ((i+taille)<=HAUTEUR && (i>=0 && i<=HAUTEUR-1) && (j>=0 && j<=LARGEUR-1) && grille[i][j]==null) {
			boolean continuer = true;
			for(int depart = i;depart<i+taille;depart++) {
				if (this.grille[depart][j] != null) {
					continuer = false;
				}
			}
		
			if (continuer==true) {
				int numPosition=0;
				for(int k = i;k<i+taille;k++)  {
					this.grille[k][j] = new BoatPart(boat,numPosition);
					numPosition++;
				}
				System.out.println("Réussi");
				return true;
			}
			else {
				System.out.println("Un autre bateau se trouve déjà ici");
				return false;
			}
		}
		else {
			System.out.println("Error");
			return false;
		}
		
	}
	
	
	public boolean shootBoat(int ligne, int colonne) {
		if (grille[ligne][colonne]==null) {
			return false;
		}
		grille[ligne][colonne].shootBoatPart();
		return true;
	}
	
	public boolean allBoatCoule() {
		for (int i=0;i<HAUTEUR;i++) {
			for (int j=0;j<LARGEUR;j++) {
				if (grille[i][j] != null) {
					if(grille[i][j].partBoatCoule()==false) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void popAllBoat() {
		for (int i=0;i<HAUTEUR;i++) {
			for (int j=0;j<LARGEUR;j++) {
				this.grille[i][j] = null;
			}
		}
	}
	
}
