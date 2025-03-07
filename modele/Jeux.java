public class Jeux {
    public static boolean partieFinie(Plateau plateau){
        return false;
    }

    private static boolean verifierHaut(int x, int y, Plateau plateau, char joueur) {
        int i = x - 1;
        boolean pionAdverse = false;

        while (i >= 0) {
            char caseActuelle = plateau.getCouleurCase(x, y);

            if (caseActuelle == (joueur == '\u26AB' ? '\u26AA' : '\u26AB')) {
                pionAdverse = true;
            } else if (caseActuelle == joueur) {
                return pionAdverse;
            } else {
                break;
            }
            i--;
        }
        return false;

    }
    private static boolean verifierBas(int x, int y, Plateau plateau, char joueur) {
        int i = x + 1;
        boolean pionAdverseTrouve = false;

        while (i < 8) {
            char caseActuelle = plateau.getCouleurCase(i, y);

            if (caseActuelle == (joueur == '⚫' ? '⚪' : '⚫')) {
                pionAdverseTrouve = true;
            } else if (caseActuelle == joueur) {
                return pionAdverseTrouve;
            } else {
                break;
            }
            i++;
        }
        return false;
    }
    private static boolean verifierGauche(int x, int y, Plateau plateau, char joueur) {
        int j = y - 1;
        boolean pionAdverseTrouve = false;

        while (j >= 0) {
            char caseActuelle = plateau.getCouleurCase(x, j);

            if (caseActuelle == (joueur == '⚫' ? '⚪' : '⚫')) {
                pionAdverseTrouve = true;
            } else if (caseActuelle == joueur) {
                return pionAdverseTrouve;
            } else {
                break;
            }
            j--;
        }
        return false;
    }
    private static boolean verifierDroite(int x, int y, Plateau plateau, char joueur) {
        int j = y + 1;
        boolean pionAdverseTrouve = false;

        while (j < 8) {
            char caseActuelle = plateau.getCouleurCase(x, j);

            if (caseActuelle == (joueur == '⚫' ? '⚪' : '⚫')) {
                pionAdverseTrouve = true;
            } else if (caseActuelle == joueur) {
                return pionAdverseTrouve;
            } else {
                break;
            }
            j++;
        }
        return false;
    }

    public static boolean coupEstValide(int x, int y, Plateau plateau, char joueur) {
        if (plateau.getCouleurcase(x, y) == "\uD83D\uDFE9") {
            return false;
        }
         return verifierHaut(x, y, plateau, joueur) || verifierBas(x, y, plateau, joueur) || verifierGauche(x, y, plateau, joueur) || verifierDroite(x, y, plateau, joueur);
    }
}

