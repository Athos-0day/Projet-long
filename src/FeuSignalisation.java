/** La classe Feu permet de définir les feux de signalisation présent sur la route.
 * 
 * @author Saadi Mohamed et Fatihi Abderrahman et Arthur Morain
 */


// Classe FeuSignalisation qui implémente l'interface Signalisation
import java.awt.Color;
import java.awt.Graphics;

public class FeuSignalisation {
    /** Position du feu de signalisation. */
    private int x, y;
    /** Etat du feu (vert ou rouge) */
    private boolean estVert;
    /**Direction du feu (similaire aux véhicules) */
    private int direction;

    public FeuSignalisation(int x, int y, boolean estVert, int direction) {
        this.x = x;
        this.y = y;
        this.estVert = estVert;
        this.direction = direction;
    }

    public void alterner() {
        this.estVert = !this.estVert;  // Alterne l'état du feu
    }

    // Méthode pour obtenir la position du feu sous forme d'un objet Point
    public Position getPosition() {
        return new Position(this.x,this.y);  // Retourne les coordonnées sous forme de Point
    }

    /** Méthode pour récupérer la direction du feu.
     * @return la valeur de la direction du feu
     */
    public int getDirection() {
        return this.direction;
    }

    public void dessiner(Graphics g) {
        // Taille du feu de signalisation
        int taille = 20;
        
        // Si le feu est vert, la couleur est verte, sinon rouge
        if (estVert) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }

        // Dessiner le feu comme un cercle
        g.fillOval(x, y, taille, taille);  // Dessiner un cercle pour le feu de signalisation
    }

    public boolean estVert() {
        return estVert;
    }

    /** Calcule la position du point d'arrêt en fonction de la direction.
     * @return la position du point d'arrêt 
     */
    public Position getPointArret() {
        int offset = 20; // distance devant le feu
        switch (direction) {
            case 0: return new Position(x + 10, y + offset); // bas
            case 1: return new Position(x + 10, y - offset); // haut
            case 2: return new Position(x - offset, y + 10); // gauche
            case 3: return new Position(x + offset, y + 10); // droite
            default: return new Position(x, y);
        }
    }
}