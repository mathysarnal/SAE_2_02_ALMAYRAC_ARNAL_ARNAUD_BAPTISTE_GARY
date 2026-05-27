package polynome.menu_operations;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import polynome.MainApp;

public class MenuOperationsController {

    @FXML private Label labelPolynome;

    @FXML
    public void initialize() {
        if (MainApp.polynomeCourant != null) {
            labelPolynome.setText(MainApp.polynomeCourant.toString());
        }
    }

    @FXML
    private void deriver() {
        MainApp.polynomeCourant = MainApp.polynomeCourant.deriver();
        // Recharge la scène pour voir instantanément le nouveau polynôme calculé !
        MainApp.changerScene("menu_operations/menu_operations.fxml");
    }

    @FXML
    private void primitive() {
        MainApp.polynomeCourant = MainApp.polynomeCourant.integrer();
        MainApp.changerScene("menu_operations/menu_operations.fxml");
    }

    @FXML private void addition() { MainApp.changerScene("menu_operations/menu_addition.fxml"); }
    @FXML private void multiplication() { MainApp.changerScene("menu_operations/menu_multiplication.fxml"); }
    @FXML private void division() { MainApp.changerScene("menu_operations/menu_division.fxml"); }
    @FXML private void evaluation() { MainApp.changerScene("menu_operations/menu_evaluation.fxml"); }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }
}