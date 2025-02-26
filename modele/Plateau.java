import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Plateau {
    ArrayList<ArrayList<String>> plateau = new ArrayList<>();
    private int n = 8;

    for (int i = 0; i<n ; i++){
        plateau.add(new ArrayList<>(Collections.nCopies(n, null)));
    }
}
