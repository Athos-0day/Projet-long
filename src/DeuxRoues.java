/**
 * La classe DeuxRoues représente un véhicule de type moto ou scooter, c'est une classe fille de la classe
 * vehicule qui utilise des constantes associées aux véhicules dans son constructeur.
 *
 * @author Arthur Morain et Mohib Alexandre
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import java.util.Random;

public class DeuxRoues extends Vehicule {

    // Définition des constantes spécifiques pour les deux-roues
    private static final double LONGUEUR_DEUX_ROUES = 2.2;  // Longueur en mètres
    private static final double LARGEUR_DEUX_ROUES = 0.8;   // Largeur en mètres
    private static final double VITESSE_MAX_DEUX_ROUES = 180; // Vitesse maximale en km/h
    private Image image; // Image de notre voiture

    // Chemins vers les images des deux-roues
    private static final String[] IMAGE_PATHS = {
        "ressources/images/moto_bleu.png",
        "ressources/images/moto_vert.png",
        "ressources/images/moto_rouge.png"
    };

    /**
     * Constructeur de la classe DeuxRoues.
     * @param x La position initiale en X.
     * @param y La position initiale en Y.
     */
    public DeuxRoues(double x, double y) {
        // Appel du constructeur de la classe parente Vehicule avec les valeurs fixés
        super(x, y, LONGUEUR_DEUX_ROUES, LARGEUR_DEUX_ROUES, VITESSE_MAX_DEUX_ROUES);
        setRandomImage(); // Sélectionner une image aléatoire
    }

    /**
     * Génére une image aléatoire parmis les 3 images de voiture (rouge,vert,bleu)
     */
    private void setRandomImage() {
        Random random = new Random();
        int index = random.nextInt(IMAGE_PATHS.length);
        setImage(IMAGE_PATHS[index]);
    }

    /**
     * @param imagepath Le chemin de notre image
     */
    public void setImage(String imagePath) {
        try {
            this.image = new ImageIcon(imagePath).getImage();
            if (this.image == null) {
                System.err.println("Erreur : Impossible de charger l'image à partir de " + imagePath);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
        }
    }

    /**
     * Dessine le véhicule à l'écran à partir d'une position centrée, avec une couleur donnée.
     *
     * @param g L'objet Graphics utilisé pour le rendu.
     * @param positionCentrée La position centrée du véhicule sur la voie.
     * @param couleur La couleur utilisée pour dessiner le véhicule.
     */
    @Override
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

        // Dessiner l'image du deux-roues avec une taille augmentée et une rotation
        if (image != null) {
            int imageWidth = width * 8; // Augmenter la largeur de l'image
            int imageHeight = height * 8; // Augmenter la hauteur de l'image
            // Ajustez les coordonnées pour centrer l'image sur la position de l'ombre
            int imageX = positionCentrée.x - (imageWidth - width) / 2;
            int imageY = positionCentrée.y - (imageHeight - height) / 2;

            Graphics2D g2d = (Graphics2D) g.create();
            // Déplacer l'origine au centre de l'image
            g2d.translate(imageX + imageWidth / 2, imageY + imageHeight / 2);
            // Appliquer la rotation en fonction de la direction
            switch (direction) {
                case 0: // Bas
                    g2d.rotate(Math.PI); // Rotation de 180 degrés pour le bas
                    break;
                case 1: // Haut
                    g2d.rotate(0); // Aucune rotation pour le haut
                    break;
                case 2: // Droite
                    g2d.rotate(Math.PI / 2); // Rotation de 90 degrés pour la droite
                    break;
                case 3: // Gauche
                    g2d.rotate(-Math.PI / 2); // Rotation de -90 degrés pour la gauche
                    break;
            }
            // Inverser les dimensions pour maintenir les proportions lors de la rotation
            if (direction == 2 || direction == 3) {
                int temp = imageWidth;
                imageWidth = imageHeight;
                imageHeight = temp;
            }
            // Dessiner l'image centrée et tournée
            g2d.drawImage(image, -imageWidth / 2, -imageHeight / 2, imageWidth, imageHeight, null);
            g2d.dispose();
        } else {
            // Dessiner un rectangle par défaut si aucune image n'est définie
            g.setColor(couleur);
            g.fillRect(positionCentrée.x, positionCentrée.y, width, height);
        }
    }
}
