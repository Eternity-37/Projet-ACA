public class Joueurs {
    private static String joueur1 = null;
    private static String joueur2 = null;

    public static void setjoueur1(String nom){
        joueur1 = nom;
    }
    public static void setjoueur2(String nom){
        joueur2 = nom;
    }

    public static String getjoueur1(){
        return joueur1;
    }
    public static String getjoueur2(){
        return joueur2;
    }
}
