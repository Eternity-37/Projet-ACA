package vue;

import modele.Awale.PlateauAwale;
import modele.Joueurs;
import modele.Othello.PlateauOthello;

import java.util.Scanner;


public class Ihm {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Affiche le plateau de jeu.
     *
     * @param plateau L'objet Plateau à afficher.
     */
    public static void afficherPlateau(PlateauOthello plateau) {
        int taillePlateau = PlateauOthello.getTaillePlateau();  // Récupère la taille du plateau
        System.out.println("   A  B  C  D   E  F  G  H");
        for (int ligne = 0; ligne < taillePlateau; ligne++) {
            System.out.print(ligne + 1 + " ");
            for (int colonne = 0; colonne < taillePlateau; colonne++) {
                System.out.print(plateau.getCouleurCase(ligne, colonne) + " ");  // Affiche la couleur de chaque case
            }
            System.out.print(ligne + 1);  // Affiche le numéro de la ligne après la grille
            System.out.println();
        }
        System.out.println("   A  B  C  D   E  F  G  H");  // Affiche à nouveau l'en-tête des colonnes
    }

    /**
     * Demande le nom d'un joueur.
     * @param estPremierJoueur true si c'est le premier joueur, false si c'est le second
     * @return Le nom du joueur.
     */
    public String demanderNomJoueur(boolean estPremierJoueur) {
        String nom = "";
        while (nom.trim().isEmpty()) {
            if (estPremierJoueur) {
                System.out.println("Joueur 1, veuillez saisir votre nom :");
            } else {
                System.out.println("Joueur 2, veuillez saisir votre nom :");
            }
            nom = scanner.nextLine();
            if (nom.trim().isEmpty()) {
                System.out.println("Erreur : Le nom ne peut pas être vide. Essayez à nouveau.");
            }
        }
        return nom;
    }

    /**
     * Demande à un joueur de choisir un coup à jouer.
     *
     * @param joueur Le numéro du joueur qui doit jouer.
     * @return Le coup saisi par le joueur sous forme de chaîne (ex : "3 D" ou "P").
     */
    public String choixCoupOthello(Joueurs joueur) {
        // Affiche un message demandant à l'utilisateur de saisir son coup ou de passer son tour.
        System.out.println(joueur.getJoueur() + " à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une lettre entre A et H (ex : 3 D) ou P pour passer son tour.");
        // Récupère l'entrée du joueur pour le coup.
        return scanner.nextLine();  // Retourne le coup saisi.
    }

    /**
     * Affiche un message d'erreur.
     * @param message Le message d'erreur à afficher.
     */
    public void afficherErreur(String message) {
        System.out.println("Erreur : " + message);
    }

    /**
     * Affiche un message indiquant le gagnant de la partie.
     *
     * @param gagnant Le nom du joueur qui a gagné la partie.
     */
    public static void Gagnant(String gagnant) {
        if (gagnant.equals("ex aequo")) {  // Utilisation de .equals() pour comparer les chaînes de caractères
            System.out.println(gagnant);  // Affiche "ex aequo" si c'est un match nul
        } else {
            System.out.println(gagnant + " a gagné la partie ! ");  // Affiche le nom du gagnant de la partie.
        }
    }

    /**
     * Informe qu'un joueur n'a plus de coup possible.
     * @param joueur Le joueur qui n'a plus de coup à jouer.
     */
    public void afficherPlusDeCoup(Joueurs joueur) {
        String nomJoueur = joueur.getJoueur();
        System.out.println(nomJoueur + " n'a plus de coup possible.");
    }

    /**
     * Affiche le score final de la partie entre les deux joueurs.
     *
     * @param nomJoueur1      Le nom du premier joueur.
     * @param victoireJoueur1 Le nombre de victoires du premier joueur.
     * @param nomJoueur2      Le nom du deuxième joueur.
     * @param victoireJoueur2 Le nombre de victoires du deuxième joueur.
     */
    public static void afficherScoreFinal(String nomJoueur1, int victoireJoueur1, String nomJoueur2, int victoireJoueur2) {
        // Affiche le score final avec le nombre de victoires de chaque joueur et les noms correspondants.
        System.out.println(nomJoueur1 + " a " + victoireJoueur1 + (victoireJoueur1 > 1 ? " victoires" : " victoire") +
                ", contre " + victoireJoueur2 + (victoireJoueur2 > 1 ? " victoires" : " victoire") +
                " pour " + nomJoueur2);
    }

    /**
     * Demande aux joueurs s'ils souhaitent rejouer la partie.
     * @return true si les joueurs veulent rejouer, false sinon.
     */
    public static boolean demanderRejouer() {
        System.out.println("Voulez-vous rejouer la partie ? (O/N)");
        String reponse = scanner.nextLine();
        return reponse.equalsIgnoreCase("O");
    }

    public static boolean estIA() {
        System.out.println("Voulez-vous jouer contre une IA ? (O/N)");
        String reponse = scanner.nextLine();
        return reponse.equalsIgnoreCase("O");
    }

    public static boolean choisirIAMinMax() {
        System.out.println("Quelle IA souhaitez-vous affronter ?");
        System.out.println("1 - IA Aléatoire (plus facile)");
        System.out.println("2 - IA MinMax (plus difficile)");
        String reponse = scanner.nextLine();
        return reponse.equals("2");
    }

    /**
     * Affiche un message de l'IA.
     * @param message Le message à afficher.
     */
    public void afficherMessageIA(String message) {
        System.out.println(message);
    }

    public static int choisirJeux(){
        System.out.println("A quel jeux souhaitez vous jouer ? ");
        System.out.println("1 - Jouer à l'Othello");
        System.out.println("2 - Jouer à l'Awale");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consomme le retour à la ligne
        return choix;
    }

    // Ajouter cette méthode à la classe Ihm
    /**
     * Affiche le plateau d'Awalé.
     * @param plateau Le plateau à afficher.
     */
    public void afficherPlateau(PlateauAwale plateau) {
        // Ligne supérieure avec les numéros des cases du joueur 2
        System.out.println("\n             6    5    4    3    2    1");
        System.out.println("    ╔═════╦════╦════╦════╦════╦════╦════╦═════╗");

        // Cases du joueur 2
        System.out.print("    ║     ║");
        for (int j = 5; j >= 0; j--) {
            int graines = plateau.getCase(1, j);
            System.out.print(String.format(" %2d ║", graines));
        }
        System.out.println("     ║");

        // Ligne de séparation avec nom des joueurs
        System.out.println("J2  ║  "+plateau.getGrenierJoueur2()+"  ╠════╬════╬════╬════╬════╬════╣  "+ plateau.getGrenierJoueur1()+"  ║  J1");

        // Cases du joueur 1
        System.out.print("    ║     ║");
        for (int j = 0; j < 6; j++) {
            int graines = plateau.getCase(0, j);
            System.out.print(String.format(" %2d ║", graines));
        }
        System.out.println("     ║");

        // Ligne inférieure
        System.out.println("    ╚═════╩════╩════╩════╩════╩════╩════╩═════╝");
        System.out.println("             1    2    3    4    5    6\n");
    }

    /**
     * Demande à un joueur de choisir un coup pour l'Awalé.
     * @param joueur Le joueur qui doit jouer.
     * @return Le numéro de la case choisie (1-6).
     */
    public String choixCoupAwale(Joueurs joueur) {
        System.out.println(joueur.getJoueur() + " à vous de jouer. Choisissez une case (1-6) ou P pour passer votre tour.");
        return scanner.nextLine();
    }

}



