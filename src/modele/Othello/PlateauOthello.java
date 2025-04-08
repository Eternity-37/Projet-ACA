package modele.Othello;

import modele.Plateau;
import modele.Joueurs;

import java.util.AbstractMap;
import java.util.List;

/**
 * Classe représentant le plateau de jeu d'Othello.
 */
public class PlateauOthello extends Plateau {
    private int[][] plateau;

    /**
     * Constructeur : Initialise un plateau vide avec les pions de départ.
     */
    public PlateauOthello() {
        super(TAILLE_PLATEAU_OTHELLO);
        this.plateau = new int[TAILLE_PLATEAU_OTHELLO][TAILLE_PLATEAU_OTHELLO];
        initialiserPlateau();
    }

    /**
     * Constructeur de copie : Crée une copie profonde du plateau donné.
     * @param autre Le plateau à copier.
     */
    public PlateauOthello(PlateauOthello autre) {
        super(TAILLE_PLATEAU_OTHELLO);
        this.plateau = new int[TAILLE_PLATEAU_OTHELLO][TAILLE_PLATEAU_OTHELLO];
        for (int i = 0; i < TAILLE_PLATEAU_OTHELLO; i++) {
            System.arraycopy(autre.plateau[i], 0, this.plateau[i], 0, TAILLE_PLATEAU_OTHELLO);
        }
    }

    private void initialiserPlateau() {
        for (int i = 0; i < TAILLE_PLATEAU_OTHELLO; i++) {
            for (int j = 0; j < TAILLE_PLATEAU_OTHELLO; j++) {
                plateau[i][j] = 0;
            }
        }
        // Placement des pions initiaux
        plateau[TAILLE_PLATEAU_OTHELLO/2 - 1][TAILLE_PLATEAU_OTHELLO/2 - 1] = 2;  // Pion blanc (joueur 2)
        plateau[TAILLE_PLATEAU_OTHELLO/2 - 1][TAILLE_PLATEAU_OTHELLO/2] = 1;      // Pion noir (joueur 1)
        plateau[TAILLE_PLATEAU_OTHELLO/2][TAILLE_PLATEAU_OTHELLO/2 - 1] = 1;      // Pion noir (joueur 1)
        plateau[TAILLE_PLATEAU_OTHELLO/2][TAILLE_PLATEAU_OTHELLO/2] = 2;          // Pion blanc (joueur 2)
    }

    /**
     * Modifie la valeur d'une case du plateau à une position donnée.
     * @param x La ligne de la case à modifier.
     * @param y La colonne de la case à modifier.
     * @param valeur La nouvelle valeur à assigner à la case (1 ou 2).
     * @throws IllegalArgumentException Si les coordonnées sont invalides.
     */
    public void setCase(int x, int y, int valeur) {
        if (x < 0 || x >= TAILLE_PLATEAU_OTHELLO || y < 0 || y >= TAILLE_PLATEAU_OTHELLO) {
            throw new IllegalArgumentException("Coordonnées invalides : (" + x + ", " + y + ")");
        }
        plateau[x][y] = valeur;
    }

    /**
     * Retourne la valeur d'une case à une position donnée.
     * @param x La ligne de la case à consulter.
     * @param y La colonne de la case à consulter.
     * @return La valeur de la case (0 = vide, 1 = joueur 1, 2 = joueur 2).
     * @throws IllegalArgumentException Si les coordonnées sont invalides.
     */
    public int getCase(int x, int y) {
        if (x < 0 || x >= TAILLE_PLATEAU_OTHELLO || y < 0 || y >= TAILLE_PLATEAU_OTHELLO) {
            throw new IllegalArgumentException("Coordonnées invalides : (" + x + ", " + y + ")");
        }
        return plateau[x][y];
    }

    /**
     * Retourne un symbole représentant la couleur de la case.
     * @param x La ligne de la case.
     * @param y La colonne de la case.
     * @return Un symbole Unicode pour représenter la couleur de la case.
     */
    public String getCouleurCase(int x, int y) {
        int valeur = plateau[x][y];  // Récupère la valeur de la case à (x, y)
        if (valeur == 0) {
            return "\uD83D\uDFE9";  // Case vide (vert)
        } else if (valeur == 1) {
            return "\u26AB";  // Joueur 1 (noir)
        } else {
            return "\u26AA";  // Joueur 2 (blanc)
        }
    }

    /**
     * Retourne les pions adverses capturés dans les directions valides.
     * @param x La ligne du coup joué.
     * @param y La colonne du coup joué.
     * @param directionsValides La liste des directions valides pour capturer des pions.
     * @param joueurCourant Le numéro du joueur courant.
     */
    public void retournerPions(int x, int y, List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides, int joueurCourant) {
        for (AbstractMap.SimpleEntry<Integer, Integer> direction : directionsValides) {
            int dx = direction.getKey();
            int dy = direction.getValue();
            int i = x + dx;
            int j = y + dy;
            while (i >= 0 && i < TAILLE_PLATEAU_OTHELLO && j >= 0 && j < TAILLE_PLATEAU_OTHELLO && plateau[i][j] != joueurCourant && plateau[i][j] != 0) {
                plateau[i][j] = joueurCourant;
                i += dx;
                j += dy;
            }
        }
    }

    /**
     * Détermine le gagnant en comptant les pions sur le plateau.
     * @return Le nom du joueur gagnant ou "ex aequo" en cas d'égalité.
     */
    public String joueurGagnant(Joueurs joueur1, Joueurs joueur2) {
        int score1 = 0;
        int score2 = 0;
        for (int i = 0; i < TAILLE_PLATEAU_OTHELLO; i++) {
            for (int j = 0; j < TAILLE_PLATEAU_OTHELLO; j++) {
                if (plateau[i][j] == 1) {
                    score1++;
                } else if (plateau[i][j] == 2) {
                    score2++;
                }
            }
        }
        if (score1 > score2) {
            return joueur1.getJoueur();
        } else if (score2 > score1) {
            return joueur2.getJoueur();
        }
        return "Match nul";
    }

    public void afficher() {
        for (int i = 0; i < TAILLE_PLATEAU_OTHELLO; i++) {
            for (int j = 0; j < TAILLE_PLATEAU_OTHELLO; j++) {
                System.out.print(plateau[i][j] + " ");
            }
            System.out.println();
        }
    }
}
