package modele;

import modele.Othello.IAStrategy;
import modele.Othello.LogiqueOthello;
import modele.Othello.PlateauOthello;
import vue.Ihm;
import java.util.List;
import java.util.AbstractMap;
/**
 * Implémentation de la stratégie du jeu Othello.
 * Gère la logique de jeu, les coups, les changements de joueur et l'état de la partie.
 */
public class OthelloStrategy implements JeuStrategy<PlateauOthello> {
    private PlateauOthello plateau;
    private Joueurs joueurCourant;
    private Joueurs joueur1;
    private Joueurs joueur2;
    private Joueurs ordinateur;
    private boolean jouerContreIA;
    private IAStrategy strategieIA;
    /**
     * Constructeur de la stratégie Othello.
     * Initialise le plateau et les joueurs par défaut.
     */
    public OthelloStrategy() {
        this.plateau = new PlateauOthello();
        this.joueur1 = new Joueurs();
        this.joueur2 = new Joueurs();
        this.ordinateur = new Joueurs();
        this.joueurCourant = joueur1;
        this.jouerContreIA = false;
    }

    @Override
    public void initialiserJeu() {
        joueurCourant = joueur1;
    }

    @Override
    public boolean estCoupValide(int x, int y) {
        List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = 
            LogiqueOthello.coupEstValide(x, y, plateau, joueurCourant == joueur1 ? 1 : 2);
        return !directionsValides.isEmpty();
    }

    @Override
    public void jouerCoup(int x, int y) {
        List<AbstractMap.SimpleEntry<Integer, Integer>> directionsValides = 
            LogiqueOthello.coupEstValide(x, y, plateau, joueurCourant == joueur1 ? 1 : 2);
        plateau.retournerPions(x, y, directionsValides, joueurCourant == joueur1 ? 1 : 2);
        plateau.setCase(x, y, joueurCourant == joueur1 ? 1 : 2);
        changerJoueur();
    }

    @Override
    public boolean estPartieTerminee() {
        return LogiqueOthello.partieFinie(plateau);
    }

    @Override
    public Joueurs getGagnant() {
        String gagnant = plateau.joueurGagnant(joueur1, joueur2);
        if (gagnant.equals(joueur1.getJoueur())) {
            return joueur1;
        } else if (gagnant.equals(joueur2.getJoueur())) {
            return joueur2;
        }
        return null;
    }

    @Override
    public void afficherPlateau(Ihm ihm) {
        ihm.afficherPlateau(plateau);
    }

    @Override
    public Joueurs getJoueurCourant() {
        return joueurCourant;
    }

    @Override
    public void changerJoueur() {
        if (jouerContreIA) {
            joueurCourant = (joueurCourant == joueur1) ? ordinateur : joueur1;
        } else {
            joueurCourant = (joueurCourant == joueur1) ? joueur2 : joueur1;
        }
    }

    @Override
    public boolean estCoupPossible() {
        return LogiqueOthello.peutJouer(joueurCourant == joueur1 ? 1 : 2, plateau);
    }

    @Override
    public void setJoueur1(Joueurs joueur1) {
        this.joueur1 = joueur1;
    }

    @Override
    public void setJoueur2(Joueurs joueur2) {
        this.joueur2 = joueur2;
    }

    @Override
    public void setIA(boolean estIA) {
        this.jouerContreIA = estIA;
    }

    @Override
    public void setStrategieIA(IAStrategy strategieIA) {
        this.strategieIA = strategieIA;
    }

    @Override
    public PlateauOthello getPlateau() {
        return plateau;
    }
} 