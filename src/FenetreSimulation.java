import java.util.List;
import javax.swing.*;

public class FenetreSimulation extends JFrame {
    public FenetreSimulation(List<Vehicule> vehicules) {
        setTitle("Simulation de Trafic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new PanneauSimulation(vehicules));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}