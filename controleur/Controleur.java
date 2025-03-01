public class Controleur {
    private Ihm ihm;
    private Plateau plateau;

    public Controleur(Ihm ihm){
        this.ihm = ihm;
        this.plateau = new Plateau();
    }
    
    public void jouer(){
        ihm.print_plateau(plateau);
}
