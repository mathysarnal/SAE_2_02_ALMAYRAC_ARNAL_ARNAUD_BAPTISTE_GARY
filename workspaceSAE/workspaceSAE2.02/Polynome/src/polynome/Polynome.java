/*
 * Polynome.java                                            13 avril 2026
 * IUT de Rodez, Info1 2025-2026, pas de copyright
 */
package polynome;

/**
 * Représente un polynôme à une variable réelle (x).
 * La classe stocke les coefficients du polynôme dans deux tableaux parallèles :
 * <ul>
 *   <li>coefficients[i] : la valeur du i-ème monôme non nul</li>
 *   <li>degres[i] : le degré du i-ème monôme non nul</li>
 * </ul>
 * <p>
 * Exemple : 2x^120 + 42x + 4 se stocke ainsi :
 *   coefficients = {2.0, 42.0, 4.0}
 *   degres = {120, 1, 0}
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
    
    /** Racines réelles du polynôme */
    private double[] racinesReelles;
    
    /** Ordres de la racines */
    private int[] ordresRacines;

    /**
     * Construit un polynôme à partir de deux tableaux parallèles
     * donnant les coefficients et les degrés des monômes non nuls.
     * <p>
     * Exemple : new Polynome(new double[]{2.0, 42.0, 4.0}, new int[]{120, 1, 0})
     *           représente P(x) = 2x^120 + 42x + 4
     * </p>
     * <p>
     * Exemple : new Polynome(new double[]{3.0, 4.0, 2.0}, new int[]{0, 1, 2})
     *           représente P(x) = 3 + 4x + 2x^2
     * </p>
     * @param coefficients valeurs des monômes non nuls
     * @param degres degrés correspondants (même longueur que coefficients)
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
     *           représente P(x) = 2(x-3)²(x+1) = 2x³ - 10x² + 6x + 18
     * </p>
     * @param racines tableau des racines réelles du polynôme
     * @param ordres ordres de multiplicité correspondants
     * @param coeffDominant coefficient du monôme de plus haut degré
     * @throws IllegalArgumentException si les tableaux sont vides,
     *         n'ont pas la même longueur, ou si coeffDominant est nul
     */
    public Polynome(double[] racines, int[] ordres, double coeffDominant) {
        if (racines.length == 0 || ordres.length == 0) {
            throw new IllegalArgumentException(
                "Il faut que le polynôme ait au moins une racine.");
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
        
        /* Stockage des racines en mémoire */
        this.racinesReelles = racines.clone();
        this.ordresRacines = ordres.clone();
    }

    /**
     * Retourne le degré du polynôme,
     * c'est-à-dire le plus grand degré parmi les monômes stockés.
     *
     * @return degré du polynôme
     */
    public int getDegre() {
        int degreMax = 0;
        
        for (int indice = 0; indice < degres.length; indice++) {
            if (degres[indice] > degreMax) {
                degreMax = degres[indice];
            }
        }
        return degreMax;
    }

    /**
     * Retourne le coefficient associé à un degré donné.
     *
     * @param degre degré du monôme souhaité
     * @return coefficient correspondant, 0.0 si ce degré n'est pas stocké
     * @throws IllegalArgumentException si degre est négatif
     */
    public double getCoefficient(int degreRecherche) {
		if (degreRecherche < 0) {
			throw new IllegalArgumentException("Le degré doit être positif ou nul.");
		}
        for (int indice = 0; indice < degres.length; indice++) {
        	if (degres[indice] == degreRecherche) {
        		return coefficients[indice];
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
     * Retourne les racines réelles mémorisées lors de la construction
     * du polynôme par racines.
     *
     * Si le polynôme a été construit à partir de coefficients,
     * un tableau vide est renvoyé.
     *
     * @return copie des racines réelles mémorisées
     */
    public double[] getRacinesReelles() {
    	// Cas où le polynôme a été créé par coefficients
    	if (this.racinesReelles == null) { 
    		return new double[0]; 
    	} else { // Cas où le polynôme a été créé par racines
    		return this.racinesReelles.clone();
    	}
    }
    
    public int[] getOrdresRacines() {
        if (this.ordresRacines == null) {
            return new int[0];
        }
        return this.ordresRacines.clone();
    }
    
    /**
     * Retourne une copie du tableau des coefficients des monômes non nuls.
     * @return tableau des coefficients
     */
    public double[] getCoefficients() {
        return this.coefficients.clone();
    }

    /**
     * Retourne une copie du tableau des degrés des monômes non nuls.
     * @return tableau des degrés
     */
    public int[] getDegres() {
        return this.degres.clone();
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
        double total = 0;
        for (int indice = 0; indice < coefficients.length; indice++) {
        	
        	double coeffActuel = coefficients[indice];
        	int puissanceActuel = degres[indice];
        	
            total += coeffActuel * Math.pow(x, puissanceActuel);
        }
        return total;
    }
    
    /**
     * Évalue le polynôme pour une valeur donnée de x
     * à l'aide de la méthode de Horner.
     *
     * @param x valeur pour laquelle évaluer le polynôme
     * @return valeur de P(x)
     */
    
    public double evaluerHorner(double x) {
    	if(this.coefficients.length == 0 || (this.getDegre() == 0 && this.coefficients[0] == 0.0)) {
    		return 0.0;
    	}
    	
    	int degreMax = this.getDegre();
    	double resultat = 0.0;
    	
    	for(int degre = degreMax; degre >= 0; degre--) {
    		resultat = resultat * x + this.getCoefficient(degre);
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
        int degreMax = Math.max(this.getDegre(),
                                autrePolynome.getDegre());
        
        double[] coefficients = new double[degreMax + 1];        
        for (int degre = 0; degre <= degreMax; degre++) {
            coefficients[degre] = this.getCoefficient(degre) + autrePolynome.getCoefficient(degre);
        }
        int nbMonomesNonNuls = 0;
        for(int degre = 0; degre <= degreMax; degre++) {
        	if(coefficients[degre] != 0.0)
            nbMonomesNonNuls++;
        }
        
        if(nbMonomesNonNuls == 0) {
        	double[] coefNul = new double[] { 0.0 };
    		int[] degNul =  new int[] { 0 };
    		
        	Polynome pNul = new Polynome(coefNul, degNul);
        	
        	return pNul;
        }
        
        double[] nouveauxCoefs = new double[nbMonomesNonNuls];
        int[] nouveauxDegres = new int[nbMonomesNonNuls];
        
        int insertion = 0;
        for(int degre = degreMax; degre >= 0; degre--) {
        	if (coefficients[degre] != 0.0) {
        		nouveauxCoefs[insertion] = coefficients[degre];
        		nouveauxDegres[insertion] = degre;
        		insertion++;
        	}
        }
        
        return new Polynome(nouveauxCoefs, nouveauxDegres);
    }

    /**
     * Multiplie ce polynôme par un scalaire réel.
     *
     * @param scalaire valeur réelle par laquelle multiplier
     * @return polynôme résultant de la multiplication
     */
    public Polynome multiplierScalaire(double scalaire) {
        double[] nouveauxCoefs = new double[this.coefficients.length];

        for (int indice = 0; indice < this.coefficients.length; indice++) {
            nouveauxCoefs[indice] = this.coefficients[indice] * scalaire;
        }

        return new Polynome(nouveauxCoefs, this.degres);
    }

    /**
     * Multiplie ce polynôme par un autre polynôme.
     *
     * @param autrePolynome polynôme multiplicateur
     * @return polynôme résultant de la multiplication
     */
    public Polynome multiplierPolynome(Polynome autrePolynome) {
        int degMax1 = this.getDegre();
        int degMax2 = autrePolynome.getDegre();
        int degMaxResultat = degMax1 + degMax2;

        double[] resultat = new double[degMaxResultat + 1];
       
        for (int monomep1 = 0; monomep1 < this.coefficients.length; monomep1++) {
        	double coef1 = this.coefficients[monomep1];
        	int deg1 = this.degres[monomep1];
        	
        	for(int monomep2 = 0; monomep2 < autrePolynome.coefficients.length; monomep2++) {
        		double coef2 = autrePolynome.coefficients[monomep2];
        		int deg2 = autrePolynome.degres[monomep2];
        		
        		int degreFinal = deg1 + deg2;
        		double coefFinal = coef1 * coef2;
        		
        		resultat[degreFinal] += coefFinal;
        	}
        }
        
        int nbMonomesNonNul = 0;
        for(int nbCaseNul = 0; nbCaseNul <= degMaxResultat; nbCaseNul++) {
        	if(resultat[nbCaseNul] != 0.0) {
        		nbMonomesNonNul++;
        	}
        }
        
        if(nbMonomesNonNul == 0) {
        	double[] coefNul = new double[] { 0.0 };
    		int[] degNul =  new int[] { 0 };
    		
        	Polynome pNul = new Polynome(coefNul, degNul);
        	
        	return pNul;
        }
        
        double[] coefficientsFinaux = new double[nbMonomesNonNul];
        int[] degresFinaux = new int[nbMonomesNonNul];
        
        int insertion = 0;
        for(int degres = degMaxResultat; degres >= 0; degres--) {
        	if(resultat[degres] != 0.0) {
        		coefficientsFinaux[insertion] = resultat[degres];
        		degresFinaux[insertion] = degres;
        		insertion++;
        	}
        }
        
        return new Polynome(coefficientsFinaux, degresFinaux);
    }
    

    /**
     * Change le signe du polynôme en son opposé.
     *
     * @return polynome avec signe opposé
     */
    public Polynome opposer() {
        double[] newCoef = new double[this.coefficients.length];
        int[] newDeg = this.degres;

        for (int i = 0; i < coefficients.length; i++) {
            newCoef[i] = -coefficients[i];
        }
        return new Polynome(newCoef, newDeg);
    }
    
    
    
    /**
     * Effectue la division euclidienne de ce polynôme par un autre.
     *
     * @param diviseur polynôme diviseur
     * @return quotient et reste de la division euclidienne
     *         sous la forme d'un tableau de deux polynômes : [quotient, reste]
     */
    public Polynome[] diviser(Polynome diviseur) {
        if (diviseur.estNul()) {
        	throw new ArithmeticException("Division par 0 impossible !");
        }
        
        Polynome reste = new Polynome(this.coefficients, this.degres);
        Polynome quotient = new Polynome(new double[]{0.0}, new int[]{0});
        double coefDiviseurDePlusHautDegre = diviseur.getCoefficient(diviseur.getDegre());
        
        while (reste.getDegre() >= diviseur.getDegre()
        	   && !reste.estNul()) {
        	double alpha = reste.getCoefficient(reste.getDegre())
        			/ coefDiviseurDePlusHautDegre;
        	int differenceDegres = reste.getDegre() - diviseur.getDegre();
        	Polynome polynome = new Polynome(new double[]{alpha}, new int[]{differenceDegres});
        	quotient = quotient.additionner(polynome);
        	reste = reste.additionner((polynome.multiplierPolynome(diviseur)).opposer());
        }
        
        Polynome[] resultat = new Polynome[2];
        resultat[0] = quotient;
        resultat[1] = reste;

        return resultat;
    }
    
    /**
     * Calcule la suite de Sturm associée au polynôme.
     *
     * @return liste des polynômes composant la suite de Sturm
     */
    public java.util.List<Polynome> calculSuiteSturm() {
    	java.util.List<Polynome> suite = new java.util.ArrayList<>();
    	
    	suite.add(this);
    	
    	if(this.estNul() || this.getDegre() == 0) {
    		return suite;
    	}
    	
    	suite.add(this.deriver());
    	
    	Polynome reste;
    	
    	do {
    		Polynome avantDernier = suite.get(suite.size() - 2);
    		Polynome dernier = suite.get(suite.size() - 1);
    		
    		reste = avantDernier.diviser(dernier)[1];
    		
    		if(!reste.estNul()) {
    			suite.add(reste.opposer());
    		}
    	} while (!reste.estNul());
    	return suite;
    }

    /**
     * Compte le nombre de changements de signe dans la suite
     * de Sturm évaluée en un point donné.
     *
     * @param x point d'évaluation
     * @return nombre de changements de signe
     */
    public int compterChangementSigne(double x) {
    	java.util.List<Polynome> suiteSturm = this.calculSuiteSturm();
    	java.util.List<Double> resultat = new java.util.ArrayList<>();
    	
    	for (Polynome polynome : suiteSturm) {
    		double evaluation = polynome.evaluerHorner(x);
    		
    		if (evaluation != 0.0) {
    			resultat.add(evaluation);
    		}
    	}
    	
    	int changement = 0;
    	for (int position = 0; position < resultat.size() - 1; position++) {
    		double valeur = resultat.get(position);
    		double valeurSuivante = resultat.get(position + 1);
    		
    		if (( valeur > 0 && valeurSuivante < 0) || (valeur < 0 && valeurSuivante > 0)) {
    			changement++;
    		}
    	}
    	return changement;
    }
    
    /**
     * Compte le nombre de racines réelles distinctes dans l'intervalle [borneA, borneB]
     * en utilisant le théorème de Sturm.
     * @param borneA la borne inférieure de l'intervalle
     * @param borneB la borne supérieure de l'intervalle
     * @return le nombre de racines réelles dans cet intervalle
     * @throws IllegalArgumentException si borneA est supérieure à borneB
     */
    public int compterRacinesIntervalle(double borneA, double borneB) {
        if (borneA > borneB) {
            throw new IllegalArgumentException("La borne A doit être inférieure ou égale à la borne B.");
        }
        
        int changementsEnA = this.compterChangementSigne(borneA);
        int changementsEnB = this.compterChangementSigne(borneB);
        
        return changementsEnA - changementsEnB;
    }
    
    /**
     * Calcule la dérivée du polynôme.
     *
     * @return dérivée du polynôme
     */
    public Polynome deriver() {
        int nbMonomesNonNuls = 0;
        for (int i = 0; i < this.degres.length; i++) {
            if (this.degres[i] > 0 && this.coefficients[i] != 0.0) {
                nbMonomesNonNuls++;
            }
        }
        
        if (nbMonomesNonNuls == 0) {
            return new Polynome(new double[] { 0.0 }, new int[] { 0 });
        }

        // Dimensionnement à la taille exacte !
        double[] nouveauxCoefs = new double[nbMonomesNonNuls];
        int[] nouveauxDegres = new int[nbMonomesNonNuls];
        int insertion = 0;

        for (int i = 0; i < this.degres.length; i++) {
            if (this.degres[i] > 0) {
                double nouveauCoef = this.coefficients[i] * this.degres[i];
                if (nouveauCoef != 0.0) {
                    nouveauxCoefs[insertion] = nouveauCoef;
                    nouveauxDegres[insertion] = this.degres[i] - 1;
                    insertion++;
                }
            }
        }
        return new Polynome(nouveauxCoefs, nouveauxDegres);
    }
    
    /**
     * Calcule une primitive du polynôme
     * en prenant la constante d'intégration égale à 0,
     * afin de faciliter la manipulation du polynôme obtenu
     * (par exemple pour calculer une moyenne sur un intervalle).
     *
     * @return primitive du polynôme
     */
    public Polynome integrer() {
    	double[] nouveauxCoefs = new double[this.coefficients.length];
        int[] nouveauxDegres = new int[this.degres.length];
    
        for (int i = 0; i < this.coefficients.length; i++) {
            nouveauxDegres[i] = this.degres[i] + 1;
            nouveauxCoefs[i] = this.coefficients[i] / nouveauxDegres[i];
        }
    
        return new Polynome(nouveauxCoefs, nouveauxDegres);
    }
    /**
     * Calcule la valeur moyenne de la fonction polynômiale associée
     * sur un intervalle donné.
     *
     * @param a borne inférieure de l'intervalle
     * @param b borne supérieure de l'intervalle
     * @return valeur moyenne de P sur [a, b]
     * @throws IllegalArgumentException si a == b (intervalle de longueur nulle)
     */
    public double moyenne(double a, double b) {  
    	if (a == b) {
    	    throw new IllegalArgumentException("Impossible de calculer une moyenne sur un intervalle de longueur nulle.");
    	}
    	Polynome primitive = this.integrer();
    	double integrale = primitive.evaluer(b) - primitive.evaluer(a);
    	
    	return integrale / (b - a);
    }
    
    /**
     * Retourne une représentation textuelle du polynôme.
     * Les monômes sont affichés sous une forme algébrique
     * lisible, par exemple : "2.0x^5 + 4.0x^3 - 5.0".
     *
     * @return représentation du polynôme sous forme de chaîne
     */
    @Override
    public String toString() {
        String resultat = "";

        for (int i = 0; i < coefficients.length; i++) {

            if (coefficients[i] == 0) {
                continue;
            }

            // Signe
            if (!resultat.isEmpty() && coefficients[i] > 0) {
                resultat += " + ";
            } else if (coefficients[i] < 0) {
                resultat += " - ";
            }

            double coeff = Math.abs(coefficients[i]);

            // Coefficient
            if (!(coeff == 1 && degres[i] != 0)) {
                resultat += coeff;
            }

            // x et puissance
            if (degres[i] > 0) {
                resultat += "x";

                if (degres[i] > 1) {
                    resultat += "^" + degres[i];
                }
            }
        }

        if (resultat.isEmpty()) {
            return "0";
        }
        
        return resultat;
    }

}