/**
 * Classe de test pour la classe Voiture.
 * Ce test vérifie l'initialisation des propriétés du constructeur.
 * 
 * @author Arthur Morains
 */

import org.junit.*;
import static org.junit.Assert.*;

public class VoitureTest {

    private Voiture voiture;
    public static final double EPSILON = 1e-6;
	// précision pour la comparaison entre réels.

    /**
     * Méthode exécutée avant chaque test.
     * Crée une nouvelle instance de Voiture pour chaque test.
     */
    @Before
    public void setUp() {
        // Initialisation d'une voiture avec une position (0, 0)
        voiture = new Voiture(0, 0);
    }

    /**
     * Teste le constructeur de la voiture pour vérifier les valeurs des dimensions et de la vitesse max.
     */
    @Test
    public void testConstructeur() {
        // Vérifie que la longueur est correcte
        assertEquals(4.0, voiture.getLongueur(), EPSILON);

        // Vérifie que la largeur est correcte
        assertEquals(1.8, voiture.getLargeur(), EPSILON);

        // Vérifie que la vitesse maximale est correcte
        assertEquals(200, voiture.getVitesseMax(), EPSILON);
        
        // Vérifie que la position initiale est correcte
        assertEquals(0, voiture.getPosition().getAbscisse(), EPSILON);
        assertEquals(0, voiture.getPosition().getOrdonee(), EPSILON);
        
        // Vérifie que la vitesse initiale est 0 et que la voiture n'est pas en mouvement
        assertEquals(0, voiture.getVitesseActuelle(), EPSILON);
        assertFalse(voiture.estEnMouvement());
    }
}
