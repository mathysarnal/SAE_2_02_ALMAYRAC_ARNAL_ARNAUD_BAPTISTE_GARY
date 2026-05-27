package polynome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolynomeTest {

    private static final double precision = 1e-6;
    
    // Attributs pour le constructeur classique
    private Polynome polynomeUn;
    private Polynome polynomeDeux;
    private Polynome polynomeTrois;
    private Polynome polynomeQuatre;
    private Polynome polynomeCinq;
    private Polynome polynomeSix;
    private Polynome polynomeSept;
    
    // Attributs pour les polynômes construits par racines
    private Polynome polynomeRacinesUn;
    private Polynome polynomeRacinesDeux;
    private Polynome polynomeRacinesTrois;
    private Polynome polynomeRacinesQuatre;
    private Polynome polynomeRacinesCinq;
    
    private double[] racinesUn; // = { 0.0 }
    private double[] racinesDeux; // = { 1.0, 2.0 }
    private double[] racinesTrois; // = { 3.0 }
    private double[] racinesQuatre; // = { -1.0 }
    private double[] racinesCinq; // = { 2.0, -2.0 }  
    
    @BeforeEach
    void setUp() {
        // Premier polynome 0.0
        double[] coefficientsUn = new double[] { 0.0 };
        int[] degresUn =  new int[] { 0 };
        polynomeUn = new Polynome(coefficientsUn, degresUn);
        
        // Deuxième polynome 2 + 3x^100
        double[] coefficientsDeux = new double[] { 2.0, 3.0 };
        int[] degresDeux = new int[] {0, 100};
        polynomeDeux = new Polynome(coefficientsDeux, degresDeux);
        
        // Troisième polynome 2 + 4x + 5x^2
        double[] coefficientsTrois = new double[] { 2.0, 4.0, 5.0 };
        int[] degresTrois = new int[] {0, 1, 2};
        polynomeTrois = new Polynome(coefficientsTrois, degresTrois);
        
        // Quatrième polynome -3 - 2x
        double[] coefficientsQuatre = new double[] { -3.0, -2.0 };
        int[] degresQuatre = new int[] { 0, 1 };
        polynomeQuatre = new Polynome(coefficientsQuatre, degresQuatre);
        
        // Cinquième polynome 5.0
        double[] coefficientsCinq = new double[] { 5.0 };
        int[] degresCinq = new int[] { 0 };
        polynomeCinq = new Polynome(coefficientsCinq, degresCinq);
        
        // Sixième polynome 4.0x^3
        double[] coefficientsSix = new double[] { 4.0 };
        int[] degresSix = new int[] { 3 };
        polynomeSix = new Polynome(coefficientsSix, degresSix);
        
        // Septième polynome 1 -2x + 5x^2 -3x^4
        double[] coefficientsSept = new double[] {1.0, -2.0, 5.0, -3.0};
        int[] degresSept = new int[] {0, 1, 2, 4};
        polynomeSept = new Polynome(coefficientsSept, degresSept);

        // Premier polynôme par racines
        int[] ordresUn = { 1 };
        double coeffDominantUn = 2.0;
        racinesUn = new double[] { 0.0 };
        polynomeRacinesUn = new Polynome(racinesUn, ordresUn, coeffDominantUn);

        // Deuxième polynôme par racines
        int[] ordresDeux = { 1, 1 };
        double coeffDominantDeux = 3.0;
        racinesDeux = new double[] { 1.0, 2.0 };
        polynomeRacinesDeux = new Polynome(racinesDeux, ordresDeux, coeffDominantDeux);

        // Troisième polynôme par racines
        int[] ordresTrois = { 2 };
        double coeffDominantTrois = 1.0;
        racinesTrois = new double[] { 3.0 };
        polynomeRacinesTrois = new Polynome(racinesTrois, ordresTrois, coeffDominantTrois);

        // Quatrième polynôme par racines
        int[] ordresQuatre = { 1 };
        double coeffDominantQuatre = -2.0;
        racinesQuatre = new double[] { -1.0 };
        polynomeRacinesQuatre = new Polynome(racinesQuatre, ordresQuatre, coeffDominantQuatre);

        // Cinquième polynôme par racines
        int[] ordresCinq = { 1, 1 };
        double coeffDominantCinq = 0.5;
        racinesCinq = new double[] { 2.0, -2.0 };
        polynomeRacinesCinq = new Polynome(racinesCinq, ordresCinq, coeffDominantCinq);
    }
    
    @Test
    void testCreationEtDegre() {
        assertEquals(0, polynomeUn.getDegre(), "Le degré devrait être de 0.");
        assertEquals(100, polynomeDeux.getDegre(), "Le degré devrait être de 100.");
        assertEquals(2, polynomeTrois.getDegre(), "Le degré devrait être de 2.");
        assertEquals(1, polynomeQuatre.getDegre(), "Le degré devrait être de 1");
        assertEquals(0, polynomeCinq.getDegre(), "Le degré devrait être de 0.");
        assertEquals(3, polynomeSix.getDegre(), "Le degré devrait être de 3."); 
        assertEquals(4, polynomeSept.getDegre(), "Le degré devrait être de 4.");
    }
    
    @Test
    void testConstructeurTableauxVides() {
        double[] coeffsVides = new double[0];
        int[] degsVides = new int[0];
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Polynome(coeffsVides, degsVides);
        });
        
        assertEquals("un polynôme doit avoir au moins un monôme", exception.getMessage());
    }
    
    @Test
    void testConstructeurTableauxNonParalleles() {
        double[] coeffsNonPara = {2.0, 3.1};
        int[] degsNonPara = {5};
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Polynome(coeffsNonPara, degsNonPara);
        });
        
        assertEquals("les tableaux coefficients et degres doivent avoir la même longueur", exception.getMessage());
    }
    
    @Test
    void testConstructeurTableauxVidesRacines() {
        double[] racinesT = new double[0];
        int[] ordresT = new int[0];
        double coeffDominantT = 0.5;
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Polynome(racinesT, ordresT, coeffDominantT);
        });
        
        assertEquals("Il faut que le polynôme ait au moins une racine.", exception.getMessage());
    }
    
    @Test
    void testConstructeurTableauxNonParallelesRacines() {
        double[] racinesNonPara = {2.0, 3.5};
        int[] ordresNonPara = {1};
        double coeffDominantT = 9;
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Polynome(racinesNonPara, ordresNonPara, coeffDominantT);
        });
        
        assertEquals("Les tableaux racines et ordres doivent avoir la même longueur.", exception.getMessage());
    }
    
    @Test
    void testConstructeurCoeffDominantNul() {
        double[] racinesT = {2.0};
        int[] ordresT = {1};
        double coeffDominantNul = 0;
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Polynome(racinesT, ordresT, coeffDominantNul);
        });
        
        assertEquals("Le coefficient dominant ne peut pas être nul.", exception.getMessage());
    }
    
    @Test
    void testConstructeurRacines_CasDegenere_nbNonNulsEgalZero() {
        double[] racines = { 0.0 };
        int[] ordres = { 0 }; 
        double coeffDominant = 0.0000000001; 

        Polynome p = new Polynome(racines, ordres, coeffDominant);
        assertNotNull(p);
    }
    
    @Test
    void testConstructeurRacines_CasNormal_nbNonNulsSuperieurA_Zero() {
        double[] racines = { 3.0 };
        int[] ordres = { 1 };
        double coeffDominant = 2.0;

        Polynome p = new Polynome(racines, ordres, coeffDominant);
        assertEquals(2.0, p.getCoefficient(1), 1e-9);
    }
    
    @Test
    void testGetCoefficient() {
        assertEquals(0.0, polynomeUn.getCoefficient(0));
    
        assertEquals(2.0, polynomeDeux.getCoefficient(0));
        assertEquals(0.0, polynomeDeux.getCoefficient(1));
        assertEquals(3.0, polynomeDeux.getCoefficient(100));
        
        assertEquals(2.0, polynomeTrois.getCoefficient(0));
        assertEquals(4.0, polynomeTrois.getCoefficient(1));
        assertEquals(5.0, polynomeTrois.getCoefficient(2));
        
        assertEquals(-3.0, polynomeQuatre.getCoefficient(0));
        assertEquals(-2.0, polynomeQuatre.getCoefficient(1));
        
        assertEquals(5.0, polynomeCinq.getCoefficient(0));
        assertEquals(0.0, polynomeCinq.getCoefficient(1));
        
        assertEquals(0.0, polynomeSix.getCoefficient(0));
        assertEquals(4.0, polynomeSix.getCoefficient(3));
        
        assertEquals(1.0, polynomeSept.getCoefficient(0));
        assertEquals(-2.0, polynomeSept.getCoefficient(1));
        assertEquals(5.0, polynomeSept.getCoefficient(2));
        assertEquals(-3.0, polynomeSept.getCoefficient(4));
    }
    
    /**
     * Vérifie la récupération des racines réelles d'un polynôme.
     * Teste deux cas :
     *      - 1 : polynôme créé avec coefficients
     *      - 2 : polynôme créé avec racines
     * Teste si la méthode retourne un tableau vide dans le cas 1,
     * ou les racines fournies dans le cas 2.
     */
    
    @Test
    void testGetRacinesReelles() {  
        // Cas 1 : polynôme créé avec coefficients
        assertEquals(0, polynomeUn.getRacinesReelles().length);
        assertEquals(0, polynomeDeux.getRacinesReelles().length);
        assertEquals(0, polynomeTrois.getRacinesReelles().length);
        assertEquals(0, polynomeQuatre.getRacinesReelles().length);
        assertEquals(0, polynomeCinq.getRacinesReelles().length);
        
        // Cas 2 : polynôme créé avec racines 
        assertArrayEquals(racinesUn, polynomeRacinesUn.getRacinesReelles(), precision);
        assertArrayEquals(racinesDeux, polynomeRacinesDeux.getRacinesReelles(), precision);
        assertArrayEquals(racinesTrois, polynomeRacinesTrois.getRacinesReelles(), precision);
        assertArrayEquals(racinesQuatre, polynomeRacinesQuatre.getRacinesReelles(), precision);
        assertArrayEquals(racinesCinq, polynomeRacinesCinq.getRacinesReelles(), precision);
    }
    
    @Test
    void testEvaluerSimple() {
        assertEquals(0.0, polynomeUn.evaluer(2.0), precision);
        assertEquals(5.0, polynomeDeux.evaluer(1.0), precision);
        assertEquals(59.0, polynomeTrois.evaluer(3.0), precision);
        assertEquals(-19.0, polynomeQuatre.evaluer(8.0), precision);
        assertEquals(5.0, polynomeCinq.evaluer(6.0), precision);
        assertEquals(256.0, polynomeSix.evaluer(4.0), precision);
        assertEquals(1.0, polynomeSept.evaluer(0.0), precision);
    }
    
    @Test
    void testEvaluerHorner() {
        assertEquals(0.0, polynomeUn.evaluerHorner(2.0), precision);
        assertEquals(5.0, polynomeDeux.evaluerHorner(1.0), precision);
        assertEquals(59.0, polynomeTrois.evaluerHorner(3.0), precision);
        assertEquals(-19.0, polynomeQuatre.evaluerHorner(8.0), precision);
        assertEquals(5.0, polynomeCinq.evaluerHorner(6.0), precision);
        assertEquals(6912.0, polynomeSix.evaluerHorner(12.0), precision);
        assertEquals(-679.0, polynomeSept.evaluerHorner(-4.0), precision);
    }
    
    @Test 
    void testPolynomeNul() {
        assertTrue(polynomeUn.estNul(), "Le polynôme devrait être nul.");
        assertFalse(polynomeDeux.estNul(), "Le polynôme ne devrait pas être nul.");
        assertFalse(polynomeTrois.estNul(), "Le polynôme ne devrait pas être nul.");
        assertFalse(polynomeQuatre.estNul(), "Le polynôme ne devrait pas être nul.");
        assertFalse(polynomeCinq.estNul(), "Le polynôme ne devrait pas être nul.");
        assertFalse(polynomeSix.estNul(), "Le polynôme ne devrait pas être nul.");
        assertFalse(polynomeSept.estNul(), "Le polynome ne devrait pas être nul");
    }
        
    @Test
    void testAdditionner() {
        Polynome resultat1 = polynomeTrois.additionner(polynomeUn);
        assertEquals(2.0, resultat1.getCoefficient(0), precision);
        assertEquals(4.0, resultat1.getCoefficient(1), precision);
        assertEquals(5.0, resultat1.getCoefficient(2), precision);

        Polynome resultat2 = polynomeQuatre.additionner(polynomeQuatre);
        assertEquals(-6.0, resultat2.getCoefficient(0), precision);
        assertEquals(-4.0, resultat2.getCoefficient(1), precision);
        
        Polynome resultat3 = polynomeTrois.additionner(polynomeQuatre);
        assertEquals(-1.0, resultat3.getCoefficient(0), precision);
        assertEquals(2.0, resultat3.getCoefficient(1), precision);
        assertEquals(5.0, resultat3.getCoefficient(2), precision);
        
        Polynome resultat4 = polynomeDeux.additionner(polynomeTrois);
        assertEquals(4.0,   resultat4.getCoefficient(0), precision);
        assertEquals(4.0,   resultat4.getCoefficient(1), precision);
        assertEquals(5.0,   resultat4.getCoefficient(2), precision);
        assertEquals(3.0,   resultat4.getCoefficient(100), precision);
  
        Polynome resultat5 = polynomeUn.additionner(polynomeUn);
        assertEquals(0.0, resultat5.getCoefficient(0));
        
        Polynome resultat6 = polynomeSix.additionner(polynomeQuatre);
        assertEquals(-3.0, resultat6.getCoefficient(0), precision);
        assertEquals(-2.0, resultat6.getCoefficient(1), precision);
        assertEquals(4.0, resultat6.getCoefficient(3), precision);
    }
    
    @Test
    void testMultiplierScalaire() {
        Polynome resultat1 = polynomeUn.multiplierScalaire(3.0);
        assertEquals(0.0, resultat1.getCoefficient(0));
        
        Polynome resultat2 = polynomeDeux.multiplierScalaire(3.0);
        assertEquals(6.0, resultat2.getCoefficient(0));
        assertEquals(9.0, resultat2.getCoefficient(100));
        
        Polynome resultat3 = polynomeTrois.multiplierScalaire(3.0);
        assertEquals(6.0, resultat3.getCoefficient(0));
        assertEquals(12.0, resultat3.getCoefficient(1));
        assertEquals(15.0, resultat3.getCoefficient(2));
        
        Polynome resultat4 = polynomeQuatre.multiplierScalaire(3.0);
        assertEquals(-9.0, resultat4.getCoefficient(0));
        assertEquals(-6.0, resultat4.getCoefficient(1));
        
        Polynome resultat5 = polynomeCinq.multiplierScalaire(3.0);
        assertEquals(15.0, resultat5.getCoefficient(0));
        
        Polynome resultat6 = polynomeSix.multiplierScalaire(3.0);
        assertEquals(12.0, resultat6.getCoefficient(3));
    }
    
    @Test 
    void testMultiplierPolynome() {
        Polynome produitUn = polynomeUn.multiplierPolynome(polynomeDeux);
        assertEquals(0.0, produitUn.getCoefficient(0));
        assertEquals(0.0, produitUn.getCoefficient(1));
        
        Polynome produitDeux = polynomeDeux.multiplierPolynome(polynomeTrois);
        assertEquals(4.0, produitDeux.getCoefficient(0), precision);
        assertEquals(8.0, produitDeux.getCoefficient(1), precision);
        assertEquals(10.0, produitDeux.getCoefficient(2), precision);
        assertEquals(6.0, produitDeux.getCoefficient(100), precision);
        assertEquals(12.0, produitDeux.getCoefficient(101), precision);
        assertEquals(15.0, produitDeux.getCoefficient(102), precision);
        
        Polynome produitTrois = polynomeTrois.multiplierPolynome(polynomeQuatre);
        assertEquals(-6.0, produitTrois.getCoefficient(0), precision);
        assertEquals(-16.0, produitTrois.getCoefficient(1), precision);
        assertEquals(-23.0, produitTrois.getCoefficient(2), precision);
        assertEquals(-10.0, produitTrois.getCoefficient(3), precision);
        
        Polynome produitQuatre = polynomeQuatre.multiplierPolynome(polynomeCinq);
        assertEquals(-15.0, produitQuatre.getCoefficient(0), precision);
        assertEquals(-10.0, produitQuatre.getCoefficient(1), precision);
        
        Polynome produitCinq = polynomeCinq.multiplierPolynome(polynomeUn);
        assertEquals(0.0, produitCinq.getCoefficient(0), precision);
        assertEquals(0.0, produitCinq.getCoefficient(1), precision);
        
        Polynome produitSix = polynomeTrois.multiplierPolynome(polynomeTrois);
        assertEquals(4.0, produitSix.getCoefficient(0), precision);
        assertEquals(16.0, produitSix.getCoefficient(1), precision);
        assertEquals(36.0, produitSix.getCoefficient(2), precision);
        assertEquals(40.0, produitSix.getCoefficient(3), precision);
        assertEquals(25.0, produitSix.getCoefficient(4), precision);
    
        Polynome produitSept = polynomeSix.multiplierPolynome(polynomeCinq);
        assertEquals(20.0, produitSept.getCoefficient(3), precision);
    }
    

	/**
	 * Vérifie que la méthode change le signe du polynôme
	 * et renvoie bien 0 pour le cas d'un polynôme nul.
	 */
	@Test
	void testOpposer() {
		// Cas d'un polynôme nul
		Polynome opposeUn = polynomeUn.opposer();
		assertTrue(opposeUn.estNul());
		
		/* 
		 * Cas d'un polynôme à coefficients positifs 
		 * 2 + 3x^100 => -2 - 3x^100
		 */
		Polynome opposeDeux = polynomeDeux.opposer();
		assertEquals(-2.0, opposeDeux.getCoefficient(0), precision);
		assertEquals(-3.0, opposeDeux.getCoefficient(100), precision);
		
		Polynome opposeTrois = polynomeTrois.opposer();
		assertEquals(-2.0, opposeTrois.getCoefficient(0), precision);
		assertEquals(-4.0, opposeTrois.getCoefficient(1), precision);
		assertEquals(-5.0, opposeTrois.getCoefficient(2), precision);
		
		/* 
		 * Cas d'un polynôme à coefficients négatifs
		 * -3 - 2x => 3 + 2x
		 */
		Polynome opposeQuatre = polynomeQuatre.opposer();
	    assertEquals(3.0, opposeQuatre.getCoefficient(0), precision);
	    assertEquals(2.0, opposeQuatre.getCoefficient(1), precision);
	    
	    /* 
	     * Cas de la double opposition :
	     * vérifie si un polynôme qui passe 2 fois dans la méthode 
	     * retrouve son état d'origine
	     */
	    Polynome premiereOpposition = polynomeTrois.opposer();
	    Polynome secondeOpposition = premiereOpposition.opposer();
	    assertEquals(polynomeTrois.getCoefficient(0), secondeOpposition.getCoefficient(0), precision);
	    assertEquals(polynomeTrois.getCoefficient(1), secondeOpposition.getCoefficient(1), precision);
	    assertEquals(polynomeTrois.getCoefficient(2), secondeOpposition.getCoefficient(2), precision);
	}
	
	/**
	 * Vérifie que le quotient et le reste soient valides, et 
	 * gère le cas de la division par zéro (impossible).
	 */
	@Test
	void testDiviser() {
		// Cas 1 : diviseur égal à zéro
		// TODO 
		
		// Cas 2 : division classique avec reste
		Polynome[] resultatClassique = polynomeTrois.diviser(polynomeQuatre);
		// On vérifie le quotient
		assertEquals(1.75, resultatClassique[0].getCoefficient(0), precision);
		assertEquals(-2.5, resultatClassique[0].getCoefficient(1), precision);
		// On vérifie le reste 
		assertEquals(7.25, resultatClassique[1].getCoefficient(0), precision);
		assertEquals(0, resultatClassique[1].getDegre()); 
		
		// Cas 3 : division d'un polynôme par lui-même
		Polynome[] resultatAutodivision = polynomeTrois.diviser(polynomeTrois);
		assertEquals(1.0, resultatAutodivision[0].getCoefficient(0), precision);
	    assertEquals(0, resultatAutodivision[0].getDegre());
	    assertTrue(resultatAutodivision[1].estNul());
	    
	    // Cas 4 : diviseur de degré supérieur à celui du dividende 
	    // TODO 
		
		
	}
	
    
    @Test 
    void testIntegrer() {
        Polynome primitiveUn = polynomeUn.integrer();
        assertTrue(primitiveUn.estNul(), "La primitive de 0 doit être 0.");
        
        Polynome primitiveDeux = polynomeDeux.integrer();
        assertEquals(0.0, primitiveDeux.getCoefficient(0), precision);
        assertEquals(2.0, primitiveDeux.getCoefficient(1), precision);
        assertEquals(3.0 / 101.0, primitiveDeux.getCoefficient(101), precision); 
        
        Polynome primitiveTrois = polynomeTrois.integrer();
        assertEquals(0.0, primitiveTrois.getCoefficient(0), precision);
        assertEquals(2.0, primitiveTrois.getCoefficient(1), precision);
        assertEquals(2.0, primitiveTrois.getCoefficient(2), precision);
        assertEquals(5.0 / 3.0, primitiveTrois.getCoefficient(3), precision);
        
        Polynome primitiveQuatre = polynomeQuatre.integrer();
        assertEquals(0.0, primitiveQuatre.getCoefficient(0), precision);
        assertEquals(-3.0, primitiveQuatre.getCoefficient(1), precision);
        assertEquals(-1.0, primitiveQuatre.getCoefficient(2), precision);
        
        Polynome primitiveCinq = polynomeCinq.integrer();
        assertEquals(0.0, primitiveCinq.getCoefficient(0), precision); 
        assertEquals(5.0, primitiveCinq.getCoefficient(1), precision);
        
        Polynome primitiveSix = polynomeSix.integrer();
        assertEquals(1.0, primitiveSix.getCoefficient(4), precision);
    }
    
    @Test
    void testDeriver() {
        assertTrue(polynomeUn.deriver().estNul());
        
        Polynome deriveeDeux = polynomeDeux.deriver();
        assertEquals(99, deriveeDeux.getDegre());
        assertEquals(300.0, deriveeDeux.getCoefficient(99), precision);
        
        Polynome deriveeCinq = polynomeCinq.deriver();
        assertTrue(deriveeCinq.estNul());
        
        Polynome deriveeSix = polynomeSix.deriver();
        assertEquals(12.0, deriveeSix.getCoefficient(2));
    }
    
    @Test
    void testMoyenne() {
        assertEquals(0.0, polynomeUn.moyenne(1, 5), precision);
        assertEquals(5.0, polynomeCinq.moyenne(0, 10), precision);
        assertEquals(38.0 / 3.0, polynomeTrois.moyenne(0, 2), precision);
        assertEquals(38.0 / 3.0, polynomeTrois.moyenne(2, 0), precision);
        assertEquals(-5.0, polynomeQuatre.moyenne(0, 2), precision);
        assertEquals(205.0 / 101.0, polynomeDeux.moyenne(0, 1), precision);
        assertEquals(40.0, polynomeSix.moyenne(1, 3), precision);
        assertThrows(IllegalArgumentException.class, () -> {
            polynomeTrois.moyenne(4.5, 4.5);
        });
    }
    
    @Test
    void testToString() {
        assertEquals("0", polynomeUn.toString());
        assertEquals("2.0 + 3.0x^100", polynomeDeux.toString());
        assertEquals("2.0 + 4.0x + 5.0x^2", polynomeTrois.toString());
        assertEquals(" - 3.0 - 2.0x", polynomeQuatre.toString());
        assertEquals("5.0", polynomeCinq.toString());
        assertEquals("4.0x^3", polynomeSix.toString());
    }
    
    @Test 
    void testLimitesMoinsInfini() {
        // polynomeRacinesUn vaut 2X (Degré impair, coeff dominant positif -> -Infini)
        assertEquals(Double.NEGATIVE_INFINITY, polynomeRacinesUn.getLimiteMoinsInfini());
        
        // polynomeRacinesQuatre vaut -2X - 2 (Degré impair, coeff dominant négatif -> +Infini)
        assertEquals(Double.POSITIVE_INFINITY, polynomeRacinesQuatre.getLimiteMoinsInfini());
        
        assertEquals(Double.POSITIVE_INFINITY, polynomeDeux.getLimiteMoinsInfini());
        assertEquals(Double.POSITIVE_INFINITY, polynomeTrois.getLimiteMoinsInfini());
        assertEquals(Double.POSITIVE_INFINITY, polynomeQuatre.getLimiteMoinsInfini());
        assertEquals(Double.POSITIVE_INFINITY, polynomeCinq.getLimiteMoinsInfini());
        assertEquals(Double.NEGATIVE_INFINITY, polynomeSix.getLimiteMoinsInfini());
    }

    @Test 
    void testLimitesPlusInfini() {
        assertEquals(0.0, polynomeUn.getLimitePlusInfini(), precision);
        assertEquals(Double.POSITIVE_INFINITY, polynomeDeux.getLimitePlusInfini());
        assertEquals(Double.POSITIVE_INFINITY, polynomeTrois.getLimitePlusInfini());
        assertEquals(Double.NEGATIVE_INFINITY, polynomeQuatre.getLimitePlusInfini());
        assertEquals(Double.POSITIVE_INFINITY, polynomeCinq.getLimitePlusInfini());
        assertEquals(Double.POSITIVE_INFINITY, polynomeSix.getLimitePlusInfini());
    }
}