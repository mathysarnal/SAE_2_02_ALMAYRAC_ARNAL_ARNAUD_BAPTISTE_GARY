package polynome;
import java.util.Scanner;

/**
 * 
 * TODO commenter la responsabilité de cette classe (SRP)
 */
public class Polynome {

    private List<Integer> degres;

    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param degres
     * @return addition des deux polynômes
     */
    public Polynome(List<Integer> degres) {
        this.degres = degres;
    }

    public void afficher() {
        System.out.println(degres);
    }
    
	/**
	 * TODO commenter le rôle de la méthode (SRP)
	 * @param monome
	 * @return degrés du polynôme
	 */
	public getDegre(monome) {
		return int degre;
	}
	
	/**
	 * TODO commenter le rôle de la méthode (SRP)
	 * @param degre
	 * @return coefficient du polynôme
	 */
	public getCoefficient(int degre) {
		return double;
	}
	
	/**
	 * TODO commenter le rôle de la méthode (SRP)
	 * @return true si le polynome est nul et false sinon
	 */
	public estNul() {
		boolean estNul = false;
		if (this.length == 0) {
			estNul = true;
		}
		return estNul;
	}
	
	/**
	 * TODO commenter le rôle de la méthode (SRP)
	 * @param x
	 * @return true si le polynome est nul et false sinon
	 */
	public evaluer(double x) {
		return double;
	}
		
    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param polynome1
     * @param polynome2
     * @return addition des deux polynômes
     */
    public String additionner(String polynome1, String polynome2) {
        return polynomeFinal;
    }
    
    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param polynome
     * @param scalaire
     * @return multiplication du polynôme donné par un scalaire réel
     */
    public String multiplier(String polynome, double scalaire) {
        return polynomeFinal;
    }
    
    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param polynome1
     * @param polynome2
     * @return produit de deux polynômes
     */
    public String multiplier(String polynome1, String polynome2) {
        return polynomeFinal;
    }
    
    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param polynome1
     * @param polynome2
     * @return division euclidienne de deux polynômes
     */
    public String diviser(String polynome1, String polynome2) {
        return polynomeFinal;
    }
    
    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param polynome
     * @return dérivation du polynôme donné
     */
    public String deriver(String polynome) {
        return polynomeFinal;
    }
    
    /**
     * TODO commenter le rôle de la méthode (SRP)
     * @param polynome
     * @return intégration du polynôme donné
     */
    public String integrer(String polynome) {
        return polynomeFinal;
    }
}