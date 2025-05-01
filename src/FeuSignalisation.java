/** La classe Feu permet de définir les feux de signalisation présent sur la route.
 * 
 * @author Saadi Mohamed et Fatihi Abderrahman et Arthur Morain
 */


// Classe FeuSignalisation qui implémente l'interface Signalisation
import java.awt.Color;
import java.awt.Graphics;

public class FeuSignalisation {
    private int x, y;  // Position du feu de signalisation
    private boolean estVert;  // Etat du feu (vert ou rouge)

    public FeuSignalisation(int x, int y, boolean estVert) {
        this.x = x;
        this.y = y;
        this.estVert = estVert;
    }

    public void alterner() {
        this.estVert = !this.estVert;  // Alterne l'état du feu
    }

    // Méthode pour obtenir la position du feu sous forme d'un objet Point
    public Position getPosition() {
        return new Position(this.x,this.y);  // Retourne les coordonnées sous forme de Point
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
}