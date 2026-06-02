package polynome.menu_fichier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import polynome.MainApp;
import polynome.PolynomeIo;
import polynome.Polynome;

import java.io.File;

public class MenuChargementController {

    private File fichierChoisi;

    @FXML private Label labelStatut; // Pour afficher si le chargement a réussi ou échoué

    @FXML
    private void choisirFichier() {
        FileChooser fc = new FileChooser();
        // Optionnel : filtrer uniquement les fichiers texte
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte (*.txt)", "*.txt"));
        
        // Si tu veux ouvrir directement le dossier "sauvegardes" s'il existe
        File dossierSauvegardes = new File("sauvegardes");
        if (dossierSauvegardes.exists() && dossierSauvegardes.isDirectory()) {
            fc.setInitialDirectory(dossierSauvegardes);
        }

        fichierChoisi = fc.showOpenDialog(null);
        
        if (fichierChoisi != null && labelStatut != null) {
            labelStatut.setText("Fichier sélectionné : " + fichierChoisi.getName());
        }
    }

    @FXML
    private void charger() {
        if (fichierChoisi == null) {
            if (labelStatut != null) {
                labelStatut.setText("Erreur : Veuillez d'abord choisir un fichier.");
            }
            return;
        }

        try {
            PolynomeIo outilIo = new PolynomeIo();
            
            // 1. On récupère le nom du fichier avec son extension (ex: "mon_polynome.txt")
            String nomAvecExtension = fichierChoisi.getName();
            
            // 2. On retire les 4 derniers caractères (".txt") car la méthode rajoute déjà ".txt"
            String nomSansExtension = nomAvecExtension.substring(0, nomAvecExtension.length() - 4);
            
            // 3. On appelle TA méthode en lui donnant la String attendue !
            Polynome p = outilIo.charger(nomSansExtension);
            
            // Sauvegarde dans la variable globale de l'application
            MainApp.polynomeCourant = p;
            
            // Redirection vers le menu principal
            MainApp.changerScene("menu_principal/menu_principal.fxml");
            
        } catch (Exception e) {
            e.printStackTrace();
            if (labelStatut != null) {
                labelStatut.setText("Erreur lors du chargement : Fichier invalide ou corrompu.");
            }
        }
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_principal/menu_principal.fxml");
    }
}