package vue;

import modele.Joueurs;
import modele.Plateau;

import java.util.List;
import java.util.Scanner;
public class Ihm {
    public static void afficher_plateau(Plateau plateau){
        int taille_plateau = Plateau.getTaillePlateau();
        System.out.println("   A  B  C  D   E  F  G  H");
        for (int ligne = 0; ligne<taille_plateau; ligne++) {
            System.out.print(ligne+1+" ");
            for (int colonne = 0; colonne<taille_plateau; colonne++) {
                System.out.print(Plateau.getCouleurcase(ligne,colonne) + " ");
            }
            System.out.print(ligne+1);
            System.out.println();
        }
        System.out.println("   A  B  C  D   E  F  G  H");
    }

    public String demandernomjoueurs(int joueur) {
        Scanner scanner = new Scanner(System.in);
        String nom = "";
        while (nom.trim().isEmpty()) {
            System.out.println("Joueur " + joueur + ", veuillez saisir votre nom :");
            nom = scanner.nextLine();
            if (nom.trim().isEmpty()) {
                System.out.println("Erreur : Le nom ne peut pas être vide. Essayez à nouveau.");
            }
        }
        if (joueur == 1) {
            Joueurs.setjoueur1(nom);
        } else {
            Joueurs.setjoueur2(nom);
        }
        return nom;
    }

    public String choixCoup(int joueur){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Joueurs.getjoueurcourant(joueur)+" à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une letttre entre A et H (ex : 3 D) ou P pour passer son tour.");
        String coup = scanner.nextLine();
        return coup;
    }
    public static void Erreur(String message){
        System.out.println("Erreur : " + message);
    }
    public static void Gagnant(String gagnant){
        System.out.println(gagnant);

    }
    public static void PlusDeCoup(int joueur){
        System.out.println( Joueurs.getjoueurcourant(joueur) + " n'a plus de coup possible. ");
    }

    public static void afficherScoreFinal(String nomJoueur1, int victoireJoueur1, String nomJoueur2, int victoireJoueur2) {
        System.out.println(nomJoueur1 + " a " + victoireJoueur1 + (victoireJoueur1 > 1 ? " victoires" : " victoire") +
                ", contre " + victoireJoueur2 + (victoireJoueur2 > 1 ? " victoires" : " victoire") +
                " pour " + nomJoueur2);
    }

        public static boolean rejouerPartie() {
            System.out.println("Voulez-vous rejouer la partie ? (O/N)");
            Scanner scanner = new Scanner(System.in);
            String reponse = scanner.nextLine();
            return reponse.equalsIgnoreCase("O");
        }

}


