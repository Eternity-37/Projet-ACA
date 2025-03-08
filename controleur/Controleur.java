package controleur;

import modele.Jeux;
import modele.Joueurs;
import modele.Plateau;
import vue.Ihm;

import java.util.AbstractMap;
import java.util.List;

public class Controleur {
    private Ihm ihm;
    private Plateau plateau;

    public Controleur(Ihm ihm){
        this.ihm = ihm;
        this.plateau = new Plateau();
    }

    public void jouer(){
        boolean etatjeu = true;
        Joueurs.setjoueur1(ihm.demandernomjoueurs(1));
        Joueurs.setjoueur2(ihm.demandernomjoueurs(2));
        int joueurcourant = 1;
        while (etatjeu){
            Ihm.afficher_plateau(plateau);
            while (!Jeux.partieFinie(plateau)) {
                String coup = ihm.choixCoup(joueurcourant);
                if (coup.equals("P")){
                    joueurcourant = Joueurs.joueurSuivant(joueurcourant);
                    continue;
                }
                if (coup.length() ==3) {
                    int x = coup.charAt(0) - '1';
                    int y = coup.charAt(2) - 'A';
                    List<AbstractMap.SimpleEntry<Integer, Integer>> coupsvalides = Jeux.coupEstValide(x, y, plateau, joueurcourant);
                    if (!coupsvalides.isEmpty()) {
                        Plateau.setCase(x, y, joueurcourant);
                        Plateau.retournerPions(plateau,x,y,coupsvalides,joueurcourant);
                        joueurcourant = Joueurs.joueurSuivant(joueurcourant);
                        Ihm.afficher_plateau(plateau);
                    }
                    else{
                        System.out.println("Erreur coup non valide");

                    }
                }
                else{
                    System.out.println("Erreur format du coup non valide");
                }
            }
            System.out.println(Plateau.joueurGagnant(plateau));
            etatjeu = false;
        }
    }
}