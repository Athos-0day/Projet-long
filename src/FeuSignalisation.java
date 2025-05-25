/** La classe Feu permet de définir les feux de signalisation présent sur la route.
 * 
 * @author Saadi Mohamed et Fatihi Abderrahman et Arthur Morain et Mohib Alexandre
 */

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.List;

public class FeuSignalisation {
    /** Position du feu de signalisation. */
    private int x, y;
    /** Etat du feu (vert ou rouge) */
    private boolean estVert;
    /** Direction du feu (similaire aux véhicules) */
    private int direction;
    /** Images des deux formes de feu (rouge et vert) */
    private Image imageFeuVert;
    private Image imageFeuRouge;


    // Chemins vers les images des feux
    private static final String IMAGE_FEU_VERT = "images/feu_vert.png";
    private static final String IMAGE_FEU_ROUGE = "images/feu_rouge.png";

    public FeuSignalisation(int x, int y, boolean estVert, int direction) {
        this.x = x;
        this.y = y;
        this.estVert = estVert;
        this.direction = direction;
        // Charger les images des feux
        this.imageFeuVert = new ImageIcon(IMAGE_FEU_VERT).getImage();
        this.imageFeuRouge = new ImageIcon(IMAGE_FEU_ROUGE).getImage();
    }

    public void alterner() {
        this.estVert = !this.estVert;  // Alterne l'état du feu
    }

    // Méthode pour obtenir la position du feu sous forme d'un objet Point
    public Position getPosition() {
        return new Position(this.x, this.y);  // Retourne les coordonnées sous forme de Point
    }

    /** Méthode pour récupérer la direction du feu.
     * @return la valeur de la direction du feu
     */
    public int getDirection() {
        return this.direction;
    }

    /** Méthode permettant de dessiner un feu dans la simulation.
     * 
     * @param g là où on fait le dessin
     */
    public void dessiner(Graphics g) {
        // Taille du feu de signalisation
        int taille = 40;

        // Dessiner l'image du feu en fonction de son état
        if (estVert) {
            if (imageFeuVert != null) {
                g.drawImage(imageFeuVert, x, y, taille, taille, null);
            } else {
                g.setColor(Color.GREEN);
                g.fillOval(x, y, taille, taille);
            }
        } else {
            if (imageFeuRouge != null) {
                g.drawImage(imageFeuRouge, x, y, taille, taille, null);
            } else {
                g.setColor(Color.RED);
                g.fillOval(x, y, taille, taille);
            }
        }
    }

    /** Méthode permettant de vérifier que le feu est au vert.
     * 
     * @return true si le feu est vert
     */
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

    /**
     * Gère l'arrêt des véhicules en fonction des feux de signalisation.
     * Arrête un véhicule s'il est proche d'un feu rouge dans sa direction.
     *
     * @param vehicules la liste des véhicules à mettre à jour
     * @param feux la liste des feux de signalisation présents
     */
    public static void gererFeuxSignalisation(List<Vehicule> vehicules, List<FeuSignalisation> feux) {
        double marge = 20; // Distance de sécurité avant le feu

        for (Vehicule v : vehicules) {
            boolean doitSArreter = false;

            for (FeuSignalisation feu : feux) {
                if (!feu.estVert()) {
                    double[] positionAvant = Vehicule.getPositionAvantVehicule(v);
                    double vx = positionAvant[0];
                    double vy = positionAvant[1];
                    double fx = feu.getPosition().getAbscisse();
                    double fy = feu.getPosition().getOrdonee();
                    int directionFeu = feu.getDirection();

                    switch (v.getDirection()) {
                        case 0 -> { if (vy < fy && (fy - vy) <= marge && directionFeu == 3) doitSArreter = true; }
                        case 1 -> { if (vy > fy && (vy - fy) <= marge && directionFeu == 0) doitSArreter = true; }
                        case 2 -> { if (vx < fx && (fx - vx) <= marge && directionFeu == 1) doitSArreter = true; }
                        case 3 -> { if (vx > fx && (vx - fx) <= marge && directionFeu == 2) doitSArreter = true; }
                    }

                    if (doitSArreter) {
                        v.setVitesse(0);
                        break;
                    }
                }
            }

            // Réaccélération si feu passé et vitesse trop faible
            if (!doitSArreter && v.getVitesseActuelle() < GenerateurVehicule.VITESSE_INITIALE) {
                v.setVitesse(GenerateurVehicule.VITESSE_INITIALE);
            }
        }
    }
}
