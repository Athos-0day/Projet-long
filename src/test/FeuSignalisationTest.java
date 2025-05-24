/**
 * Application simple pour afficher le feu de signalisation.
 * 
 *  @author Arthur Morain
 */

import java.awt.*;
import javax.swing.*;

public class FeuSignalisationTest extends JFrame {

    private FeuSignalisation feu;

    public FeuSignalisationTest() {
        // Initialisation de la fenêtre
        setTitle("Test Feu Signalisation");
        setSize(200, 300);  // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création d'un feu de signalisation avec des durées de 5 secondes pour chaque état
        feu = new FeuSignalisation(5, 5, 5);  // 5 secondes pour chaque état (ROUGE, VERT, ORANGE)

        // Démarrage du feu
        feu.demarrer();

        // Ajout d'un composant pour dessiner le feu
        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dessin du feu de signalisation à la position (50, 50)
                feu.dessiner(g, 50, 50);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Lancer l'application
        SwingUtilities.invokeLater(FeuSignalisationTest::new);
    }
}
