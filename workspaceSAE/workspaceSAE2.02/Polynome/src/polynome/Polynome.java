/*
 * Polynome.java                                        05 mai 2026
 * IUT de Rodez, Info1 2025-2026, pas de copyright
 */
package polynome;

/**
 * Représente un polynôme à une variable réelle (x).
 * La classe stocke les coefficients du polynôme dans deux tableaux parallèles :
 * - coefficients[i] : la valeur du i-ème monôme non nul
 * - degres[i]       : le degré du i-ème monôme non nul
 * <p>
 * Exemple : 2x^120 + 42x + 4 se stocke ainsi :
 *   coefficients = {2.0, 42.0, 4.0}
 *   degres       = {120,   1,   0}
 * </p>
 * <p>
 * Responsabilité (SRP) :
 * - Stocker un polynôme
 * - Fournir des opérations mathématiques sur ce polynôme
 * </p>
 */
public class Polynome {

    /** Valeurs des monômes non nuls du polynôme */
    private double[] coefficients;

    /** Degrés des monômes non nuls du polynôme, parallèle à coefficients */
    private int[] degres;

    /**
     * Construit un polynôme à partir de deux tableaux parallèles
     * donnant les coefficients et les degrés des monômes non nuls.
     * <p>
     * Exemple : new Polynome(new double[]{2.0, 42.0, 4.0}, new int[]{120, 1, 0})
     *           représente P(x) = 2x^120 + 42x + 4
     * </p>
     * <p>
     * Exemple : new Polynome(new double[]{3.0, 4.0, 2.0}, new int[]{0, 1, 2})
     *           représente P(x) = 3 + 4x + 2x²
     * </p>
     * @param coefficients valeurs des monômes non nuls
     * @param degres       degrés correspondants (même longueur que coefficients)
     * @throws IllegalArgumentException si les tableaux sont vides
     *         ou n'ont pas la même longueur
     */
    public Polynome(double[] coefficients, int[] degres) {
        if (coefficients.length == 0 || degres.length == 0) {
            throw new IllegalArgumentException(
                "un polynôme doit avoir au moins un monôme");
        }
        if (coefficients.length != degres.length) {
            throw new IllegalArgumentException(
                "les tableaux coefficients et degres doivent avoir la même longueur");
        }
        this.coefficients = new double[coefficients.length];
        this.degres = new int[degres.length];
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
            this.degres[i] = degres[i];
        }
    }

    /**
     * Construit un polynôme à partir de ses racines réelles, de leur ordre
     * de multiplicité et du coefficient du monôme de plus haut degré.
     * L'algorithme part du coefficient dominant puis multiplie successivement
     * par chaque facteur (X - r) répété selon l'ordre de multiplicité.
     * <p>
     * Exemple : new Polynome(new double[]{3.0, -1.0}, new int[]{2, 1}, 2.0)
     *           représente P(x) = 2(x-3)²(x+1) = 2x³ - 16x² + 18
     * </p>
     * @param racines 		tableau des racines réelles du polynôme
     * @param ordres        ordres de multiplicité correspondants
     * @param coeffDominant coefficient du monôme de plus haut degré
     * @throws IllegalArgumentException si les tableaux sont vides,
     *         n'ont pas la même longueur, ou si coeffDominant est nul
     */
    public Polynome(double[] racines, int[] ordres, double coeffDominant) {
        if (racines.length == 0 || ordres.length == 0) {
            throw new IllegalArgumentException(
                "Il faut que lepolynôme ait au moins une racine");
        }
        if (racines.length != ordres.length) {
            throw new IllegalArgumentException(
                "Les tableaux racines et ordres doivent avoir la même longueur.");
        }
        if (coeffDominant == 0) {
            throw new IllegalArgumentException(
                "Le coefficient dominant ne peut pas être nul.");
        }

        /* Calcul du degré total => somme des ordres de multiplicité */
        int degreFinal = 0;
        for (int i = 0; i < ordres.length; i++) {
            degreFinal += ordres[i];
        }

        /*
         * Tableau temporaire de taille degreFinal + 1 pour les calculs.
         * L'indice d correspond au coefficient du monôme de degré d.
         * On convertira en tableaux parallèles à la fin.
         */
        double[] temp = new double[degreFinal + 1];

        /* On démarre avec juste le coefficient dominant : P = coeffDominant */
        temp[0] = coeffDominant;
        int degreActuel = 0;

        /* Pour chaque racine, on multiplie par (X - racine) autant de fois
         * que son ordre de multiplicité.
         */ 
        for (int i = 0; i < racines.length; i++) {
            for (int ordre = 0; ordre < ordres[i]; ordre++) {

                /* Multiplication de temp temporaire par (X - racines[i]) :
                 * on parcourt de droite à gauche pour ne pas écraser
                 * les valeurs dont on a encore besoin.
                 */
                for (int d = degreActuel; d >= 0; d--) {
                    temp[d + 1] += temp[d];          /* terme en X */
                    temp[d]     *= -racines[i];       /* terme constant */
                }
                degreActuel++;
            }
        }

        /* Comptage des monômes non nuls pour dimensionner les tableaux finaux */
        int nbNonNuls = 0;
        for (int d = 0; d <= degreFinal; d++) {
            if (temp[d] != 0) {
                nbNonNuls++;
            }
        }

        /* Cas dégénéré : polynôme entièrement nul, on garde au moins un terme */
        if (nbNonNuls == 0) {
            nbNonNuls = 1;
        }

        /* Remplissage des tableaux parallèles finaux */
        this.coefficients = new double[nbNonNuls];
        this.degres = new int[nbNonNuls];
        int index = 0;
        for (int d = 0; d <= degreFinal; d++) {
            if (temp[d] != 0) {
                this.coefficients[index] = temp[d];
                this.degres[index] = d;
                index++;
            }
        }
    }

    /**
     * Affiche les monômes non nuls du polynôme.
     */
    public void afficher() {
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println("coefficient degré " + degres[i]
                               + " : " + coefficients[i]);
        }
    }

    /**
     * Retourne le degré du polynôme,
     * c'est-à-dire le plus grand degré parmi les monômes stockés.
     *
     * @return degré du polynôme
     */
    public int getDegre() {
        int max = degres[0];
        for (int i = 1; i < degres.length; i++) {
            if (degres[i] > max) {
                max = degres[i];
            }
        }
        return max;
    }

    /**
     * Retourne le coefficient associé à un degré donné.
     *
     * @param degre degré du monôme souhaité
     * @return coefficient correspondant, 0.0 si ce degré n'est pas stocké
     * @throws IllegalArgumentException si degre est négatif
     */
    public double getCoefficient(int degre) {
        if (degre < 0) {
            throw new IllegalArgumentException(
                "le degré ne peut pas être négatif");
        }
        for (int i = 0; i < degres.length; i++) {
            if (degres[i] == degre) {
                return coefficients[i];
            }
        }
        return 0.0;
    }

    /**
     * Retourne la limite de la fonction polynômiale associée en +infini.
     *
     * @return limite du polynôme en +infini
     */
    public double getLimitePlusInfini() {
        double coef = getCoefficient(getDegre());

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
        double coef = getCoefficient(degre);

        if (degre % 2 == 0) {
            /* Degré pair : même limite qu'en +infini */
            if (coef > 0) {
                return Double.POSITIVE_INFINITY;
            } else {
                return Double.NEGATIVE_INFINITY;
            }
        } else {
            /* Degré impair : limite opposée à celle en +infini */
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
            resultat += coefficients[i] * Math.pow(x, degres[i]);
        }
        return resultat;
    }

    /**
     * Additionne ce polynôme avec un autre polynôme.
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
     * @return polynôme dérivé
     */
    public Polynome deriver() {
        // TODO faire
        return null;
    }

    /**
     * Calcule une primitive de ce polynôme (constante additive nulle).
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
     * @param a borne inférieure de l'intervalle
     * @param b borne supérieure de l'intervalle
     * @return valeur moyenne de P sur [a, b]
     */
    public double moyenne(double a, double b) {
        // TODO faire
        return 0;
    }
}
