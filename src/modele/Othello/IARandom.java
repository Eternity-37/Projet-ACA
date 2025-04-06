package modele.Othello;

import modele.Joueurs;

import java.util.AbstractMap;
import java.util.List;
import java.util.Random;

public class IARandom implements IAStrategy {
    /**
     * Calcule un coup aléatoire parmi les coups possibles.
     * @param plateau L'état actuel du plateau.
     * @param joueurCourant Le joueur pour lequel on calcule le coup.
     * @param joueur1 Le premier joueur.
     * @param joueur2 Le second joueur.
     * @return Un coup aléatoire parmi les coups possibles, ou null si aucun coup n'est possible.
     */
    @Override
    public AbstractMap.SimpleEntry<Integer,Integer> calculerCoup(PlateauOthello plateau, int joueurCourant, Joueurs joueur1, Joueurs joueur2) {
        List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles = LogiqueOthello.coupsPossibles(joueurCourant, plateau);
        if (coupsPossibles == null || coupsPossibles.isEmpty()) {
            return null;
        }
        Random generateurAleatoire = new Random();
        return coupsPossibles.get(generateurAleatoire.nextInt(coupsPossibles.size()));
    }
}
