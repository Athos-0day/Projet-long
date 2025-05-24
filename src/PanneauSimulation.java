/** La classe permet de dessiner l'intersection dans la simulation.
 * 
 * @author Arthur Morain
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class PanneauSimulation extends JPanel {
    private List<Vehicule> vehicules;
    private List<FeuSignalisation> feux;
    private Timer feuTimer;

    private static final int ROUTE_WIDTH = 100;
    private static final int TROTTOIR_WIDTH = 20;
    private static final int LIGNE_LONGUEUR = 30;
    private static final int LIGNE_ESPACE = 20;

    public PanneauSimulation(List<Vehicule> vehicules) {
        this.vehicules = vehicules;
        setPreferredSize(new Dimension(800, 800));
        setBackground(new Color(34, 139, 34)); // Vert herbe

        feuTimer = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (FeuSignalisation feu : feux) {
                    feu.alterner();
                }
                repaint();
            }
        });

        feuTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();
        int rw = ROUTE_WIDTH;
        int tw = TROTTOIR_WIDTH;

        int roadX = (w - rw) / 2;
        int roadY = (h - rw) / 2;

        // Trottoirs
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, roadY - tw, w, tw);     // haut
        g.fillRect(0, roadY + rw, w, tw);     // bas
        g.fillRect(roadX - tw, 0, tw, h);     // gauche
        g.fillRect(roadX + rw, 0, tw, h);     // droite

        // Routes
        g.setColor(Color.GRAY);
        g.fillRect(0, roadY, w, rw);          // horizontale
        g.fillRect(roadX, 0, rw, h);          // verticale

        // Bordures blanches
        g.setColor(Color.WHITE);
        g.fillRect(0, roadY, roadX + 2, 2);
        g.fillRect(0, roadY + rw - 2, roadX + 2, 2);
        g.fillRect(roadX + rw - 2, roadY, w - roadX - rw + 2, 2);
        g.fillRect(roadX + rw - 2, roadY + rw - 2, w - roadX - rw + 2, 2);
        g.fillRect(roadX, 0, 2, roadY + 2);
        g.fillRect(roadX + rw - 2, 0, 2, roadY + 2);
        g.fillRect(roadX, roadY + rw - 2, 2, h - roadY - rw + 2);
        g.fillRect(roadX + rw - 2, roadY + rw - 2, 2, h - roadY - rw + 2);

        // Lignes discontinues
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

        // Initialisation des feux
        if (feux == null) {
            feux = List.of(
                new FeuSignalisation(roadX - tw, roadY - tw, true,3),
                new FeuSignalisation(roadX + rw, roadY - tw, false,2),
                new FeuSignalisation(roadX - tw, roadY + rw, false, 1),
                new FeuSignalisation(roadX + rw, roadY + rw, true, 0)
            );
        }

        // Dessin des feux
        for (FeuSignalisation feu : feux) {
            feu.dessiner(g);
        }

        // Dessin des véhicules
        for (int i = 0; i < vehicules.size(); i++) {
            Vehicule v = vehicules.get(i);
            Point p = centrerSurVoie(v);

            float hue = (float) i / vehicules.size();
            Color vehicleColor = Color.getHSBColor(hue, 1.0f, 1.0f);

            v.dessiner(g, p, vehicleColor);
        }
    }

    /**
     * Centre le véhicule sur la bonne voie selon sa direction.
     * Retourne un Point contenant la position corrigée (x, y).
     */
    private Point centrerSurVoie(Vehicule v) {
        int rw = ROUTE_WIDTH;
        int w = getWidth();
        int h = getHeight();

        int roadX = (w - rw) / 2;
        int roadY = (h - rw) / 2;

        int x = (int) v.getPosition().getAbscisse();
        int y = (int) v.getPosition().getOrdonee();

        switch (v.getDirection()) {
            case 0: // Bas
                x = roadX + 12;
                break;
            case 1: // Haut
                x = roadX + 64;
                break;
            case 2: // Gauche
                y = roadY + 64;
                break;
            case 3: // Droite
                y = roadY + 12;
                break;
        }

        return new Point(x, y);
    }

    /** Méthode pour récupérer la liste des feux.
     * 
     * @return la liste des feux
     */
    public List<FeuSignalisation> getFeux() {
        return feux;
    }
}
