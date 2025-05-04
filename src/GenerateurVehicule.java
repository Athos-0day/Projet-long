import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class GenerateurVehicule {

    private final List<Vehicule> vehicules;
    private final Supplier<Integer> densiteDelayProvider;
    private final Random random;
    private long lastGenerationTime;

    public GenerateurVehicule(List<Vehicule> vehicules, Supplier<Integer> densiteDelayProvider) {
        this.vehicules = vehicules;
        this.densiteDelayProvider = densiteDelayProvider;
        this.random = new Random();
        this.lastGenerationTime = System.currentTimeMillis();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        int delay = densiteDelayProvider.get();

        if (currentTime - lastGenerationTime >= delay) {
            Vehicule v = creerVehiculeAleatoire();
            vehicules.add(v);
            lastGenerationTime = currentTime;
        }
    }

    private Vehicule creerVehiculeAleatoire() {
        int direction = random.nextInt(4); // 0 = bas, 1 = haut, 2 = droite, 3 = gauche

        int x = 0, y = 0;

        // Coordonnées du centre de la route
        int centreX = 400;
        int centreY = 400;
        int demiRoute = 50;

        switch (direction) {
            case 0 -> { // Bas (du haut vers le bas)
                x = centreX + 15; // voie de droite (vue du haut)
                y = -50;
            }
            case 1 -> { // Haut (du bas vers le haut)
                x = centreX - 35; // voie de gauche (vue du bas)
                y = 850;
            }
            case 2 -> { // Droite (de la gauche vers la droite)
                x = -50;
                y = centreY + 15; // voie du bas (vue de gauche)
            }
            case 3 -> { // Gauche (de la droite vers la gauche)
                x = 850;
                y = centreY - 35; // voie du haut (vue de droite)
            }
        }

        Vehicule vehicule;
        int type = random.nextInt(3);
        if (type == 0) {
            vehicule = new Voiture(x, y);
        } else if (type == 1) {
            vehicule = new DeuxRoues(x, y);
        } else {
            vehicule = new PoidsLourds(x, y);
        }

        vehicule.setDirection(direction);
        return vehicule;
    }
}
