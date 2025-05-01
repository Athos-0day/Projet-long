import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * La classe PanneauSimulation est responsable de l'affichage graphique de la simulation.
 * Elle dessine la route ainsi que les véhicules qui s'y déplacent.
 *
 * Chaque véhicule est représenté par un rectangle bleu dont les dimensions sont
 * proportionnelles à sa taille réelle.
 *
 * @author Arthur Morain
 */
public class PanneauSimulation extends JPanel {
    /** Liste des véhicules à afficher dans la simulation. */
    private List<Vehicule> vehicules;

    /**
     * Constructeur du panneau de simulation.
     * @param vehicules Liste des véhicules à afficher.
     */
    public PanneauSimulation(List<Vehicule> vehicules) {
        this.vehicules = vehicules;
        setPreferredSize(new Dimension(800, 600)); // Taille de la fenêtre
    }

    /**
     * Méthode d'affichage appelée automatiquement pour redessiner le panneau.
     * @param g Contexte graphique utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessin de la route (une bande grise horizontale)
        g.setColor(Color.GRAY);
        g.fillRect(0, 250, 800, 100);

        // Dessin de chaque véhicule
        for (Vehicule v : vehicules) {
            int x = (int) v.getPosition().getAbscisse();
            int y = (int) v.getPosition().getOrdonee();

            g.setColor(Color.BLUE);
            g.fillRect(x, y, (int) (v.getLongueur() * 10), (int) (v.getLargeur() * 10));
        }
    }
}
