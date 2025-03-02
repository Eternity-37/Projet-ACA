public class Controleur {
    private Ihm ihm;
    private Plateau plateau;

    public Controleur(Ihm ihm){
        this.ihm = ihm;
        this.plateau = new Plateau();
    }
    
    public void jouer(){
        boolean etatjeu = true;
        Joueurs.setjoueur1(ihm.asknomjoueurs(1));
        Joueurs.setjoueur2(ihm.asknomjoueurs(2));
        int joueurcourant = 1;
        while (etatjeu){
            ihm.print_plateau(plateau);
            String coup = ihm.choixCoup(joueurcourant);
            int x = coup.charAt(0) - '1';
            int y = coup.charAt(1) - 'A';
            Plateau.setCase(x,y,Joueurs.getcouleurjoueurcourant(joueurcourant));
            ihm.print_plateau(plateau);
            etatjeu = false;
        }
    }
}