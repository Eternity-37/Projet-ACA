package modele.Awale;

import modele.ChoixPlateau;
import modele.Joueurs;

public class PlateauAwale implements ChoixPlateau {

    private int[][] plateau;

    public PlateauAwale() {
        plateau = new int[7][7];  // Crée un tableau 2D pour le plateau de jeu

        // Initialisation du plateau
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
            plateau[i][j] = 4;  // Initialise chaque case à 4
            }
        }
        // met les greniers à 0
        plateau[0][0] = 0; //côté joueur 1
        plateau[0][1] = 0; //côté joueur 2
    }
    @Override
    public void setCase(int x, int y,int valeur) {plateau[x][y] = valeur;}

    @Override
    public int getCase(int x, int y) {return plateau[x][y];}
    @Override
    public String joueurGagnant(Joueurs joueur1, Joueurs joueur2) {
        int nombreGrainesJoueur1 = getCase(0,0);
        int nombreGrainesJoueur2 = getCase(0,1);

        // Détermination du gagnant
        if (nombreGrainesJoueur1 > nombreGrainesJoueur2) {
            return joueur1.getJoueur();  // Retourne le nom du joueur 1 s'il a plus de pions
        } else if (nombreGrainesJoueur2 > nombreGrainesJoueur1) {
            return joueur2.getJoueur();  // Retourne le nom du joueur 2 s'il a plus de pions
        } else {
            return "ex aequo";  // Retourne "ex aequo" en cas d'égalité
        }
    }

    public void rafle(int x, int y,int joueurcourant) {

    }


}
