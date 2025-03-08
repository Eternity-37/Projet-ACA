package modele;

import java.util.AbstractMap.SimpleEntry;

public class Jeux {
    private static SimpleEntry<Integer, Integer>[] casesVoisines = new SimpleEntry[]{
            new SimpleEntry<>(0,1), new SimpleEntry<>(0,-1),
            new SimpleEntry<>(1,0), new SimpleEntry<>(1,1),
            new SimpleEntry<>(1,-1), new SimpleEntry<>(-1,0),
            new SimpleEntry<>(-1,1), new SimpleEntry<>(-1,-1),
    };

    public static boolean partieFinie(Plateau plateau){
        return false;
    }

    public static boolean coupEstValide(int x, int y, Plateau plateau,int joueurcourant){
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }
        if (Plateau.getCase(x, y) != 0 || plateau.getCase(x, y) == Joueurs.joueurSuivant(joueurcourant)) {
            return false;
        }
        for (SimpleEntry<Integer, Integer> direction : casesVoisines) {
            int ligne = direction.getKey();
            int colonne = direction.getValue();
            int lignecourante = x + ligne;
            int colonnourante = y + colonne;
            boolean aTrouveAdversaire = false;

            while (lignecourante >= 0 && lignecourante < 8 && colonnourante >= 0 && colonnourante < 8) {
                int caseCourante = plateau.getCase(lignecourante, colonnourante);

                if (caseCourante == 0) {
                    break;
                }
                if (caseCourante == joueurcourant) {
                    if (aTrouveAdversaire) {
                        return true;
                    }
                    else {
                        break;
                    }
                }
                aTrouveAdversaire = true;
                lignecourante += ligne;
                colonnourante += colonne;
            }
        }
        return false;
    }
}
