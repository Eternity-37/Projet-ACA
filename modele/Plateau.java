package modele;

import java.util.AbstractMap;
import java.util.List;

public class Plateau {
    private static int taille_plateau = 4;
    private static int plateau[][] = new int[taille_plateau][taille_plateau];
    public static int  getTaillePlateau() {return taille_plateau;}
    
    public Plateau(){
        for (int i = 0; i<taille_plateau ; i++){
            for (int j = 0; j<taille_plateau ; j++) {
                plateau[i][j] = 0;
            }
        }
        setCase(taille_plateau/2 - 1,taille_plateau/2 - 1,2);setCase(taille_plateau/2 - 1,taille_plateau/2,1);setCase(taille_plateau/2,taille_plateau/2 - 1,1);setCase(taille_plateau/2,taille_plateau/2,2);
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

    public static void retournerPions(Plateau plateau, int x, int y,List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides, int joueurcourant){
        for (AbstractMap.SimpleEntry<Integer, Integer> direction : directionsValides) {
            int ligne = direction.getKey();
            int colonne = direction.getValue();
            int lignecourante = x + ligne;
            int colonnecourante = y + colonne;

            while (plateau.plateau[lignecourante][colonnecourante] == Joueurs.joueurSuivant(joueurcourant)) {
                plateau.plateau[lignecourante][colonnecourante] = joueurcourant;
                lignecourante += ligne;
                colonnecourante += colonne;
            }
        }
    }
    public static String joueurGagnant(Plateau plateau){
        int nbPionsjoueur1 = 0;
        int nbPionsjoueur2 = 0;
        for (int i = 0; i < plateau.plateau.length; i++) {
            for (int j = 0; j < plateau.plateau.length; j++) {
                if (plateau.getPlateau()[i][j]==1){
                    nbPionsjoueur1++;
                }
                else if (plateau.getPlateau()[i][j]==2){
                    nbPionsjoueur2++;
                }
            }
        }
        if (nbPionsjoueur1>nbPionsjoueur2){
            return "Le joueur "+ modele.Joueurs.getJoueur1() + " a gagné avec : "+nbPionsjoueur1;
        }
        else if (nbPionsjoueur2>nbPionsjoueur1){
            return "Le joueur " + modele.Joueurs.getJoueur2() + " a gagné avec : "+nbPionsjoueur2;
        }
        else{
            return "ex aequo";
        }
    }
}
