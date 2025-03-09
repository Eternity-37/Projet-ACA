package modele;

public class Joueurs {
    private static String joueur1 = null;
    private static String joueur2 = null;
    private static String couleurjoueur1 = "\u26AB";
    private static String couleurjoueur2 = "\u26AA";
    private static int nbVictoirejoueur1 = 0;
    private static int nbVictoirejoueur2 = 0;

    public static void setjoueur1(String nom){
        joueur1 = nom;
    }
    public static void setjoueur2(String nom){
        joueur2 = nom;
    }

    public static String getJoueur1() {
        return joueur1;
    }
    public static String getJoueur2() {
        return joueur2;
    }

    public static String getjoueurcourant(int n){
        if (n==1){
            return joueur1;
        }return joueur2;
    }

    public static String getcouleurjoueurcourant(int n){
        if (n == 1){
            return couleurjoueur1;
        }else return couleurjoueur2;
    }
    public static int getNbVictoirejoueur1(){return nbVictoirejoueur1;}
    public static int getNbVictoirejoueur2(){return nbVictoirejoueur2;}

    public static void setNbVictoirejoueur1(int nb){nbVictoirejoueur1 = nb;}
    public static void setNbVictoirejoueur2(int nb){nbVictoirejoueur2 = nb;}

    public static int joueurSuivant(int joueur){
        if (joueur == 1){
            return 2;
        }
        else{
            return 1;
        }
    }
}