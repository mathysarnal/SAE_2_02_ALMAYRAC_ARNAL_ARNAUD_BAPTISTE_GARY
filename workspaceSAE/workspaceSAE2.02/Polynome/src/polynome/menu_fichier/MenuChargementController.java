package polynome.menu_fichier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import polynome.MainApp;
import polynome.FileManager;
import polynome.Polynome;
import java.io.File;

public class MenuChargementController {

    private File fichierChoisi;
    @FXML private Label labelPolynome;

    @FXML
    public void initialize() {
        if (MainApp.polynomeCourant != null) {
            labelPolynome.setText(MainApp.polynomeCourant.toString());
        }
    }

    @FXML
    private void choisirFichier() {
        FileChooser fc = new FileChooser();
        fichierChoisi = fc.showOpenDialog(null);
    }

    @FXML
    private void charger() {
        if (fichierChoisi != null) {
            try {
                Polynome p = FileManager.charger(fichierChoisi);
                MainApp.polynomeCourant = p;
                MainApp.changerScene("menu_principal/menu_principal.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }
}