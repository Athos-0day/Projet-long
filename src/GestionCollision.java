import java.util.List;

/** La classe permet de mettre en place
 *  la gestion des collisions.
 * 
 *  @author Morain Arthur
 */
public class GestionCollision {

    /** Défiinition d'une constante pour la distance de sécurité. */
    private static final double DISTANCE_SECURITE = 5.0;


    /** La méthode permet d'éviter les cas de collisions.
     * 
     * @param vehicules la liste des véhicules
     * @param deltaTemps la variation de temps
     */
    public static void gererCollisions(List<Vehicule> vehicules, double deltaTemps) {

        //On parcourt tous les véhicules de la liste 
        for (int i = 0; i < vehicules.size(); i++) {
            Vehicule v1 = vehicules.get(i);

            //On cherche le véhicule le plus proche devant
            //(même voie et même direction)
            Vehicule plusProcheDevant = null;
            double minDistance = Double.MAX_VALUE;

            //Pour cela il faut parcourir tous les véhicules tel que j!=i
            for (int j = 0; j < vehicules.size(); j++) {
                //Vérification de j!=i
                if (j != i) {
                    Vehicule v2 = vehicules.get(j);
                    //Vérification de la voie et de la direction
                    if (v1.getDirection() == v2.getDirection() && estAligneSurVoie(v1, v2)) {
                        double distance = calculerDistance(v1, v2);
                        if (estDevant(v1, v2) && distance < minDistance) {
                            minDistance = distance;
                            plusProcheDevant = v2;
                        }
                    }
                }


            }

            //Décision d'arrêt ou de ralentissement 
            if (plusProcheDevant != null) {
                if (minDistance < 2.0) {
                    v1.freiner(v1.getVitesseActuelle()); // arrêt complet
                } else if (minDistance < DISTANCE_SECURITE) {
                    v1.freiner(5.0); // freine modérément
                }
            } else {
                v1.accelerer(deltaTemps); // personne devant : accélère normalement
            }
        }
    }

    /** La méthode permet de vérifier que deux véhicules sont alignés 
     *  sur la même voie
     *  @param v1 le premier véhicule
     *  @param v2 le deuxième véhicule
     *  @return vrai s'ils sont sur la même voie, faux sinon
     */
    private static boolean estAligneSurVoie(Vehicule v1, Vehicule v2) {
        if (v1.getDirection() == 0 || v1.getDirection() == 1) {
            return Math.abs(v1.getPosition().getAbscisse() - v2.getPosition().getAbscisse()) < 1.0;
        } else {
            return Math.abs(v1.getPosition().getOrdonee() - v2.getPosition().getOrdonee()) < 1.0;
        }
    }

    /**
     * Détermine si le véhicule v2 est devant le véhicule v1, selon leur direction.
     *
     * @param v1 Le véhicule courant
     * @param v2 Le véhicule potentiel devant
     * @return vraie si v2 est devant v1 dans la même direction
     */
    private static boolean estDevant(Vehicule v1, Vehicule v2) {
        switch (v1.getDirection()) {
            case 0: return v2.getPosition().getOrdonee() > v1.getPosition().getOrdonee(); // bas
            case 1: return v2.getPosition().getOrdonee() < v1.getPosition().getOrdonee(); // haut
            case 2: return v2.getPosition().getAbscisse() > v1.getPosition().getAbscisse(); // droite
            case 3: return v2.getPosition().getAbscisse() < v1.getPosition().getAbscisse(); // gauche
            default: return false;
        }
    }

    /**
     * Calcule la distance euclidienne entre deux véhicules.
     *
     * @param v1 Le premier véhicule
     * @param v2 Le second véhicule
     * @return La distance en mètres
     */
    private static double calculerDistance(Vehicule v1, Vehicule v2) {
        double dx = v1.getPosition().getAbscisse() - v2.getPosition().getAbscisse();
        double dy = v1.getPosition().getOrdonee() - v2.getPosition().getOrdonee();
        return Math.sqrt(dx * dx + dy * dy);
    }
}