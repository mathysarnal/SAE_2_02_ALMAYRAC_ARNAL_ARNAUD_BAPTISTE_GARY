package polynome.menu_fichier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import polynome.MainApp;
import polynome.PolynomeIo;

public class MenuSauvegardeController {

    @FXML private Label labelPolynome;
    @FXML private TextField champNomFichier;
    @FXML private Label labelStatut;

    @FXML
    public void initialize() {
        if (MainApp.polynomeCourant != null) {
            labelPolynome.setText(MainApp.polynomeCourant.toString());
        }
    }

    @FXML
    private void sauvegarder() {
        String nomFichier = champNomFichier.getText();
        if (nomFichier == null || nomFichier.trim().isEmpty()) {
            labelStatut.setStyle("-fx-text-fill: red;");
            labelStatut.setText("Erreur : Veuillez entrer un nom de fichier.");
            return;
        }

        try {
            PolynomeIo outilIo = new PolynomeIo();
            // Appel de ta méthode de sauvegarde personnalisée
            outilIo.sauvegarder(MainApp.polynomeCourant, nomFichier.trim());
            
            labelStatut.setStyle("-fx-text-fill: #27ae60;");
            labelStatut.setText("Sauvegarde réussie dans le dossier 'sauvegardes' !");
            champNomFichier.clear();
        } catch (Exception e) {
            e.printStackTrace();
            labelStatut.setStyle("-fx-text-fill: red;");
            labelStatut.setText("Erreur lors de l'écriture du fichier.");
        }
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }
}