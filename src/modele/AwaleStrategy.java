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


    /**
     * Constructeur de la stratégie Awalé. Initialise un nouveau plateau et deux joueurs.
     */

    public AwaleStrategy() {
        this.plateau = new PlateauAwale();
        this.joueur1 = new Joueurs();
        this.joueur2 = new Joueurs();
        this.joueurActuel = 1;
        this.jouerContreIA = false;
    }
    /**
     * Initialise le jeu en réinitialisant le joueur courant à 1.
     */
    @Override
    public void initialiserJeu() {
        joueurActuel = 1;
    }


    /**
     * Vérifie si un coup est valide pour le joueur courant.
     *
     * @param x La ligne (non utilisée dans Awalé mais conservée pour compatibilité).
     * @param y L’index de la case dans le camp du joueur.
     * @return true si le coup est autorisé, false sinon.
     */

    @Override
    public boolean estCoupValide(int x, int y) {
        return !LogiqueAwale.coupsAffameAdversaire(x, y, plateau) && plateau.getCase(x, y) > 0;
    }


    /**
     * Joue le coup donné pour le joueur courant, puis change de joueur.
     *
     * @param x La ligne (inutile ici).
     * @param y L’index de la case.
     */
    @Override
    public void jouerCoup(int x, int y) {
        LogiqueAwale.jouerCoup(x, y, plateau, joueurActuel);
        changerJoueur();
    }


    /**
     * Vérifie si la partie est terminée.
     *
     * @return true si la partie est finie, false sinon.
     */

    @Override
    public boolean estPartieTerminee() {
        return LogiqueAwale.partieFinie(plateau);
    }


    /**
     * Retourne le joueur gagnant à la fin de la partie.
     *
     * @return Le joueur gagnant, ou null en cas d’égalité.
     */

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


    /**
     * Affiche l’état actuel du plateau via l’interface console.
     *
     * @param ihm L’interface utilisateur utilisée pour afficher le plateau.
     */
    @Override
    public void afficherPlateau(Ihm ihm) {
        ihm.afficherPlateau(plateau);
    }


    /**
     * Retourne le joueur courant.
     *
     * @return Le joueur dont c’est le tour.
     */
    @Override
    public Joueurs getJoueurCourant() {
        return (joueurActuel == 1) ? joueur1 : joueur2;
    }
    /**
     * Change le joueur actif.
     */
    @Override
    public void changerJoueur() {
        joueurActuel = (joueurActuel == 1) ? 2 : 1;
    }


    /**
     * Vérifie si le joueur courant peut encore jouer.
     *
     * @return true si un coup est encore possible, false sinon.
     */
    @Override
    public boolean estCoupPossible() {
        return LogiqueAwale.peutJouer(joueurActuel, plateau);
    }


    /**
     * Définit le joueur 1.
     *
     * @param joueur1 Le joueur à assigner.
     */
    @Override
    public void setJoueur1(Joueurs joueur1) {
        this.joueur1 = joueur1;
    }


    /**
     * Définit le joueur 2.
     *
     * @param joueur2 Le joueur à assigner.
     */
    @Override
    public void setJoueur2(Joueurs joueur2) {
        this.joueur2 = joueur2;
    }


    /**
     * Active ou désactive l'utilisation d'une IA.
     *
     * @param estIA true si le joueur 2 est une IA, false sinon.
     */
    @Override
    public void setIA(boolean estIA) {
        this.jouerContreIA = estIA;
    }


    /**
     * Définit la stratégie utilisée par l’IA.
     *
     * @param strategieIA La stratégie d’intelligence artificielle.
     */
    @Override
    public void setStrategieIA(IAStrategy strategieIA) {
        this.strategieIA = strategieIA;
    }


    /**
     * Retourne le plateau de jeu.
     *
     * @return Le plateau de type PlateauAwale.
     */
    @Override
    public PlateauAwale getPlateau() {
        return plateau;
    }
} 