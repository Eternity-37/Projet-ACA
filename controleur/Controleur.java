package controleur;

import modele.Joueurs;
import modele.Plateau;
import modele.IAStrategy;
import modele.IARandom;
import modele.IAMinMax;
import modele.Jeux;
import vue.Ihm;
import java.util.AbstractMap;
import java.util.List;

public class Controleur {
    private Ihm ihm;
    private Plateau plateau;
    private Joueurs joueur1;
    private Joueurs joueur2;
    private Joueurs ordinateur;
    private IAStrategy strategieIA;

    /**
     * Constructeur du contrôleur, initialise l'interface utilisateur et le plateau de jeu.
     * @param ihm L'interface utilisateur pour interagir avec le joueur.
     */
    public Controleur(Ihm ihm) {
        this.ihm = ihm;
        this.plateau = new Plateau();
        this.joueur1 = new Joueurs();
        this.joueur2 = new Joueurs();
        this.ordinateur = new Joueurs();
        this.strategieIA = null;
    }

    public void jouer() {
        joueur1.setNomJoueur(ihm.demanderNomJoueur(true));
        boolean jouerContreIA = ihm.estIA();
        if (!jouerContreIA) {
            joueur2.setNomJoueur(ihm.demanderNomJoueur(false));
        } else {
            ordinateur.setNomJoueur("Ordinateur");
            boolean utiliserMinMax = ihm.choisirIAMinMax();
            this.strategieIA = utiliserMinMax ? new IAMinMax() : new IARandom();
        }

        do {
            plateau = new Plateau();
            Joueurs joueurCourant = joueur1;
            Joueurs joueurAdverse = jouerContreIA ? ordinateur : joueur2;
            boolean partieTerminee = false;
            int joueurActuel = 1;

            while (!partieTerminee) {
                ihm.afficherPlateau(plateau);
                if (Jeux.peutJouer(joueurActuel, plateau)) {
                    AbstractMap.SimpleEntry<Integer, Integer> coup = null;
                    if (joueurCourant == ordinateur) {
                        coup = strategieIA.calculerCoup(plateau, joueurActuel, joueur1, joueur2);
                        if (coup != null) {
                            char colonne = (char)('A' + coup.getValue());
                            int ligne = coup.getKey() + 1;
                            ihm.afficherMessageIA("L'IA joue : " + ligne + " " + colonne);
                        }
                    } else {
                        String choixCoup = ihm.choixCoup(joueurCourant);
                        if (choixCoup.equalsIgnoreCase("P")) {
                            if (!Jeux.peutJouer(joueurActuel, plateau)) {
                                ihm.afficherPlusDeCoup(joueurCourant);
                                continue;
                            }
                            else{
                                ihm.afficherErreur("Il reste des coups valides");
                                continue;
                            }

                        }
                        try {
                            int x = choixCoup.charAt(0) - '1';
                            int y = choixCoup.charAt(2) - 'A';
                            List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = 
                                Jeux.coupEstValide(x, y, plateau, joueurActuel);
                            if (!directionsValides.isEmpty()) {
                                coup = new AbstractMap.SimpleEntry<>(x, y);
                            } else {
                                ihm.afficherErreur("Coup non valide");
                                continue;
                            }
                        } catch (Exception e) {
                            ihm.afficherErreur("Format du coup non valide");
                            continue;
                        }
                    }

                    if (coup != null) {
                        int x = coup.getKey();
                        int y = coup.getValue();
                        List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = 
                            Jeux.coupEstValide(x, y, plateau, joueurActuel);
                        plateau.retournerPions(x, y, directionsValides, joueurActuel);
                        plateau.setCase(x, y, joueurActuel);
                    }
                } else {
                    ihm.afficherPlusDeCoup(joueurCourant);
                    partieTerminee = true;
                }

                Joueurs temp = joueurCourant;
                joueurCourant = joueurAdverse;
                joueurAdverse = temp;
                joueurActuel = joueurActuel == 1 ? 2 : 1;
            }

            String gagnant = plateau.joueurGagnant(joueur1, joueur2);
            if (gagnant.equals(joueur1.getJoueur())) {
                joueur1.setNombreVictoires(joueur1.getNombreVictoires() + 1);
            } else if (gagnant.equals(joueur2.getJoueur())) {
                joueur2.setNombreVictoires(joueur2.getNombreVictoires() + 1);
            }
            ihm.Gagnant(gagnant);
            ihm.afficherScoreFinal(joueur1.getJoueur(), joueur1.getNombreVictoires(), 
            joueur2.getJoueur(), joueur2.getNombreVictoires());
        } while (ihm.demanderRejouer());
    }
}
