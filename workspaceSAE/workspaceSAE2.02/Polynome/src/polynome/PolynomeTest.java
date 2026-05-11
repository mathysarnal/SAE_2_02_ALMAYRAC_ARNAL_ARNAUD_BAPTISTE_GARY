package polynome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolynomeTest {

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
		assertEquals(2.0, polynomeDeux.getCoefficient(100));
		
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
			
		assertEquals(0.0,polynomeUn.evaluer(2.0));
		
		assertEquals(5.0,polynomeDeux.evaluer(1.0));
		
		assertEquals(59.0,polynomeTrois.evaluer(3.0));
		
		assertEquals(-19.0,polynomeQuatre.evaluer(8.0));
		
		assertEquals(5.0,polynomeCinq.evaluer(6.0));
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
     * Vérifie la représentation textuelle du polynôme.
     * Attend une chaîne comprenant un format avec les puissances de X.
     */
	
	@Test
	void testToString() {
		assertEquals("0.0", polynomeUn.toString());
		assertEquals("2 + 3x^100", polynomeDeux.toString());
		assertEquals("2 + 4x + 5x^2", polynomeTrois.toString());
		assertEquals("-3 - 2x", polynomeQuatre.toString());
		assertEquals("5.0", polynomeCinq.toString());
	}
	
	/**
     * Vérifie l'addition de deux polynômes.
     * Teste la somme des coefficients degré par degré.
     */
    
	@Test
	void testAdditionner() {
		
		Polynome resultat1 = polynomeTrois.additionner(polynomeUn);
        assertEquals(2.0, resultat1.getCoefficient(0));
        assertEquals(4.0, resultat1.getCoefficient(1));
        assertEquals(5.0, resultat1.getCoefficient(2));

        Polynome resultat2 = polynomeQuatre.additionner(polynomeQuatre);
        assertEquals(- 6.0, resultat2.getCoefficient(0));
        assertEquals(-4.0, resultat2.getCoefficient(1));
  
	}
	
	/**
     * Vérifie la multiplication du polynôme par un scalaire.
     */
	
	@Test
	void testMultiplierScalaire() {
				
		Polynome resultat1 = polynomeUn.multiplier(3.0);
		assertEquals(0.0, resultat1.getCoefficient(0));
		
		Polynome resultat2 = polynomeDeux.multiplier(3.0);
        assertEquals(6.0, resultat2.getCoefficient(0));
        assertEquals(9.0, resultat2.getCoefficient(100));
		
		
		Polynome resultat3 = polynomeTrois.multiplier(3.0);
        assertEquals(6.0, resultat3.getCoefficient(0));
        assertEquals(12.0, resultat3.getCoefficient(1));
        assertEquals(15.0, resultat3.getCoefficient(2));
        
        Polynome resultat4 = polynomeQuatre.multiplier(3.0);
        assertEquals(-9.0, resultat4.getCoefficient(0));
        assertEquals(-6.0, resultat4.getCoefficient(1));
        
        Polynome resultat5 = polynomeTrois.multiplier(3.0);
        assertEquals(15.0, resultat5.getCoefficient(0));
	}
	
	/**
     * Vérifie le calcul de la dérivée du polynôme.
     */
	
	@Test
	void testDeriver() {
		
        assertTrue(polynomeUn.deriver().estNul());
        
        Polynome deriveeDeux = polynomeDeux.deriver();
        assertEquals(99, deriveeDeux.getDegre());
        assertEquals(0.0,deriveeDeux.getCoefficient(0));
        assertEquals(300,deriveeDeux.getCoefficient(99));
	
		Polynome deriveeTrois = polynomeTrois.deriver();
        assertEquals(1, deriveeTrois.getDegre());
        assertEquals(4.0, deriveeTrois.getCoefficient(0));
        assertEquals(10.0, deriveeTrois.getCoefficient(1));
        
        Polynome deriveeQuatre = polynomeTrois.deriver();
        assertEquals(0, deriveeQuatre.getDegre());
        assertEquals(-2.0, deriveeQuatre.getCoefficient(0));
        
        Polynome deriveeCinq = polynomeTrois.deriver();
        assertEquals(0, deriveeCinq.getDegre());
        assertEquals(0.0, deriveeCinq.getCoefficient(0));
	}
}