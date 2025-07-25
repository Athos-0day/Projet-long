/** La classe permet de mettre en place
 *  la gestion des collisions.
 *  elle ne gère que les collisions 
 *  dans la même direction pour le moment.
 * 
 *  @author Morain Arthur
 */

import java.util.List;

public class GestionCollision {

    /** Défiinition d'une constante pour la distance de sécurité. */
    private static final double DISTANCE_SECURITE = 20.0;


    /** La méthode permet d'éviter les cas de collisions.
     * 
     * @param vehicules la liste des véhicules
     */
    public static void gererCollisions(List<Vehicule> vehicules) {

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
                    if (v1.getDirection() == v2.getDirection()) {
                        double distance = calculerDistance(v1, v2);
                        if (distance < minDistance) {
                            minDistance = distance;
                            plusProcheDevant = v2;
                        }
                    }
                }


            }

            //Décision d'arrêt ou de ralentissement 
            if (plusProcheDevant != null) {
                if (minDistance < 2.0) {
                    v1.setVitesse(0); // arrêt complet
                } else if (minDistance < DISTANCE_SECURITE) {
                    v1.freiner(5.0); // freine modérément
                }
            } else {
                v1.setVitesse(GenerateurVehicule.VITESSE_INITIALE); // personne devant : accélère normalement
            }
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
     * Calcule la distance entre deux véhicules selon l'axe de déplacement.
     *
     * @param v1 Le premier véhicule
     * @param v2 Le second véhicule
     * @return La distance en mètres (sur un seul axe)
     */
    private static double calculerDistance(Vehicule v1, Vehicule v2) {
        switch (v1.getDirection()) {
            case 0: // bas
            case 1: // haut
                return Math.abs(v1.getPosition().getOrdonee() - v2.getPosition().getOrdonee());
            case 2: // droite
            case 3: // gauche
                return Math.abs(v1.getPosition().getAbscisse() - v2.getPosition().getAbscisse());
            default:
                return Double.MAX_VALUE; // sécurité en cas de direction inconnue
        }
    }

}