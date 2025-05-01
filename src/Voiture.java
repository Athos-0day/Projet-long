/**
 *La classe Voiture est une classe fille de la classe Vehicule. Une voiture à le même
 *fonctionnement que le véhicule mais sa dimension et sa vitesse maximale sont définies 
 *par des constantes.
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
     * @param x La position initiale en X.
     * @param y La position initiale en Y.
     */
    public Voiture(double x, double y) {
        super(x, y, LONGUEUR, LARGEUR, VITESSE_MAX);
    }
    
    @Override
    public String toString() {
        return "Voiture {\n" +
                "  Position: (" + this.getPosition().getAbscisse() + ", " + this.getPosition().getOrdonee() + ")\n" +
                "  Longueur: " + LONGUEUR + "m\n" +
                "  Largeur: " + LARGEUR + "m\n" +
                "  Vitesse Max: " + VITESSE_MAX + " km/h\n" +
                "  Vitesse Actuelle: " + this.getVitesseActuelle() + " km/h\n" +
                "  Accélération: " + this.getAcceleration() + " m/s²\n" +
                "  En mouvement: " + (this.estEnMouvement() ? "Oui" : "Non") + "\n" +
                "  Conducteur: " + this.getConducteur().toString() + "\n" +
                "}";
    }
}