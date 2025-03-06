import java.util.List;
import java.util.Scanner;
public class Ihm {
    public static void afficher_plateau(Plateau plateau){
        System.out.println("   A  B  C  D   E  F  G  H");
        for (int ligne = 0; ligne<8; ligne++) {
            System.out.print(ligne+1+" ");
            for (int colonne = 0; colonne<8; colonne++) {
                System.out.print(Plateau.getCouleurcase(ligne,colonne) + " ");
            }
            System.out.print(ligne+1);
            System.out.println();
        }
        System.out.println("   A  B  C  D   E  F  G  H");
    }

    public String demandernomjoueurs(int joueur){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Joueur "+joueur+" veuillez saisir votre nom :");
        String nom = scanner.nextLine();
        return nom;

    }

    public String choixCoup(int joueur){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Joueurs.getjoueurcourant(joueur)+" à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une letttre entre A et H (ex : 3 D) ou P pour passer son tour.");
        String coup = scanner.nextLine();
        return coup;
    }
}