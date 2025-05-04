/**
 * La classe Voiture est une classe fille de la classe Vehicule. Une voiture a le même
 * fonctionnement qu'un véhicule mais ses dimensions et sa vitesse maximale sont définies 
 * par des constantes.
 *
 * @author Morain Arthur
 */
public class Voiture extends Vehicule {
    
    // Constantes définissant les dimensions et la vitesse maximale d'une voiture
    private static final double LONGUEUR = 4.0;   // Longueur standard d'une voiture en mètres
    private static final double LARGEUR = 1.8;    // Largeur standard d'une voiture en mètres
    private static final double VITESSE_MAX = 200; // Vitesse maximale standard d'une voiture en km/h

    /**
     * Constructeur de la classe Voiture.
     * Initialise une voiture avec une position (x, y) et des dimensions fixes.
     * @param x La position initiale en X.
     * @param y La position initiale en Y.
     */
    public Voiture(double x, double y) {
        super(x, y, LONGUEUR, LARGEUR, VITESSE_MAX);  // Appel du constructeur de la classe parente
    }
    
    /**
     * Redéfinition de la méthode toString() pour afficher les informations spécifiques à la Voiture.
     * @return Une chaîne de caractères représentant la voiture.
     */
    @Override
    public String toString() {
        // On réutilise la méthode toString de la classe parente et on ajoute des détails spécifiques à la voiture
        return "Voiture {\n" +
                super.toString() + // Réutilisation de la méthode toString de la classe parent (Vehicule)
                "}";
    }
}
