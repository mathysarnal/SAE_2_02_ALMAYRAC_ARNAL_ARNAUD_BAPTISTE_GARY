package polynome;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PolynomeTest {

	private static final double PRECISION = 1e-6;
	
	@Test
	void testCreationEtDregre() {
        Polynome p = new Polynome(3, 4, 2); // 3 + 4x + 2X^2
        
        assertEquals(2, p.getDegre(), "Le degré devrait être de 2");
	}
	
	@Test
	void testGetCoefficient() {
		Polynome p = new Polynome(1, 0, 5); // 1 + 5x^2 
		assertEquals(1.0, p.getCoefficient(0), PRECISION);
		assertEquals(0.0, p.getCoefficient(1), PRECISION);
		assertEquals(5.0, p.getCoefficient(2), PRECISION);
	}
	
	@Test
	void testEvaluerSimple() {
		// P(X)= 2 + 3X => P(2) = 3x2 + 2 = 8
		Polynome p = new Polynome(2, 3);
		
		assertEquals(8.0,p.evaluer(2.0), PRECISION);
	}

	@Test 
	void testPolynomeNul() {
		Polynome pNul = new Polynome(0, 0, 0);
		assertTrue(pNul.estNul(), "Le polynôme devrait être nul.");
		
		Polynome pNonNul = new Polynome(0, 1, 0);
		
		assertFalse(pNonNul.estNul(), "Le polynôme ne devrait pas être nul.");
	}

	@Test
	void testToString() {
		Polynome p = new Polynome(2, 4, 3);
		assertEquals("2 + 4X + 3X^2", p.toString());
	}
    
	@Test
	void testAdditionner() {
		Polynome p1 = new Polynome(2, 1);
		Polynome p2 = new Polynome(1, 3);
		Polynome resultat = p1.additionner(p2);
		
		assertEquals(3.0, resultat.getCoefficient(0), PRECISION);
		assertEquals(4.0, resultat.getCoefficient(1), PRECISION);
	}
	
	@Test
	void testMultiplierScalaire() {
		Polynome p = new Polynome(3, 4);
		Polynome resultat = p.multiplier(2.0);
		
		assertEquals(6.0, resultat.getCoefficient(0), PRECISION);
		assertEquals(8.0, resultat.getCoefficient(1), PRECISION);
	}
	
	@Test
	void testDeriver() {
		Polynome p = new Polynome(2, 4, 3);
		Polynome derivee = p.deriver();
		
		assertEquals(4.0, derivee.getCoefficient(0), PRECISION);
		assertEquals(6.0, derivee.getCoefficient(1), PRECISION);
		assertEquals(1, derivee.getDegre());
	}
}