/**
 * La classe PoidsLourds représente un véhicule de type camion, qui est une classe fille de vehicule.
 * Elle définit un constructeur qui utilise des constantes associées au type de véhicules.
 * 
 * @author Arthur Morain
 */

public class PoidsLourds extends Vehicule {

    // Définition des constantes spécifiques pour les poids-lourds
    private static final double LONGUEUR_POIDS_LOURDS = 12.0;  // Longueur en mètres
    private static final double LARGEUR_POIDS_LOURDS = 2.5;    // Largeur en mètres
    private static final double VITESSE_MAX_POIDS_LOURDS = 120; // Vitesse maximale en km/h

    /**
     * Constructeur de la classe PoidsLourds.
     * @param x La position initiale en X.
     * @param y La position initiale en Y.
     */
    public PoidsLourds(double x, double y) {
        // Appel du constructeur de la classe parente Vehicule avec les valeurs fixées
        super(x, y, LONGUEUR_POIDS_LOURDS, LARGEUR_POIDS_LOURDS, VITESSE_MAX_POIDS_LOURDS);
    }
}
