package modele;

/**
 * Classe abstraite représentant un plateau de jeu.
 * Cette classe définit les méthodes communes à tous les plateaux de jeu.
 */
public abstract class Plateau {
    protected static int taillePlateau;
    public static final int TAILLE_PLATEAU_OTHELLO = 8;
    public static final int TAILLE_PLATEAU_AWALE = 6;

    /**
     * Constructeur du plateau.
     * @param taillePlateau La taille du plateau.
     */
    public Plateau(int taillePlateau) {
        Plateau.taillePlateau = taillePlateau;
    }

    /**
     * Retourne la taille du plateau.
     * @return La taille du plateau.
     */
    public static int getTaillePlateau() {
        return taillePlateau;
    }

    /**
     * Modifie la valeur d'une case du plateau.
     * @param x La ligne de la case.
     * @param y La colonne de la case.
     * @param valeur La nouvelle valeur de la case.
     */
    public abstract void setCase(int x, int y, int valeur);

    /**
     * Retourne la valeur d'une case du plateau.
     * @param x La ligne de la case.
     * @param y La colonne de la case.
     * @return La valeur de la case.
     */
    public abstract int getCase(int x, int y);

    /**
     * Affiche le plateau dans la console.
     */
    public abstract void afficher();

    /**
     * Détermine le gagnant de la partie.
     * @param joueur1 Le premier joueur.
     * @param joueur2 Le deuxième joueur.
     * @return Le nom du joueur gagnant ou "ex aequo" en cas d'égalité.
     */
    public abstract String joueurGagnant(Joueurs joueur1, Joueurs joueur2);
} 