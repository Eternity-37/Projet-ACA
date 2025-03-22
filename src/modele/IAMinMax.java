package modele;

import java.util.AbstractMap;
import java.util.List;
import java.util.Random;

public class IAMinMax {

    public static int coupIA(Plateau plateau,int profondeur, int joueurcourant, Joueurs joueur1, Joueurs joueur2){
        if (profondeur == 0 || Jeux.partieFinie(plateau)){
            return Plateau.evaluationPlateau(plateau, joueurcourant, joueur1, joueur2);
        }
        if (joueurcourant == 2){
            int valeur = -2000;

        }
        return 0;
    }
}
