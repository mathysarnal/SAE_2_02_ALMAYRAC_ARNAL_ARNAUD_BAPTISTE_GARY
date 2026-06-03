package polynome.menu_operations;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import polynome.MainApp;
import polynome.Polynome;
import polynome.PolynomeIo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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
        if (MainApp.polynomeCourant == null) return;
        Polynome pDerive = MainApp.polynomeCourant.deriver();
        labelResultat.setText("P'(x) = " + pDerive.toString());
    }

    @FXML
    private void primitive() {
        if (MainApp.polynomeCourant == null) return;
        Polynome pPrimitive = MainApp.polynomeCourant.integrer();
        labelResultat.setText("∫P(x) dx = " + pPrimitive.toString() + " + C");
    }

    /**
     * Utilise PolynomeIo en créant un fichier temporaire pour décoder la saisie
     */
    private Polynome convertirSaisieEnPolynome(String saisie) throws Exception {
        // On s'assure que le dossier sauvegardes existe
        File dossier = new File("sauvegardes");
        if (!dossier.exists()) {
            dossier.mkdir();
        }

        // Écriture de la saisie brute (ex: COEFF;2;2;3;0) dans un fichier temporaire
        String nomFichierTemp = "__temp__";
        File fichierTemp = new File(dossier.getName() + File.separator + nomFichierTemp + ".txt");
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierTemp))) {
            writer.write(saisie.trim());
        }

        PolynomeIo outilIo = new PolynomeIo();
        Polynome p = outilIo.charger(nomFichierTemp);

        // Nettoyage : on supprime le fichier temporaire pour ne pas polluer le dossier
        if (fichierTemp.exists()) {
            fichierTemp.delete();
        }

        return p;
    }

    @FXML
    private void addition() {
        if (MainApp.polynomeCourant == null) return;
        
        String saisie = champSaisieOperation.getText();
        if (saisie == null || saisie.trim().isEmpty()) {
            labelResultat.setText("Erreur : Saisissez Q(x) au format COEFF;coeff;degre...");
            return;
        }
        try {
            Polynome q = convertirSaisieEnPolynome(saisie);
            Polynome res = MainApp.polynomeCourant.additionner(q); 
            labelResultat.setText("P(x) + Q(x) = " + res.toString());
        } catch (Exception e) {
            labelResultat.setText("Erreur : Format incorrect. Exemple: COEFF;2;3;4;1");
        }
    }

    @FXML
    private void multiplication() {
        if (MainApp.polynomeCourant == null) return;

        String saisie = champSaisieOperation.getText();
        if (saisie == null || saisie.trim().isEmpty()) {
            labelResultat.setText("Erreur : Saisissez Q(x) au format COEFF;coeff;degre...");
            return;
        }
        try {
            Polynome q = convertirSaisieEnPolynome(saisie);
            Polynome res = MainApp.polynomeCourant.multiplierPolynome(q);
            labelResultat.setText("P(x) * Q(x) = " + res.toString());
        } catch (Exception e) {
            labelResultat.setText("Erreur : Format incorrect. Exemple: COEFF;2;3;4;1");
        }
    }

    @FXML
    private void evaluation() {
        if (MainApp.polynomeCourant == null) return;

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
            labelResultat.setText("Erreur : Veuillez entrer un nombre valide (ex: 2.5).");
        }
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }
}