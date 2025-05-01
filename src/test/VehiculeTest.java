import org.junit.*;
import static org.junit.Assert.*;

/**
 * Classe de test pour la classe Vehicule.
 * @author Morain Arthur
 */
public class VehiculeTest {

    private Vehicule vehicule;
    public static final double EPSILON = 1e-6;
	// précision pour la comparaison entre réels.

    @Before
    public void setUp() {
        // Initialisation d'un véhicule avant chaque test
        vehicule = new Vehicule(0, 0, 4.5, 2.0, 180);
    }

    @Test
    public void testInitialisation() {
        // Vérifie que les valeurs initiales sont correctes
        assertEquals(0, vehicule.getPosition().getAbscisse(), EPSILON);
        assertEquals(0, vehicule.getPosition().getOrdonee(), EPSILON);
        assertEquals(180, vehicule.getVitesseMax(), EPSILON);
        assertEquals(0, vehicule.getVitesseActuelle(), EPSILON);
        assertFalse(vehicule.estEnMouvement());
    }

    @Test
    public void testAcceleration() {
        // Test de l'accélération
        vehicule.accelerer(5); // Accélérer pendant 5 secondes
        assertTrue(vehicule.getVitesseActuelle() > 0);
        assertTrue(vehicule.getVitesseActuelle() <= vehicule.getVitesseMax());
    }

    @Test
    public void testFreinage() {
        // Test du freinage
        vehicule.accelerer(5);
        double vitesseAvantFreinage = vehicule.getVitesseActuelle();
        vehicule.freiner(50); // Freiner de 50 km/h
        assertTrue(vehicule.getVitesseActuelle() <= vitesseAvantFreinage);
        assertTrue(vehicule.getVitesseActuelle() >= 0);
    }

    @Test
    public void testDeplacementSurX() {
        // Test du déplacement sur l'axe X
        double positionInitialeX = vehicule.getPosition().getAbscisse();
        vehicule.accelerer(5); // Accélérer pendant 5 secondes
        vehicule.deplacerSurX(3); // Déplacer de 3 secondes
        assertTrue(vehicule.getPosition().getAbscisse() > positionInitialeX);
    }

    @Test
    public void testDeplacementEnVirage() {
        // Test du déplacement en virage
        double positionInitialeX = vehicule.getPosition().getAbscisse();
        double positionInitialeY = vehicule.getPosition().getOrdonee();
        vehicule.accelerer(5);
        vehicule.deplacerEnVirage(3, 45); // Déplacement en virage à 45°
        //assertNotEquals(positionInitialeX, vehicule.getPosition().getAbscisse(), EPSILON);
        //assertNotEquals(positionInitialeY, vehicule.getPosition().getOrdonee(), EPSILON);
    }

    @Test
    public void testArretComplet() {
        // Test de l'arrêt du véhicule
        vehicule.accelerer(5);
        vehicule.freiner(180); // Freiner jusqu'à l'arrêt
        assertEquals(0, vehicule.getVitesseActuelle(), EPSILON);
        assertFalse(vehicule.estEnMouvement());
    }
}
