package modele.Othello;

import modele.Joueurs;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class LogiqueOthello {
    // Définit les directions possibles autour d'une case (les 8 directions autour d'une case)
    private static AbstractMap.SimpleEntry<Integer, Integer>[] directionsPossibles = new AbstractMap.SimpleEntry[]{
            new AbstractMap.SimpleEntry<>(0,1), new AbstractMap.SimpleEntry<>(0,-1),
            new AbstractMap.SimpleEntry<>(1,0), new AbstractMap.SimpleEntry<>(1,1),
            new AbstractMap.SimpleEntry<>(1,-1), new AbstractMap.SimpleEntry<>(-1,0),
            new AbstractMap.SimpleEntry<>(-1,1), new AbstractMap.SimpleEntry<>(-1,-1),
    };
    private static int taillePlateau = PlateauOthello.getTaillePlateau();  // Taille du plateau

    /**
     * Vérifie si la partie est terminée.
     * La partie est terminée si aucun des deux joueurs n'a de coup possible à jouer.
     * @param plateau L'état actuel du plateau.
     * @return true si la partie est terminée (aucun coup possible), false sinon.
     */
    public static boolean partieFinie(PlateauOthello plateau){
        // Vérifie si aucun des deux joueurs ne peut jouer
        return !peutJouer(1, plateau) && !peutJouer(2, plateau);
    }

    /**
     * Vérifie si un coup est valide pour le joueur donné.
     * Un coup est valide si le joueur peut capturer des pions adverses en plaçant son pion à la position donnée.
     * @param x Coordonnée en ligne de la case.
     * @param y Coordonnée en colonne de la case.
     * @param plateau L'état actuel du plateau.
     * @param joueurCourant Le joueur qui joue (1 ou 2).
     * @return Liste des directions valides où le coup peut être effectué.
     */
    public static List<AbstractMap.SimpleEntry<Integer, Integer>> coupEstValide(int x, int y, PlateauOthello plateau, int joueurCourant){
        List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = new ArrayList<>();

        // Vérification si les coordonnées sont valides
        if (x < 0 || x >= taillePlateau || y < 0 || y >= taillePlateau) {
            return directionsValides;  // Si la case est hors du plateau, retourne une liste vide
        }
        // Si la case est déjà occupée, le coup est invalide
        if (plateau.getCase(x, y) != 0) {
            return directionsValides;
        }

        // Vérification dans les 8 directions possibles autour de la case
        for (AbstractMap.SimpleEntry<Integer, Integer> direction : directionsPossibles) {
            int ligne = direction.getKey();
            int colonne = direction.getValue();
            int ligneCourante = x + ligne;
            int colonneCourante = y + colonne;
            boolean aTrouveAdversaire = false;

            // Vérifie chaque case dans la direction donnée pour voir si des pions adverses peuvent être capturés
            while (ligneCourante >= 0 && ligneCourante < taillePlateau && colonneCourante >= 0 && colonneCourante < taillePlateau) {
                int valeurCase = plateau.getCase(ligneCourante, colonneCourante);

                if (valeurCase == 0) {
                    break;  // Arrête si une case vide est rencontrée
                }
                if (valeurCase == joueurCourant) {
                    // Si on rencontre un pion du joueur courant et que des pions adverses ont été trouvés
                    if (aTrouveAdversaire) {
                        directionsValides.add(new AbstractMap.SimpleEntry<>(ligne, colonne));  // Ajoute la direction valide
                    } else {
                        break;  // Arrête si aucun pion adverse n'a été trouvé avant
                    }
                }
                aTrouveAdversaire = true;
                ligneCourante += ligne;
                colonneCourante += colonne;
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
    public static boolean peutJouer(int joueur, PlateauOthello plateau) {
        // Vérifie chaque case pour savoir si un coup valide est possible
        for (int i = 0; i < taillePlateau; i++) {
            for (int j = 0; j < taillePlateau; j++) {
                if (coupEstValide(i, j, plateau, joueur).size() > 0) {
                    return true;  // Si un coup valide est trouvé, le joueur peut jouer
                }
            }
        }
        return false;  // Si aucun coup valide n'est trouvé, le joueur ne peut pas jouer
    }

    /**
     * Retourne la liste des coups possibles pour un joueur donné.
     * @param joueurCourant Le joueur pour lequel on cherche les coups possibles.
     * @param plateau L'état actuel du plateau.
     * @return Liste des coordonnées (x,y) des coups possibles.
     */
    public static List<AbstractMap.SimpleEntry<Integer,Integer>> coupsPossibles(int joueurCourant, PlateauOthello plateau) {
        List<AbstractMap.SimpleEntry<Integer,Integer>> coupsPossibles = new ArrayList<>();
        for (int i = 0; i < taillePlateau; i++) {
            for (int j = 0; j < taillePlateau; j++) {
                if (coupEstValide(i, j, plateau, joueurCourant).size() > 0) {
                    coupsPossibles.add(new AbstractMap.SimpleEntry<>(i, j));
                }
            }
        }
        return coupsPossibles;
    }
}
