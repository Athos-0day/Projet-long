import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class GenerateurVehicule {

    private final List<Vehicule> vehicules;
    private final Supplier<Integer> densiteDelayProvider;
    private final Random random;
    private long lastGenerationTime;
    private static final int VITESSE_INITIALE = 210;
    private static final int DISTANCE_SECURITE = 100;

    public GenerateurVehicule(List<Vehicule> vehicules, Supplier<Integer> densiteDelayProvider) {
        this.vehicules = vehicules;
        this.densiteDelayProvider = densiteDelayProvider;
        this.random = new Random();
        this.lastGenerationTime = System.currentTimeMillis();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        int delay = densiteDelayProvider.get();

        // Probabilité d'apparition selon densité (ex : 1 = faible -> 10%, 10 = élevée -> 100%)
        int proba = Math.min(100, delay / 10); // Plus le delay est bas, plus la densité est haute

        if (currentTime - lastGenerationTime >= delay) {
            if (random.nextInt(100) < proba) {  // Génération conditionnelle
                Vehicule v = creerVehiculeAleatoire();

                if (entreeLibrePour(v)) {
                    v.setVitesse(VITESSE_INITIALE);  // Vitesse fixe à l’entrée
                    vehicules.add(v);
                    lastGenerationTime = currentTime;
                }
            }
        }
    }

    private Vehicule creerVehiculeAleatoire() {
        int direction = random.nextInt(4); // 0 = bas, 1 = haut, 2 = droite, 3 = gauche
        int centreX = 400;
        int centreY = 400;
        int x = 0, y = 0;

        switch (direction) {
            case 0 -> { x = centreX + 15; y = -100; }
            case 1 -> { x = centreX - 35; y = 900; }
            case 2 -> { x = -100; y = centreY + 15; }
            case 3 -> { x = 900; y = centreY - 35; }
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
        vehicule.setVitesse(VITESSE_INITIALE);  // Appliquer vitesse max
        return vehicule;
    }

    private boolean entreeLibrePour(Vehicule nouveau) {
        double nx = nouveau.getPosition().getAbscisse();
        double ny = nouveau.getPosition().getOrdonee();

        double nW = (nouveau.getDirection() == 0 || nouveau.getDirection() == 1)
                ? nouveau.getLargeur() * 10 : nouveau.getLongueur() * 10;
        double nH = (nouveau.getDirection() == 0 || nouveau.getDirection() == 1)
                ? nouveau.getLongueur() * 10 : nouveau.getLargeur() * 10;

        // Appliquer une marge de sécurité autour du nouveau véhicule
        double nxSafe = nx - DISTANCE_SECURITE;
        double nySafe = ny - DISTANCE_SECURITE;
        double nWSafe = nW + 2 * DISTANCE_SECURITE;
        double nHSafe = nH + 2 * DISTANCE_SECURITE;

        for (Vehicule v : vehicules) {
            double vx = v.getPosition().getAbscisse();
            double vy = v.getPosition().getOrdonee();

            double vW = (v.getDirection() == 0 || v.getDirection() == 1)
                    ? v.getLargeur() * 10 : v.getLongueur() * 10;
            double vH = (v.getDirection() == 0 || v.getDirection() == 1)
                    ? v.getLongueur() * 10 : v.getLargeur() * 10;

            if (nxSafe < vx + vW && nxSafe + nWSafe > vx &&
                nySafe < vy + vH && nySafe + nHSafe > vy) {
                return false;
            }
        }

        return true;
    }

}
