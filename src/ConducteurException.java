/**
 * Classe d'exceptions spécifiques aux erreurs liées à la classe Conducteur.
 * 
 * @author Arthur Morain
 */
public class ConducteurException extends RuntimeException {
    
    /**
     * Constructeur avec un message personnalisé.
     * @param message Le message décrivant l'erreur.
     */
    public ConducteurException(String message) {
        super(message);
    }
}
