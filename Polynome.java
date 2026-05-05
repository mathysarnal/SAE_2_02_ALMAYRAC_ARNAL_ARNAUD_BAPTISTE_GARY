/*
 * Polynome.java                                        27 avril 2026
 * IUT de Rodez, Info1 2025-2026, pas de copyright
 */
package polynome;

/**
 * Représente un polynôme à une variable réelle (x).
 *
 * La classe stocke les coefficients du polynôme dans un tableau de réels,
 * où l'indice correspond au degré du monôme associé.
 *
 * Exemple : Polynome(3.0, 4.0, 2.0) représente P(x) = 3 + 4x + 2x²
 *
 * Responsabilité (SRP) :
 * - Stocker un polynôme
 * - Fournir des opérations mathématiques sur ce polynôme
 */
public class Polynome {

    /** coefficients du polynôme, l'indice i correspond au degré i */
    private double[] coefficients;

    /**
     * Construit un polynôme à partir de ses coefficients.
     *
     * Chaque argument correspond au coefficient du monôme de degré égal à son index.
     *
     * Exemple : new Polynome(3.0, 4.0, 2.0) => P(x) = 3 + 4x + 2x²
     *
     * @param coefficients coefficients du polynôme (du degré 0 au plus grand)
     * @throws IllegalArgumentException si aucun coefficient n'est fourni
     */
    public Polynome(double... coefficients) {
        if (coefficients.length == 0) {
            throw new IllegalArgumentException("un polynôme doit avoir au moins un coefficient");
        }
        this.coefficients = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
        }
    }

    /**
     * Affiche les coefficients du polynôme.
     */
    public void afficher() {
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println("coefficient degré " + i + " : " + coefficients[i]);
        }
    }

    /**
     * Retourne le degré du polynôme,
     * c'est-à-dire l'indice du coefficient non nul de plus haut rang.
     *
     * @return degré du polynôme, 0 si polynôme nul
     */
    public int getDegre() {
        for (int degre = coefficients.length - 1; degre >= 0; degre--) {
            if (coefficients[degre] != 0) {
                return degre;
            }
        }
        return 0;
    }

    /**
     * Retourne le coefficient associé à un degré donné.
     *
     * @param degre degré du monôme souhaité
     * @return coefficient correspondant, 0 si degré supérieur au degré du polynôme
     * @throws IllegalArgumentException si degre est négatif
     */
    public double getCoefficient(int degre) {
        if (degre < 0) {
            throw new IllegalArgumentException("le degré ne peut pas être négatif");
        }
        if (degre >= coefficients.length) {
            return 0;
        }
        return coefficients[degre];
    }

    /**
     * Retourne la limite de la fonction polynômiale associée en +infini.
     *
     * @return limite du polynôme en +infini
     */
    public double getLimitePlusInfini() {
        double coef = coefficients[getDegre()];

        if (coef > 0) {
            return Double.POSITIVE_INFINITY;
        } else if (coef < 0) {
            return Double.NEGATIVE_INFINITY;
        }
        return 0;
    }

    /**
     * Retourne la limite de la fonction polynômiale associée en -infini.
     *
     * @return limite du polynôme en -infini
     */
    public double getLimiteMoinsInfini() {
        int degre = getDegre();
        double coef = coefficients[degre];

        if (degre % 2 == 0) {
            /* degré pair : même limite qu'en +infini */
            if (coef > 0) {
                return Double.POSITIVE_INFINITY;
            } else {
                return Double.NEGATIVE_INFINITY;
            }
        } else {
            /* degré impair : limite opposée à celle en +infini */
            if (coef > 0) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        }
    }

    /**
     * Indique si le polynôme est nul (tous les coefficients sont nuls).
     *
     * @return true si tous les coefficients sont nuls, false sinon
     */
    public boolean estNul() {
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Évalue le polynôme pour une valeur donnée de x.
     *
     * @param x valeur pour laquelle on évalue le polynôme
     * @return résultat de l'évaluation P(x)
     */
    public double evaluer(double x) {
        double resultat = 0;
        for (int i = 0; i < coefficients.length; i++) {
            resultat += coefficients[i] * Math.pow(x, i);
        }
        return resultat;
    }

    /**
     * Additionne ce polynôme avec un autre polynôme.
     *
     * Responsabilité :
     * Calculer la somme de deux polynômes.
     *
     * @param autrePolynome polynôme à additionner
     * @return polynôme résultant de l'addition
     */
    public Polynome additionner(Polynome autrePolynome) {
        // TODO faire
        return null;
    }

    /**
     * Multiplie ce polynôme par un scalaire réel.
     *
     * Responsabilité :
     * Appliquer une multiplication de chaque coefficient par un réel.
     *
     * @param scalaire valeur réelle par laquelle multiplier
     * @return polynôme résultant de la multiplication
     */
    public Polynome multiplier(double scalaire) {
        // TODO faire
        return null;
    }

    /**
     * Multiplie ce polynôme par un autre polynôme.
     *
     * Responsabilité :
     * Calculer le produit de deux polynômes.
     *
     * @param autrePolynome polynôme multiplicateur
     * @return polynôme résultant de la multiplication
     */
    public Polynome multiplier(Polynome autrePolynome) {
        // TODO faire
        return null;
    }

    /**
     * Effectue la division euclidienne de ce polynôme par un autre.
     *
     * Responsabilité :
     * Calculer le quotient de la division euclidienne.
     *
     * @param diviseur polynôme diviseur
     * @return quotient de la division euclidienne
     */
    public Polynome diviser(Polynome diviseur) {
        // TODO faire
        return null;
    }

    /**
     * Calcule la dérivée de ce polynôme.
     *
     * Responsabilité :
     * Appliquer la règle de dérivation terme à terme.
     *
     * @return polynôme dérivé
     */
    public Polynome deriver() {
        // TODO faire
        return null;
    }

    /**
     * Calcule une primitive de ce polynôme (constante additive nulle).
     *
     * Responsabilité :
     * Calculer l'intégrale terme à terme.
     *
     * @return polynôme primitif (constante d'intégration = 0)
     */
    public Polynome integrer() {
        // TODO faire
        return null;
    }

    /**
     * Calcule la valeur moyenne de la fonction polynômiale associée
     * sur un intervalle donné.
     *
     * Responsabilité :
     * Calculer la moyenne de P sur [a, b].
     *
     * @param a borne inférieure de l'intervalle
     * @param b borne supérieure de l'intervalle
     * @return valeur moyenne de P sur [a, b]
     */
    public double moyenne(double a, double b) {
        // TODO faire
        return 0;
    }
}
