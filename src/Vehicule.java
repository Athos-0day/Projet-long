/**
 * La classe Vehicule représente un véhicule sur la route avec ses caractéristiques principales.
 * Elle servira de superclasse pour différents types de véhicules (motos, voitures, poids-lourds).
 *
 *
 * Cette classe gère aussi l'accélération, le freinage et le déplacement.
 * 
 * @author Morain Arthur
 */
public class Vehicule {
    /** La position du véhicule sur la route. */
    private Position position;
    /** Le conducteur du véhicule. */
    private final Conducteur conducteur;
    /** La longueur du véhicule en mètres. */
    private final double longueur;
    /** La largeur du véhicule en mètres. */
    private final double largeur;
    /** La vitesse maximale du véhicule en km/h. */
    private final double vitesseMax;
    /** La vitesse actuelle du véhicule en km/h. */
    private double vitesseActuelle;
    /** L'accélération du véhicule en m/s². */
    private final double acceleration;
    /** L'état du véhicule (true si en mouvement, false si arrêté). */
    private boolean enMouvement;

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
     * Déplace le véhicule en ligne droite sur l'axe X.
     * @param deltaTemps Temps écoulé en secondes.
     */
    public void deplacerSurX(double deltaTemps) {
        double deplacement = (vitesseActuelle * 1000 / 3600) * deltaTemps; // Conversion km/h en m/s
        this.position.setAbscisse(this.position.getAbscisse() + deplacement);
    }

    /**
     * Déplace le véhicule en ligne droite sur l'axe Y.
     * @param deltaTemps Temps écoulé en secondes.
     */
    public void deplacerSurY(double deltaTemps) {
        double deplacement = (vitesseActuelle * 1000 / 3600) * deltaTemps;
        this.position.setOrdonee(this.position.getOrdonee() + deplacement);
    }

    /**
     * Déplace le véhicule en effectuant un virage selon un angle donné.
     * Cette méthode suffit d'alleurs à remplacer les deux méthodes précédentes.
     * @param deltaTemps Temps écoulé en secondes.
     * @param angle Angle du virage en degrés (positif vers la droite, négatif vers la gauche).
     */
    public void deplacerEnVirage(double deltaTemps, double angle) {
        double deplacement = (vitesseActuelle * 1000 / 3600) * deltaTemps;
        double angleRad = Math.toRadians(angle); // Conversion en radians

        double deltaX = deplacement * Math.cos(angleRad);
        double deltaY = deplacement * Math.sin(angleRad);

        this.position.setPosition(this.position.getAbscisse() + deltaX, this.position.getOrdonee() + deltaY);

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
                "  Conducteur: " + this.conducteur.toString() + "\n" +
                "}";
    }
}








