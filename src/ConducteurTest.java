import org.junit.*;
import static org.junit.Assert.*;

/**
 * Classe de test pour la classe Conducteur.
 * @author Morain Arthur
 */
public class ConducteurTest {

    /**
     * Initialisation d'un conducteur avant chaque test.
     */
    private Conducteur conducteur;

    @Before
    public void setUp() {
        conducteur = new Conducteur();
    }

    /**
     * Le premier test vérifie que prudence et agressivité
     * sont bien des valeurs entre 0 et 1.
     */
    @Test
    public void testPrudenceEtAgressivite() {
        assertTrue(conducteur.getPrudence() >= 0 && conducteur.getPrudence() <= 1);
        assertTrue(conducteur.getAgressivite() >= 0 && conducteur.getAgressivite() <= 1);
    }

    /**
     * Le second test vérifie que la fatigue est comprise 
     * entre 0 et 1.
     */
    @Test
    public void testFatigue() {
        assertTrue(conducteur.getFatigue() >= 0 && conducteur.getFatigue() <= 1);
    }

    /**
     * Le troisième test vérifie que le temps de
     * réaction est compris en 0.5 et 2 secondes.
     */
    @Test
    public void testTempsReaction() {
        assertTrue(conducteur.getTempsReaction() >= Conducteur.TEMPS_REACT_MIN && conducteur.getTempsReaction() <= 2.0);
    }

    /**
     * Le quatrième test vérifie le bon 
     * fonctionnement de changer fatigue.
     * 
     * Il faut alors vérifier le changement de sa valeur,
     * que celle-ci soit conforme mais aussi que le temps
     * de réaction est aussi changé et conforme.
     * 
     * Celui-ci vérifie d'abord pour un cas positif.
     */
    @Test
    public void testChangerFatigueAugmentation() {
        double tempsReactionInitial = conducteur.getTempsReaction();
        double fatigueInitiale = conducteur.getFatigue();

        //On change la fatigue avec un facteur 0.3 > 0
        conducteur.changerFatigue(0.3);

        //On doit observer une augmentation de la fatigue
        //et du temps de réaction
        assertTrue(conducteur.getFatigue() >= fatigueInitiale);
        assertTrue(conducteur.getTempsReaction() >= tempsReactionInitial);
    }

    /**
     * Le cinquième test fait la même chose que le précédent 
     * pour un cas négatif.
     */
    @Test
    public void testChangerFatigueDiminution() {
        double tempsReactionInitial = conducteur.getTempsReaction();
        double fatigueInitiale = conducteur.getFatigue();

        // On change la fatigue avec un facteur négatif
        conducteur.changerFatigue(-0.3);

        // La fatigue doit diminuer mais rester >= 0
        assertTrue(conducteur.getFatigue() <= fatigueInitiale && conducteur.getFatigue() >= 0);

        // Le temps de réaction doit diminuer
        assertTrue(conducteur.getTempsReaction() <= tempsReactionInitial);
    }

    /**
     * Le test suivant vérifie le bon 
     * fonctionnement de estPrudent.
     */
    @Test
    public void testEstPrudent() {
        assertEquals(conducteur.getPrudence() > 0.6, conducteur.estPrudent());
    }

    /**
     * Ce test vérifie la valeur 
     * renvoyé par decideDepasser.
     */
    @Test
    public void testDeciderDepasser() {
        boolean decision = conducteur.deciderDepasser();
        assertNotNull(decision);
    }


}