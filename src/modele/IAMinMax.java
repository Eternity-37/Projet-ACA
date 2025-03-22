package modele;

import java.util.AbstractMap;
import java.util.List;
import java.util.Random;

public class IAMinMax {

    public static int coupIA(Plateau plateau,int profondeur, int joueurcourant, Joueurs joueur1, Joueurs joueur2){
        int valeur;
        if (profondeur == 0 || Jeux.partieFinie(plateau)){
            return Plateau.evaluationPlateau(plateau, joueurcourant, joueur1, joueur2);
        }
        if (joueurcourant == 2){
            valeur = -2000;
            Plateau plateau2 = plateau;
            List<AbstractMap.SimpleEntry<Integer,Integer>> coupPossible = Jeux.coupPossibleJoueurs(joueurcourant, plateau);
            for (AbstractMap.SimpleEntry<Integer,Integer> entry : coupPossible) {
                plateau2.setCase(entry.getKey(), entry.getValue(), joueurcourant);
                valeur = Math.max(valeur, coupIA(plateau2,profondeur-1,Joueurs.joueurSuivant(joueurcourant),joueur1,joueur2));
            }
        }
        else{
            valeur = 2000;
            Plateau plateau2 = plateau;
            List<AbstractMap.SimpleEntry<Integer,Integer>> coupPossible = Jeux.coupPossibleJoueurs(joueurcourant, plateau);
            for (AbstractMap.SimpleEntry<Integer,Integer> entry : coupPossible) {
                plateau2.setCase(entry.getKey(), entry.getValue(), joueurcourant);
                valeur = Math.min(valeur, coupIA(plateau2, profondeur - 1,joueurcourant, joueur1, joueur2));
            }
        }
        return valeur;
    }
}