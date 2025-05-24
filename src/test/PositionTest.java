/**
 * Classe de test pour la classe Position.
 * 
 * @author Morain Arthur
 */

import org.junit.*;
import static org.junit.Assert.*;

public class PositionTest {

    private Position position;
    public static final double EPSILON = 1e-6;
	// précision pour la comparaison entre réels.

    @Before
    public void setUp() {
        position = new Position(5.0, 10.0);
    }

    /**On teste d'abord le constructeur de la classe. */
    @Test
    public void testConstructeur() {
        assertEquals(5.0, position.getAbscisse(), EPSILON);
        assertEquals(10.0, position.getOrdonee(), EPSILON);
    }

    /**Test de la méthode setAbscisse. */
    @Test
    public void testSetAbscisse() {
        position.setAbscisse(7.5);
        assertEquals(7.5, position.getAbscisse(), EPSILON);
    }

    /**Test de la méthode setOrdonnee. */
    @Test
    public void testSetOrdonnee() {
        position.setOrdonee(12.3);
        assertEquals(12.3, position.getOrdonee(), EPSILON);
    }

    /**Test de la méthode setPosition. */
    @Test
    public void testSetPosition() {
        position.setPosition(3.0, -2.0);
        assertEquals(3.0, position.getAbscisse(), EPSILON);
        assertEquals(-2.0, position.getOrdonee(), EPSILON);
    }

    /**Test de la méthode Deplacer */
    @Test 
    public void testDeplacer() {
        position.deplacer(3.0, -2.0);
        assertEquals(8.0, position.getAbscisse(), EPSILON);
        assertEquals(8.0, position.getOrdonee(), EPSILON);
    }

    /**Test de la méthode calculerDistance. */
    @Test
    public void testDistanceAvec() {
        Position autrePosition = new Position(8.0, 14.0);
        double distance = position.calculerDistance(autrePosition);
        assertEquals(5.0, distance, EPSILON);
    }
}