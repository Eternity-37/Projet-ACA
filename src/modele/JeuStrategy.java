package modele;

import modele.Othello.IAStrategy;
import vue.Ihm;

public interface JeuStrategy<T extends Plateau> {
    /**
     * Initialise l'état du jeu avant de commencer une partie.
     */
    public void initialiserJeu();


    /**
     * Affiche l'état actuel du plateau à l'aide de l'IHM.
     *
     * @param ihm l'interface utilisateur utilisée pour afficher.
     */
    public void afficherPlateau(Ihm ihm);

    /**
     * Indique si la partie est terminée.
     *
     * @return true si la partie est finie, false sinon.
     */
    public boolean estPartieTerminee();

    /**
     * Indique si le joueur courant a encore des coups possibles.
     *
     * @return true si au moins un coup est possible.
     */
    public boolean estCoupPossible();

    /**
     * Vérifie si un coup est valide aux coordonnées spécifiées.
     *
     * @param x ligne (ou identifiant logique selon le jeu)
     * @param y colonne (ou case dans Awalé)
     * @return true si le coup est autorisé
     */
    public boolean estCoupValide(int x, int y);

    /**
     * Joue un coup aux coordonnées spécifiées.
     *
     * @param x ligne ou camp
     * @param y colonne ou trou sélectionné
     */
    public void jouerCoup(int x, int y);

    /**
     * Change le joueur actif.
     */
    public void changerJoueur();

    /**
     * Retourne le joueur dont c'est actuellement le tour.
     *
     * @return le joueur courant
     */
    public Joueurs getJoueurCourant();

    /**
     * Retourne le joueur gagnant à la fin de la partie, ou null en cas d'égalité.
     *
     * @return le joueur gagnant ou null
     */
    public Joueurs getGagnant();

    /**
     * Définit le joueur 1.
     *
     * @param joueur1 le premier joueur
     */
    public void setJoueur1(Joueurs joueur1);

    /**
     * Définit le joueur 2.
     *
     * @param joueur2 le second joueur
     */
    public void setJoueur2(Joueurs joueur2);

    /**
     * Indique si une IA est activée dans la partie.
     *
     * @param estIA true si une IA doit être utilisée
     */
    public void setIA(boolean estIA);

    /**
     * Définit la stratégie IA à utiliser (si applicable).
     *
     * @param strategieIA la stratégie de l’IA
     */
    public void setStrategieIA(IAStrategy strategieIA);

    /**
     * Retourne le plateau de jeu utilisé.
     *
     * @return l’objet représentant le plateau
     */
    public T getPlateau();
} 