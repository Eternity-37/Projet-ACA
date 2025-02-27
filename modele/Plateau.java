import java.util.ArrayList;
import java.util.Collections;

public class Plateau {
    
    private static void set_plateau(){
        ArrayList<ArrayList<String>> plateau = new ArrayList<>();
        int taille_plateau = 8;
        for (int i = 0; i<taille_plateau ; i++){
            plateau.add(new ArrayList<>(Collections.nCopies(taille_plateau, "\uD83D\uDFE9")));
        }
    }
}
