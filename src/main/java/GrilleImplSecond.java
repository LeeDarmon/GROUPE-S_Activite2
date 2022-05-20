import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrilleImplSecond implements Grille {

    /** Grille implementee.
     * Peut faire 9x9 ou 16x16
     */
    private char[][] grille;
    /** Grille implementee.
     * @return retourne la grille
     */
    public char[][] getGrille() {
        return grille.clone();
    }

    /** Recupere la grille implementee.
     *
     * @param g La grille à ajouter
     *
     */
    public final void setGrille(final char[][] g) {
        this.grille = g.clone();
    }

    @Override
    public final int getDimension() {
        return grille.length;
    }

    @Override
    public final void setValue(final int x,
                               final int y,
                               final char v)
            throws IllegalArgumentException {
        this.grille[x][y] = v;
    }

    @Override
    public final char getValue(final int x,
                               final int y) throws IllegalArgumentException {
        return this.grille[x][y];
    }


    @Override
    public final boolean complete() {
        boolean r = false;
        for (int i = 0; i < this.grille.length; i++) {
            for (int j = 0; j < this.grille.length; j++) {
                if (this.grille[i][j] == EMPTY) {
                    r = false;
                } else {
                    r = true;
                    break;
                }
            }
        }
        return r;
    }

    /** Montre en console la grille et ses valeurs.
     */
    public void displayGrille() {
        for (int x = 0; x < this.getDimension(); x++) {
            for (int y = 0; y < this.getDimension(); y++) {
                System.out.print(this.getValue(x, y) + " ");
            }
            System.out.println("");
        }
    }
    @Override
    public final boolean possible(final int x,
                                  final int y,
                                  final char v)
            throws IllegalArgumentException {

        List<Character> possibleChar = createListPossible();

        if (checkChar(v, possibleChar)) {
            if (checkX(x, v)) {
                if (checkY(y, v)) {
                    return checkBloc(x, y, v);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /** Creation de la liste des caracteres possibles.
     * @return la liste de caracteres possibles
     */
    public List<Character> createListPossible() {
        //creation tableau possibilités
        int dimension = getDimension();
        //reduction correspond aux caracteres pour la grille 16x16
        final int limit = 9;
        final int caracSupp = 6;
        List<Character> possibleChar = new ArrayList();

        if (dimension == limit) {
            for (int i = 0; i < possible.length - caracSupp; i++) {
                possibleChar.add(possible[i]);
            }
        } else {
            for (int i = 0; i < possible.length; i++) {
                possibleChar.add(possible[i]);
            }
        }
        return possibleChar;
    }

    /** Verifie la liste des caracteres pour le char passe en parametre.
     *
     * @param value La valeur teste
     * @param possibleChar La liste de tous les caracteres dans la liste
     * @return true si la valeur est dans la liste
     */
    public boolean checkChar(final char value,
                             final List<Character> possibleChar)
            throws IllegalArgumentException {
        return possibleChar.contains(value);
    }

    /** Verifie qu'un caractere value peut etre pose en testant sa ligne x.
     * @param x Valeur x sur la grille
     * @param value Valeur teste pour placement en x dans la grille
     * @return true si la valeur est placable sur la ligne x
     */
    public boolean checkX(final int x,
                          final char value) throws IllegalArgumentException {
        List<Character> impossibleChar = new ArrayList();
       for (int i = 0; i < grille.length; i++) {
           impossibleChar.add(getValue(x, i));
       }
       return !impossibleChar.contains(value);
    }

    /** Verifie qu'un caractere value peut etre pose en testant sa colonne y.
     * @param y Valeur y sur la grille
     * @param value Valeur teste pour placement en y dans la grille
     * @return true si la valeur est placable sur la colonne y
     */
    public boolean checkY(final int y,
                          final char value) throws IllegalArgumentException {
        List<Character> impossibleChar = new ArrayList();
        for (int i = 0; i < grille.length; i++) {
            impossibleChar.add(getValue(i, y));
        }
        return !impossibleChar.contains(value);
    }

    /** Verifie un caractere value en testant son bloc a la position x, y.
     * @param x bloc a la position x dans la grille
     * @param y bloc a la position y dans la grille
     * @param value Valeur teste pour placement x, y dans la grille
     * @return true si la valeur est placable dans le bloc
     */
    public boolean checkBloc(final int x,
                             final int y,
                             final char value) throws IllegalArgumentException {
        final List<Character> impossibleChar = new ArrayList();
        final int dimension = getDimension();
        int dimensionMinimum = 9;
        final int diff = 1;

        int c = 0;
        int i = 0;

        //gestion 9 x 9
        if (dimension == dimensionMinimum) {

            if((x + diff) % 3 <= 1){
                c = 2;
                i = 0;

            }
            if((x + diff) % 3 <= 2){
                c = 5;
                i = 3;

            }
            if((x + diff) % 3 <= 3){
                c = 8;
                i = 6;
            }

            for (int j = i; j <= c; j++) {
                char v1 = getValue(j, i);
                char v2 = getValue(j, i+1);
                char v3 = getValue(j, i+2);
                Collections.addAll(impossibleChar, v1, v2, v3);
            }


        }

        //gestion 16 x 16
      /*  else {
            if (x + diff <= 4) {
                c = 4;
                if (y + diff <= 4) {
                    for (int i = 0; i <= c; i++) {
                        char v1 = getValue(i, 0);
                        char v2 = getValue(i, 1);
                        char v3 = getValue(i, 2);
                        char v4 = getValue(i, 3);

                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 8) {
                    for (int i = 0; i <= c; i++) {
                        char v1 = getValue(i, 4);
                        char v2 = getValue(i, 5);
                        char v3 = getValue(i, 6);
                        char v4 = getValue(i, 7);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 12) {
                    for (int i = 0; i <= c; i++) {
                        char v1 = getValue(i, 8);
                        char v2 = getValue(i, 9);
                        char v3 = getValue(i, 10);
                        char v4 = getValue(i, 11);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 16) {
                    for (int i = 0; i <= c; i++) {
                        char v1 = getValue(i, 12);
                        char v2 = getValue(i, 13);
                        char v3 = getValue(i, 14);
                        char v4 = getValue(i, 15);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                }
            } else if (x + diff <= 8) {
                c = 8;
                if (y + diff <= 4) {
                    for (int i = 4; i <= c; i++) {
                        char v1 = getValue(i, 0);
                        char v2 = getValue(i, 1);
                        char v3 = getValue(i, 2);
                        char v4 = getValue(i, 3);

                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 8) {
                    for (int i = 4; i <= c; i++) {
                        char v1 = getValue(i, 4);
                        char v2 = getValue(i, 5);
                        char v3 = getValue(i, 6);
                        char v4 = getValue(i, 7);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 12) {
                    for (int i = 4; i <= c; i++) {
                        char v1 = getValue(i, 8);
                        char v2 = getValue(i, 9);
                        char v3 = getValue(i, 10);
                        char v4 = getValue(i, 11);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 16) {
                    for (int i = 4; i <= c; i++) {
                        char v1 = getValue(i, 12);
                        char v2 = getValue(i, 13);
                        char v3 = getValue(i, 14);
                        char v4 = getValue(i, 15);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                }
            } else if (x + diff <= 12) {
                c = 12;
                if (y + diff <= 4) {
                    for (int i = 8; i <= c; i++) {
                        char v1 = getValue(i, 0);
                        char v2 = getValue(i, 1);
                        char v3 = getValue(i, 2);
                        char v4 = getValue(i, 3);

                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 8) {
                    for (int i = 8; i <= c; i++) {
                        char v1 = getValue(i, 4);
                        char v2 = getValue(i, 5);
                        char v3 = getValue(i, 6);
                        char v4 = getValue(i, 7);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 12) {
                    for (int i = 8; i <= c; i++) {
                        char v1 = getValue(i, 8);
                        char v2 = getValue(i, 9);
                        char v3 = getValue(i, 10);
                        char v4 = getValue(i, 11);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 16) {
                    for (int i = 8; i <= c; i++) {
                        char v1 = getValue(i, 12);
                        char v2 = getValue(i, 13);
                        char v3 = getValue(i, 14);
                        char v4 = getValue(i, 15);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                }
            } else if (x + diff <= 16) {
                c = 16;
                if (y + diff <= 4) {
                    for (int i = 12; i <= c; i++) {
                        char v1 = getValue(i, 0);
                        char v2 = getValue(i, 1);
                        char v3 = getValue(i, 2);
                        char v4 = getValue(i, 3);

                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 8) {
                    for (int i = 12; i <= c; i++) {
                        char v1 = getValue(i, 4);
                        char v2 = getValue(i, 5);
                        char v3 = getValue(i, 6);
                        char v4 = getValue(i, 7);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 12) {
                    for (int i = 12; i <= c; i++) {
                        char v1 = getValue(i, 8);
                        char v2 = getValue(i, 9);
                        char v3 = getValue(i, 10);
                        char v4 = getValue(i, 11);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                } else if (y + diff <= 16) {
                    for (int i = 12; i <= c; i++) {
                        char v1 = getValue(i, 12);
                        char v2 = getValue(i, 13);
                        char v3 = getValue(i, 14);
                        char v4 = getValue(i, 15);
                        Collections.addAll(impossibleChar, v1, v2, v3, v4);
                    }
                }
            }

        } */





        System.out.println(impossibleChar);
        return !impossibleChar.contains(value);
    }
}
