package polynome.menu_saisie;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import polynome.MainApp;
import polynome.Polynome;

public class SaisieClassiqueController {

    @FXML private TextField champDegre;
    @FXML private TextField champCoefficients;

    @FXML
    private void valider() {
        int deg = Integer.parseInt(champDegre.getText());
        String[] parts = champCoefficients.getText().trim().split("\\s+");

        double[] temp = new double[deg + 1];

        for (int i = 0; i < parts.length; i++) {
            temp[deg - i] = Double.parseDouble(parts[i]);
        }

        int nb = 0;
        for (int d = 0; d <= deg; d++) {
            if (temp[d] != 0) nb++;
        }

        Polynome p;

        if (nb == 0) {
            p = new Polynome(new double[]{0}, new int[]{0});
        } else {
            double[] coefs = new double[nb];
            int[] degs = new int[nb];
            int idx = 0;

            for (int d = deg; d >= 0; d--) {
                if (temp[d] != 0) {
                    coefs[idx] = temp[d];
                    degs[idx] = d;
                    idx++;
                }
            }
            p = new Polynome(coefs, degs);
        }

        MainApp.polynomeCourant = p;
        // Retour automatique au menu principal après la création réussie
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_saisie/menu_saisie.fxml");
    }
}