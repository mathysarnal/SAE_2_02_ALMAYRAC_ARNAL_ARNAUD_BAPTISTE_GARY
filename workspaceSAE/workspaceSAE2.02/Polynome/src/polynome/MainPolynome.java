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

        for (int i = 0; i < nbMonomes; i++) { // TODO changer le nom de l'incrément en qqchose d'explicite
            System.out.println("Monôme " + (i + 1) + " : ");
            System.out.print("  Coefficient : ");
            coefficients[i] = analyseurEntree.nextDouble();
            System.out.print("  Degré : ");
            degres[i] = analyseurEntree.nextInt();
        }

        // Création du polynôme principal
        Polynome p1 = new Polynome(coefficients, degres);

        System.out.println("\n=== POLYNOME SAISI ===");
        System.out.println(p1.toString());

        System.out.println("\n=== DEMONSTRATION DES GETTERS ===");

        System.out.print("Degré du polynôme : ");
        System.out.println(p1.getDegre());
        System.out.print("Coefficient du plus grand degré du polynôme : ");
        System.out.println(p1.getCoefficient(p1.getDegre()));
        System.out.print("Limite en plus l'infini : ");
        System.out.println(p1.getLimitePlusInfini());
        System.out.print("Limite en moins l'infini : ");
        System.out.println(p1.getLimiteMoinsInfini());

        System.out.println("\n=== DEMONSTRATION DES METHODES ===");
        
        // 1. Évaluation
        System.out.print("Entrez une valeur pour x afin d'évaluer le polynôme : ");
        double x = analyseurEntree.nextDouble();
        System.out.println("P(" + x + ") = " + p1.evaluer(x));

        // 2. Dérivation
        Polynome derivee = p1.deriver();
        System.out.println("\nPolynôme dérivé :");
        System.out.println(derivee.toString());

        // 3. Intégration
        Polynome primitive = p1.integrer();
        System.out.println("\nPolynôme intégré :");
        System.out.println(primitive.toString());

        // 4. Valeur Moyenne
        System.out.println("\nCalcul de la valeur moyenne sur un intervalle [a, b] :");
        System.out.print("  Entrez la borne a : ");
        double a = analyseurEntree.nextDouble();
        System.out.print("  Entrez la borne b : ");
        double b = analyseurEntree.nextDouble();
        try {
            double valMoyenne = p1.moyenne(a, b);
            System.out.println("Valeur moyenne sur [" + a + ", " + b + "] = " + valMoyenne);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // 5. Addition (Démonstration avec un polynôme fixe : + 3x^2)
        System.out.println("\n=== TEST DE L'ADDITION ===");
        System.out.println("On ajoute le polynôme (3.0x^2) à votre polynôme...");
        Polynome aAjouter = new Polynome(new double[]{3.0}, new int[]{2});
        Polynome somme = p1.additionner(aAjouter);
        System.out.print(somme.toString());
        
        // 6. Multiplication par un scalaire
        System.out.println("\n=== TEST DE LA MULTIPLICATION SCALAIRE ===");
        System.out.println("On multiplie le scalaire réel 5,6 à votre polynôme...");
        double aMultiplier = 5.6;
        Polynome produit = p1.multiplierScalaire(aMultiplier);
        System.out.print(produit.toString());
        
        // 7. Multiplication par un polynôme
        System.out.println("\n=== TEST DE LA MULTIPLICATION POLYNOMIALE ===");
        System.out.println("On multiplie le polynôme (3.0x^2) à votre polynôme...");
        Polynome aMultiplier2 = new Polynome(new double[]{3.0}, new int[]{2});
        Polynome produit2 = p1.multiplierPolynome(aMultiplier2);
        System.out.print(produit2.toString());
        
        // 8. Division par un polynôme
        System.out.println("\n=== TEST DE LA DIVISION ===");
        System.out.println("On divise votre polynôme par le polynôme (3.0x^2)...");
        Polynome aDiviser = new Polynome(new double[]{3.0}, new int[]{2});
        Polynome quotient = p1.diviser(aDiviser);
        System.out.print(quotient.toString());
        
        analyseurEntree.close();
        System.out.println("\nFin du polynome");
    }
}