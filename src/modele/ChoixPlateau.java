package modele;

public interface ChoixPlateau {

    void setCase(int x,int y,int valeur);

    int getCase(int x,int y);

    String joueurGagnant(Joueurs joueur1, Joueurs joueur2);

}
