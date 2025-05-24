/**
 * Classe de test de la classe PoidsLourds
 * 
 * @author Arthur Morain
 */

import org.junit.*;
import static org.junit.Assert.*;

public class PoidsLourdsTest {

    private PoidsLourds poidsLourds;
    public static final double EPSILON = 1e-6;
	// précision pour la comparaison entre réels.

    /**
     * Méthode exécutée avant chaque test.
     * Crée une nouvelle instance de PoidsLourds pour chaque test.
     */
    @Before
    public void setUp() {
        // Initialisation d'un PoidsLourds avec une position (0, 0)
        poidsLourds = new PoidsLourds(0, 0);
    }

    /**
     * Teste le constructeur de la classe PoidsLourds pour vérifier les valeurs des dimensions et de la vitesse max.
     */
    @Test
    public void testConstructeur() {
        // Vérifie que la longueur est correcte
        assertEquals(12.0, poidsLourds.getLongueur(), EPSILON);

        // Vérifie que la largeur est correcte
        assertEquals(2.5, poidsLourds.getLargeur(), EPSILON);

        // Vérifie que la vitesse maximale est correcte
        assertEquals(120, poidsLourds.getVitesseMax(), EPSILON);
        
        // Vérifie que la position initiale bien nulle
        assertEquals(0, poidsLourds.getPosition().getAbscisse(), EPSILON);
        assertEquals(0, poidsLourds.getPosition().getOrdonee(), EPSILON);
        
        // Vérifie que la vitesse initiale est 0 et que le véhicule est immobile
        assertEquals(0, poidsLourds.getVitesseActuelle(), EPSILON);
        assertFalse(poidsLourds.estEnMouvement());
    }
}