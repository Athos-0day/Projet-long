/** La classe véhicule permet de définir le comportement d'un véhicule.
 * 
 * @author Arthur Morain
 */

import java.awt.*;

public class Vehicule {
    /** La position du véhicule sur la route. */
    private Position position;
    /** Le conducteur du véhicule. */
    private final Conducteur conducteur;
    /** La longueur du véhicule en mètres. */
    protected final double longueur;
    /** La largeur du véhicule en mètres. */
    protected final double largeur;
    /** La vitesse maximale du véhicule en km/h. */
    private final double vitesseMax;
    /** La vitesse actuelle du véhicule en km/h. */
    private double vitesseActuelle;
    /** L'accélération du véhicule en m/s². */
    private final double acceleration;
    /** L'état du véhicule (true si en mouvement, false si arrêté). */
    private boolean enMouvement;
    /** La direction du véhicule (0 = bas, 1 = haut, 2 = droite, 3 = gauche). */
    protected int direction;

    /**
     * Constructeur de la classe Vehicule.
     * Initialise un véhicule avec une position, un conducteur et ses caractéristiques.
     * @param x La position initiale en X.
     * @param y La position initiale en Y.
     * @param longueur La longueur du véhicule en mètres.
     * @param largeur La largeur du véhicule en mètres.
     * @param vitesseMax La vitesse maximale en km/h.
     */
    public Vehicule(double x, double y, double longueur, double largeur, double vitesseMax) {
        this.position = new Position(x, y);
        this.longueur = longueur;
        this.largeur = largeur;
        this.vitesseMax = vitesseMax;
        this.vitesseActuelle = 0; // Démarre à l'arrêt
        this.acceleration = 2.0; // Valeur par défaut en m/s²
        this.enMouvement = false;
        this.conducteur = new Conducteur(); // Assigne un conducteur aléatoire
        this.direction = 2; // Par défaut, direction vers la droite (0 = bas, 1 = haut, 2 = droite, 3 = gauche)
    }

    /**
     * Récupère la position actuelle du véhicule.
     * @return La Position représentant la position actuelle.
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Récupère la longueur du véhicule.
     * @return La longueur du véhicule en mètres.
     */
    public double getLongueur() {
        return this.longueur;
    }

    /**
     * Récupère la largeur du véhicule.
     * @return La largeur du véhicule en mètres.
     */
    public double getLargeur() {
        return this.largeur;
    }

    /**
     * Récupère la vitesse maximale du véhicule.
     * @return La vitesse maximale en km/h.
     */
    public double getVitesseMax() {
        return this.vitesseMax;
    }

    /**
     * Récupère la vitesse actuelle du véhicule.
     * @return La vitesse actuelle en km/h.
     */
    public double getVitesseActuelle() {
        return this.vitesseActuelle;
    }

    /**
     * Récupère l'accélération actuelle du véhicule.
     * @return L'accélération en m/s².
     */
    public double getAcceleration() {
        return this.acceleration;
    }

    /**
     * Vérifie si le véhicule est en mouvement.
     * @return true si en mouvement, false sinon.
     */
    public boolean estEnMouvement() {
        return this.enMouvement;
    }

    /**
     * Récupère le conducteur du véhicule.
     * @return Le conducteur associé au véhicule.
     */
    public Conducteur getConducteur() {
        return this.conducteur;
    }

    /**
     * Change la vitesse du véhicule, sans dépasser la vitesse maximale du véhicule.
     * @param deltaTemps Temps écoulé en secondes.
     */
    public void accelerer(double deltaTemps) {
        double nouvelleVitesse = this.vitesseActuelle + (acceleration * deltaTemps * 3.6); // Conversion m/s en km/h
        this.vitesseActuelle = Math.min(nouvelleVitesse, vitesseMax);
        this.enMouvement = true;
    }

    /**
     * Déplace le véhicule spécifiquement sur l'axe X.
     * @param deltaTemps Temps écoulé en secondes.
     */
    public void deplacerSurX(double deltaTemps) {
        double deplacement = (vitesseActuelle * 1000 / 3600) * deltaTemps; // Conversion km/h en m/s
        this.position.setAbscisse(this.position.getAbscisse() + deplacement);
    }

    /**
     * Déplace le véhicule spécifiquement sur l'axe Y.
     * @param deltaTemps Temps écoulé en secondes.
     */
    public void deplacerSurY(double deltaTemps) {
        double deplacement = (vitesseActuelle * 1000 / 3600) * deltaTemps; // Conversion km/h en m/s
        this.position.setOrdonee(this.position.getOrdonee() + deplacement);
    }

    /**
     * Freine le véhicule.
     * La vitesse ne peut pas être inférieure à 0 km/h.
     * @param decrement La réduction de vitesse en km/h.
     */
    public void freiner(double decrement) {
        this.vitesseActuelle = Math.max(vitesseActuelle - decrement, 0);
        if (this.vitesseActuelle == 0) {
            this.enMouvement = false;
        }
    }

    /**
     * Déplace le véhicule en fonction de la direction sur l'axe X ou Y.
     * @param deltaTemps Temps écoulé en secondes.
     */
    public void deplacer(double deltaTemps) {
        double deplacement = (vitesseActuelle * 1000 / 3600) * deltaTemps; // Conversion km/h en m/s
        switch (direction) {
            case 0: // Bas
                this.position.setOrdonee(this.position.getOrdonee() + deplacement);
                break;
            case 1: // Haut
                this.position.setOrdonee(this.position.getOrdonee() - deplacement);
                break;
            case 2: // Droite
                this.position.setAbscisse(this.position.getAbscisse() + deplacement);
                break;
            case 3: // Gauche
                this.position.setAbscisse(this.position.getAbscisse() - deplacement);
                break;
        }
    }

    /**
     * Récupère la direction actuelle du véhicule.
     * @return La direction actuelle du véhicule (0 = bas, 1 = haut, 2 = droite, 3 = gauche).
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * Modifie la direction du véhicule.
     * @param direction La nouvelle direction à appliquer (0 = bas, 1 = haut, 2 = droite, 3 = gauche).
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /** 
     * Modifie la vitesse du véhicule.
     * @param vitesse La nouvelle vistesse
     */
    public void setVitesse(double vitesse) {
        this.vitesseActuelle = Math.max(0, vitesse); // Empêche vitesse > 50 ou < 0
    }


    /**
     * Dessine le véhicule à l'écran à partir d'une position centrée, avec une couleur donnée.
     *
     * @param g L'objet Graphics utilisé pour le rendu.
     * @param positionCentrée La position centrée du véhicule sur la voie.
     * @param couleur La couleur utilisée pour dessiner le véhicule.
     */
    public void dessiner(Graphics g, Point positionCentrée, Color couleur) {
        int pixelLongueur = (int) (this.longueur * 10);
        int pixelLargeur = (int) (this.largeur * 10);

        int width, height;
        if (direction == 0 || direction == 1) {
            width = pixelLargeur;
            height = pixelLongueur;
        } else {
            width = pixelLongueur;
            height = pixelLargeur;
        }

        // Ombre
        g.setColor(new Color(0, 0, 0, 50));
        g.fillRect(positionCentrée.x + 5, positionCentrée.y + 5, width, height);

        // Véhicule
        g.setColor(couleur);
        g.fillRect(positionCentrée.x, positionCentrée.y, width, height);
    }

    /**
     * Calcule la position de l'avant du véhicule selon sa direction. (en terme de pixels)
     * @param v Le véhicule concerné.
     * @return Un tableau [x, y] correspondant à la position avant du véhicule.
     */
    public static double[] getPositionAvantVehicule(Vehicule v) {
        double x = v.getPosition().getAbscisse();
        double y = v.getPosition().getOrdonee();
        double demiLongueur = v.getLongueur() / 2;

        switch (v.getDirection()) {
            case 0: // Bas
                return new double[]{x, y + demiLongueur};
            case 1: // Haut
                return new double[]{x, y - demiLongueur};
            case 2: // Droite
                return new double[]{x + demiLongueur , y};
            case 3: // Gauche
                return new double[]{x - demiLongueur  , y};
            default:
                return new double[]{x, y};
        }
    }

    /**
     * Affiche les informations du véhicule.
     * @return Une chaîne de caractères représentant le véhicule.
     */
    @Override
    public String toString() {
        return "Véhicule {\n" +
                "  Position: (" + this.position.getAbscisse() + ", " + this.position.getOrdonee() + ")\n" +
                "  Longueur: " + longueur + "m\n" +
                "  Largeur: " + largeur + "m\n" +
                "  Vitesse Max: " + vitesseMax + " km/h\n" +
                "  Vitesse Actuelle: " + vitesseActuelle + " km/h\n" +
                "  Accélération: " + acceleration + " m/s²\n" +
                "  En mouvement: " + (enMouvement ? "Oui" : "Non") + "\n" +
                "  Direction: " + (direction == 0 ? "Bas" : direction == 1 ? "Haut" : direction == 2 ? "Droite" : "Gauche") + "\n" +
                "  Conducteur: " + this.conducteur.toString() + "\n" +
                "}";
    }
}
