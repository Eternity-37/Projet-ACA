package modele;

import java.util.AbstractMap;
import java.util.List;

public interface IAStrategy {
    /**
     * Calcule le prochain coup pour l'IA.
     * @param plateau Le plateau de jeu actuel.
     * @param joueurCourant Le joueur actuel (1 ou 2).
     * @param joueur1 Les informations du joueur 1.
     * @param joueur2 Les informations du joueur 2.
     * @return Les coordonnées du prochain coup (x, y) ou null si aucun coup n'est valide.
     */
    AbstractMap.SimpleEntry<Integer,Integer> calculerCoup(Plateau plateau, int joueurCourant, Joueurs joueur1, Joueurs joueur2);
} 