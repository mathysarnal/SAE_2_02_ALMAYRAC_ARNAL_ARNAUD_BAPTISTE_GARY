package polynome;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PolynomeTest {

	/**
     * Vérifie que le degré du polynôme est correctement calculé 
     * lors de la création à partir de tableaux parallèles.
     */

	@Test
	void testCreationEtDegre() {
        // pour P(x) = 3 + 4X +2X^2
		double[] coefficient = {3.0, 4.0, 2.0};
		int[] degres = {0, 1, 2};
		
		Polynome polynome = new Polynome(coefficient, degres);
        
        assertEquals(2, polynome.getDegre(), "Le degré devrait être de 2");
	}
	
	/**
     * Vérifie la récupération d'un coefficient spécifique par son degré.
     * Teste également si la méthode retourne 0.0 pour un degré non présent.
     */
	
	@Test
	void testGetCoefficient() {
		
		double[] coefficient = {1.0, 0.0, 5.0};
		int[] degres = {0, 1, 2 };
		
		Polynome polynome = new Polynome(coefficient, degres); 
		
		assertEquals(1.0, polynome.getCoefficient(0));
		assertEquals(0.0, polynome.getCoefficient(1));
		assertEquals(5.0, polynome.getCoefficient(2));
		
		// TODO vérifier que ça marche si on ne stocke pas le 0.0
	}
	
	/**
     * Vérifie l'évaluation du polynôme pour une valeur de x donnée.
     */
	
	@Test
	void testEvaluerSimple() {
		
		double[] coefficient = {2.0, 3.0};
		int[] degres = {0, 1};
		
		Polynome polynome = new Polynome(coefficient, degres);
		
		assertEquals(8.0,polynome.evaluer(2.0));
	}

	/**
     * Vérifie que la méthode estNul() retourne true lorsque 
     * tous les coefficients du polynôme sont à zéro.
     */
	
	@Test 
	void testPolynomeNul() {
		
		double[] coefficient = {0.0, 0.0, 0.0 };
		int[] degres = {0, 1, 2};
		
		Polynome polynomeNul = new Polynome(coefficient, degres);
		
		assertTrue(polynomeNul.estNul(), "Le polynôme devrait être nul.");
	}
	
	/**
     * Vérifie que la méthode estNul() retourne false lorsqu'au 
     * moins un coefficient est non nul.
     */
	
	@Test
	void testPolynomeNonNul() {
		
		double[] coefficient = {0.0, 1.0, 0.0};
		int[] degres = {0, 1, 2};
		
		Polynome polynomeNonNul = new Polynome(coefficient, degres);
		
		assertFalse(polynomeNonNul.estNul(), "Le polynôme ne devrait pas être nul.");
	}

	/**
     * Vérifie la représentation textuelle du polynôme.
     * Attend une chaîne comprenant un format avec les puissances de X.
     */
	
	@Test
	void testToString() {
		
		double[] coefficient = {2.0, 4.0, 3.0};
		int [] degres = {0, 1, 2 };
		
		Polynome polynome = new Polynome(coefficient, degres);
		assertEquals("2 + 4X + 3X^2", polynome.toString());
	}
	
	/**
     * Vérifie l'addition de deux polynômes.
     * Teste la somme des coefficients degré par degré.
     */
    
	@Test
	void testAdditionner() {
		
		double[] premierCoefficient = {2.0, 1.0};
		int[] premierDegres = {0, 1};
		
		double[] deuxiemeCoefficient = {1.0, 3.0};
		int[] deuxiemeDegres = {0, 1};
		
		Polynome premierPolynome = new Polynome(premierCoefficient, premierDegres);
		Polynome deuxiemePolynome = new Polynome(deuxiemeCoefficient, deuxiemeDegres);
		Polynome resultat = premierPolynome.additionner(deuxiemePolynome);
		
		assertEquals(3.0, resultat.getCoefficient(0));
		assertEquals(4.0, resultat.getCoefficient(1));
	}
	
	/**
     * Vérifie la multiplication du polynôme par un scalaire.
     */
	
	@Test
	void testMultiplierScalaire() {
		
		double[] coefficient = {3.0, 4.0};
		int[] degres = {0, 1};
		
		Polynome polynome = new Polynome(coefficient, degres);
		Polynome resultat = polynome.multiplier(2.0);
		
		assertEquals(6.0, resultat.getCoefficient(0));
		assertEquals(8.0, resultat.getCoefficient(1));
	}
	
	/**
     * Vérifie le calcul de la dérivée du polynôme.
     */
	
	@Test
	void testDeriver() {
		
		double[] coefficient = {2.0, 4.0, 3.0};
		int[] degres = {0, 1, 2};
		
		Polynome polynome = new Polynome(coefficient, degres);
		Polynome derivee = polynome.deriver();
		
		assertEquals(4.0, derivee.getCoefficient(0));
		assertEquals(6.0, derivee.getCoefficient(1));
		assertEquals(1, derivee.getDegre());
	}
}