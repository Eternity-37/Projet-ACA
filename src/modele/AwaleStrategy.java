package modele;

import modele.Awale.LogiqueAwale;
import modele.Awale.PlateauAwale;
import modele.Othello.IAStrategy;
import vue.Ihm;

public class AwaleStrategy implements JeuStrategy<PlateauAwale> {
    private PlateauAwale plateau;
    private int joueurActuel;
    private Joueurs joueur1;
    private Joueurs joueur2;
    private boolean jouerContreIA;
    private IAStrategy strategieIA;

    public AwaleStrategy() {
        this.plateau = new PlateauAwale();
        this.joueur1 = new Joueurs();
        this.joueur2 = new Joueurs();
        this.joueurActuel = 1;
        this.jouerContreIA = false;
    }

    @Override
    public void initialiserJeu() {
        joueurActuel = 1;
    }

    @Override
    public boolean estCoupValide(int x, int y) {
        return !LogiqueAwale.coupsAffameAdversaire(x, y, plateau) && plateau.getCase(x, y) > 0;
    }

    @Override
    public void jouerCoup(int x, int y) {
        LogiqueAwale.jouerCoup(x, y, plateau, joueurActuel);
        changerJoueur();
    }

    @Override
    public boolean estPartieTerminee() {
        return LogiqueAwale.partieFinie(plateau);
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
        return (joueurActuel == 1) ? joueur1 : joueur2;
    }

    @Override
    public void changerJoueur() {
        joueurActuel = (joueurActuel == 1) ? 2 : 1;
    }

    @Override
    public boolean estCoupPossible() {
        return LogiqueAwale.peutJouer(joueurActuel, plateau);
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
    public PlateauAwale getPlateau() {
        return plateau;
    }
} 