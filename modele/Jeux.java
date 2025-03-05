public class Jeux {
    public static boolean partieFinie(Plateau plateau){
        return false;
    }

    public static boolean coupEstValide(int x, int y, Plateau plateau){
        if (Plateau.getCouleurcase(x,y)=="\uD83D\uDFE9"){
            return true;
        }
        else{
            return false;
        }
    }
}
