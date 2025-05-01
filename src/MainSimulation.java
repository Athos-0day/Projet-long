import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe MainSimulation initialise et exécute la simulation graphique de circulation de véhicules.
 * Elle utilise un Timer pour mettre à jour régulièrement la position des véhicules et rafraîchir l'affichage.
 *
 * @author Arthur Morain
 */
public class MainSimulation {
    public static void main(String[] args) {
        // Création de quelques véhicules
        List<Vehicule> vehicules = new ArrayList<>();
        vehicules.add(new Voiture(0, 270));        // Voiture placée sur la route
        vehicules.add(new DeuxRoues(50, 280));     // Deux-roues à une autre position
        vehicules.add(new PoidsLourds(100, 260));  // Poids-lourds en haut de la route

        // Création de la fenêtre principale
        JFrame frame = new JFrame("Simulation de Véhicules");
        PanneauSimulation panneau = new PanneauSimulation(vehicules);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panneau);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Création d'un Timer pour faire bouger les véhicules régulièrement
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vehicule v : vehicules) {
                    v.accelerer(0.1);       // Accélération simulée
                    v.deplacerSurX(0.1);    // Déplacement vers la droite
                }
                panneau.repaint();          // Redessiner les véhicules
            }
        });

        timer.start(); // Démarrage du timer
    }
}