package modele.Awale;

import modele.ChoixJeux;
import modele.Joueurs;
import modele.Othello.PlateauOthello;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class LogiqueAwale implements ChoixJeux {

    public boolean partieFinie(PlateauAwale plateau){
        // Vérifie si aucun des deux joueurs ne peut jouer
        return !peutJouer(1, plateau) || !peutJouer(2, plateau);
    }

    public static boolean peutJouer(int joueur, PlateauAwale plateau) {
        for (int i = 1; i < 7; i++) {
            if (plateau.getCase(joueur-1,i) !=0 ){
                return true;
            }
        }
        return false;
    }

    public static List<AbstractMap.SimpleEntry<Integer,Integer>> coupsPossibles(int joueurCourant, PlateauAwale plateau) {
        List<AbstractMap.SimpleEntry<Integer,Integer>> coupsPossibles = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            if (plateau.getCase(joueurCourant-1,i) !=0 ) {
                coupsPossibles.add(new AbstractMap.SimpleEntry<>(joueurCourant-1, i));
            }
        }
        return coupsPossibles;
    }

    /**
     * Vérifie si un coup est valide pour le joueur donné.
     * Un coup est valide si une case est de son côté, n'est pas vide et n'affame pas le joueur adverse à la fin de son coup.
     * @param x Coordonnée en ligne de la case.
     * @param y Coordonnée en colonne de la case.
     * @param plateau L'état actuel du plateau.
     * @param joueurCourant Le joueur qui joue (1 ou 2).
     * @return Liste des directions valides où le coup peut être effectué.
     */
    public static List<AbstractMap.SimpleEntry<Integer, Integer>> coupEstValide(int x, int y, PlateauOthello plateau, int joueurCourant){
        List<AbstractMap.SimpleEntry<Integer, Integer>> coupsValides = new ArrayList<>();
        if (x <0 || x >= 2 || y < 0 || y >= 6) {
            return coupsValides;  // Si la case est hors du plateau, retourne une liste vide
        }
        if (plateau.getCase(x, y) == 0 || plateau.getCase(x, y) == Joueurs.joueurSuivant(joueurCourant)) {
            return coupsValides;
        }

        return coupsValides;
    }
}
