/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022-2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * La classe HelloController illustre le fonctionnement du contrôleur associé à une vue.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public class BatailleNavaleController {

	// GÉNÉRAL
	private static final int NB_LIGNES = 10;
	private static final int NB_COLONNES = 10;

    @FXML
    private Label LabelWin;
    
    @FXML
    private Button StartButton;
    
    @FXML
    public void StartBatailleNavale() {
    	Bateau01_1.setDisable(true);
    	Bateau01_2.setDisable(true);
    	Bateau01_3.setDisable(true);
    	Bateau01_4.setDisable(true);
    	Vertical01.setDisable(true);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
            	 Grid02Buttons[i][j].setDisable(false);
            	 Grid01Buttons[i][j].setDisable(true);
            }
           }
        StartButton.setDisable(true);
        StartButton.setVisible(false);
        putBoadRandom();
    }
    
    @FXML
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button btn = new Button();
                Grid01.add(btn, j, i);
                Grid01Buttons[i][j] = btn;
                Grid01Buttons[i][j].setPrefSize(51.3,31.4);
                Grid01Buttons[i][j].setOnAction(this::putBoatJoueur1);
                
            }
        }
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button btn = new Button();
                Grid02.add(btn, j, i);
                Grid02Buttons[i][j] = btn;
                Grid02Buttons[i][j].setPrefSize(51.3,31.4);
                Grid02Buttons[i][j].setOnAction(this::shoot);
                Grid02Buttons[i][j].setDisable(true);
            }
        }
        StartButton.setDisable(true);
        Label2_04.setText("Porte-avion ennemi restant : "+nbBateau02_4);
        Label2_03.setText("Torpilleurs ennemi restant : "+nbBateau02_3);
        Label2_02.setText("Croiseurs ennemi restant : "+nbBateau02_2);
        Label2_01.setText("Destroyeurs ennemi restant : "+nbBateau02_1);
        Bateau01_1.setText("Destroyeurs ("+nbBateau01_1+")");
        Bateau01_2.setText("Croiseurs ("+nbBateau01_2+")");
        Bateau01_3.setText("Torpilleurs ("+nbBateau01_3+")");
        Bateau01_4.setText("Porte-avions ("+nbBateau01_4+")");
        LabelWin.setText("");
    }

    
    private Boat currentBoat = null;
    private int boatChoisi;
    
    // Joueur 1
    @FXML
    private GridPane Grid01;
    
    private Button[][] Grid01Buttons = new Button[NB_LIGNES][NB_COLONNES];
    private GridJeu grilleJoueur1 = new GridJeu();
    
    @FXML
    private Button Bateau01_1;
    
    private int nbBateau01_1 = 4;
    @FXML
    private Button Bateau01_2;
    
    private int nbBateau01_2 = 3;
    @FXML
    private Button Bateau01_3;
    
    private int nbBateau01_3 = 2;
    @FXML
    private Button Bateau01_4;
    
    private int nbBateau01_4 = 1;
    @FXML
    private Button Vertical01;
    
    @FXML
    public void placerBateau01_1() {
    	boatChoisi = 1;
    	currentBoat = new Boat(BoatType.DESTROYEUR);
    }
    @FXML
    public void placerBateau01_2() {
    	boatChoisi = 2;
    	currentBoat = new Boat(BoatType.CROISEUR);

    }
    @FXML
    public void placerBateau01_3() {
    	boatChoisi = 3;
    	currentBoat = new Boat(BoatType.TORPILLEUR);

    }
    @FXML
    public void placerBateau01_4() {
    	boatChoisi = 4;
    	currentBoat = new Boat(BoatType.PORTE_AVION);

    }
    @FXML
    public void tournerPiece01() {
    	String s = Vertical01.getText();
    	if (s.equals("Verticale")) {
        	Vertical01.setText("Horizontale");	
    	}
    	else {
    		Vertical01.setText("Verticale");	
    	}
    }
    
    @FXML
    private void putBoatJoueur1(ActionEvent event) {
    	if (boatChoisi!=0) {
    		if ((boatChoisi==1 && nbBateau01_1>0) || (boatChoisi==2 && nbBateau01_2>0) || (boatChoisi==3 && nbBateau01_3>0) || (boatChoisi==4 && nbBateau01_4>0) ) {
    			Button btn = (Button) event.getSource();
    	    	int row = GridPane.getRowIndex(btn);
    	    	int column = GridPane.getColumnIndex(btn);
    	    	if (Vertical01.getText().equals("Horizontale")) {
    	    		if (grilleJoueur1.placerBoatHorizontal(currentBoat,row,column)) {
        	    		grilleJoueur1.placerBoatHorizontal(currentBoat, row, column);
    	    		}
    	    		else {
    	    			System.out.println("Il y a déjà un bateau ici !");
    	    			if (boatChoisi==1) {
        		        	nbBateau01_1++;
        		        }
        		        if (boatChoisi==2) {
        		        	nbBateau01_2++;
        		        }
        		        if (boatChoisi==3) {
        		        	nbBateau01_3++;
        		        }
        		        if (boatChoisi==4) {
        		        	nbBateau01_4++;
        		        }
    	    		}
    	    	}
    	    	else if (Vertical01.getText().equals("Verticale")) {
    	    		if (grilleJoueur1.placerBoatVertical(currentBoat,row,column)) {
    	    			grilleJoueur1.placerBoatVertical(currentBoat, row, column);
    	    		}
    	    		else {
    	    			System.out.println("Il y a déjà un bateau ici !");
    	    			if (boatChoisi==1) {
        		        	nbBateau01_1++;
        		        }
        		        if (boatChoisi==2) {
        		        	nbBateau01_2++;
        		        }
        		        if (boatChoisi==3) {
        		        	nbBateau01_3++;
        		        }
        		        if (boatChoisi==4) {
        		        	nbBateau01_4++;
        		        }
    	    		}
    	    	}
    	    	for (int i=0;i<NB_LIGNES;i++) {
    	    		for (int j=0;j<NB_COLONNES;j++) {
    	    			if (grilleJoueur1.caseGrille(i, j) != null ) {
    	    				Grid01Buttons[i][j].setDisable(true);
    	    				Grid01Buttons[i][j].setStyle("-fx-background-color: #27ae60");
    	    			}
    	    		}
    	    	}
    	    	  if (boatChoisi==1) {
    		        	nbBateau01_1--;
    		        }
    		        if (boatChoisi==2) {
    		        	nbBateau01_2--;
    		        }
    		        if (boatChoisi==3) {
    		        	nbBateau01_3--;
    		        }
    		        if (boatChoisi==4) {
    		        	nbBateau01_4--;
    		        }
    		        currentBoat = null;
    		        boatChoisi = 0;
    		}
    	}
    	if (nbBateau01_1==0 && nbBateau01_2==0 && nbBateau01_3==0 && nbBateau01_4==0) {
    		StartButton.setDisable(false);
    	}
        Bateau01_1.setText("Destroyeurs ("+nbBateau01_1+")");
        Bateau01_2.setText("Croiseurs ("+nbBateau01_2+")");
        Bateau01_3.setText("Torpilleurs ("+nbBateau01_3+")");
        Bateau01_4.setText("Porte-avions ("+nbBateau01_4+")");
    }

    public void shoot(ActionEvent event) {
    	Button btn = (Button) event.getSource();
    	int row = GridPane.getRowIndex(btn);
    	int column = GridPane.getColumnIndex(btn);
    	if (grilleJoueur2.shootBoat(row, column)) {
    		grilleJoueur2.shootBoat(row, column);
    		grilleJoueur2.caseGrille(row, column).setTirAlready(true);
    		Grid02Buttons[row][column].setStyle("-fx-background-color: #ff1f1f");
    		Grid02Buttons[row][column].setDisable(true);
    		if (grilleJoueur2.caseGrille(row, column).partBoatCoule()) {
    			if (grilleJoueur2.caseGrille(row, column).typeDuBoat() == BoatType.DESTROYEUR) {
    				nbBateau02_1--;
    				Label2_01.setText("Destroyeurs ennemi restant : "+nbBateau02_1);
    			}
    			if (grilleJoueur2.caseGrille(row, column).typeDuBoat() == BoatType.CROISEUR) {
    				nbBateau02_2--;
    				Label2_02.setText("Croiseurs ennemi restant : "+nbBateau02_2);
    			}
    			if (grilleJoueur2.caseGrille(row, column).typeDuBoat() == BoatType.TORPILLEUR) {
    				nbBateau02_3--;
    				Label2_03.setText("Torpilleurs ennemi restant : "+nbBateau02_3);
    			}
    			if (grilleJoueur2.caseGrille(row, column).typeDuBoat() == BoatType.PORTE_AVION) {
    				nbBateau02_4--;
    				Label2_04.setText("Porte-avion ennemi restant : "+nbBateau02_4);
    			}
    		}
    	}
    	else {
    		Grid02Buttons[row][column].setStyle("-fx-background-color: #1ff1ff");
    	}
    	if (grilleJoueur2.allBoatCoule()) {
    		LabelWin.setText("Le joueur 1 a gagne !");
    		for (int i=0;i<NB_LIGNES;i++) {
	    		for (int j=0;j<NB_COLONNES;j++) {
	    			Grid02Buttons[i][j].setDisable(true);
	    			Bateau01_1.setVisible(false);
	    			Bateau01_2.setVisible(false);
	    			Bateau01_3.setVisible(false);
	    			Bateau01_4.setVisible(false);

	    		}
    		}
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Fin de partie");
    		alert.setHeaderText("La partie est fini !");
    		alert.setContentText("Le joueur 1 a gagné !");

    		alert.showAndWait();
    	}
    	else {
        	shootRandom();	
    	}
    	
    	if (grilleJoueur1.allBoatCoule()) {
    		LabelWin.setText("Le joueur 2 a gagne !");
    		for (int i=0;i<NB_LIGNES;i++) {
	    		for (int j=0;j<NB_COLONNES;j++) {
	    			Grid02Buttons[i][j].setDisable(true);
	    			
	    		}
    		}
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Fin de partie");
    		alert.setHeaderText("La partie est fini !");
    		alert.setContentText("Le joueur 2 a gagné !");

    		alert.showAndWait();

    	}
    }
    
    
    // Joueur 2
    @FXML
    private GridPane Grid02;
    
    private Button[][] Grid02Buttons = new Button[NB_LIGNES][NB_COLONNES];
    private GridJeu grilleJoueur2 = new GridJeu();
    

    @FXML
    private Label Label2_01;

    @FXML
    private Label Label2_02;

    @FXML
    private Label Label2_03;

    @FXML
    private Label Label2_04;


    
    private int nbBateau02_1 = 4;


    
    private int nbBateau02_2 = 3;


    
    private int nbBateau02_3 = 2;


    
    private int nbBateau02_4 = 1;

    
    @FXML
    public void placerBateau02_1() {
    	boatChoisi = 5;
    	currentBoat = new Boat(BoatType.DESTROYEUR);
    }
    @FXML
    public void placerBateau02_2() {
    	boatChoisi = 6;
    	currentBoat = new Boat(BoatType.CROISEUR);

    }
    @FXML
    public void placerBateau02_3() {
    	boatChoisi = 7;
    	currentBoat = new Boat(BoatType.TORPILLEUR);

    }
    @FXML
    public void placerBateau02_4() {
    	boatChoisi = 8;
    	currentBoat = new Boat(BoatType.PORTE_AVION);

    }
    
    public void putBoadRandom() {
    	final Random r = new Random();
    	int nbDestroyeur = 4;
    	int nbCroiseur = 3;
    	int nbTorpilleur = 2;
    	int nbPorteAvion = 1;
    	int ligne;
    	int colonne;
    	boolean sens;
    	
    	for (int i=0;i<nbDestroyeur;i++) {
    		Boat destroyeur = new Boat(BoatType.DESTROYEUR);
    		ligne = r.nextInt(NB_LIGNES);
        	colonne = r.nextInt(NB_COLONNES);
        	sens = r.nextBoolean();
        	if (sens==true) {
        		if (grilleJoueur2.placerBoatHorizontal(destroyeur,ligne,colonne) ) {
        			grilleJoueur2.placerBoatHorizontal(destroyeur,ligne,colonne); 
        		}
        		else {
        			i--;
        		}
        	}
        	else {
        		if (grilleJoueur2.placerBoatVertical(destroyeur,ligne,colonne)){
        			grilleJoueur2.placerBoatVertical(destroyeur,ligne,colonne);
        		}
        		else {
        			i--;
        		}
        		
        	}
    	}
    	
    	for (int i=0;i<nbCroiseur;i++) {
    		Boat croiseur = new Boat(BoatType.CROISEUR);
    		ligne = r.nextInt(NB_LIGNES);
        	colonne = r.nextInt(NB_COLONNES);
        	sens = r.nextBoolean();
        	if (sens==true) {
        		if (grilleJoueur2.placerBoatHorizontal(croiseur,ligne,colonne) ) {
        			grilleJoueur2.placerBoatHorizontal(croiseur,ligne,colonne); 
        		}
        		else {
        			i--;
        		}
        	}
        	else {
        		if (grilleJoueur2.placerBoatVertical(croiseur,ligne,colonne)){
        			grilleJoueur2.placerBoatVertical(croiseur,ligne,colonne);
        		}
        		else {
        			i--;
        		}
        		
        	}
    	}

    	for (int i=0;i<nbTorpilleur;i++) {
    		Boat torpilleur = new Boat(BoatType.TORPILLEUR);
    		ligne = r.nextInt(NB_LIGNES);
        	colonne = r.nextInt(NB_COLONNES);
        	sens = r.nextBoolean();
        	if (sens==true) {
        		if (grilleJoueur2.placerBoatHorizontal(torpilleur,ligne,colonne) ) {
        			grilleJoueur2.placerBoatHorizontal(torpilleur,ligne,colonne);
        		}
        		else {
        			i--;
        		}
        	}
        	else {
        		if (grilleJoueur2.placerBoatVertical(torpilleur,ligne,colonne)){
        			grilleJoueur2.placerBoatVertical(torpilleur,ligne,colonne);
        		}
        		else {
        			i--;
        		}
        		
        	}
    	}
    	
    	for (int i=0;i<nbPorteAvion;i++) {
    		Boat porteAvion = new Boat(BoatType.PORTE_AVION);
    		ligne = r.nextInt(NB_LIGNES);
        	colonne = r.nextInt(NB_COLONNES);
        	sens = r.nextBoolean();
        	if (sens==true) {
        		if (grilleJoueur2.placerBoatHorizontal(porteAvion,ligne,colonne) ) {
        			grilleJoueur2.placerBoatHorizontal(porteAvion,ligne,colonne); 
        		}
        		else {
        			i--;
        		}
        	}
        	else {
        		if (grilleJoueur2.placerBoatVertical(porteAvion,ligne,colonne)){
        			grilleJoueur2.placerBoatVertical(porteAvion,ligne,colonne);
        		}
        		else {
        			i--;
        		}
        		
        	}
    	}
    	
    }
    
    @FXML
    public void shootRandom() {
    	final Random r = new Random();
    	boolean continuer = true;
    	while (continuer) {
        	int row = r.nextInt(NB_LIGNES);
        	int column = r.nextInt(NB_COLONNES);
    		if (Grid01Buttons[row][column].getStyle().equals("-fx-background-color: #ff1f1f") || Grid01Buttons[row][column].getStyle().equals("-fx-background-color: #1ff1ff")) {
    			continuer = true;
    		}
    		else {
    			continuer = false;
		    	if (grilleJoueur1.shootBoat(row, column)) {
		    		grilleJoueur1.shootBoat(row, column);
		    		grilleJoueur1.caseGrille(row, column).setTirAlready(true);
		    		Grid01Buttons[row][column].setStyle("-fx-background-color: #ff1f1f");
		    		Grid01Buttons[row][column].setDisable(true);
		    		if (grilleJoueur1.caseGrille(row, column).partBoatCoule()) {
		    			if (grilleJoueur1.caseGrille(row, column).typeDuBoat() == BoatType.DESTROYEUR) {
		    				nbBateau01_1--;
		    			}
		    			if (grilleJoueur1.caseGrille(row, column).typeDuBoat() == BoatType.CROISEUR) {
		    				nbBateau01_2--;
		    			}
		    			if (grilleJoueur1.caseGrille(row, column).typeDuBoat() == BoatType.TORPILLEUR) {
		    				nbBateau01_3--;
		    			}
		    			if (grilleJoueur1.caseGrille(row, column).typeDuBoat() == BoatType.PORTE_AVION) {
		    				nbBateau01_4--;
		    			}
		    		}
		    	}
		    	else {
		    		Grid01Buttons[row][column].setStyle("-fx-background-color: #1ff1ff");
		    	}
    		}
    	}
    }
    
    
    
    
    
    /*
    @FXML
    private void putBoatJoueur2(ActionEvent event) {
    	if (boatChoisi!=0) {
    		if ((boatChoisi==5 && nbBateau02_1>0) || (boatChoisi==6 && nbBateau02_2>0) || (boatChoisi==7 && nbBateau02_3>0) || (boatChoisi==8 && nbBateau02_4>0) ) {
    			Button btn = (Button) event.getSource();
    	    	int row = GridPane.getRowIndex(btn);
    	    	int column = GridPane.getColumnIndex(btn);
    	    	if (Vertical02.getText().equals("Horizontale")) {
    	    		if (grilleJoueur2.placerBoatHorizontal(currentBoat,row,column)) {
        	    		grilleJoueur2.placerBoatHorizontal(currentBoat, row, column);
    	    		}
    	    		else {
    	    			System.out.println("Il y a déjà un bateau ici !");
    	    			if (boatChoisi==5) {
        		        	nbBateau02_1++;
        		        }
        		        if (boatChoisi==6) {
        		        	nbBateau02_2++;
        		        }
        		        if (boatChoisi==7) {
        		        	nbBateau02_3++;
        		        }
        		        if (boatChoisi==8) {
        		        	nbBateau02_4++;
        		        }
    	    		}
    	    	}
    	    	else if (Vertical02.getText().equals("Verticale")) {
    	    		if (grilleJoueur2.placerBoatVertical(currentBoat,row,column)) {
    	    			grilleJoueur2.placerBoatVertical(currentBoat, row, column);
    	    		}
    	    		else {
    	    			System.out.println("Il y a déjà un bateau ici !");
    	    			if (boatChoisi==5) {
        		        	nbBateau02_1++;
        		        }
        		        if (boatChoisi==6) {
        		        	nbBateau02_2++;
        		        }
        		        if (boatChoisi==7) {
        		        	nbBateau02_3++;
        		        }
        		        if (boatChoisi==8) {
        		        	nbBateau02_4++;
        		        }
    	    		}
    	    	}
    	    	for (int i=0;i<NB_LIGNES;i++) {
    	    		for (int j=0;j<NB_COLONNES;j++) {
    	    			if (grilleJoueur2.caseGrille(i, j) != null ) {
    	    				Grid02Buttons[i][j].setDisable(true);
    	    			}
    	    		}
    	    	}
    	    	  if (boatChoisi==5) {
    		        	nbBateau02_1--;
    		        }
    		        if (boatChoisi==6) {
    		        	nbBateau02_2--;
    		        }
    		        if (boatChoisi==7) {
    		        	nbBateau02_3--;
    		        }
    		        if (boatChoisi==8) {
    		        	nbBateau02_4--;
    		        }
    		        currentBoat = null;
    		        boatChoisi = 0;
    		}
			
    	}
    }
    */
   
}
