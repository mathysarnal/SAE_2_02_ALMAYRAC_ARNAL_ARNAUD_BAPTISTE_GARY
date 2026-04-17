package polynome;

import java.util.HashMap;
import java.util.Map;

public class PolynomeTest {

    public static void main(String[] args) {

        // 🔹 Test 1 : constructeur avec varargs
        Polynome p1 = new Polynome(3, 4, 2); // 3 + 4x + 2x²
        System.out.println("p1 :");
        p1.afficher();

        // 🔹 Test 2 : constructeur avec Map (grands degrés)
        Map<Integer, Integer> termes = new HashMap<>();
        termes.put(0, 3);
        termes.put(12, 4);
        termes.put(10, 2);

        Polynome p2 = new Polynome(termes); // 3 + 4x^12 + 2x^10
        System.out.println("\np2 :");
        p2.afficher();

        // 🔹 Test 3 : évaluation
        double valeur = p1.evaluer(2);
        System.out.println("\np1(2) = " + valeur);

        // 🔹 Test 4 : estNul
        Polynome pNul = new Polynome(0, 0, 0);
        System.out.println("\npNul est nul ? " + pNul.estNul());

        // 🔹 Test 5 : addition
        Polynome somme = p1.additionner(p1);
        System.out.println("\np1 + p1 :");
        somme.afficher();

        // 🔹 Test 6 : multiplication par scalaire
        Polynome pMult = p1.multiplier(2);
        System.out.println("\np1 * 2 :");
        pMult.afficher();
    }
}