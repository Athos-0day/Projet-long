/**
 * Classe de test pour la classe DeuxRoues.
 * vérifie le bon fonctionnement du constructeur.
 * 
 * @author Arthur Morain
 */

import org.junit.*;
import static org.junit.Assert.*;

public class DeuxRouesTest {

    private DeuxRoues deuxRoues;
    public static final double EPSILON = 1e-6;
	// précision pour la comparaison entre réels.

    /**
     * Méthode exécutée avant chaque test.
     * Crée une nouvelle instance de DeuxRoues pour chaque test.
     */
    @Before
    public void setUp() {
        // Initialisation d'un DeuxRoues avec une position (0, 0)
        deuxRoues = new DeuxRoues(0, 0);
    }

    /**
     * Teste le constructeur de la classe DeuxRoues pour vérifier les valeurs des dimensions et de la vitesse max.
     */
    @Test
    public void testConstructeur() {
        // Vérifie que la longueur est correcte
        assertEquals(2.2, deuxRoues.getLongueur(), EPSILON);

        // Vérifie que la largeur est correcte
        assertEquals(0.8, deuxRoues.getLargeur(), EPSILON);

        // Vérifie que la vitesse maximale est correcte
        assertEquals(180, deuxRoues.getVitesseMax(), EPSILON);
        
        // Vérifie que la position initiale est correcte
        assertEquals(0, deuxRoues.getPosition().getAbscisse(), EPSILON);
        assertEquals(0, deuxRoues.getPosition().getOrdonee(), EPSILON);
        
        // Vérifie que la vitesse initiale est 0 et que le véhicule n'est pas en mouvement
        assertEquals(0, deuxRoues.getVitesseActuelle(), EPSILON);
        assertFalse(deuxRoues.estEnMouvement());
    }
}