/**
 * La classe Conducteur modélise le comportement d'un conducteur sur la route.
 * (Ces paramètres ne servent pas dans la version de simulation actuelle)
 * Chaque conducteur possède des caractéristiques influençant ses décisions :
 * - Prudence : impacte la distance de sécurité et la probabilité de dépasser.
 * - Agressivité : détermine la tendance à prendre des risques.
 * - Fatigue : diminue la réactivité au fil du temps.
 * - Temps de réaction : influence le temps d'action (freinage, dépassement).
 * 
 * Certains de ces attributs sont définis dès le départ, mais ne seront pas
 * forcément utilisés immédiatement dans la simulation. Ils sont présents
 * pour permettre une évolution et un enrichissement progressif du modèle.
 * 
 * @author Arthur Morain
 */

import java.util.Random;

public class Conducteur {
    /**La prudence du conducteur.*/
    private final double prudence; // 0 (très agressif) à 1 (très prudent)
    /**L'agressivite du conducteur.*/
    private double agressivite; // 0 (calme) à 1 (très nerveux)
    /**La fatigue du conducteur.*/
    private double fatigue;     // 0 (repos) à 1 (épuisé) - pas forcément utilisé dès le départ
    /**Le temps de réaction du conducteur.*/
    private double tempsReaction; // Temps de réaction en secondes - optionnel selon la version de la simulation
    /**L'attribut pour générer des valeurs aléatoires.*/
    private final Random random;

    /**La constante du temps minimum de réaction */
    private final static double TEMPS_REACT_MIN = 0.5;

    /**
     * Constructeur par défaut générant un conducteur avec des caractéristiques aléatoires,
     * tout en respectant des relations logiques entre les attributs.
     */
    public Conducteur() {
        this.random = new Random();

        //Génération de la prudence et l'agressivité 
        //On part du principe qu'une prudence faible implique une agressivité accrut
        this.prudence = random.nextDouble(); // 0 à 1
        this.agressivite = 1 - this.prudence + (random.nextDouble() * 0.2 - 0.1); // Inversement proportionnel à prudence

        //Agressivite doit rester entre 0 et 1
        this.agressivite = Math.max(0, Math.min(1, this.agressivite));

        //Génération de la valeur de fatigue
        this.fatigue = random.nextDouble();

        //Le temps de réaction enfin est régit par la fatigue
        this.tempsReaction = TEMPS_REACT_MIN + 1.5 * fatigue; // Temps de réaction entre 0.5s et 2.0s

    }

    /**
     * Récupère la valeur de la prudence du conducteur.
     * @return La valeur de la prudence du conducteur (comprise entre 0 et 1).
     */
    public double getPrudence() {
        return this.prudence;
    }

    /**
     * Récupère la valeur de l'agressivite du conducteur.
     * @return La valeur de l'agressivite du conducteur (comprise entre 0 et 1).
     */
    public double getAgressivite() {
        return this.agressivite;
    }

    /**
     * Récupère la valeur de la fatigue du conducteur.
     * @return La valeur de la fatigue du conducteur (comprise entre 0 et 1).
     */
    public double getFatigue() {
        return this.fatigue;
    }

    /**
     * Récupère le temps de réaction du conducteur.
     * Le temps de réaction est influencé par la fatigue et varie entre 0.5s et 2.0s.
     * @return Le temps de réaction du conducteur en secondes.
     */
    public double getTempsReaction() {
        return this.tempsReaction;
    }

    /**
     * Modifie le niveau de fatigue du conducteur en fonction d'un facteur.
     * @param facteur Le facteur d'augmentation ou de réduction de la fatigue.
     * @throws ConducteurException lorsque le facteur n'est pas dans [-1;1] 
     */
    public void changerFatigue(double facteur) throws ConducteurException {
        if (facteur < -1 || facteur > 1) {
            throw new ConducteurException("Le facteur de fatigue doit être compris entre -1 et 1.");
        }

        this.fatigue = Math.max(0, Math.min(1, this.fatigue + facteur));
        this.tempsReaction = TEMPS_REACT_MIN + 1.5 * fatigue; // Si on change la fatigue il faut changer le temps de réaction
    }

    /**
     * Vérifie si le conducteur est considéré comme prudent. 
     * @return true si le conducteur est prudent (prudence > 0.6), false sinon.
     */
    public boolean estPrudent() {
        return this.prudence > 0.6;
    }

    /**
     * Détermine si le conducteur décide de dépasser un autre véhicule.
     * La décision est influencée par l'agressivité et la prudence du conducteur.
     * @return true si le conducteur décide de dépasser, false sinon.
     */
    public boolean deciderDepasser() {
        double seuil = 0.5; // seuil de base pour décider de dépasser

        
        if (this.agressivite > 0.6 && this.prudence < 0.3) {
            return random.nextDouble() < 0.8; // La probabilité que le conducteur agressif dépasse est plus importante que les autres
        }

        else if (this.prudence > 0.6) {
            return random.nextDouble() < 0.2; // La probabilité que le conducteur prudent dépasse est faible
        }
        
        return random.nextDouble() < seuil; // Le cas qui traite le conducteur moyen
    }

    /**
     * Affiche les caractéristiques du conducteur.
     * @return l'affichage du Conducteur.
     */
    @Override
    public String toString() {
        return "Conducteur { \n" +
            "  Agressivité: " + this.agressivite + "\n" +
            "  Prudence: " + this.prudence + "\n" +
            "  Fatigue: " + this.fatigue + "\n" +
            "  Temps de réaction: " + this.tempsReaction + "s \n" +
            "}";
    }

}

