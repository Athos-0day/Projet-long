// @author Mohib Alexandre

import javax.swing.*;
import java.awt.*;

//Classe pour faire la page d'Accueil du simulateur.

public class PageAccueil extends JFrame {

    public PageAccueil() {
        setTitle("Simulateur de trafic routier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Charger l'image (ici gif) de fond du simulateur.
        ImageIcon gifFond = new ImageIcon("ressources/images//fondAnime.gif");

        // Récupérer la taille de l'image pour adapter la taille de la fenetre.
        int largeurGif = gifFond.getIconWidth();
        int hauteurGif = gifFond.getIconHeight();
        setSize(largeurGif, hauteurGif);
        JLabel backgroundLabel = new JLabel(gifFond);
        backgroundLabel.setLayout(new GridBagLayout());

        // Créer les boutons
        JButton commencerButton = new JButton("Commencer");
        JButton quitterButton = new JButton("Quitter");

        commencerButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitterButton.setFont(new Font("Arial", Font.BOLD, 20));

        // Bouton commencer ferme la page d'accueil et ouvre la page du simulateur.
        commencerButton.addActionListener(e -> {
            dispose();
            new TraficRoutierSwing();
        });

        // Bouton qui ferme la page d'accueil.
        quitterButton.addActionListener(e -> System.exit(0));

        // Panel transparent contenant les deux boutons.
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.add(commencerButton);
        buttonPanel.add(quitterButton);


        backgroundLabel.add(buttonPanel);
        add(backgroundLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PageAccueil::new);
    }
}
