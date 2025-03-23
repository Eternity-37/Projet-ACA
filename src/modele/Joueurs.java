package modele;

public class Joueurs {
    // Noms des joueurs
    private String nomJoueur;
    private int nombreVictoires;

    /**
     * Constructeur de la classe Joueurs
     */
    public Joueurs() {
        this.nomJoueur = null;
        this.nombreVictoires = 0;
    }

    /**
     * Définit le nom du joueur.
     * @param nom Nom du joueur.
     */
    public void setNomJoueur(String nom) {
        this.nomJoueur = nom;
    }

    /**
     * Retourne le nom du joueur.
     * @return Nom du joueur.
     */
    public String getJoueur() {
        return this.nomJoueur;
    }

    /**
     * Retourne le nombre de victoires du joueur.
     * @return Nombre de victoires du joueur.
     */
    public int getNombreVictoires() {
        return this.nombreVictoires;
    }

    /**
     * Met à jour le nombre de victoires du joueur.
     * @param nombre Nouveau nombre de victoires.
     */
    public void setNombreVictoires(int nombre) {
        this.nombreVictoires = nombre;
    }

    /**
     * Retourne le numéro du joueur suivant.
     * @param joueur Numéro du joueur actuel (1 ou 2).
     * @return Numéro du joueur suivant (2 si le joueur actuel est 1, 1 sinon).
     */
    public static int joueurSuivant(int joueur) {
        return (joueur == 1) ? 2 : 1;
    }
}
