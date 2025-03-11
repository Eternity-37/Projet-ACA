package modele;

import java.util.AbstractMap;
import java.util.List;
import java.util.Random;

public class IA {

    public static AbstractMap.SimpleEntry<Integer,Integer> coupIA(List<AbstractMap.SimpleEntry<Integer, Integer>> coupPossibles){
        Random nbrandom = new Random();
        return coupPossibles.get(nbrandom.nextInt(coupPossibles.size()));
    }
}
