package modele.Othello;

import modele.Joueurs;

import java.util.AbstractMap;
import java.util.List;


public class IAMinMax implements IAStrategy {
    private static int taillePlateau = PlateauOthello.getTaillePlateau();  // Taille du plateau

    @Override
    public AbstractMap.SimpleEntry<Integer,Integer> calculerCoup(PlateauOthello plateau, int joueurCourant, Joueurs joueur1, Joueurs joueur2) {
        AbstractMap.SimpleEntry<Integer,Integer> meilleurCoup = null;
        int meilleurScore = Integer.MIN_VALUE;
        int profondeur = 6;

        List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles = LogiqueOthello.coupsPossibles(joueurCourant, plateau);
        if (coupsPossibles == null || coupsPossibles.isEmpty()) {
            return null;
        }

        for (AbstractMap.SimpleEntry<Integer, Integer> coup : coupsPossibles) {
            PlateauOthello copiePlateau = new PlateauOthello(plateau);
            List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = LogiqueOthello.coupEstValide(coup.getKey(), coup.getValue(), copiePlateau, joueurCourant);
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
    private int minimax(PlateauOthello plateau, int profondeur, boolean estMaximisant, int joueurCourant, Joueurs joueur1, Joueurs joueur2) {
        if (profondeur == 0 || LogiqueOthello.partieFinie(plateau)) {
            return evaluationPlateau(plateau, joueurCourant, joueur1, joueur2);
        }

        if (estMaximisant) {
            int meilleurScore = Integer.MIN_VALUE;
            List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles = LogiqueOthello.coupsPossibles(joueurCourant, plateau);
            if (coupsPossibles != null && !coupsPossibles.isEmpty()) {
                for (AbstractMap.SimpleEntry<Integer, Integer> coup : coupsPossibles) {
                    PlateauOthello copiePlateau = new PlateauOthello(plateau);
                    List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = LogiqueOthello.coupEstValide(coup.getKey(), coup.getValue(), copiePlateau, joueurCourant);
                    copiePlateau.setCase(coup.getKey(), coup.getValue(), joueurCourant);
                    copiePlateau.retournerPions(coup.getKey(), coup.getValue(), directionsValides, joueurCourant);
                    meilleurScore = Math.max(meilleurScore, minimax(copiePlateau, profondeur - 1, false, joueurCourant, joueur1, joueur2));
                }
            }
            return meilleurScore;
        } else {
            int meilleurScore = Integer.MAX_VALUE;
            int joueurAdverse = joueurCourant == 1 ? 2 : 1;
            List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles = LogiqueOthello.coupsPossibles(joueurAdverse, plateau);
            if (coupsPossibles != null && !coupsPossibles.isEmpty()) {
                for (AbstractMap.SimpleEntry<Integer, Integer> coup : coupsPossibles) {
                    PlateauOthello copiePlateau = new PlateauOthello(plateau);
                    List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = LogiqueOthello.coupEstValide(coup.getKey(), coup.getValue(), copiePlateau, joueurAdverse);
                    copiePlateau.setCase(coup.getKey(), coup.getValue(), joueurAdverse);
                    copiePlateau.retournerPions(coup.getKey(), coup.getValue(), directionsValides, joueurAdverse);
                    meilleurScore = Math.min(meilleurScore, minimax(copiePlateau, profondeur - 1, true, joueurCourant, joueur1, joueur2));
                }
            }
            return meilleurScore;
        }
    }
    /**
     * Évalue la position actuelle du plateau pour un joueur donné.
     * @param plateau Le plateau à évaluer.
     * @param joueurCourant Le joueur pour lequel on évalue la position.
     * @param joueur1 Le premier joueur.
     * @param joueur2 Le second joueur.
     * @return Une valeur numérique représentant l'évaluation de la position.
     */
    public static int evaluationPlateau(PlateauOthello plateau, int joueurCourant, Joueurs joueur1, Joueurs joueur2) {
        int valeur = 0;
        if (LogiqueOthello.partieFinie(plateau)) {
            String gagnant = plateau.joueurGagnant(joueur1, joueur2);
            if (gagnant.equals(joueur2.getJoueur())) {
                valeur = 1000;
            } else if (gagnant.equals(joueur1.getJoueur())) {
                valeur = -1000;
            }
        }
        for (int i = 0; i < taillePlateau; i++) {
            for (int j = 0; j < taillePlateau; j++) {
                if (plateau.getCase(i, j) == joueurCourant) {
                    if (i == 0 && j == 0 || i == taillePlateau - 1 && j == taillePlateau - 1 || i == 0 && j == taillePlateau - 1 || i == taillePlateau - 1 && j == 0) {
                        valeur += 11;  // Bonus pour les coins
                    } else if (i == 0 || j == 0 || i == taillePlateau - 1 || j == taillePlateau - 1) {
                        valeur += 6;  // Bonus pour les bords
                    } else {
                        valeur++;  // Valeur de base pour les autres cases
                    }
                }
            }
        }
        return valeur;
    }
}