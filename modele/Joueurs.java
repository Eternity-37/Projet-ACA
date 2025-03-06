public class Joueurs {
    private static String joueur1 = null;
    private static String joueur2 = null;
    private static String couleurjoueur1 = "\u26AB";
    private static String couleurjoueur2 = "\u26AA";

    public static void setjoueur1(String nom){
        joueur1 = nom;
    }
    public static void setjoueur2(String nom){
        joueur2 = nom;
    }

    public static String getjoueurcourant(int n){
        if (n==1){
            return joueur1;
        }
        return joueur2;
    }

    public static String getcouleurjoueurcourant(int n){
        if (n == 1){
            return couleurjoueur1;
        }
        else return couleurjoueur2;
    }

    public static int joueurSuivant(int joueur){
        if (joueur == 1){
            return 2;
        }
        else{
            return 1;
        }
    }
}