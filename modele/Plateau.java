package modele;

import java.util.AbstractMap;
import java.util.List;

public class Plateau {
    private static final int taille_plateau = 8; // Taille fixe du plateau
    private int[][] plateau; // Instance propre à chaque jeu

    /**
     * Constructeur : Initialise un plateau vide avec les pions de départ.
     */
    public Plateau() {
        plateau = new int[taille_plateau][taille_plateau];  // Crée un tableau 2D pour le plateau de jeu

        // Initialisation du plateau à vide
        for (int i = 0; i < taille_plateau; i++) {
            for (int j = 0; j < taille_plateau; j++) {
                plateau[i][j] = 0;  // Initialise chaque case à 0 (case vide)
            }
        }

        // Placement des 4 pions initiaux au centre
        setCase(taille_plateau / 2 - 1, taille_plateau / 2 - 1, 2);  // Pion blanc (joueur 2)
        setCase(taille_plateau / 2 - 1, taille_plateau / 2, 1);  // Pion noir (joueur 1)
        setCase(taille_plateau / 2, taille_plateau / 2 - 1, 1);  // Pion noir (joueur 1)
        setCase(taille_plateau / 2, taille_plateau / 2, 2);  // Pion blanc (joueur 2)
    }

    /**
     * Retourne la taille du plateau.
     * @return La taille du plateau (8x8).
     */
    public static int getTaillePlateau() {
        return taille_plateau;  // Retourne la taille fixe du plateau
    }

    /**
     * Modifie la valeur d'une case du plateau à une position donnée.
     * @param x La ligne de la case à modifier.
     * @param y La colonne de la case à modifier.
     * @param num La nouvelle valeur à assigner à la case (1 ou 2).
     */
    public void setCase(int x, int y, int num) {
        plateau[x][y] = num;  // Modifie la valeur de la case spécifiée par (x, y)
    }

    /**
     * Retourne la valeur d'une case à une position donnée.
     * @param x La ligne de la case à consulter.
     * @param y La colonne de la case à consulter.
     * @return La valeur de la case (0 = vide, 1 = joueur 1, 2 = joueur 2).
     */
    public int getCase(int x, int y) {
        return plateau[x][y];  // Retourne la valeur de la case spécifiée par (x, y)
    }

    /**
     * Retourne un symbole représentant la couleur de la case.
     * @param x La ligne de la case.
     * @param y La colonne de la case.
     * @return Un symbole Unicode pour représenter la couleur de la case.
     */
    public String getCouleurcase(int x, int y) {
        int num = plateau[x][y];  // Récupère la valeur de la case à (x, y)
        if (num == 0) {
            return "\uD83D\uDFE9";  // Case vide (vert)
        } else if (num == 1) {
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
     * @param joueurcourant Le numéro du joueur courant.
     */
    public void retournerPions(int x, int y, List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides, int joueurcourant) {
        for (AbstractMap.SimpleEntry<Integer, Integer> direction : directionsValides) {
            int ligne = direction.getKey();
            int colonne = direction.getValue();
            int lignecourante = x + ligne;
            int colonnecourante = y + colonne;

            // Tant que la case appartient à l'adversaire, on retourne les pions
            while (getCase(lignecourante, colonnecourante) == Joueurs.joueurSuivant(joueurcourant)) {
                setCase(lignecourante, colonnecourante, joueurcourant);  // Retourne le pion dans cette direction
                lignecourante += ligne;  // Avance dans la direction
                colonnecourante += colonne;  // Avance dans la direction
            }
        }
    }

    /**
     * Détermine le gagnant en comptant les pions sur le plateau.
     * @return Le nom du joueur gagnant ou "ex aequo" en cas d'égalité.
     */
    public int joueurGagnant(int joueur1,int joueur2) {
        int nbPionsjoueur1 = 0;
        int nbPionsjoueur2 = 0;

        // Comptage des pions sur le plateau
        for (int i = 0; i < taille_plateau; i++) {
            for (int j = 0; j < taille_plateau; j++) {
                if (plateau[i][j] == 1) {
                    nbPionsjoueur1++;  // Comptabilise les pions du joueur 1
                } else if (plateau[i][j] == 2) {
                    nbPionsjoueur2++;  // Comptabilise les pions du joueur 2
                }
            }
        }

        // Détermination du gagnant
        if (nbPionsjoueur1 > nbPionsjoueur2) {
            return joueur1;  // Retourne 1 si le joueur 1 n'a plus de pions
        } else if (nbPionsjoueur2 > nbPionsjoueur1) {
            return joueur2;  // Retourne 2 si le joueur 2 n'a plus de pions
        } else {
            return 0;  // Retourne 0 en cas d'égalité
        }
    }

    public int evaluationPlateau(Plateau plateau, int joueur) {
        int eval = 0;
        for (int i = 0; i < taille_plateau; i++) {
            for (int j = 0; j < taille_plateau; j++) {
                // Vérification des coins
                if (i == taille_plateau - 1 && j == taille_plateau - 1 || i == 0 && j == 0 || i == 0 && j == taille_plateau - 1 || i == taille_plateau - 1 && j == 0) {
                    if (plateau.getCase(i,j) == joueur) {
                        eval += 11;
                    }
                }
                // Vérification des bords
                else if (i == 0 || i == taille_plateau - 1 || j == 0 || j == taille_plateau -1) {
                    if (plateau.getCase(i,j) == joueur) {
                        eval += 6;
                    }
                }
                // Si le pion n'est ni sur un bord ni sur un coin
                else if (plateau.getCase(i,j) == joueur) {
                    eval ++;
                }
                // Vérification si la partie sera finie
                if (plateau.joueurGagnant(joueur, Joueurs.joueurSuivant(joueur)) == joueur){
                    eval += 1000;
                } else if (plateau.joueurGagnant(joueur,Joueurs.joueurSuivant(joueur)) == Joueurs.joueurSuivant(joueur)) {
                    eval -= 1000;
                }
            }
        }
        return eval;
    }
}
