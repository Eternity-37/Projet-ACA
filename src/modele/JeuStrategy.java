package modele;

import modele.Othello.IAStrategy;
import vue.Ihm;

public interface JeuStrategy<T extends Plateau> {
    public void initialiserJeu();
    public void afficherPlateau(Ihm ihm);
    public boolean estPartieTerminee();
    public boolean estCoupPossible();
    public boolean estCoupValide(int x, int y);
    public void jouerCoup(int x, int y);
    public void changerJoueur();
    public Joueurs getJoueurCourant();
    public Joueurs getGagnant();
    public void setJoueur1(Joueurs joueur1);
    public void setJoueur2(Joueurs joueur2);
    public void setIA(boolean estIA);
    public void setStrategieIA(IAStrategy strategieIA);
    public T getPlateau();
} 