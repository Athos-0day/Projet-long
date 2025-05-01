import java.awt.Color;

/** La classe Feu permet de définir les feux de signalisation présent sur la route.
 * 
 * @author Saadi Mohamed et Fatihi Abderrahman
 */

public class Feu implements Signalisation {
    /** Durée entre chaque transition du feu. */
    private double dureeFeu;
    /** Couleur du feu. */
    private Color couleur;
    /** Position du Feu */
    private Position position;

    /** Constructeur de la classe Feu.
     * 
     * @param dureeFeu Durée entre chaque transition du feu.
     * @param couleur
     * @param x
     * @param y
     */
    public Feu(double dureeFeu, Color couleur, double x, double y) {
        this.dureeFeu = dureeFeu;
        this.couleur = couleur;
        this.position = new Position(x, y);
    }

    /** Permet de connaitre la durée du feu */
    public double getDuree() {
        return this.dureeFeu;
    }

    /** Permet de modifier la durée du feu */
    public void setDuree(double dureeFeu) {
        this.dureeFeu = dureeFeu;
    }

    /** Permet de connaitre la couleur du feu */
    public Color getCouleur() {
        return this.couleur;
    }

    /** Permet de modifier la couleur du feu */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /** Permet de connaitre la position du feu */
    public Position getPosition() {
        return this.position;
    }

    /** Permet de modifier la position du feu */
    public void setPosition(double x, double y) {
        this.position.setPosition(x, y);
    }
    
    /**
     * Affiche les informations du Feu.
     * @return Une chaîne de caractères représentant le feu.
     */
    public String toString() {
        return "Feu {\n" +
                "  Durée du Feu: " + this.dureeFeu + " unité\n" +
                "  Couleur: " + this.couleur +
                "  Position: (" + this.position.getAbscisse() + ", " + this.position.getOrdonee() + ")\n" +
                "}";
    }
}
