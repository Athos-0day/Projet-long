import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TraficRoutierSwing extends JFrame {

    private Timer simulationTimer;
    private JButton startButton, stopButton, vitesseButton, densiteButton;
    private int simulationSpeed = 1000;
    private String densite = "Moyenne";

    private int elapsedSeconds = 0;
    private JLabel timerLabel;

    public TraficRoutierSwing() {
        super("Simulation");

        // Définition du layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel des boutons
        JPanel boutonsPanel1 = new JPanel(new GridLayout(4, 1, 5, 5));

        // Création des boutons
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        vitesseButton = new JButton("Vit.Sim");
        densiteButton = new JButton("Densite");

        // Ajout des boutons au panel
        boutonsPanel1.add(startButton);
        boutonsPanel1.add(stopButton);
        boutonsPanel1.add(vitesseButton);
        boutonsPanel1.add(densiteButton);

        // Wrapper vertical pour centrer les boutons et ne pas prendre toute la hauteur
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.gridy = 0;
        leftGbc.anchor = GridBagConstraints.NORTH;
        leftPanel.add(boutonsPanel1, leftGbc);

        // Placement du panel gauche contenant les boutons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(leftPanel, gbc);

        // Panel principal de simulation avec un BorderLayout
        JPanel simulationPanel = new JPanel(new BorderLayout());
        simulationPanel.setBackground(Color.LIGHT_GRAY);
        simulationPanel.setPreferredSize(new Dimension(800, 800));

        // Affichage du temps écoulé
        timerLabel = new JLabel("Temps : 0 s", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        simulationPanel.add(timerLabel, BorderLayout.NORTH);

        // Placement du panel de simulation à droite
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(simulationPanel, gbc);

        // Timer de simulation
        simulationTimer = new Timer(simulationSpeed, e -> {
            elapsedSeconds++;
            timerLabel.setText("Temps : " + elapsedSeconds + " s");
        });

        // Actions des boutons
        startButton.addActionListener(e -> {
            simulationTimer.start();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        });

        stopButton.addActionListener(e -> {
            simulationTimer.stop();
            startButton.setEnabled(true);
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
                    options[0]
            );

            if (choix != null) {
                boolean vitesseChangee = true;
                if (choix.equals("x0.5")) {
                    simulationSpeed = 1500;
                } else if (choix.equals("x1")) {
                    simulationSpeed = 1000; 
                } else if (choix.equals("x1.5")) {
                    simulationSpeed = 666;
                } else if (choix.equals("x2")) {
                    simulationSpeed = 500;
                } else if (choix.equals("x3")) {
                    simulationSpeed = 333;
                } else {
                    vitesseChangee = false;
                }
                if (vitesseChangee) {
                    simulationTimer.setDelay(simulationSpeed);
                }
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
                    options[1]
            );

            if (choix != null) {
                densite = choix;
                System.out.println("Densité choisie : " + densite);
            }
        });

        stopButton.setEnabled(false); // désactiver Stop au départ

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1200, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TraficRoutierSwing::new);
    }
}