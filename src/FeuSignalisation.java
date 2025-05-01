/** La classe Feu permet de définir les feux de signalisation présent sur la route.
 * 
 * @author Saadi Mohamed et Fatihi Abderrahman et Arthur Morain
 */

// Importation des bibliothèques nécessaires
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;  // Nécessaire pour le Timer qui gère le changement d'état du feu

// Classe FeuSignalisation qui implémente l'interface Signalisation
public class FeuSignalisation implements Signalisation {

    /** État actuel du feu (ROUGE, VERT, ORANGE). */
    private String etatFeu;     
    /** Durée du feu rouge. */
    private int dureeFeuRouge; 
    /** Durée du feu vert. */       
    private int dureeFeuVert;  
    /** Durée du feu Orange. */       
    private int dureeFeuOrange;   
    /** Temps écoulé depuis le dernier changement d'état. */   
    private int tempsEcoule;   
    /** Timer pour changer l'état du feu à intervalles réguliers. */       
    private Timer timer;              

    /**
     * Constructeur de la classe FeuSignalisation.
     * Ce constructeur permet d'initialiser les durées des feux rouge, vert et orange,
     * ainsi que l'état initial du feu et le compteur de temps.
     *
     * @param dureeFeuRouge La durée du feu rouge en secondes.
     * @param dureeFeuVert La durée du feu vert en secondes.
     * @param dureeFeuOrange La durée du feu orange en secondes.
     */
    public FeuSignalisation(int dureeFeuRouge, int dureeFeuVert, int dureeFeuOrange) {
        // Initialisation des durées et de l'état initial
        this.dureeFeuRouge = dureeFeuRouge;
        this.dureeFeuVert = dureeFeuVert;
        this.dureeFeuOrange = dureeFeuOrange;
        this.etatFeu = "ROUGE";  // L'état initial est le feu rouge
        this.tempsEcoule = 0;    // Initialisation du compteur de temps

        // Création d'un timer qui déclenche l'événement de changement d'état toutes les secondes
        this.timer = new Timer(5000, e -> changerEtatFeu());
    }

    /** Méthode pour démarrer la simulation de signalisation.
     * 
     */
    @Override
    public void demarrer() {
        this.timer.start();  // Démarre le timer qui va gérer les changements d'état
    }

    /** Méthode pour arrêter la simulation de signalisation.
     *
     */
    @Override
    public void arreter() {
        this.timer.stop();  // Arrête le timer et donc la simulation du changement d'état
    }

    /** Méthode pour récupérer l'état actuel du feu
     * 
     */
    @Override
    public String getEtat() {
        return etatFeu;
    }

    /** Méthode pour dessiner la signalisation (le feu de circulation) sur l'écran.
     * 
     */
    @Override
    public void dessiner(Graphics g, int x, int y) {
        // Dessin du support du feu (un rectangle noir représentant le poteau)
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 50, 150);

        // Dessin des différentes lumières en fonction de l'état du feu
        switch (etatFeu) {
            case "ROUGE":
                g.setColor(Color.RED);  // Si le feu est rouge, on dessine un cercle rouge
                g.fillOval(x + 15, y + 30, 20, 20);
                break;
            case "VERT":
                g.setColor(Color.GREEN);  // Si le feu est vert, on dessine un cercle vert
                g.fillOval(x + 15, y + 30, 20, 20);
                break;
            case "ORANGE":
                g.setColor(Color.ORANGE);  // Si le feu est orange, on dessine un cercle orange
                g.fillOval(x + 15, y + 30, 20, 20);
                break;
        }
    }

    /** Méthode qui gère le changement d'état du feu (ROUGE -> VERT -> ORANGE -> ROUGE).
     * 
     */
    private void changerEtatFeu() {
        tempsEcoule++;  // Incrémente le temps écoulé depuis le dernier changement

        switch (etatFeu) {
            case "ROUGE":
                // Si la durée du feu rouge est atteinte, passer au feu vert
                if (tempsEcoule >= dureeFeuRouge) {
                    etatFeu = "VERT";
                    tempsEcoule = 0;  // Réinitialiser le compteur de temps
                }
                break;
            case "VERT":
                // Si la durée du feu vert est atteinte, passer au feu orange
                if (tempsEcoule >= dureeFeuVert) {
                    etatFeu = "ORANGE";
                    tempsEcoule = 0;
                }
                break;
            case "ORANGE":
                // Si la durée du feu orange est atteinte, passer au feu rouge
                if (tempsEcoule >= dureeFeuOrange) {
                    etatFeu = "ROUGE";
                    tempsEcoule = 0;
                }
                break;
        }
    }
}