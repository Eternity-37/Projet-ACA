package vue;

import modele.Joueurs;
import modele.Plateau;

import java.util.List;
import java.util.Scanner;


public class Ihm {

    /**
     * Affiche le plateau de jeu.
     * @param plateau L'objet Plateau à afficher.
     */
    public static void afficher_plateau(Plateau plateau){
        int taille_plateau = Plateau.getTaillePlateau();  // Récupère la taille du plateau.
        System.out.println("   A  B  C  D   E  F  G  H");
        for (int ligne = 0; ligne<taille_plateau; ligne++) {
            System.out.print(ligne+1+" ");
            for (int colonne = 0; colonne<taille_plateau; colonne++) {
                System.out.print(plateau.getCouleurcase(ligne,colonne) + " ");  // Affiche la couleur de chaque case du plateau.
            }
            System.out.print(ligne+1);  // Affiche le numéro de la ligne après la grille.
            System.out.println();
        }
        System.out.println("   A  B  C  D   E  F  G  H");  // Affiche à nouveau l'en-tête des colonnes.
    }

    /**
     * Demande le nom d'un joueur.
     * @param joueur Le joueur à qui il faut demander le nom.
     * @return Le nom du joueur.
     */
    public String demandernomjoueurs(int joueur) {
        Scanner scanner = new Scanner(System.in);  // Crée un scanner pour récupérer les saisies.
        String nom = "";
        while (nom.trim().isEmpty()) {
            System.out.println("Joueur " + joueur + ", veuillez saisir votre nom :");
            nom = scanner.nextLine();  // Lit le nom du joueur.
            if (nom.trim().isEmpty()) {
                System.out.println("Erreur : Le nom ne peut pas être vide. Essayez à nouveau.");  // Gère l'erreur si le nom est vide.
            }
        }
        if (joueur == 1) {
            Joueurs.setjoueur1(nom);  // Définit le nom pour le joueur 1.
        } else {
            Joueurs.setjoueur2(nom);  // Définit le nom pour le joueur 2.
        }
        return nom;  // Retourne le nom du joueur.
    }

    /**
     * Demande à un joueur de choisir un coup à jouer.
     * @param joueur Le numéro du joueur qui doit jouer.
     * @return Le coup saisi par le joueur sous forme de chaîne (ex : "3 D" ou "P").
     */
    public String choixCoup(int joueur){
        Scanner scanner = new Scanner(System.in);  // Crée un scanner pour lire l'entrée de l'utilisateur.
        // Affiche un message demandant à l'utilisateur de saisir son coup ou de passer son tour.
        System.out.println(Joueurs.getjoueurcourant(joueur) + " à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une lettre entre A et H (ex : 3 D) ou P pour passer son tour.");
        String coup = scanner.nextLine();  // Récupère l'entrée du joueur pour le coup.
        return coup;  // Retourne le coup saisi.
    }

    /**
     * Affiche un message d'erreur.
     * @param message Le message d'erreur à afficher.
     */
    public static void Erreur(String message){
        System.out.println("Erreur : " + message);  // Affiche le message d'erreur fourni en paramètre.
    }

    /**
     * Affiche un message indiquant le gagnant de la partie.
     * @param gagnant Le nom du joueur qui a gagné la partie.
     */
    public static void Gagnant(String gagnant){
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
    public static void PlusDeCoup(int joueur){
        // Affiche que le joueur actuel ne peut plus jouer.
        System.out.println(Joueurs.getjoueurcourant(joueur) + " n'a plus de coup possible.");
    }

    /**
     * Affiche le score final de la partie entre les deux joueurs.
     * @param nomJoueur1 Le nom du premier joueur.
     * @param victoireJoueur1 Le nombre de victoires du premier joueur.
     * @param nomJoueur2 Le nom du deuxième joueur.
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
    public static boolean rejouerPartie() {
        // Demande à l'utilisateur s'il souhaite rejouer.
        System.out.println("Voulez-vous rejouer la partie ? (O/N)");
        Scanner scanner = new Scanner(System.in);  // Crée un scanner pour lire l'entrée.
        String reponse = scanner.nextLine();  // Récupère la réponse de l'utilisateur.
        return reponse.equalsIgnoreCase("O");  // Retourne 'true' si la réponse est "O" (oui), sinon 'false'.
    }



}


