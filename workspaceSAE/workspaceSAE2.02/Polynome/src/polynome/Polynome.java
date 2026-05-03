package polynome;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un polynôme à une variable réelle (x).
 *
 * La classe stocke les coefficients du polynôme dans une liste,
 * où l'indice correspond au degré du monôme.
 *
 * Exemple : Polynome(3,4,2) représente P(x) = 3 + 4x + 2x²
 *
 * Responsabilité (SRP) :
 * - Stocker un polynôme
 * - Fournir des opérations mathématiques sur ce polynôme
 */
public class Polynome {

    private List<Integer> degres;

    /**
     * Construit un polynôme à partir d'une liste de coefficients.
     *
     * Chaque argument correspond au coefficient du monôme de degré égal à son index.
     *
     * Exemple : new Polynome(3,4,2) => P(x) = 3 + 4x + 2x²
     *
     * @param degres coefficients du polynôme (du degré 0 au plus grand)
     */
    public Polynome(int... degres) {
        this.degres = new ArrayList<>();
        for (int d : degres) {
            this.degres.add(d);
        }
    }

    /**
     * Retourne le degré du polynôme
     *
     * @return degré du polynôme
     */
	public int getDegre() {
		for (int degre = degres.size() - 1; degre >= 0; degre--) {
		    if (degres.get(degre) != 0) {
		        return degre;
		    }
		}
		return 0;
	}
	
	/**
     * Retourne le coefficient associé à un degré donné.
     *
     * @param degre degré du monôme
     * @return coefficient correspondant
     */
	public getCoefficient(int degre) {
		return degres.get(degre);
	}
	
	/**
	 * Retourne la limite en plus l'infini du polynôme
	 *
	 * @return limite du polynôme en plus l'infini
	 */
	public double getLimitePlusInfini() {
	    int degre = getDegre();
	    double coef = getCoefficient(degre);

	    if (coef > 0) {
	    	return Double.POSITIVE_INFINITY;
	    } else if (coef < 0) {
	    	return Double.NEGATIVE_INFINITY;
	    }
	    return 0;
	}
	
	/**
	 * Retourne la limite en moins l'infini du polynôme
	 *
	 * @return limite du polynôme en moins l'infini
	 */
	public double getLimiteMoinsInfini() {
	    int degre = getDegre();
	    double coef = getCoefficient(degre);

	    if (degre % 2 == 0) {
	        if (coef > 0) {
	            return Double.POSITIVE_INFINITY;
	        } else {
	            return Double.NEGATIVE_INFINITY;
	        }
	    } else {
	        if (coef > 0) {
	            return Double.POSITIVE_INFINITY;
	        } else {
	            return Double.NEGATIVE_INFINITY;
	        }
	    }
	}
	
	/**
	 * Retourne la limite en plus l'infini associé au degré du polynôme.
	 *
	 * @return limite du polynôme en plus l'infini
	 */
	public double getRacinesReelles() {
	    // TODO faire
	}
	
	/**
     * Indique si le polynôme est nul (tous les coefficients sont nuls).
     *
     * Responsabilité :
     * Vérifier si le polynôme représente la fonction nulle.
     *
     * @return true si tous les coefficients sont nuls, false sinon
     */
	public boolean estNul() {
        for (int coef : degres) {
            if (coef != 0) {
                return false;
            }
        }
        return true;
    }
	
	/**
     * Évalue le polynôme pour une valeur donnée de x.
     *
     * Responsabilité :
     * Calculer P(x) en utilisant les coefficients stockés.
     *
     * @param x valeur pour laquelle on évalue le polynôme
     * @return résultat de l'évaluation
     */
	public double evaluer(double x) {
        double resultat = 0;
        for (int i = 0; i < degres.size(); i++) {
            resultat += degres.get(i) * Math.pow(x, i);
        }
        return resultat;
    }
		
	/**
     * Additionne deux polynômes.
     *
     * Responsabilité :
     * Calculer la somme de deux polynômes.
     *
     * @param polynome1 premier polynôme
     * @param polynome2 second polynôme
     * @return polynôme résultant de l'addition
     */
    public Polynome additionner(Polynome polynome1, Polynome polynome2) {
        int degreP1 = polynome1.getDegre(); // TODO ajuster pour tous les degrés
        int coefP1 = polynome1.getCoefficient(degreP1);
        int degreP2 = polynome2.getDegre();
        int coefP2 = polynome2.getCoefficient(degreP2);



        return polynomeFinal;
    }
  
    /**
     * Multiplie un polynôme par un scalaire.
     *
     * Responsabilité :
     * Appliquer une multiplication de chaque coefficient par un réel.
     *
     * @param polynome polynôme à multiplier
     * @param scalaire valeur réelle
     * @return polynôme résultant de la multiplication
     */
    public Polynome multiplier(Polynome polynome, double sclaire) {
        return polynomeFinal;
    }
  
    /**
     * Multiplie deux polynômes.
     *
     * Responsabilité :
     * Calculer le produit de deux polynômes.
     *
     * @param polynome1 premier polynôme
     * @param polynome2 second polynôme
     * @return polynôme résultant de la multiplication
     */
    public Polynome multiplier(Polynome polynome1, Polynome polynome2) {
        return polynomeFinal;
    }
  
    /**
     * Effectue la division euclidienne de deux polynômes.
     *
     * Responsabilité :
     * Calculer le quotient et le reste de la division.
     *
     * @param polynome1 dividende
     * @param polynome2 diviseur
     * @return résultat de la division
     */
    public Polynome diviser(Polynome polynome1, Polynome polynome2) {
        return polynomeFinal;
    }
  
    /**
     * Calcule la dérivée d'un polynôme.
     *
     * Responsabilité :
     * Appliquer la règle de dérivation terme à terme.
     *
     * @param polynome polynôme à dériver
     * @return polynôme dérivé
     */
    public Polynome deriver(Polynome polynome) {
        return polynomeFinal;
    }
  
    /**
     * Calcule une primitive (intégrale) du polynôme.
     *
     * Responsabilité :
     * Calculer l'intégrale terme à terme.
     *
     * @param polynome polynôme à intégrer
     * @return polynôme résultant
     */
    public Polynome integrer(Polynome polynome) {
        return polynomeFinal;
    }

    /**
     * Calcule la valeur moyenne de la fonction polynômiale associée.
     *
     * Responsabilité :
     * Calculer la moyenne.
     *
     * @param polynome polynôme à intégrer
     * @return moyenne du polynome
     */
    public Polynome moyenne(Polynome polynome) {
        return polynomeFinal;
    }
}
