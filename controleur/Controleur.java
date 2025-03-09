package controleur;

import modele.Jeux;
import modele.Joueurs;
import modele.Plateau;
import vue.Ihm;

import java.util.AbstractMap;
import java.util.List;

public class Controleur {
    private Ihm ihm;
    private Plateau plateau;

    public Controleur(Ihm ihm) {
        this.ihm = ihm;
        this.plateau = new Plateau();
    }

    private void mettreAJourVictoires(String gagnant) {
        if (gagnant.equals(Joueurs.getJoueur1())) {
            Joueurs.setNbVictoirejoueur1(Joueurs.getNbVictoirejoueur1() + 1);
        } else if (gagnant.equals(Joueurs.getJoueur2())) {
            Joueurs.setNbVictoirejoueur2(Joueurs.getNbVictoirejoueur2() + 1);
        }
    }


    public void jouer() {
        boolean etatjeu = true;
        Joueurs.setjoueur1(ihm.demandernomjoueurs(1));
        Joueurs.setjoueur2(ihm.demandernomjoueurs(2));
        int joueurcourant = 1;

        while (etatjeu) {
            Ihm.afficher_plateau(plateau);
            while (!Jeux.partieFinie(plateau)) {
                String coup = ihm.choixCoup(joueurcourant);


                if (!Jeux.peutJouer(joueurcourant, plateau)) {              //la je verif si le joueur n'a plus de coup si il en a plus alors victoire pour l autre
                    Ihm.PlusDeCoup(joueurcourant);
                    joueurcourant = Joueurs.joueurSuivant(joueurcourant);
                    break;
                    }


                    if (coup.equals("P")) {
                        joueurcourant = Joueurs.joueurSuivant(joueurcourant);
                        continue;
                    }
                    if (coup.length() == 3) {
                        int x = coup.charAt(0) - '1';
                        int y = coup.charAt(2) - 'A';
                        List<AbstractMap.SimpleEntry<Integer, Integer>> coupsvalides = Jeux.coupEstValide(x, y, plateau, joueurcourant);
                        if (!coupsvalides.isEmpty()) {
                            Plateau.setCase(x, y, joueurcourant);
                            Plateau.retournerPions(plateau, x, y, coupsvalides, joueurcourant);
                            joueurcourant = Joueurs.joueurSuivant(joueurcourant);
                            Ihm.afficher_plateau(plateau);
                        } else {
                            Ihm.Erreur("Coup non valide");

                        }
                    } else {
                        Ihm.Erreur("Format du coup non valide");
                    }
                }
            String gagnant = Plateau.joueurGagnant(plateau);   //c'est la que j ai essayé de compter les victoires avec la méthode en haut mais je capte pas ca fait toujours 0-0
            Ihm.Gagnant(gagnant);

            mettreAJourVictoires(gagnant);

            Ihm.afficherScoreFinal(Joueurs.getJoueur1(), Joueurs.getNbVictoirejoueur1(),
                    Joueurs.getJoueur2(), Joueurs.getNbVictoirejoueur2());

            boolean rejouer = ihm.rejouerPartie();
            if (rejouer) {
                plateau = new Plateau();
                etatjeu = true;
            } else {
                etatjeu = false;
            }

            }

        }

    }


