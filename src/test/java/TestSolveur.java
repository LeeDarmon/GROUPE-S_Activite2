import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Tests pour solveur.
 */
public final class TestSolveur {
    /**
     * Tests methode resoudreSudoku() avec grille complete.
     */
    @Test
    void resoudreSudoku() {
        char[][] grilleComplete = {
            {'3', '9', '1', '2', '8', '6', '5', '7', '4'},
            {'4', '8', '7', '3', '5', '9', '1', '2', '6'},
            {'6', '5', '2', '7', '1', '4', '8', '3', '9'},
            {'8', '7', '5', '4', '3', '1', '6', '9', '2'},
            {'2', '1', '3', '9', '6', '7', '4', '8', '5'},
            {'9', '6', '4', '5', '2', '8', '7', '1', '3'},
            {'1', '4', '9', '6', '7', '3', '@', '5', '8'},
            {'5', '3', '8', '1', '4', '2', '9', '6', '7'},
            {'7', '2', '6', '8', '9', '5', '3', '@', '1'},
        };

        GrilleImplSecond objetGrille1 = new GrilleImplSecond(grilleComplete);
        //objetGrille1.setGrille(grilleComplete);
        Solveur s = new Solveur(objetGrille1);
        Assertions.assertTrue(s.resoudreSudoku());
        char[][] g = {
                {'3', '9', '1', '2', '8', '6', '5', '7', '4'},
                {'4', '8', '7', '3', '5', '9', '1', '2', '6'},
                {'6', '5', '2', '7', '1', '4', '8', '3', '9'},
                {'@', '7', '5', '4', '3', '1', '6', '9', '2'},
                {'2', '1', '3', '9', '6', '@', '4', '8', '5'},
                {'9', '6', '4', '5', '2', '8', '7', '1', '3'},
                {'1', '4', '@', '6', '7', '3', '@', '5', '8'},
                {'5', '3', '8', '1', '4', '2', '9', '6', '7'},
                {'@', '2', '6', '8', '9', '5', '3', '@', '1'},
        };

        GrilleImplSecond gis = new GrilleImplSecond(g);
        Solveur s1 = new Solveur(gis);
        Assertions.assertTrue(s1.resoudreSudoku());
    }
}
