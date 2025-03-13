package modele;

public class Joueurs {
    // Noms des joueurs (initialement null)
    private static String nomjoueur = null;

    // Nombre de victoires pour chaque joueur
    private static int nbVictoirejoueur = 0;

    /**
     * Définit le nom du joueur 1.
     * @param nom Nom du joueur 1.
     */
    public static void setjoueur(String nom) {
        nomjoueur = nom;  // Assigne le nom du joueur 1
    }

    /**
     * Retourne le nom du joueur 1.
     * @return Nom du joueur 1.
     */
    public static String getJoueur() {
        return nomjoueur;  // Retourne le nom du joueur 1
    }

    /**
     * Retourne le nombre de victoires du joueur 1.
     * @return Nombre de victoires du joueur 1.
     */
    public static int getNbVictoire() {
        return nbVictoirejoueur;  // Retourne le nombre de victoires du joueur 1
    }

    /**
     * Met à jour le nombre de victoires du joueur 1.
     * @param nb Nouveau nombre de victoires.
     */
    public static void setNbVictoire(int nb) {
        nbVictoirejoueur = nb;  // Met à jour le nombre de victoires du joueur 1
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
