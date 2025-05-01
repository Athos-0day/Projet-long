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
    /** Signalisation présente sur la route, peut être null */
    private Signalisation signalisation;

    /**
     * Constructeur sans signalisation (initialisée à null).
     * @param positionDebut Position du début de la route.
     * @param positionFin Position de la fin de la route.
     */
    public Route(Position positionDebut, Position positionFin) {
        this.positionDebut = positionDebut;
        this.positionFin = positionFin;
        this.signalisation = null;
    }

    /**
     * Constructeur complet avec signalisation.
     * @param positionDebut Position du début.
     * @param positionFin Position de fin.
     * @param signalisation Signalisation présente.
     */
    public Route(Position positionDebut, Position positionFin, Signalisation signalisation) {
        this.positionDebut = positionDebut;
        this.positionFin = positionFin;
        this.signalisation = signalisation;
    }

    public Position getPositionDebut() {
        return positionDebut;
    }

    public void setPositionDebut(Position positionDebut) {
        this.positionDebut = positionDebut;
    }

    public Position getPositionFin() {
        return positionFin;
    }

    public void setPositionFin(Position positionFin) {
        this.positionFin = positionFin;
    }

    public Signalisation getSignalisation() {
        return signalisation;
    }

    public void setSignalisation(Signalisation signalisation) {
        this.signalisation = signalisation;
    }

    @Override
    public String toString() {
        return "Route {\n" +
                "  Début: (" + positionDebut.getAbscisse() + ", " + positionDebut.getOrdonee() + ")\n" +
                "  Fin:   (" + positionFin.getAbscisse() + ", " + positionFin.getOrdonee() + ")\n" +
                "  Signalisation: " + (signalisation != null ? signalisation.toString() : "Aucune") + "\n" +
                "}";
    }
}