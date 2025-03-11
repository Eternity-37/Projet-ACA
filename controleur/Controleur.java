package controleur;

import modele.Jeux;
import modele.Joueurs;
import modele.Plateau;
import vue.Ihm;

import java.util.AbstractMap;
import java.util.List;

public class Controleur {
    private Ihm ihm;      // Interface utilisateur (pour l'interaction avec le joueur)
    private Plateau plateau;  // Plateau de jeu
    private final Joueurs joueur1;
    private final Joueurs joueur2;
    /**
     * Constructeur du contrôleur, initialise l'interface utilisateur et le plateau de jeu.
     * @param ihm L'interface utilisateur pour interagir avec le joueur.
     */
    public Controleur(Ihm ihm) {
        this.ihm = ihm;
        this.plateau = new Plateau(); // Initialise un plateau de jeu vide
        this.joueur1 = new Joueurs();
        this.joueur2 = new Joueurs();
    }

    /**
     * Met à jour le nombre de victoires du joueur gagnant après chaque partie.
     * @param gagnant Le nom du joueur gagnant.
     */
    private void mettreAJourVictoires(String gagnant, Joueurs joueur1, Joueurs joueur2) {
        // Si le gagnant est le joueur 1, on augmente son nombre de victoires
        if (gagnant.equals(joueur1.getJoueur())) {
            joueur1.setNbVictoire(joueur1.getNbVictoire() + 1);
        }
        // Si le gagnant est le joueur 2, on augmente son nombre de victoires
        else if (gagnant.equals(joueur1.getJoueur())) {
            joueur2.setNbVictoire(joueur2.getNbVictoire() + 1);
        }
    }

    /**
     * Gère le déroulement d'une partie.
     * Elle permet à chaque joueur de jouer son tour et termine la partie lorsqu'il n'y a plus de coups possibles.
     */
    public void jouer() {
        boolean etatjeu = true;  // L'état du jeu, utilisé pour savoir si la partie doit continuer

        joueur1.setjoueur(ihm.demandernomjoueurs(true)); // Demande le nom du joueur 1
        joueur2.setjoueur(ihm.demandernomjoueurs(false)); // Demande le nom du joueur 2
        int joueurcourant = 1;  // Le joueur courant (1 pour le joueur 1, 2 pour le joueur 2)

        // Boucle de jeu principale : la partie continue tant que 'etatjeu' est vrai
        while (etatjeu) {
            Ihm.afficher_plateau(plateau);  // Affiche l'état actuel du plateau
            while (!Jeux.partieFinie(plateau)) {  // Tant que la partie n'est pas terminée
                String coup = "";
                if (joueurcourant==1){
                    coup = ihm.choixCoup(joueur1);  // Demande le coup du joueur courant
                } else{
                    coup = ihm.choixCoup(joueur2);
                }


                // Vérifie si le joueur peut encore jouer, si ce n'est pas le cas, on passe au joueur suivant
                if (!Jeux.peutJouer(joueurcourant, plateau)) {
                    if (joueurcourant==1) {
                        Ihm.PlusDeCoup(joueur1);  // Affiche un message si le joueur n'a plus de coups possibles
                    }
                    else if (joueurcourant==2) {
                        Ihm.PlusDeCoup(joueur2);
                    }
                    joueurcourant = Joueurs.joueurSuivant(joueurcourant);  // Passe au joueur suivant
                    continue;  // Passe à l'itération suivante de la boucle
                }

                // Gestion du passage de tour : le joueur choisit "P" pour passer son tour
                if (coup.equals("P")) {
                    joueurcourant = Joueurs.joueurSuivant(joueurcourant);  // Passe au joueur suivant
                    continue;  // Passe à l'itération suivante de la boucle
                }

                // Vérification et exécution du coup, si le format du coup est correct
                if (coup.length() == 3 && coup.charAt(1) == ' '){
                    int x = coup.charAt(0) - '1';  // Conversion du premier caractère (ligne)
                    int y = coup.charAt(2) - 'A';  // Conversion du troisième caractère (colonne)

                    // Vérification des directions valides pour ce coup
                    List<AbstractMap.SimpleEntry<Integer, Integer>> coupsvalides = Jeux.coupEstValide(x, y, plateau, joueurcourant);

                    // Si le coup est valide, on applique le coup
                    if (!coupsvalides.isEmpty()) {
                        plateau.setCase(x, y, joueurcourant);  // Place le pion du joueur courant sur le plateau
                        plateau.retournerPions(x, y, coupsvalides, joueurcourant);  // Retourne les pions adverses
                        joueurcourant = Joueurs.joueurSuivant(joueurcourant);  // Passe au joueur suivant
                        Ihm.afficher_plateau(plateau);  // Affiche l'état actuel du plateau
                    } else {
                        Ihm.Erreur("Coup non valide");  // Affiche un message d'erreur si le coup est invalide
                    }
                } else {
                    Ihm.Erreur("Format du coup non valide");  // Affiche un message d'erreur si le format du coup est incorrect
                }
            }

            // Une fois la partie terminée, on affiche le gagnant
            String gagnant = plateau.joueurGagnant(joueur1,joueur2);  // Détermine le gagnant de la partie
            Ihm.Gagnant(gagnant);  // Affiche le gagnant

            // Mise à jour du score des victoires du gagnant
            mettreAJourVictoires(gagnant, joueur1, joueur2);

            // Affiche le score final des deux joueurs
            Ihm.afficherScoreFinal(Joueurs.getJoueur(), Joueurs.getNbVictoire(),
                    Joueurs.getJoueur(), Joueurs.getNbVictoire());

            // Demande à l'utilisateur s'il souhaite rejouer
            boolean rejouer = ihm.rejouerPartie();
            if (rejouer) {
                plateau = new Plateau();  // Réinitialise le plateau pour une nouvelle partie
                etatjeu = true;  // La partie continue
            } else {
                etatjeu = false;  // La partie se termine
            }
        }
    }
}
