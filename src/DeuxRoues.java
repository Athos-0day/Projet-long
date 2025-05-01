/**
 * La classe DeuxRoues représente un véhicule de type moto ou scooter, c'est une classe fille de la classe
 * vehicule qui utilise des constantes associées aux véhicules dans son constructeur.
 *
 * @author Arthur Morain
 */
public class DeuxRoues extends Vehicule {

    // Définition des constantes spécifiques pour les deux-roues
    private static final double LONGUEUR_DEUX_ROUES = 2.2;  // Longueur en mètres
    private static final double LARGEUR_DEUX_ROUES = 0.8;   // Largeur en mètres
    private static final double VITESSE_MAX_DEUX_ROUES = 180; // Vitesse maximale en km/h

    /**
     * Constructeur de la classe DeuxRoues.
     * @param x La position initiale en X.
     * @param y La position initiale en Y.
     */
    public DeuxRoues(double x, double y) {
        // Appel du constructeur de la classe parente Vehicule avec les valeurs fixés
        super(x, y, LONGUEUR_DEUX_ROUES, LARGEUR_DEUX_ROUES, VITESSE_MAX_DEUX_ROUES);
    }
}