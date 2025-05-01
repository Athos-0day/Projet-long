import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class PanneauSimulation extends JPanel {
    private List<Vehicule> vehicules;
    private List<FeuSignalisation> feux;  // Liste des feux de signalisation
    private Timer feuTimer;

    private static final int ROUTE_WIDTH = 100;
    private static final int TROTTOIR_WIDTH = 20;
    private static final int LIGNE_LONGUEUR = 30;
    private static final int LIGNE_ESPACE = 20;

    public PanneauSimulation(List<Vehicule> vehicules) {
        this.vehicules = vehicules;
        setPreferredSize(new Dimension(800, 800));
        setBackground(new Color(34, 139, 34)); // Vert herbe

        // Timer pour changer l'état des feux (rouge/vert) toutes les secondes
        feuTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Alterner l'état de chaque feu
                for (FeuSignalisation feu : feux) {
                    feu.alterner();  // Alterne l'état du feu
                }
                repaint();  // Redessiner après chaque changement
            }
        });

        // Initialisation des feux de signalisation après la création du panneau
        // (doit être après setPreferredSize())
        feuTimer.start(); // Démarrer le cycle des feux
    }

    // Méthode appelée pour dessiner l'ensemble du panneau
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();
        int rw = ROUTE_WIDTH;
        int tw = TROTTOIR_WIDTH;
        
        // Calcul des coordonnées pour positionner la route au centre du panneau
        int roadX = (w - rw) / 2;
        int roadY = (h - rw) / 2;

        // === TROTTOIRS ===
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, roadY - tw, w, tw);           // haut
        g.fillRect(0, roadY + rw, w, tw);           // bas
        g.fillRect(roadX - tw, 0, tw, h);           // gauche
        g.fillRect(roadX + rw, 0, tw, h);           // droite

        // === ROUTES ===
        g.setColor(Color.GRAY);
        g.fillRect(0, roadY, w, rw);                // horizontale
        g.fillRect(roadX, 0, rw, h);                // verticale

        // === BORDURES BLANCHES ===
        g.setColor(Color.WHITE);
        // Horizontale gauche
        g.fillRect(0, roadY, roadX + 2, 2); // Allonger un peu la ligne gauche
        g.fillRect(0, roadY + rw - 2, roadX + 2, 2); // Allonger un peu la ligne gauche en bas
        // Horizontale droite
        g.fillRect(roadX + rw - 2, roadY, w - roadX - rw + 2, 2); // Allonger un peu la ligne droite
        g.fillRect(roadX + rw - 2, roadY + rw - 2, w - roadX - rw + 2, 2); // Allonger un peu la ligne droite en bas
        // Verticale haut
        g.fillRect(roadX, 0, 2, roadY + 2); // Allonger la ligne du haut
        g.fillRect(roadX + rw - 2, 0, 2, roadY + 2); // Allonger la ligne du haut côté droit
        // Verticale bas
        g.fillRect(roadX, roadY + rw - 2, 2, h - roadY - rw + 2); // Allonger la ligne du bas
        g.fillRect(roadX + rw - 2, roadY + rw - 2, 2, h - roadY - rw + 2); // Allonger la ligne du bas côté droit


        // === LIGNES DISCONTINUES (hors intersection) ===
        int yMid = h / 2 - 2;
        for (int x = 0; x < w; x += (LIGNE_LONGUEUR + LIGNE_ESPACE)) {
            if (x + LIGNE_LONGUEUR < roadX || x > roadX + rw) {
                g.fillRect(x, yMid, LIGNE_LONGUEUR, 4);
            }
        }

        int xMid = w / 2 - 2;
        for (int y = 0; y < h; y += (LIGNE_LONGUEUR + LIGNE_ESPACE)) {
            if (y + LIGNE_LONGUEUR < roadY || y > roadY + rw) {
                g.fillRect(xMid, y, 4, LIGNE_LONGUEUR);
            }
        }

        // === INITIALISATION DES FEUX DE SIGNALISATION ===
        if (feux == null) {
            // Position des feux aux coins des trottoirs
            feux = List.of(
                new FeuSignalisation(roadX - tw, roadY - tw, true),  // Feu vert en haut à gauche
                new FeuSignalisation(roadX + rw , roadY - tw, false),  // Feu rouge en haut à droite
                new FeuSignalisation(roadX - tw, roadY + rw , false),  // Feu rouge en bas à gauche (ajusté)
                new FeuSignalisation(roadX + rw , roadY + rw, true)  // Feu vert en bas à droite
            );
        }

        // === DESSIN DES FEUX DE SIGNALISATION ===
        for (FeuSignalisation feu : feux) {
            feu.dessiner(g);  // Dessiner chaque feu
        }

        // === VÉHICULES ===
        for (int i = 0; i < vehicules.size(); i++) {
            Vehicule v = vehicules.get(i);
            int x = (int) v.getPosition().getAbscisse();
            int y = (int) v.getPosition().getOrdonee();

            // Générer une couleur unique pour chaque véhicule
            float hue = (float) i / vehicules.size(); // Utiliser l'indice pour générer des couleurs distinctes
            Color vehicleColor = Color.getHSBColor(hue, 1.0f, 1.0f); // Couleur en utilisant l'espace HSB

            // Ombre sous les véhicules
            g.setColor(new Color(0, 0, 0, 50));  // Ombre légèrement translucide
            g.fillRect(x + 5, y + 5, (int) (v.getLongueur() * 10), (int) (v.getLargeur() * 10));

            // Dessin du véhicule avec une couleur unique
            g.setColor(vehicleColor);
            g.fillRect(x, y, (int) (v.getLongueur() * 10), (int) (v.getLargeur() * 10));
        }
    }
}
