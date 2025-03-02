import java.util.List;
import java.util.Scanner;
public class Ihm {
    public void print_plateau(Plateau plateau){
        for (List<String> row : plateau.getPlateau()) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public String asknomjoueurs(int joueur){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Joueur "+joueur+" veuillez saisir votre nom :");
        String nom = scanner.nextLine();
        scanner.close();
        return nom;
        
    }

    public String choixCoup(int joueur){
        Scanner scanner = new Scanner(System.in);
        if(joueur == 1){
            System.out.println(Joueurs.getjoueur1()+"à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une letttre entre A et H (ex : 3D) ou P pour passer son tour.");
        }
        else{
            System.out.println(Joueurs.getjoueur2()+"à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une letttre entre A et H (ex : 3D) ou P pour passer son tour.");
        }
        String coup = scanner.nextLine();
        scanner.close();
        return coup;
    }
}
