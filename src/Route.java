/**
 * La classe Route représente une route avec ses caractéristiques principales.
 * Elle est définie par une position de début, une position de fin et une éventuelle signalisation.
 * 
 * @author Saadi Mohamed et Fatihi Abderrahman
 */
public class Route {
    /** Position du début de la route */
    private Position positionDebut;
    /** Position de la fin de la route */
    private Position positionFin;
    /** Signalisation qui peut être présente sur la route. */
    private Signalisation signalisation;

    /**
     * Constructeur de Route.
     * @param positionDebut Position du début de la route.
     * @param positionFin Position de la fin de la route.
     * @param signalisation Signalisation qui peut être présente sur la route.
     */
    public Route(Position positionDebut, Position positionFin, Signalisation signalisation) {
        this.positionDebut = positionDebut;
        this.positionFin = positionFin;
        this.signalisation = signalisation;
    }

    /** Retourne la position du début de la route. */
    public Position getPositionDebut() {
        return this.positionDebut;
    }

    /** Modifie la position du début de la route. */
    public void setPositionDebut(Position positionDebut) {
        this.positionDebut = positionDebut;
    }

    /** Retourne la position de la fin de la route. */
    public Position getPositionFin() {
        return this.positionFin;
    }

    /** Modifie la position de la fin de la route. */
    public void setPositionFin(Position positionFin) {
        this.positionFin = positionFin;
    }

    /** Retourne la signalisation présente sur la route. */
    public Signalisation getSignalisation() {
        return this.signalisation;
    }

    /** Modifie la signalisation sur la route. */
    public void setSignalisation(Signalisation signalisation) {
        this.signalisation = signalisation;
    }

    /**
     * Calcule la longueur de la route en mètres.
     * @return La distance entre le début et la fin de la route.
     */
    public double calculerLongueur() {
        return this.positionDebut.calculerDistance(this.positionFin);
    }

    /**
     * Affiche les informations de la route.
     * @return Une chaîne de caractères représentant la route.
     */
    @Override
    public String toString() {
        return "Route {\n" +
               "  Position du début de la route: (" + this.positionDebut.getAbscisse() +
               ", " + this.positionDebut.getOrdonee() + ")\n" +
               "  Position de la fin de la route: (" + this.positionFin.getAbscisse() +
               ", " + this.positionFin.getOrdonee() + ")\n" +
               "  Longueur: " + calculerLongueur() + " mètres\n" +
               "  Signalisation sur la route: " + this.signalisation +
               "\n}";
    }
}
