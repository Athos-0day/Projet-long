/**
 * La classe Position représente une position en deux dimensions (x, y) sur une route.
 * Elle permet de manipuler la position des véhicules de manière plus structurée.
 * Indépendamment de la classe véhicule.
 *
 * @author Morain Arthur
 */
public class Position {
    /** Coordonnée en X (mètres). */
    private double abscisse;
    /** Coordonnée en Y (mètres). */
    private double ordonnee;

    /**
     * Constructeur initialisant la position aux coordonnées spécifiées.
     * @param x Position en X en mètres.
     * @param y Position en Y en mètres.
     */
    public Position(double x, double y) {
        this.abscisse = x;
        this.ordonnee = y;
    }

    /**
     * Obtient la coordonnée X.
     * @return La position en X.
     */
    public double getAbscisse() {
        return this.abscisse;
    }

    /**
     * Obtient la coordonnée Y.
     * @return La position en Y.
     */
    public double getOrdonee() {
        return this.ordonnee;
    }

    /**
     * Modifie la coordonnée X.
     * @param x Nouvelle position en X.
     */
    public void setAbscisse(double x) {
        this.abscisse = x;
    }

    /**
     * Modifie la coordonnée Y.
     * @param y Nouvelle position en Y.
     */
    public void setOrdonee(double y) {
        this.ordonnee = y;
    }

    /**
     * Modifie la position en une nouvelle coordonnée (x, y).
     * @param x Nouvelle coordonnée X.
     * @param y Nouvelle coordonnée Y.
     */
    public void setPosition(double x, double y) {
        this.abscisse = x;
        this.ordonnee = y;
    }

    /**
     * Permet de déplacer une position de dx et dy
     * @param dx le déplacement selon x
     * @param dy le déplacement selon y
     */
    public void deplacer(double dx, double dy) {
        this.abscisse = this.getAbscisse() + dx;
        this.ordonnee = this.getOrdonee() + dy;
    }

    /**
     * Calcule la distance entre cette position et une autre.
     * @param autre La position avec laquelle calculer la distance.
     * @return La distance entre les deux positions.
     */
    public double calculerDistance(Position autre) {
        return Math.sqrt(Math.pow(this.abscisse - autre.getAbscisse(), 2) + Math.pow(this.ordonnee - autre.getOrdonee(), 2));
    }

    /**
     * Affiche la position sous forme de chaîne de caractères.
     * @return Une représentation textuelle de la position.
     */
    @Override
    public String toString() {
        return "Position { x: " + this.abscisse + ", y: " + this.ordonnee + " }";
    }
}
