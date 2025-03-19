package modele;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class Jeux {
    // Définit les directions possibles autour d'une case (les 8 directions autour d'une case)
    private static SimpleEntry<Integer, Integer>[] casesVoisines = new SimpleEntry[]{
            new SimpleEntry<>(0,1), new SimpleEntry<>(0,-1),
            new SimpleEntry<>(1,0), new SimpleEntry<>(1,1),
            new SimpleEntry<>(1,-1), new SimpleEntry<>(-1,0),
            new SimpleEntry<>(-1,1), new SimpleEntry<>(-1,-1),
    };
    private static int taille_plateau = Plateau.getTaillePlateau();  // Taille du plateau

    /**
     * Vérifie si la partie est terminée.
     * La partie est terminée si aucun des deux joueurs n'a de coup possible à jouer.
     * @param plateau L'état actuel du plateau.
     * @return true si la partie est terminée (aucun coup possible), false sinon.
     */
    public static boolean partieFinie(Plateau plateau){
        boolean coupPossible = false;

        // Vérification de chaque case du plateau pour voir si un coup est possible
        for (int i = 0; i<taille_plateau ; i++){
            for (int j = 0; j<taille_plateau ; j++) {
                if (plateau.getCase(i,j)==0) {
                    // Vérification des coups possibles pour chaque joueur
                    if (coupEstValide(i, j, plateau, 1).isEmpty()) {
                        coupPossible = true;
                    }
                    if (coupEstValide(i, j, plateau, 2).isEmpty()) {
                        coupPossible = true;
                    }
                }
            }
        }
        return !coupPossible;  // Si aucun coup n'est possible, la partie est terminée
    }

    /**
     * Vérifie si un coup est valide pour le joueur donné.
     * Un coup est valide si le joueur peut capturer des pions adverses en plaçant son pion à la position donnée.
     * @param x Coordonnée en ligne de la case.
     * @param y Coordonnée en colonne de la case.
     * @param plateau L'état actuel du plateau.
     * @param joueurcourant Le joueur qui joue (1 ou 2).
     * @return Liste des directions valides où le coup peut être effectué.
     */
    public static List<SimpleEntry<Integer, Integer>> coupEstValide(int x, int y, Plateau plateau, int joueurcourant){
        List<SimpleEntry<Integer, Integer>> directionsValides = new ArrayList<>();

        // Vérification si les coordonnées sont valides
        if (x < 0 || x >= taille_plateau || y < 0 || y >= taille_plateau) {
            return directionsValides;  // Si la case est hors du plateau, retourne une liste vide
        }
        // Si la case est déjà occupée ou appartient au joueur courant, le coup est invalide
        if (plateau.getCase(x, y) != 0 || plateau.getCase(x, y) == Joueurs.joueurSuivant(joueurcourant)) {
            return directionsValides;
        }

        // Vérification dans les 8 directions possibles autour de la case
        for (SimpleEntry<Integer, Integer> direction : casesVoisines) {
            int ligne = direction.getKey();
            int colonne = direction.getValue();
            int lignecourante = x + ligne;
            int colonnecourante = y + colonne;
            boolean aTrouveAdversaire = false;

            // Vérifie chaque case dans la direction donnée pour voir si des pions adverses peuvent être capturés
            while (lignecourante >= 0 && lignecourante < taille_plateau && colonnecourante >= 0 && colonnecourante < taille_plateau) {
                int caseCourante = plateau.getCase(lignecourante, colonnecourante);

                if (caseCourante == 0) {
                    break;  // Arrête si une case vide est rencontrée
                }
                if (caseCourante == joueurcourant) {
                    // Si on rencontre un pion du joueur courant et que des pions adverses ont été trouvés
                    if (aTrouveAdversaire) {
                        directionsValides.add(new SimpleEntry<>(ligne, colonne));  // Ajoute la direction valide
                    } else {
                        break;  // Arrête si aucun pion adverse n'a été trouvé avant
                    }
                }
                aTrouveAdversaire = true;
                lignecourante += ligne;
                colonnecourante += colonne;
            }
        }
        return directionsValides;  // Retourne les directions valides pour ce coup
    }

    /**
     * Vérifie si le joueur peut encore jouer.
     * Un joueur peut jouer s'il a au moins une case sur laquelle il peut poser un pion.
     * @param joueur Le joueur (1 ou 2).
     * @param plateau L'état actuel du plateau.
     * @return true si le joueur peut encore jouer, false sinon.
     */
    public static boolean peutJouer(int joueur, Plateau plateau) {
        // Vérifie chaque case pour savoir si un coup valide est possible
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (coupEstValide(i, j, plateau, joueur).size() > 0) {
                    return true;  // Si un coup valide est trouvé, le joueur peut jouer
                }
            }
        }
        return false;  // Si aucun coup valide n'est trouvé, le joueur ne peut pas jouer
    }

}
