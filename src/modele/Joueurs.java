package modele;

public class Joueurs {
    // Noms des joueurs (initialement null)
    private static String joueur1 = null;
    private static String joueur2 = null;

    // Symboles des joueurs (⚫ pour joueur 1, ⚪ pour joueur 2)
    private static String couleurjoueur1 = "\u26AB"; // ⚫ Noir
    private static String couleurjoueur2 = "\u26AA"; // ⚪ Blanc

    // Nombre de victoires pour chaque joueur
    private static int nbVictoirejoueur1 = 0;
    private static int nbVictoirejoueur2 = 0;

    /**
     * Définit le nom du joueur 1.
     * @param nom Nom du joueur 1.
     */
    public static void setjoueur1(String nom) {
        joueur1 = nom;  // Assigne le nom du joueur 1
    }

    /**
     * Définit le nom du joueur 2.
     * @param nom Nom du joueur 2.
     */
    public static void setjoueur2(String nom) {
        joueur2 = nom;  // Assigne le nom du joueur 2
    }

    /**
     * Retourne le nom du joueur 1.
     * @return Nom du joueur 1.
     */
    public static String getJoueur1() {
        return joueur1;  // Retourne le nom du joueur 1
    }

    /**
     * Retourne le nom du joueur 2.
     * @return Nom du joueur 2.
     */
    public static String getJoueur2() {
        return joueur2;  // Retourne le nom du joueur 2
    }

    /**
     * Retourne le nom du joueur courant en fonction de son numéro.
     * @param n Numéro du joueur (1 ou 2).
     * @return Nom du joueur courant.
     */
    public static String getjoueurcourant(int n) {
        return (n == 1) ? joueur1 : joueur2;  // Retourne le nom du joueur correspondant au numéro (1 ou 2)
    }

    /**
     * Retourne le nombre de victoires du joueur 1.
     * @return Nombre de victoires du joueur 1.
     */
    public static int getNbVictoirejoueur1() {
        return nbVictoirejoueur1;  // Retourne le nombre de victoires du joueur 1
    }

    /**
     * Retourne le nombre de victoires du joueur 2.
     * @return Nombre de victoires du joueur 2.
     */
    public static int getNbVictoirejoueur2() {
        return nbVictoirejoueur2;  // Retourne le nombre de victoires du joueur 2
    }

    /**
     * Met à jour le nombre de victoires du joueur 1.
     * @param nb Nouveau nombre de victoires.
     */
    public static void setNbVictoirejoueur1(int nb) {
        nbVictoirejoueur1 = nb;  // Met à jour le nombre de victoires du joueur 1
    }

    /**
     * Met à jour le nombre de victoires du joueur 2.
     * @param nb Nouveau nombre de victoires.
     */
    public static void setNbVictoirejoueur2(int nb) {
        nbVictoirejoueur2 = nb;  // Met à jour le nombre de victoires du joueur 2
    }

    /**
     * Retourne le numéro du joueur suivant.
     * @param joueur Numéro du joueur actuel (1 ou 2).
     * @return 2 si joueur = 1, sinon 1.
     */
    public static int joueurSuivant(int joueur) {
        return (joueur == 1) ? 2 : 1;  // Retourne le numéro du joueur suivant (si joueur 1, retourne 2, sinon retourne 1)
    }
}
