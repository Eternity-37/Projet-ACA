import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Plateau {
    private List<List<String>> plateau = new ArrayList<List<String>>();
    
    public Plateau(){
        
        int taille_plateau = 8;
        for (int i = 0; i<taille_plateau ; i++){
            plateau.add(new ArrayList<>(Collections.nCopies(taille_plateau, "\uD83D\uDFE9")));
        }
    
    }

    public List<List<String>> getPlateau(){
        return plateau;
    }
}
