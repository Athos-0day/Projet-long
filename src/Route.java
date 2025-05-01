/** La classe Route représente une route avec ses caractéristiques principales.
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

    /** Constructeur de Route. 
     * @param positionDebut Position du début de la route.
     * @param positionFin Position de la fin de la route.
     * @param signalisation Signalisation qui peut être présente sur la route.
    */
    public Route(Position positionDebut, Position positionFin,Signalisation signalisation) {
        this.positionDebut = positionDebut;
        this.positionFin = positionFin;
        this.signalisation = signalisation;
    }

    /** Permet de connaitre la position du début de la route. */
    public Position getPositionDebut() {
        return this.positionDebut;
    }

    /** Permet de modifier la position du début de la route. */
    public void setPostionDebut(Position positionDebut) {
        this.positionDebut = positionDebut;
    }

    /** Permet de connaitre la position de la fin de la route. */
    public Position getPositionFin() {
        return this.positionFin;
    }

    /** Permet de modifier la position de la fin de la route. */
    public void setPostionDebut(Position positionFin) {
        this.positionFin = positionFin;
    }

    /** Permet de connaitre la signalisation sur la route */
    public Signalisation getSignalisation() {
        return this.signalisation;
    }

    /** Permet de modifier la signalisation sur la route. */
    public void setSignalisation(Signalisation signalisation) {
        this.signalisation = signalisation;
    }

    /**
     * Affiche les informations de la route.
     * @return Une chaîne de caractères représentant la route.
     */
    public String toString() {
        return "Route {\n" +
                "  Position du début de la route: " + this.positionDebut.getAbscisse() +
                 ", " + this.positionDebut.getOrdonee() + ")\n" +
                "  Position de la fin de la route: (" + this.positionFin.getAbscisse() +
                 ", " + this.positionFin.getOrdonee() + ")\n" +
                " Signalisation sur la route: " + this.signalisation +
                "}";
    }
}
