package polynome.menu_saisie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import polynome.MainApp;
import polynome.Polynome;
import polynome.PolynomeParser;

public class SaisieDirecteController {

    @FXML private TextField champTextePolynome;
    @FXML private Label labelErreur;

    @FXML
    private void valider() {
        String saisie = champTextePolynome.getText();
        if (saisie == null || saisie.trim().isEmpty()) {
            labelErreur.setText("Veuillez saisir une expression valide.");
            return;
        }

        try {
            // Utilisation directe de ton parser mathématique existant !
            Polynome p = PolynomeParser.parse(saisie);
            MainApp.polynomeCourant = p;
            
            // Retour au menu principal
            MainApp.changerScene("menu_principal/menu_principal.fxml");
        } catch (Exception e) {
            labelErreur.setText("Erreur de syntaxe dans le polynôme.");
        }
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_saisie/menu_saisie.fxml");
    }
}