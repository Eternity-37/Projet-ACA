package modele.Awale;

import modele.Plateau;
import modele.Joueurs;

/**
 * Classe représentant le plateau de jeu d'Awalé.
 */
public class PlateauAwale extends Plateau {
    private int[][] plateau;
    private int grenierJoueur1;
    private int grenierJoueur2;

    /**
     * Constructeur du plateau d'Awalé.
     */
    public PlateauAwale() {
        super(TAILLE_PLATEAU_AWALE);
        this.plateau = new int[2][TAILLE_PLATEAU_AWALE];  // 2 rangées de 6 trous
        this.grenierJoueur1 = 0;
        this.grenierJoueur2 = 0;
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < TAILLE_PLATEAU_AWALE; j++) {
                plateau[i][j] = 4;
            }
        }
    }

    /**
     * Constructeur de copie.
     * @param autre Le plateau à copier.
     */
    public PlateauAwale(PlateauAwale autre) {
        super(TAILLE_PLATEAU_AWALE);
        this.plateau = new int[2][TAILLE_PLATEAU_AWALE];
        this.grenierJoueur1 = autre.grenierJoueur1;
        this.grenierJoueur2 = autre.grenierJoueur2;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < TAILLE_PLATEAU_AWALE; j++) {
                this.plateau[i][j] = autre.plateau[i][j];
            }
        }
    }

    /**
     * Modifie la valeur d'une case du plateau.
     * @param x La ligne de la case (0 ou 1).
     * @param y La colonne de la case (0-5).
     * @param valeur La nouvelle valeur de la case.
     * @throws IllegalArgumentException Si les coordonnées sont invalides.
     */
    public void setCase(int x, int y, int valeur) {
        if (x < 0 || x >= 2 || y < 0 || y >= TAILLE_PLATEAU_AWALE) {
            throw new IllegalArgumentException("Coordonnées invalides : (" + x + ", " + y + ")");
        }
        plateau[x][y] = valeur;
    }

    /**
     * Retourne la valeur d'une case du plateau.
     * @param x La ligne de la case (0 ou 1).
     * @param y La colonne de la case (0-5).
     * @return La valeur de la case.
     * @throws IllegalArgumentException Si les coordonnées sont invalides.
     */
    public int getCase(int x, int y) {
        if (x < 0 || x >= 2 || y < 0 || y >= TAILLE_PLATEAU_AWALE) {
            throw new IllegalArgumentException("Coordonnées invalides : (" + x + ", " + y + ")");
        }
        return plateau[x][y];
    }

    @Override
    public void afficher() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < TAILLE_PLATEAU_AWALE; j++) {
                System.out.print(plateau[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Grenier Joueur 1: " + grenierJoueur1);
        System.out.println("Grenier Joueur 2: " + grenierJoueur2);
    }

    public int getGrenierJoueur1() {
        return grenierJoueur1;
    }

    public int getGrenierJoueur2() {
        return grenierJoueur2;
    }

    public void setGrenierJoueur1(int valeur) {
        this.grenierJoueur1 = valeur;
    }

    public void setGrenierJoueur2(int valeur) {
        this.grenierJoueur2 = valeur;
    }

    public void ajouterAuGrenier(int joueur, int nbGraines) {
        if (joueur == 1) {
            grenierJoueur1 += nbGraines;
        } else if (joueur == 2) {
            grenierJoueur2 += nbGraines;
        }
    }

    @Override
    public String joueurGagnant(Joueurs joueur1, Joueurs joueur2) {
        // Ajoute les graines restantes au joueur correspondant
        if (!LogiqueAwale.peutJouer(1, this)) {
            // Le joueur 1 ne peut plus jouer, ajouter ses graines à l'adversaire
            for (int j = 0; j < TAILLE_PLATEAU_AWALE; j++) {
                grenierJoueur2 += plateau[1][j];
                plateau[1][j] = 0;
            }
        } else if (!LogiqueAwale.peutJouer(2, this)) {
            // Le joueur 2 ne peut plus jouer, ajouter ses graines à l'adversaire
            for (int j = 0; j < TAILLE_PLATEAU_AWALE; j++) {
                grenierJoueur1 += plateau[0][j];
                plateau[0][j] = 0;
            }
        }

        // Détermine le gagnant en fonction des greniers
        if (grenierJoueur1 > grenierJoueur2) {
            return joueur1.getJoueur();
        } else if (grenierJoueur2 > grenierJoueur1) {
            return joueur2.getJoueur();
        } else {
            return "ex aequo";
        }
    }

    /**
     * Effectue une rafle des graines à partir d'une position donnée.
     * @param ligne La ligne de la case.
     * @param colonne La colonne de la case.
     * @param joueur Le joueur qui capture (1 ou 2).
     * @return Le nombre de graines capturées.
     */
    public int rafle(int ligne, int colonne, int joueur) {
        int grainesCapturees = 0;
        int position = colonne;

        // Capture les graines en reculant
        while (position >= 0) {
            int valeurCase = getCase(ligne, position);
            if (valeurCase == 2 || valeurCase == 3) {
                grainesCapturees += valeurCase;
                setCase(ligne, position, 0);
                position--;
            } else {
                break;
            }
        }

        return grainesCapturees;
    }

    /**
     * Retourne le symbole pour représenter une case du plateau.
     * @param x La ligne de la case.
     * @param y La colonne de la case.
     * @return La représentation textuelle de la case.
     */
    public String getSymboleCase(int x, int y) {
        int valeur = getCase(x, y);
        return "(" + valeur + ")";
    }

    public static int getTaillePlateau() {
        return Plateau.getTaillePlateau();
    }
}