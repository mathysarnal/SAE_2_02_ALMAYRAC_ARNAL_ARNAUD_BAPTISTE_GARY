package polynome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolynomeTest {

	
	private static final double precision = 1e-6;
	
	/**
     * Vérifie que le degré du polynôme est correctement calculé 
     * lors de la création à partir de tableaux parallèles.
     */
	
	private Polynome polynomeUn;
	private Polynome polynomeDeux;
	private Polynome polynomeTrois;
	private Polynome polynomeQuatre;
	private Polynome polynomeCinq;
	
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
		
	}
	
	
	
	@Test
	void testCreationEtDegre() {
        assertEquals(0, polynomeUn.getDegre(), "Le degré devrait être de 0.");
        assertEquals(100, polynomeDeux.getDegre(), "Le degré devrait être de 100.");
        assertEquals(2, polynomeTrois.getDegre(), "Le degré devrait être de 2.");
        assertEquals(1, polynomeQuatre.getDegre(), "Le degré devrait être de 1");
        assertEquals(0, polynomeCinq.getDegre(), "Le degré devrait être de 0.");
	
	}
	
	/**
     * Vérifie la récupération d'un coefficient spécifique par son degré.
     * Teste également si la méthode retourne 0.0 pour un degré non présent.
     */
	
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
		
	}
	
	/**
     * Vérifie l'évaluation du polynôme pour une valeur de x donnée.
     */
	
	@Test
	void testEvaluerSimple() {
			
		assertEquals(0.0,polynomeUn.evaluer(2.0), precision);
		
		assertEquals(5.0,polynomeDeux.evaluer(1.0), precision);
		
		assertEquals(59.0,polynomeTrois.evaluer(3.0), precision);
		
		assertEquals(-19.0,polynomeQuatre.evaluer(8.0), precision);
		
		assertEquals(5.0,polynomeCinq.evaluer(6.0), precision);
	}
	
	/**
	 * Vérifie l'évaluation d'un polynome pour une valeur x donnée selon la méthode de horner.
	 */

	@Test
	void testEvaluerHorner() {
		assertEquals(0.0,polynomeUn.evaluerHorner(2.0), precision);
		
		assertEquals(5.0,polynomeDeux.evaluerHorner(1.0), precision);
		
		assertEquals(59.0,polynomeTrois.evaluerHorner(3.0), precision);
		
		assertEquals(-19.0,polynomeQuatre.evaluerHorner(8.0), precision);
		
		assertEquals(5.0,polynomeCinq.evaluerHorner(6.0), precision);
	}
	
	/**
     * Vérifie que la méthode estNul() retourne true lorsque 
     * tous les coefficients du polynôme sont à zéro.
     */
	
	@Test 
	void testPolynomeNul() {
				
		assertTrue(polynomeUn.estNul(), "Le polynôme devrait être nul.");
		
		assertFalse(polynomeDeux.estNul(), "Le polynôme ne devrait pas être nul.");
		
		assertFalse(polynomeTrois.estNul(), "Le polynôme ne devrait pas être nul.");
		
		assertFalse(polynomeQuatre.estNul(), "Le polynôme ne devrait pas être nul.");
		
		assertFalse(polynomeCinq.estNul(), "Le polynôme ,ne devrait pas être nul.");
	}
		
	/**
     * Vérifie l'addition de deux polynômes.
     * Teste la somme des coefficients degré par degré.
     */
    
	@Test
	void testAdditionner() {
		
		Polynome resultat1 = polynomeTrois.additionner(polynomeUn);
        assertEquals(2.0, resultat1.getCoefficient(0), precision);
        assertEquals(4.0, resultat1.getCoefficient(1), precision);
        assertEquals(5.0, resultat1.getCoefficient(2), precision);

        Polynome resultat2 = polynomeQuatre.additionner(polynomeQuatre);
        assertEquals(- 6.0, resultat2.getCoefficient(0), precision);
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
  
	}
	
	/**
     * Vérifie la multiplication du polynôme par un scalaire.
     */
	
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
	}
	
	/**
     * Vérifie le calcul de la dérivée du polynôme.
     */
	
	
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
		
	}
	
	@Test
	void testDiviser() {
		Polynome quotientUn = polynomeUn.diviser(polynomeDeux);
		
		assertEquals(0.0, quotientUn.getCoefficient(0));
		assertEquals(0, quotientUn.getDegre());
		
		
		Polynome quotientDeux = polynomeTrois.diviser(polynomeCinq);
		
		assertEquals(0.4, quotientDeux.getCoefficient(0));
		assertEquals(0.8, quotientDeux.getCoefficient(1));
		assertEquals(1.0, quotientDeux.getCoefficient(2));
		assertEquals(2, quotientDeux.getDegre());
		
		Polynome quotientTrois = polynomeQuatre.diviser(polynomeTrois);
		
		assertEquals(0.0, quotientTrois.getCoefficient(0));
		assertEquals(0, quotientTrois.getDegre());
		
		Polynome quotientQuatre = polynomeTrois.diviser(polynomeTrois);
		
		assertEquals(1.0, quotientQuatre.getCoefficient(0));
		assertEquals(0, quotientQuatre.getDegre());
		
		Polynome quotientCinq = polynomeTrois.diviser(polynomeQuatre);
		
		assertEquals(-2.5, quotientCinq.getCoefficient(0));
		assertEquals(1.75, quotientCinq.getCoefficient(1));
		assertEquals(1, quotientCinq.getDegre());
		
		// cas particulier de division par 0
		
		// Cas 1 : Division d'un polynôme de degré 2 par 0
        assertThrows(ArithmeticException.class, () -> {
            polynomeTrois.diviser(polynomeUn);
        });

        // Cas 2 : Division du polynôme nul par lui-même (0 / 0)
        assertThrows(ArithmeticException.class, () -> {
            polynomeUn.diviser(polynomeUn);
        });

        // Cas 3 : Division d'un polynôme constant non nul par 0 (5.0 / 0)
        assertThrows(ArithmeticException.class, () -> {
            polynomeCinq.diviser(polynomeUn);
        });
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
		
	}
	
	
	@Test
	void testDeriver() {
		
        assertTrue(polynomeUn.deriver().estNul());
        
        Polynome deriveeDeux = polynomeDeux.deriver();
        assertEquals(99, deriveeDeux.getDegre());
        assertEquals(300.0,deriveeDeux.getCoefficient(99), precision);
        assertEquals(0,deriveeDeux.getCoefficient(0), precision);
	
		Polynome deriveeTrois = polynomeTrois.deriver();
        assertEquals(1, deriveeTrois.getDegre());
        assertEquals(4.0, deriveeTrois.getCoefficient(0), precision);
        assertEquals(10.0, deriveeTrois.getCoefficient(1), precision);
        
        Polynome deriveeQuatre = polynomeQuatre.deriver();
        assertEquals(0, deriveeQuatre.getDegre());
        assertEquals(-2.0, deriveeQuatre.getCoefficient(0), precision);
        
        Polynome deriveeCinq = polynomeCinq.deriver();
        assertTrue(deriveeCinq.estNul());
        }
	
	/**
     * Vérifie la représentation textuelle du polynôme.
     * Attend une chaîne comprenant un format avec les puissances de X.
     */
	
	@Test
	void testMoyenne() {
		assertEquals(5.0, polynomeCinq.moyenne(0, 10), precision);
		assertEquals(38.0 / 3.0, polynomeTrois.moyenne(0, 2), precision);
		assertEquals(38.0 / 3.0, polynomeTrois.moyenne(2, 0), precision);
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
	}
	
	@Test 
	void testLimitesMoinsInfini() {
	    assertEquals(Double.NEGATIVE_INFINITY, polynomeUn.getLimiteMoinsInfini());
	    assertEquals(Double.POSITIVE_INFINITY, polynomeDeux.getLimiteMoinsInfini());
	    assertEquals(Double.POSITIVE_INFINITY, polynomeTrois.getLimiteMoinsInfini());
	    assertEquals(Double.POSITIVE_INFINITY, polynomeQuatre.getLimiteMoinsInfini());
	    assertEquals(Double.POSITIVE_INFINITY, polynomeCinq.getLimiteMoinsInfini());
	}

	@Test 
	void testLimitesPlusInfini() {
	    assertEquals(0.0, polynomeUn.getLimitePlusInfini(), precision);
	    assertEquals(Double.POSITIVE_INFINITY, polynomeDeux.getLimitePlusInfini());
	    assertEquals(Double.POSITIVE_INFINITY, polynomeTrois.getLimitePlusInfini());
	    assertEquals(Double.NEGATIVE_INFINITY, polynomeQuatre.getLimitePlusInfini());
	    assertEquals(Double.POSITIVE_INFINITY, polynomeCinq.getLimitePlusInfini());
	}
}