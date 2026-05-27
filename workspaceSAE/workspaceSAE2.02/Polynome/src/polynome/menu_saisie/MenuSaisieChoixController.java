package polynome.menu_saisie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import polynome.MainApp;

public class MenuSaisieChoixController {

    @FXML private Label labelPolynome;

    @FXML
    public void initialize() {
        if (MainApp.polynomeCourant != null) {
            labelPolynome.setText(MainApp.polynomeCourant.toString());
        }
    }

    @FXML
    private void choisirDirecte() {
        MainApp.changerScene("menu_saisie/saisie_directe.fxml");
    }

    @FXML
    private void choisirClassique() {
        MainApp.changerScene("menu_saisie/saisie_classique.fxml");
    }

    @FXML
    private void choisirRacines() {
        MainApp.changerScene("menu_saisie/saisie_racines.fxml");
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }
}