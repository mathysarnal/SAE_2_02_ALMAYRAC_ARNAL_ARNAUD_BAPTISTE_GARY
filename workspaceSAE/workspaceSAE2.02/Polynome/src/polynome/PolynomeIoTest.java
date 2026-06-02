package polynome;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

/**
 * Classe de test JUnit 5 mise à jour pour PolynomeIo.
 */
public class PolynomeIoTest {

    private final PolynomeIo io = new PolynomeIo();
    
    // On ne met plus l'extension .txt ici car la classe PolynomeIo la gère toute seule !
    private final String NOM_TEST_COEFF = "test_poly_coeff";
    private final String NOM_TEST_RACINES = "test_poly_racines";

    @Test
    public void testSauvegarderEtChargerParCoefficients() {
        try {
            // 1. Création d'un polynôme par coefficients : P(x) = 3.0x^2 - 5.0
            double[] coeffs = {3.0, -5.0};
            int[] degres = {2, 0};
            Polynome polyOriginal = new Polynome(coeffs, degres);

            // 2. Sauvegarde (va créer "sauvegardes/test_poly_coeff.txt")
            io.sauvegarder(polyOriginal, NOM_TEST_COEFF);

            // 3. Chargement
            Polynome polyCharge = io.charger(NOM_TEST_COEFF);

            // 4. Vérifications (Assertions)
            assertNotNull(polyCharge, "Le polynôme chargé ne devrait pas être null.");
            assertEquals(polyOriginal.getDegre(), polyCharge.getDegre(), "Le degré devrait être le même.");
            assertEquals(3.0, polyCharge.getCoefficient(2), 0.001, "Le coefficient de degré 2 est faux.");
            assertEquals(-5.0, polyCharge.getCoefficient(0), 0.001, "Le coefficient de degré 0 est faux.");

        } catch (IOException e) {
            fail("Une exception IOException a été levée alors que le test devrait réussir : " + e.getMessage());
        } finally {
            // Nettoyage : On cible le fichier à l'intérieur du dossier sauvegardes
            File fichierNettoyage = new File("sauvegardes" + File.separator + NOM_TEST_COEFF + ".txt");
            if (fichierNettoyage.exists()) {
                fichierNettoyage.delete();
            }
        }
    }

    @Test
    public void testSauvegarderEtChargerParRacines() {
        try {
            // 1. Création d'un polynôme par racines : P(x) = 2.0 * (x - 3.0)^2
            double[] racines = {3.0};
            int[] ordres = {2};
            double coeffDominant = 2.0;
            Polynome polyOriginal = new Polynome(racines, ordres, coeffDominant);

            // 2. Sauvegarde (va créer "sauvegardes/test_poly_racines.txt")
            io.sauvegarder(polyOriginal, NOM_TEST_RACINES);

            // 3. Chargement
            Polynome polyCharge = io.charger(NOM_TEST_RACINES);

            // 4. Vérifications (Assertions)
            assertNotNull(polyCharge, "Le polynôme chargé ne devrait pas être null.");
            assertArrayEquals(polyOriginal.getRacinesReelles(), polyCharge.getRacinesReelles(), 0.001, "Les racines chargées sont fausses.");
            assertArrayEquals(polyOriginal.getOrdresRacines(), polyCharge.getOrdresRacines(), "Les ordres des racines sont faux.");
            assertEquals(polyOriginal.getCoefficient(polyOriginal.getDegre()), polyCharge.getCoefficient(polyCharge.getDegre()), 0.001, "Le coefficient dominant est faux.");

        } catch (IOException e) {
            fail("Une exception IOException a été levée alors que le test devrait réussir : " + e.getMessage());
        } finally {
            // Nettoyage : On cible le fichier à l'intérieur du dossier sauvegardes
            File fichierNettoyage = new File("sauvegardes" + File.separator + NOM_TEST_RACINES + ".txt");
            if (fichierNettoyage.exists()) {
                fichierNettoyage.delete();
            }
        }
    }
}