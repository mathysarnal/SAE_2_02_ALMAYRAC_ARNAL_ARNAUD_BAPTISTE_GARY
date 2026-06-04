package iut.info1.polynome.menu_saisie;

import iut.info1.polynome.MainApp;
import iut.info1.polynome.Polynome;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SaisieRacinesController {

    @FXML private TextField champRacines;
    @FXML private TextField champA;

    @FXML
    private void valider() {

        String[] parts = champRacines.getText().trim().split("\\s+");
        double[] racines = new double[parts.length];

        for (int i = 0; i < parts.length; i++) {
            racines[i] = Double.parseDouble(parts[i]);
        }

        double a = Double.parseDouble(champA.getText());

        // multiplicité = 1 pour chaque racine
        int[] ordres = new int[racines.length];
        for (int i = 0; i < ordres.length; i++) ordres[i] = 1;

        Polynome p = new Polynome(racines, ordres, a);

        MainApp.polynomeCourant = p;
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_saisie/menu_saisie.fxml");
    }
}
