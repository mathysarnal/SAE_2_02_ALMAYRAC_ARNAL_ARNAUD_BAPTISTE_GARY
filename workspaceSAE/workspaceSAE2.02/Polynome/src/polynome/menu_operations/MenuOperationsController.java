package polynome.menu_operations;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import polynome.MainApp;
import polynome.Polynome;
import polynome.PolynomeParser;

public class MenuOperationsController {

    @FXML private Label labelPolynome;
    @FXML private TextField champSaisieOperation;
    @FXML private Label labelResultat;

    @FXML
    public void initialize() {
        rafraichirPolynomeCourant();
    }

    private void rafraichirPolynomeCourant() {
        if (MainApp.polynomeCourant != null) {
            labelPolynome.setText(MainApp.polynomeCourant.toString());
        }
    }

    @FXML
    private void deriver() {
        Polynome pDerive = MainApp.polynomeCourant.deriver();
        labelResultat.setText("P'(x) = " + pDerive.toString());
        
        // Optionnel : Si tu veux que le résultat devienne le nouveau polynôme courant :
        // MainApp.polynomeCourant = pDerive;
        // rafraichirPolynomeCourant();
    }

    @FXML
    private void primitive() {
        Polynome pPrimitive = MainApp.polynomeCourant.integrer();
        labelResultat.setText("∫P(x) dx = " + pPrimitive.toString() + " + C");
    }

    @FXML
    private void addition() {
        String saisie = champSaisieOperation.getText();
        if (saisie == null || saisie.trim().isEmpty()) {
            labelResultat.setText("Erreur : Veuillez saisir un deuxième polynôme Q(x) dans la case.");
            return;
        }
        try {
            Polynome q = PolynomeParser.parse(saisie);
            // On suppose que ta classe Polynome possède une méthode ajouter(Polynome autre) ou plus(Polynome autre)
            // Adapte le nom de la méthode ci-dessous (.ajouter) selon ton fichier Polynome.java
            Polynome res = MainApp.polynomeCourant.additionner(q); 
            labelResultat.setText("P(x) + Q(x) = " + res.toString());
        } catch (Exception e) {
            labelResultat.setText("Erreur : Syntaxe du polynôme Q(x) incorrecte.");
        }
    }

    @FXML
    private void multiplication() {
        String saisie = champSaisieOperation.getText();
        if (saisie == null || saisie.trim().isEmpty()) {
            labelResultat.setText("Erreur : Veuillez saisir un deuxième polynôme Q(x) dans la case.");
            return;
        }
        try {
            Polynome q = PolynomeParser.parse(saisie);
            // Adapte le nom de la méthode (.multiplier) selon ton fichier Polynome.java
            Polynome res = MainApp.polynomeCourant.multiplierPolynome(q);
            labelResultat.setText("P(x) * Q(x) = " + res.toString());
        } catch (Exception e) {
            labelResultat.setText("Erreur : Syntaxe du polynôme Q(x) incorrecte.");
        }
    }

    @FXML
    private void evaluation() {
        String saisie = champSaisieOperation.getText();
        if (saisie == null || saisie.trim().isEmpty()) {
            labelResultat.setText("Erreur : Veuillez saisir un nombre x.");
            return;
        }
        try {
            double x = Double.parseDouble(saisie.trim());
            double res = MainApp.polynomeCourant.evaluer(x);
            labelResultat.setText("P(" + x + ") = " + res);
        } catch (NumberFormatException e) {
            labelResultat.setText("Erreur : Veuillez entrer un nombre valide (ex: 2.5 ou -3).");
        }
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }
}