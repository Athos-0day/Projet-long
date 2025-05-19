import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TraficRoutierSwing extends JFrame {

    private Timer simulationTimer;
    private JButton startButton, stopButton, vitesseButton, densiteButton;
    private int simulationSpeed = 1000;
    private String densite = "Moyenne";

    private int elapsedSeconds = 0;
    private JLabel timerLabel;

    private List<Vehicule> vehicules;
    private PanneauSimulation panneau;
    private GenerateurVehicule generateur;

    //Attributs pour les affichage des véhicules présents passés
    private JLabel vehiculesPresentsLabel;
    private JLabel vehiculesPassesLabel;
    private int vehiculesPassesCount = 0;

    /** contructeur de l'interface graphique. */
    public TraficRoutierSwing() {
        super("Simulation Trafic Routier");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // === BOUTONS ===
        JPanel boutonsPanel1 = new JPanel(new GridLayout(4, 1, 5, 5));
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        vitesseButton = new JButton("Vit.Sim");
        densiteButton = new JButton("Densite");

        boutonsPanel1.add(startButton);
        boutonsPanel1.add(stopButton);
        boutonsPanel1.add(vitesseButton);
        boutonsPanel1.add(densiteButton);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.gridy = 0;
        leftGbc.anchor = GridBagConstraints.NORTH;
        leftPanel.add(boutonsPanel1, leftGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(leftPanel, gbc);

        // === PANEL SIMULATION ===
        JPanel simulationPanel = new JPanel(new BorderLayout());
        simulationPanel.setBackground(Color.LIGHT_GRAY);
        simulationPanel.setPreferredSize(new Dimension(800, 800));

        // === Panel affichage valeurs ===
        JPanel topInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        timerLabel = new JLabel("Temps : 0 s");
        vehiculesPresentsLabel = new JLabel("Présent : 0");
        vehiculesPassesLabel = new JLabel("Passés : 0");

        Font infoFont = new Font("Arial", Font.BOLD, 18);
        timerLabel.setFont(infoFont);
        vehiculesPresentsLabel.setFont(infoFont);
        vehiculesPassesLabel.setFont(infoFont);

        topInfoPanel.add(vehiculesPresentsLabel);
        topInfoPanel.add(Box.createHorizontalStrut(20));
        topInfoPanel.add(vehiculesPassesLabel);
        topInfoPanel.add(Box.createHorizontalStrut(20));
        topInfoPanel.add(timerLabel);
        simulationPanel.add(topInfoPanel, BorderLayout.NORTH);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(simulationPanel, gbc);

        // === LOGIQUE SIMULATION ===
        vehicules = new ArrayList<>();
        generateur = new GenerateurVehicule(vehicules, this::getDensiteDelay);

        panneau = new PanneauSimulation(vehicules);
        simulationPanel.add(panneau, BorderLayout.CENTER);

        simulationTimer = new Timer(simulationSpeed, e -> {
            generateur.update(); // génération éventuelle de véhicules

            for (Vehicule v : vehicules) {
                switch (v.getDirection()) {
                    case 0 -> v.deplacerSurY(0.5);   // bas
                    case 1 -> v.deplacerSurY(-0.5);  // haut
                    case 2 -> v.deplacerSurX(0.5);   // droite
                    case 3 -> v.deplacerSurX(-0.5);  // gauche
                }
            }

            //Suppression des véhicules hors cadre et incrémentation.
            vehicules.removeIf(v -> {
                if (estHorsCadre(v)) {
                    vehiculesPassesCount++;
                    return true;
                }
                return false;
            });

            //Mise à jour des panneaux
            panneau.repaint();
            elapsedSeconds++;
            vehiculesPresentsLabel.setText("Présents : " + vehicules.size());
            vehiculesPassesLabel.setText("Passés : " + vehiculesPassesCount);
            timerLabel.setText("Temps : " + elapsedSeconds + " s");
        });

        // === ACTIONS BOUTONS ===
        startButton.addActionListener(e -> {
            simulationTimer.start();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        });

        stopButton.addActionListener(e -> {
            simulationTimer.stop();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        });

        vitesseButton.addActionListener(e -> {
            String[] options = {"x0.5", "x1", "x1.5", "x2", "x3"};
            String choix = (String) JOptionPane.showInputDialog(
                    this,
                    "Choisir une vitesse de simulation",
                    "Vitesse",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    "x1"
            );

            if (choix != null) {
                switch (choix) {
                    case "x0.5" -> simulationSpeed = 1500;
                    case "x1" -> simulationSpeed = 1000;
                    case "x1.5" -> simulationSpeed = 666;
                    case "x2" -> simulationSpeed = 500;
                    case "x3" -> simulationSpeed = 333;
                }
                simulationTimer.setDelay(simulationSpeed);
            }
        });

        densiteButton.addActionListener(e -> {
            String[] options = {"Faible", "Moyenne", "Élevée", "Très Élevée"};
            String choix = (String) JOptionPane.showInputDialog(
                    this,
                    "Choisir la densité de circulation",
                    "Densité",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    densite
            );

            if (choix != null) {
                densite = choix;
                System.out.println("Densité choisie : " + densite);
            }
        });

        stopButton.setEnabled(false);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1200, 800);
        setVisible(true);
    }

    private boolean estHorsCadre(Vehicule v) {
        int x = (int) v.getPosition().getAbscisse();
        int y = (int) v.getPosition().getOrdonee();
        return (x < -100 || x > 1000 || y < -100 || y > 1000);
    }

    private int getDensiteDelay() {
        return switch (densite) {
            case "Faible" -> 5000;
            case "Moyenne" -> 4000;
            case "Élevée" -> 2500;
            case "Très Élevée" -> 1500;
            default -> 4000;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TraficRoutierSwing::new);
    }
}
