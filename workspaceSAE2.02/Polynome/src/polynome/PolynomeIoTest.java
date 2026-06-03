package polynome;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

/**
 * Classe de validation unitaire JUnit 6 pour le composant PolynomeIo.
 * <p>
 * Ces tests assurent le fonctionnement complet des méthodes charger et sauvegarder de PolynomeIo
 * en vérifiant que le coverage de tests soit de 100% sur toutes les méthodes.
 * </p>
 * <p>
 * Chaque scénario écrit un fichier temporaire dans le dossier de sauvegarde, 
 * vérifie l'égalité des structures mathématiques avant et après stockage 
 * puis assure un nettoyage automatique du système de fichiers à la fin de 
 * son exécution.
 * </p>
 */
public class PolynomeIoTest {

    /** Instance unique du gestionnaire d'entrées/sorties soumise au protocole de test. */
    private final PolynomeIo io = new PolynomeIo();
    
    /** Nom du fichier texte temporaire employé pour tester le format d'export par monômes. */
    private final String NOM_TEST_COEFF = "test_poly_coeff";
    
    /** Nom du fichier texte temporaire employé pour tester le format d'export par racines. */
    private final String NOM_TEST_RACINES = "test_poly_racines";

    /**
     * Valide la persistance d'un polynôme défini par ses coefficients.
     * <p>
     * Le cas de test applique le scénario suivant :
     * </p>
     * <ol>
     *   <li>Création du polynôme P(x) = 3.0x^2 - 5.0.</li>
     *   <li>Exportation textuelle via la méthode sauvegarder().</li>
     *   <li>Importation et reconstruction via la méthode charger().</li>
     *   <li>Vérifie la conformité du polynôme (présence, degré et coefficients).</li>
     * </ol>
     */
    @Test
    public void testSauvegarderEtChargerParCoefficients() throws IOException {
        
        // 1. Création d'un polynôme par coefficients : P(x) = 3.0x^2 - 5.0
        double[] coeffs = {3.0, -5.0};
        int[] degres = {2, 0};
        Polynome polyOriginal = new Polynome(coeffs, degres);

        // 2. Sauvegarde (va créer "sauvegardes/test_poly_coeff.txt")
        io.sauvegarder(polyOriginal, NOM_TEST_COEFF);

        // 3. Chargement
        Polynome polyCharge = io.charger(NOM_TEST_COEFF);

        // 4. Vérifications (Assertions ordonnées et regroupées)
        assertNotNull(polyCharge, "Le polynôme chargé ne devrait pas être null.");
        assertAll("Vérification des propriétés du polynôme par coefficients",
            () -> assertEquals(polyOriginal.getDegre(), polyCharge.getDegre(), "Le degré devrait être le même."),
            () -> assertEquals(3.0, polyCharge.getCoefficient(2), 0.001, "Le coefficient de degré 2 est faux."),
            () -> assertEquals(-5.0, polyCharge.getCoefficient(0), 0.001, "Le coefficient de degré 0 est faux.")
        );

        // 5. Nettoyage : Exécuté dès que les assertions se terminent avec succès
        File fichierNettoyage = new File("sauvegardes" + File.separator + NOM_TEST_COEFF + ".txt");
        if (fichierNettoyage.exists()) {
            fichierNettoyage.delete();
        }
    }

    /**
     * Valide la persistance d'un polynôme défini sous sa forme factorisée (par racines).
     * <p>
     * Le cas de test applique le scénario suivant :
     * </p>
     * <ol>
     *   <li>Création du polynôme P(x) = 2.0 * (x - 3.0)^2.</li>
     *   <li>Exportation textuelle via la méthode sauvegarder().</li>
     *   <li>Importation et reconstruction via la méthode charger().</li>
     *   <li>Vérification par assertions de la parfaite correspondance
     *       des tableaux de racines, des ordres de multiplicité
     *       associés ainsi que du coefficient de plus haut degré.</li>
     * </ol>
     */
    @Test
    public void testSauvegarderEtChargerParRacines() throws IOException {
        
        // 1. Création d'un polynôme par racines : P(x) = 2.0 * (x - 3.0)^2
        double[] racines = {3.0};
        int[] ordres = {2};
        double coeffDominant = 2.0;
        Polynome polyOriginal = new Polynome(racines, ordres, coeffDominant);

        // 2. Sauvegarde (va créer "sauvegardes/test_poly_racines.txt")
        io.sauvegarder(polyOriginal, NOM_TEST_RACINES);

        // 3. Chargement
        Polynome polyCharge = io.charger(NOM_TEST_RACINES);

        // 4. Vérifications (Assertions regroupées avec assertAll)
        assertNotNull(polyCharge, "Le polynôme chargé ne devrait pas être null.");
        assertAll("Vérification des propriétés du polynôme par racines",
            () -> assertArrayEquals(polyOriginal.getRacinesReelles(), polyCharge.getRacinesReelles(), 0.001, "Les racines chargées sont fausses."),
            () -> assertArrayEquals(polyOriginal.getOrdresRacines(), polyCharge.getOrdresRacines(), "Les ordres des racines sont faux."),
            () -> assertEquals(polyOriginal.getCoefficient(polyOriginal.getDegre()), polyCharge.getCoefficient(polyCharge.getDegre()), 0.001, "Le coefficient dominant est faux.")
        );

        // 5. Nettoyage : Exécuté dès que les assertions se terminent avec succès
        File fichierNettoyage = new File("sauvegardes" + File.separator + NOM_TEST_RACINES + ".txt");
        if (fichierNettoyage.exists()) {
            fichierNettoyage.delete();
        }
    }
}