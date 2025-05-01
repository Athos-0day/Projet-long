/** La classe Signalisation permet de définir tous les panneaux et les feux qui
 * peuvent être présent sur une route.
 * 
 * @author Saadi Mohamed et Fatihi Abderrahman
 */
import java.awt.Graphics;

public interface Signalisation {

    // Méthode pour démarrer la signalisation (ex. démarrer un Timer pour changer l'état)
    void demarrer();

    // Méthode pour arrêter la signalisation (ex. arrêter un Timer)
    void arreter();

    // Méthode pour obtenir l'état actuel de la signalisation
    String getEtat();

    // Méthode pour dessiner la signalisation à l'écran
    void dessiner(Graphics g, int x, int y);
}