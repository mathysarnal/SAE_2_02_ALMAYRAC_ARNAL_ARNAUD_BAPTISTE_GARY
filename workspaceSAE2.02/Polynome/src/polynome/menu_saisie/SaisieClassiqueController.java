package polynome.menu_saisie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import polynome.MainApp;
import polynome.Polynome;

public class SaisieClassiqueController {

    @FXML private Label labelPolynome;
    @FXML private TextField champDegres;
    @FXML private TextField champCoefficients;
    @FXML private Label labelErreur;

    @FXML
    public void initialize() {
        if (MainApp.polynomeCourant != null) {
            labelPolynome.setText(MainApp.polynomeCourant.toString());
        }
    }

    @FXML
    private void valider() {
        labelErreur.setText(""); // Réinitialise les erreurs

        String texteDegres = champDegres.getText();
        String texteCoefs = champCoefficients.getText();

        if (texteDegres == null || texteDegres.trim().isEmpty() || texteCoefs == null || texteCoefs.trim().isEmpty()) {
            labelErreur.setText("Veuillez remplir les deux champs.");
            return;
        }

        try {
            // Découpage par rapport aux espaces
            String[] partsDegres = texteDegres.trim().split("\\s+");
            String[] partsCoefs = texteCoefs.trim().split("\\s+");

            // Vérification de la correspondance des tailles
            if (partsDegres.length != partsCoefs.length) {
                labelErreur.setText("Erreur : Le nombre de degrés (" + partsDegres.length 
                        + ") ne correspond pas au nombre de coefficients (" + partsCoefs.length + ").");
                return;
            }

            // Tableau temporaire pour accumuler les coefficients par degré
            // (Taille de 200 par sécurité, ou plus si tes degrés max dépassent 200)
            double[] sommeCoefsParDegre = new double[200];
            boolean[] degreSaisi = new boolean[200];

            for (int i = 0; i < partsDegres.length; i++) {
                int d = Integer.parseInt(partsDegres[i]);
                double c = Double.parseDouble(partsCoefs[i]);

                if (d < 0 || d >= sommeCoefsParDegre.length) {
                    labelErreur.setText("Erreur : Le degré " + d + " n'est pas supporté (doit être entre 0 et 199).");
                    return;
                }

                sommeCoefsParDegre[d] += c;
                degreSaisi[d] = true;
            }

            // Comptage des monômes non nuls pour la structure en "creux"
            int nbMonomesNuls = 0;
            for (int d = 0; d < sommeCoefsParDegre.length; d++) {
                if (degreSaisi[d] && sommeCoefsParDegre[d] != 0) {
                    nbMonomesNuls++;
                }
            }

            Polynome p;

            // Si tout s'annule ou est égal à 0
            if (nbMonomesNuls == 0) {
                p = new Polynome(new double[]{0}, new int[]{0});
            } else {
                double[] coefsFinaux = new double[nbMonomesNuls];
                int[] degresFinaux = new int[nbMonomesNuls];
                int idx = 0;

                // On parcourt à l'envers pour ranger les plus grands degrés en premier si souhaité
                for (int d = sommeCoefsParDegre.length - 1; d >= 0; d--) {
                    if (degreSaisi[d] && sommeCoefsParDegre[d] != 0) {
                        coefsFinaux[idx] = sommeCoefsParDegre[d];
                        degresFinaux[idx] = d;
                        idx++;
                    }
                }
                p = new Polynome(coefsFinaux, degresFinaux);
            }

            // Sauvegarde globale et retour au menu
            MainApp.polynomeCourant = p;
            MainApp.changerScene("menu_principal/menu_principal.fxml");

        } catch (NumberFormatException e) {
            labelErreur.setText("Erreur de format : Assurez-vous d'entrer uniquement des entiers pour les degrés et des nombres pour les coefficients.");
        } catch (Exception e) {
            labelErreur.setText("Une erreur inattendue est survenue lors de la création du polynôme.");
        }
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_saisie/menu_saisie.fxml");
    }
}