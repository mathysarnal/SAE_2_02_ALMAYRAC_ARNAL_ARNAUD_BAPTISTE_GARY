package polynome.menu_principal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import polynome.MainApp;

public class MenuPrincipalController {

    @FXML private Label labelPolynome;

    @FXML
    public void initialize() {
        if (MainApp.polynomeCourant != null) {
            labelPolynome.setText(MainApp.polynomeCourant.toString());
        } else {
            labelPolynome.setText("Aucun polynôme");
        }
    }

    @FXML
    private void ouvrirMenuSaisie() {
        MainApp.changerScene("menu_saisie/menu_saisie.fxml");
    }

    @FXML
    private void ouvrirMenuOperations() {
        MainApp.changerScene("menu_operations/menu_operations.fxml");
    }

    @FXML
    private void ouvrirMenuChargement() {
        MainApp.changerScene("menu_fichier/menu_chargement.fxml");
    }
    
    @FXML
    private void ouvrirMenuSauvegarde() {
        MainApp.changerScene("menu_fichier/menu_sauvegarde.fxml");
    }

    @FXML
    private void quitter() {
        System.exit(0);
    }
}