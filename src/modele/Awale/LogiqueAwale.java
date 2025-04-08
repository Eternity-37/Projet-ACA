package modele.Awale;

import modele.Joueurs;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class LogiqueAwale {

    /**
     * Vérifie si la partie est terminée.
     * @param plateau L'état actuel du plateau d'Awalé.
     * @return true si la partie est terminée, false sinon.
     */
    public static boolean partieFinie(PlateauAwale plateau) {
        // Vérifie si aucun des deux joueurs ne peut jouer
        return !peutJouer(1, plateau) || !peutJouer(2, plateau);
    }

    /**
     * Vérifie si un joueur peut encore jouer.
     * @param joueur Le numéro du joueur (1 ou 2).
     * @param plateau L'état actuel du plateau.
     * @return true si le joueur peut encore jouer, false sinon.
     */
    public static boolean peutJouer(int joueur, PlateauAwale plateau) {
        for (int i = 0; i < 6; i++) {
            if (plateau.getCase(joueur - 1, i) != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la liste des coups possibles pour un joueur donné.
     * @param joueurCourant Le joueur pour lequel on cherche les coups possibles.
     * @param plateau L'état actuel du plateau.
     * @return Liste des coordonnées (x,y) des coups possibles.
     */
    public static List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles(int joueurCourant, PlateauAwale plateau) {
        List<AbstractMap.SimpleEntry<Integer, Integer>> coupsPossibles = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (plateau.getCase(joueurCourant - 1, i) != 0) {
                // Vérifie si le coup n'affame pas l'adversaire
                if (!coupsAffameAdversaire(joueurCourant - 1, i, plateau)) {
                    coupsPossibles.add(new AbstractMap.SimpleEntry<>(joueurCourant - 1, i));
                }
            }
        }
        return coupsPossibles;
    }

    /**
     * Vérifie si un coup va affamer l'adversaire (ne lui laissant aucune graine).
     * @param x Ligne du coup (0 pour joueur 1, 1 pour joueur 2).
     * @param y Colonne du coup (0-5).
     * @param plateau L'état actuel du plateau.
     * @return true si le coup affame l'adversaire, false sinon.
     */
    public static boolean coupsAffameAdversaire(int x, int y, PlateauAwale plateau) {
        PlateauAwale plateauCopie = new PlateauAwale(plateau);
        int adversaire = (x == 0) ? 1 : 0;
        int nbGraines = plateauCopie.getCase(x, y);

        // Simule la distribution des graines
        plateauCopie.setCase(x, y, 0);
        int position = y;
        int ligne = x;

        for (int i = 0; i < nbGraines; i++) {
            position++;
            if (position > 5) {
                position = 0;
                ligne = (ligne == 0) ? 1 : 0;
            }
            plateauCopie.setCase(ligne, position, plateauCopie.getCase(ligne, position) + 1);
        }

        // Vérifie si l'adversaire a des graines
        boolean adversaireAvecGraines = false;
        for (int i = 0; i < 6; i++) {
            if (plateauCopie.getCase(adversaire, i) > 0) {
                adversaireAvecGraines = true;
                break;
            }
        }

        return !adversaireAvecGraines;
    }

    /**
     * Effectue un coup et retourne les graines capturées.
     * @param x Ligne du coup (0 pour joueur 1, 1 pour joueur 2).
     * @param y Colonne du coup (0-5).
     * @param plateau Le plateau de jeu.
     * @param joueurActuel Le joueur qui joue (1 ou 2).
     * @return Le nombre de graines capturées.
     */
    public static int jouerCoup(int x, int y, PlateauAwale plateau, int joueurActuel) {
        int nbGraines = plateau.getCase(x, y);
        int grainesCapturees = 0;

        if (nbGraines == 0) {
            return 0; // Pas de graines à jouer
        }

        // Vide la case initiale
        plateau.setCase(x, y, 0);

        // Position initiale
        int position = y;
        int ligne = x;
        boolean dansGrenier = false;
        int grenierActuel = joueurActuel;  // Commence par le grenier du joueur qui joue

        // Distribution des graines (sens anti-horaire)
        while (nbGraines > 0) {
            if (!dansGrenier) {
                position++;
                // Si on arrive au bout de la rangée
                if (position > 5) {
                    // On passe au grenier
                    dansGrenier = true;
                } else {
                    // On distribue dans la case si ce n'est pas la case de départ
                    if (!(ligne == x && position == y)) {
                        plateau.setCase(ligne, position, plateau.getCase(ligne, position) + 1);
                        nbGraines--;
                    }
                }
            } else {
                // On est dans un grenier
                // On ne distribue rien dans le grenier, on passe directement à la case suivante
                position = -1;  // Sera incrémenté à 0 au prochain tour
                ligne = (ligne == 0) ? 1 : 0;
                grenierActuel = (grenierActuel == 1) ? 2 : 1;  // Alterne entre les greniers
                dansGrenier = false;
            }
        }

        // Vérifie la capture de graines (dans le camp adverse uniquement)
        if (ligne != x && !dansGrenier) { // Si on termine dans le camp adverse et pas dans un grenier
            position = Math.min(position, 5); // S'assure que position est valide
            // On capture tant qu'on trouve des groupes de 2 ou 3 graines en reculant
            while (position >= 0) {
                int valeurCase = plateau.getCase(ligne, position);
                if (valeurCase == 2 || valeurCase == 3) {
                    grainesCapturees += valeurCase;
                    plateau.setCase(ligne, position, 0);
                    position--;
                } else {
                    break;
                }
            }
            // Ajoute les graines capturées au grenier du joueur uniquement si des graines ont été capturées
            if (grainesCapturees > 0) {
                plateau.ajouterAuGrenier(joueurActuel, grainesCapturees);
            }
        }

        return grainesCapturees;
    }
}