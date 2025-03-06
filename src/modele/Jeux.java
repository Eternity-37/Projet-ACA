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
        if (x >=0 && x<8 && y >=0 && y<8) {
            for (SimpleEntry<Integer,Integer> cases : casesVoisines) {
                int ligne = cases.getKey();
                int colonne = cases.getValue();
                if (x + ligne >= 0 && x + ligne < 8 && y + colonne >= 0 && y + colonne < 8) {
                    if (Plateau.getCase(x + ligne, y + colonne) != Joueurs.joueurSuivant(joueurcourant)) {
                        continue;
                    }
                }
                x += ligne;
                y += colonne;
                while (x>=0 && x<8 && y>=0 && y<8) {
                    if (x + ligne >= 0 && x + ligne < 8 && y + colonne >= 0 && y + colonne < 8) {
                        if (Plateau.getCase(x + ligne, y + colonne) == Joueurs.joueurSuivant(joueurcourant)) {
                            x += ligne;
                            y += colonne;
                        }
                        else if(Plateau.getCase(x+ligne,y+colonne) == joueurcourant){
                            return true;
                        }

                    }

                }
                return false;
            }
        }
        return false;
    }
}
