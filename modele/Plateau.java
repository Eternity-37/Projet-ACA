package modele;

import java.util.AbstractMap;
import java.util.List;

public class Plateau {
    private static final int taillePlateau = 8; // Taille fixe du plateau
    private int[][] plateau; // Instance propre à chaque jeu

    /**
     * Constructeur : Initialise un plateau vide avec les pions de départ.
     */
    public Plateau() {
        plateau = new int[taillePlateau][taillePlateau];  // Crée un tableau 2D pour le plateau de jeu

        // Initialisation du plateau à vide
        for (int i = 0; i < taillePlateau; i++) {
            for (int j = 0; j < taillePlateau; j++) {
                plateau[i][j] = 0;  // Initialise chaque case à 0 (case vide)
            }
        }

        // Placement des 4 pions initiaux au centre
        setCase(taillePlateau / 2 - 1, taillePlateau / 2 - 1, 2);  // Pion blanc (joueur 2)
        setCase(taillePlateau / 2 - 1, taillePlateau / 2, 1);  // Pion noir (joueur 1)
        setCase(taillePlateau / 2, taillePlateau / 2 - 1, 1);  // Pion noir (joueur 1)
        setCase(taillePlateau / 2, taillePlateau / 2, 2);  // Pion blanc (joueur 2)
    }

    /**
     * Constructeur de copie : Crée une copie profonde du plateau donné.
     * @param autre Le plateau à copier.
     */
    public Plateau(Plateau autre) {
        plateau = new int[taillePlateau][taillePlateau];
        for (int i = 0; i < taillePlateau; i++) {
            for (int j = 0; j < taillePlateau; j++) {
                plateau[i][j] = autre.getCase(i, j);
            }
        }
    }

    /**
     * Retourne la taille du plateau.
     * @return La taille du plateau (8x8).
     */
    public static int getTaillePlateau() {
        return taillePlateau;  // Retourne la taille fixe du plateau
    }

    /**
     * Retourne l'état actuel du plateau sous forme de tableau 2D.
     * @return Le plateau de jeu sous forme de tableau.
     */
    public int[][] getPlateau() {
        return plateau;  // Retourne le plateau actuel
    }

    /**
     * Modifie la valeur d'une case du plateau à une position donnée.
     * @param x La ligne de la case à modifier.
     * @param y La colonne de la case à modifier.
     * @param valeur La nouvelle valeur à assigner à la case (1 ou 2).
     */
    public void setCase(int x, int y, int valeur) {
        plateau[x][y] = valeur;  // Modifie la valeur de la case spécifiée par (x, y)
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
            int ligne = direction.getKey();
            int colonne = direction.getValue();
            int ligneCourante = x + ligne;
            int colonneCourante = y + colonne;

            // Tant que la case appartient à l'adversaire et est dans les limites du plateau, on retourne les pions
            while (ligneCourante >= 0 && ligneCourante < taillePlateau && 
                colonneCourante >= 0 && colonneCourante < taillePlateau && 
                getCase(ligneCourante, colonneCourante) == Joueurs.joueurSuivant(joueurCourant)) {
                setCase(ligneCourante, colonneCourante, joueurCourant);  // Retourne le pion dans cette direction
                ligneCourante += ligne;  // Avance dans la direction
                colonneCourante += colonne;  // Avance dans la direction
            }
        }
    }

    /**
     * Détermine le gagnant en comptant les pions sur le plateau.
     * @return Le nom du joueur gagnant ou "ex aequo" en cas d'égalité.
     */
    public String joueurGagnant(Joueurs joueur1, Joueurs joueur2) {
        int nombrePionsJoueur1 = 0;
        int nombrePionsJoueur2 = 0;

        // Comptage des pions sur le plateau
        for (int i = 0; i < taillePlateau; i++) {
            for (int j = 0; j < taillePlateau; j++) {
                if (plateau[i][j] == 1) {
                    nombrePionsJoueur1++;  // Comptabilise les pions du joueur 1
                } else if (plateau[i][j] == 2) {
                    nombrePionsJoueur2++;  // Comptabilise les pions du joueur 2
                }
            }
        }

        // Détermination du gagnant
        if (nombrePionsJoueur1 > nombrePionsJoueur2) {
            return joueur1.getJoueur();  // Retourne le nom du joueur 1 s'il a plus de pions
        } else if (nombrePionsJoueur2 > nombrePionsJoueur1) {
            return joueur2.getJoueur();  // Retourne le nom du joueur 2 s'il a plus de pions
        } else {
            return "ex aequo";  // Retourne "ex aequo" en cas d'égalité
        }
    }

    /**
     * Évalue la position actuelle du plateau pour un joueur donné.
     * @param plateau Le plateau à évaluer.
     * @param joueurCourant Le joueur pour lequel on évalue la position.
     * @param joueur1 Le premier joueur.
     * @param joueur2 Le second joueur.
     * @return Une valeur numérique représentant l'évaluation de la position.
     */
    public static int evaluationPlateau(Plateau plateau, int joueurCourant, Joueurs joueur1, Joueurs joueur2) {
        int valeur = 0;
        if (Jeux.partieFinie(plateau)) {
            String gagnant = plateau.joueurGagnant(joueur1, joueur2);
            if (gagnant.equals(joueur2.getJoueur())) {
                valeur = 1000;
            } else if (gagnant.equals(joueur1.getJoueur())) {
                valeur = -1000;
            }
        }
        for (int i = 0; i < taillePlateau; i++) {
            for (int j = 0; j < taillePlateau; j++) {
                if (plateau.getCase(i, j) == joueurCourant) {
                    if (i == 0 && j == 0 || i == taillePlateau - 1 && j == taillePlateau - 1 || i == 0 && j == taillePlateau - 1 || i == taillePlateau - 1 && j == 0) {
                        valeur += 11;  // Bonus pour les coins
                    } else if (i == 0 || j == 0 || i == taillePlateau - 1 || j == taillePlateau - 1) {
                        valeur += 6;  // Bonus pour les bords
                    } else {
                        valeur++;  // Valeur de base pour les autres cases
                    }
                }
            }
        }
        return valeur;
    }
}
