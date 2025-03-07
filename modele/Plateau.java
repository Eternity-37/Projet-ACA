package modele;

public class Plateau {
    private static int plateau[][] = new int[8][8];
    
    public Plateau(){
        int taille_plateau = 8;
        for (int i = 0; i<taille_plateau ; i++){
            for (int j = 0; j<taille_plateau ; j++) {
                plateau[i][j] = 0;
            }
        }
        setCase(3,3,2);setCase(3,4,1);setCase(4,3,1);setCase(4,4,2);
    }

    public int[][] getPlateau(){
        return plateau;
    }

    public static void setCase(int x,int y,int num){
        plateau[x][y] = num;
    }

    public static int getCase(int x,int y){return plateau[x][y];}

    public static String getCouleurcase(int x , int y){
        int num = plateau[x][y];
        if (num == 0){
            return "\uD83D\uDFE9";
        }
        else if (num == 1){
            return "\u26AB";
        }
        else{
            return "\u26AA";
        }
    }

    public static void retournerPions(){}
}
