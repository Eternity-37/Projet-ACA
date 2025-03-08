package modele;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class Jeux {
    private static SimpleEntry<Integer, Integer>[] casesVoisines = new SimpleEntry[]{
            new SimpleEntry<>(0,1), new SimpleEntry<>(0,-1),
            new SimpleEntry<>(1,0), new SimpleEntry<>(1,1),
            new SimpleEntry<>(1,-1), new SimpleEntry<>(-1,0),
            new SimpleEntry<>(-1,1), new SimpleEntry<>(-1,-1),
    };
    private static int taille_plateau = Plateau.getTaillePlateau();

    public static boolean partieFinie(Plateau plateau){
        boolean coupPossible = false;
        for (int i = 0; i<taille_plateau ; i++){
            for (int j = 0; j<taille_plateau ; j++) {
                if (plateau.getCase(i,j)==0) {
                    if (coupEstValide(i, j, plateau, 1).isEmpty()) {
                        coupPossible = true;
                    }
                    if (coupEstValide(i, j, plateau, 2).isEmpty()) {
                        coupPossible = true;
                    }
                }
            }
        }
        return !coupPossible;
    }

    public static List<SimpleEntry<Integer, Integer>> coupEstValide(int x, int y, Plateau plateau, int joueurcourant){
        List<SimpleEntry<Integer, Integer>> directionsValides = new ArrayList<>();

        if (x < 0 || x >= taille_plateau || y < 0 || y >= taille_plateau) {
            return directionsValides;
        }
        if (Plateau.getCase(x, y) != 0 || plateau.getCase(x, y) == Joueurs.joueurSuivant(joueurcourant)) {
            return directionsValides;
        }
        for (SimpleEntry<Integer, Integer> direction : casesVoisines) {
            int ligne = direction.getKey();
            int colonne = direction.getValue();
            int lignecourante = x + ligne;
            int colonnecourante = y + colonne;
            boolean aTrouveAdversaire = false;

            while (lignecourante >= 0 && lignecourante < taille_plateau && colonnecourante >= 0 && colonnecourante < taille_plateau) {
                int caseCourante = plateau.getCase(lignecourante, colonnecourante);

                if (caseCourante == 0) {
                    break;
                }
                if (caseCourante == joueurcourant) {
                    if (aTrouveAdversaire) {
                        directionsValides.add(new SimpleEntry<>(ligne, colonne));
                    }
                    else {
                        break;
                    }
                }
                aTrouveAdversaire = true;
                lignecourante += ligne;
                colonnecourante += colonne;
            }
        }
        return directionsValides;
    }
}
