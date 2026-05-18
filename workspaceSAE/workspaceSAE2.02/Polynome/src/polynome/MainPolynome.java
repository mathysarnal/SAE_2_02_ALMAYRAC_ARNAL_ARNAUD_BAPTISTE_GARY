package polynome;

import java.util.Scanner;

public class MainPolynome {

    public static void main(String[] args) {
        Scanner analyseurEntree = new Scanner(System.in);

        System.out.println("=== CREATION DU PREMIER POLYNOME ===");
        System.out.print("Combien de monômes non nuls ? ");
        int nbMonomes = analyseurEntree.nextInt();

        double[] coefficients = new double[nbMonomes];
        int[] degres = new int[nbMonomes];

        for (int i = 0; i < nbMonomes; i++) {
            System.out.println("Monôme " + (i + 1));
            System.out.print("  Coefficient : ");
            coefficients[i] = analyseurEntree.nextDouble();
            System.out.print("  Degré : ");
            degres[i] = analyseurEntree.nextInt();
        }

        // Création du polynôme principal
        Polynome p1 = new Polynome(coefficients, degres);

        System.out.println("\n=== POLYNOME SAISI ===");
        p1.afficher();

        System.out.println("\n=== DEMONSTRATION DES METHODES ===");
        
        // 1. Évaluation
        System.out.print("Entrez une valeur pour x afin d'évaluer le polynôme : ");
        double x = analyseurEntree.nextDouble();
        System.out.println("-> P(" + x + ") = " + p1.evaluer(x));

        // 2. Dérivation
        Polynome derivee = p1.deriver();
        System.out.println("\n-> Polynôme Dérivé (Fiche d'identité) :");
        derivee.afficher();

        // 3. Intégration
        Polynome primitive = p1.integrer();
        System.out.println("\n-> Polynôme Intégré / Primitive (Fiche d'identité) :");
        primitive.afficher();

        // 4. Valeur Moyenne
        System.out.println("\nCalcul de la valeur moyenne sur un intervalle [a, b] :");
        System.out.print("  Entrez la borne a : ");
        double a = analyseurEntree.nextDouble();
        System.out.print("  Entrez la borne b : ");
        double b = analyseurEntree.nextDouble();
        try {
            double valMoyenne = p1.moyenne(a, b);
            System.out.println("-> Valeur moyenne sur [" + a + ", " + b + "] = " + valMoyenne);
        } catch (IllegalArgumentException e) {
            System.out.println("-> Erreur : " + e.getMessage());
        }

        // 5. Addition (Démonstration avec un polynôme fixe : + 3x^2)
        System.out.println("\n=== TEST DE L'ADDITION ===");
        System.out.println("On ajoute le polynôme (3.0x^2) à votre polynôme...");
        Polynome aAjouter = new Polynome(new double[]{3.0}, new int[]{2});
        Polynome somme = p1.additionner(aAjouter);
        somme.afficher();

        analyseurEntree.close();
        System.out.println("\nFin du polynome");
    }
}