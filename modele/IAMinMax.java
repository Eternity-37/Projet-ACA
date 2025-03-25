package modele;

import java.util.AbstractMap;
import java.util.List;

public class IAMinMax implements IAStrategy {

    @Override
    public AbstractMap.SimpleEntry<Integer,Integer> calculerCoup(Plateau plateau, int joueurCourant, Joueurs joueur1, Joueurs joueur2) {
        AbstractMap.SimpleEntry<Integer,Integer> meilleurCoup = null;
        int meilleurScore = Integer.MIN_VALUE;
        int profondeur = 6;

        List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles = Jeux.coupsPossibles(joueurCourant, plateau);
        if (coupsPossibles == null || coupsPossibles.isEmpty()) {
            return null;
        }

        for (AbstractMap.SimpleEntry<Integer, Integer> coup : coupsPossibles) {
            Plateau copiePlateau = new Plateau(plateau);
            List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = Jeux.coupEstValide(coup.getKey(), coup.getValue(), copiePlateau, joueurCourant);
            copiePlateau.setCase(coup.getKey(), coup.getValue(), joueurCourant);
            copiePlateau.retournerPions(coup.getKey(), coup.getValue(), directionsValides, joueurCourant);

            int score = minimax(copiePlateau, profondeur - 1, false, joueurCourant, joueur1, joueur2);
            if (score > meilleurScore) {
                meilleurScore = score;
                meilleurCoup = coup;
            }
        }

        return meilleurCoup;
    }

    /**
     * Implémente l'algorithme minimax pour évaluer les coups possibles.
     * @param plateau L'état actuel du plateau.
     * @param profondeur La profondeur de recherche restante.
     * @param estMaximisant Indique si c'est le tour du joueur maximisant.
     * @param joueurCourant Le joueur pour lequel on évalue les coups.
     * @param joueur1 Le premier joueur.
     * @param joueur2 Le second joueur.
     * @return La valeur évaluée de la position.
     */
    private int minimax(Plateau plateau, int profondeur, boolean estMaximisant, int joueurCourant, Joueurs joueur1, Joueurs joueur2) {
        if (profondeur == 0 || Jeux.partieFinie(plateau)) {
            return Plateau.evaluationPlateau(plateau, joueurCourant, joueur1, joueur2);
        }

        if (estMaximisant) {
            int meilleurScore = Integer.MIN_VALUE;
            List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles = Jeux.coupsPossibles(joueurCourant, plateau);
            if (coupsPossibles != null && !coupsPossibles.isEmpty()) {
                for (AbstractMap.SimpleEntry<Integer, Integer> coup : coupsPossibles) {
                    Plateau copiePlateau = new Plateau(plateau);
                    List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = Jeux.coupEstValide(coup.getKey(), coup.getValue(), copiePlateau, joueurCourant);
                    copiePlateau.setCase(coup.getKey(), coup.getValue(), joueurCourant);
                    copiePlateau.retournerPions(coup.getKey(), coup.getValue(), directionsValides, joueurCourant);
                    meilleurScore = Math.max(meilleurScore, minimax(copiePlateau, profondeur - 1, false, joueurCourant, joueur1, joueur2));
                }
            }
            return meilleurScore;
        } else {
            int meilleurScore = Integer.MAX_VALUE;
            int joueurAdverse = joueurCourant == 1 ? 2 : 1;
            List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles = Jeux.coupsPossibles(joueurAdverse, plateau);
            if (coupsPossibles != null && !coupsPossibles.isEmpty()) {
                for (AbstractMap.SimpleEntry<Integer, Integer> coup : coupsPossibles) {
                    Plateau copiePlateau = new Plateau(plateau);
                    List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = Jeux.coupEstValide(coup.getKey(), coup.getValue(), copiePlateau, joueurAdverse);
                    copiePlateau.setCase(coup.getKey(), coup.getValue(), joueurAdverse);
                    copiePlateau.retournerPions(coup.getKey(), coup.getValue(), directionsValides, joueurAdverse);
                    meilleurScore = Math.min(meilleurScore, minimax(copiePlateau, profondeur - 1, true, joueurCourant, joueur1, joueur2));
                }
            }
            return meilleurScore;
        }
    }
}