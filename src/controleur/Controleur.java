package controleur;

import modele.*;
import modele.Awale.LogiqueAwale;
import modele.Awale.PlateauAwale;
import modele.Othello.IAMinMax;
import modele.Othello.IARandom;
import modele.Othello.IAStrategy;
import modele.Othello.LogiqueOthello;
import modele.Othello.PlateauOthello;
import vue.Ihm;
import java.util.AbstractMap;
/**
 * Contrôleur principal de l'application.
 * Gère le déroulement global du jeu en fonction de la stratégie choisie (Othello ou Awalé),
 * l'initialisation des joueurs, la boucle de jeu, et la communication avec l'IHM.
 */
public class Controleur {
    private Ihm ihm;
    private JeuStrategy strategieCourante;
    private Joueurs joueur1;
    private Joueurs joueur2;
    private Joueurs ordinateur;
    private IAStrategy strategieIA;


    /**
     * Constructeur du contrôleur.
     * @param ihm l'interface homme-machine utilisée pour interagir avec l'utilisateur.
     */

    public Controleur(Ihm ihm) {
        this.ihm = ihm;
        this.joueur1 = new Joueurs();
        this.joueur2 = new Joueurs();
        this.ordinateur = new Joueurs();
    }


    /**
     * Lance la boucle de jeu principale, gère les entrées utilisateur,
     * les coups de l'IA et la logique de victoire/fin de partie.
     */

    public void jouer() {
        // Choix du jeu
        int typeJeu = ihm.choisirJeux();
        
        // Configuration de la stratégie en fonction du choix
        if (typeJeu == 1) {
            strategieCourante = new OthelloStrategy();
        } else {
            strategieCourante = new AwaleStrategy();
        }

        // Configuration des joueurs
        joueur1.setNomJoueur(ihm.demanderNomJoueur(true));
        boolean jouerContreIA = ihm.estIA();

        // Si on joue à l'Awalé, on force le jeu contre un humain pour le moment
        if (typeJeu == 2) {
            jouerContreIA = false;
        }

        if (!jouerContreIA) {
            joueur2.setNomJoueur(ihm.demanderNomJoueur(false));
        } else {
            ordinateur.setNomJoueur("Ordinateur");
            boolean utiliserMinMax = ihm.choisirIAMinMax();
            this.strategieIA = utiliserMinMax ? new IAMinMax() : new IARandom();
        }

        // Configuration de la stratégie
        strategieCourante.setJoueur1(joueur1);
        strategieCourante.setJoueur2(joueur2);
        strategieCourante.setIA(jouerContreIA);
        strategieCourante.setStrategieIA(strategieIA);
        strategieCourante.initialiserJeu();

        // Boucle de jeu
        do {
            while (!strategieCourante.estPartieTerminee()) {
                strategieCourante.afficherPlateau(ihm);
                
                if (!strategieCourante.estCoupPossible()) {
                    ihm.afficherPlusDeCoup(strategieCourante.getJoueurCourant());
                    strategieCourante.changerJoueur();
                    continue;
                }

                int x = -1;
                int y = -1;
                boolean coupValide = false;
                int[] coup = null;

                while (!coupValide) {
                    if (strategieCourante.getJoueurCourant() == ordinateur) {
                        // L'IA joue (uniquement pour Othello)
                        if (strategieCourante instanceof OthelloStrategy) {
                            OthelloStrategy othelloStrategy = (OthelloStrategy) strategieCourante;
                            AbstractMap.SimpleEntry<Integer, Integer> meilleurCoup = strategieIA.calculerCoup(
                                othelloStrategy.getPlateau(),
                                2,
                                joueur1,
                                joueur2
                            );
                            if (meilleurCoup != null) {
                                x = meilleurCoup.getKey();
                                y = meilleurCoup.getValue();
                                coupValide = true;
                            } else {
                                coupValide = false;
                            }
                        } else {
                            // Pour l'Awalé, on ne devrait pas arriver ici car on force le jeu contre un humain
                            throw new IllegalStateException("L'IA n'est pas supportée pour l'Awalé");
                        }
                    } else {
                        // Le joueur humain joue
                        String coupStr;
                        if (strategieCourante instanceof OthelloStrategy) {
                            coupStr = ihm.choixCoupOthello(strategieCourante.getJoueurCourant());
                        } else {
                            coupStr = ihm.choixCoupAwale(strategieCourante.getJoueurCourant());
                        }
                        
                        // Conversion du String en coordonnées
                        try {
                            String[] coordonnees = coupStr.split(" ");
                            if (strategieCourante instanceof OthelloStrategy) {
                                // Pour Othello : format "3 D"
                                x = Integer.parseInt(coordonnees[0]) - 1;
                                y = coordonnees[1].toUpperCase().charAt(0) - 'A';
                            } else {
                                // Pour Awalé : format "3" (numéro de case)
                                x = (strategieCourante.getJoueurCourant() == joueur1) ? 0 : 1;  // Ligne du joueur
                                y = Integer.parseInt(coordonnees[0]) - 1;  // Case (1-6 -> 0-5)
                            }
                            coupValide = strategieCourante.estCoupValide(x, y);
                            if (!coupValide) {
                                ihm.afficherErreur("Coup invalide ! Veuillez réessayer.");
                            }
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            if (strategieCourante instanceof OthelloStrategy) {
                                ihm.afficherErreur("Format de coup invalide ! Veuillez entrer un nombre entre 1 et 8 suivi d'une lettre entre A et H, séparés par un espace (ex: 3 D).");
                            } else {
                                ihm.afficherErreur("Format de coup invalide ! Veuillez entrer un nombre entre 1 et 6.");
                            }
                            coupValide = false;
                        }
                    }
                }

                strategieCourante.jouerCoup(x, y);
            }

            strategieCourante.afficherPlateau(ihm);
            Joueurs gagnant = strategieCourante.getGagnant();
            if (gagnant != null) {
                ihm.Gagnant(gagnant.getJoueur());
                if (gagnant == joueur1) {
                    joueur1.setNombreVictoires(joueur1.getNombreVictoires() + 1);
                } else if (gagnant == joueur2) {
                    joueur2.setNombreVictoires(joueur2.getNombreVictoires() + 1);
                }
            } else {
                ihm.Gagnant("Match nul !");
            }
            
            ihm.afficherScoreFinal(joueur1.getJoueur(), joueur1.getNombreVictoires(),
                    joueur2.getJoueur(), joueur2.getNombreVictoires());
        } while (ihm.demanderRejouer());
    }
}