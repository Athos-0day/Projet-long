import java.util.List;
import javax.swing.*;

public class Simulation {
    private List<Vehicule> vehicules;
    private double deltaTemps = 0.1;
    private JPanel panneau;

    public Simulation(List<Vehicule> vehicules, JPanel panneau) {
        this.vehicules = vehicules;
        this.panneau = panneau;
    }

    public void start() {
        Timer timer = new Timer(100, e -> {
            for (Vehicule v : vehicules) {
                v.accelerer(deltaTemps);
                v.deplacerSurX(deltaTemps);
            }
            panneau.repaint();
        });
        timer.start();
    }
}