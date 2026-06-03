package iut.info1.polynome.menu_saisie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import iut.info1.polynome.MainApp;
import iut.info1.polynome.Polynome;

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
            Polynome p = analyserFormuleMathematique(saisie);
            MainApp.polynomeCourant = p;
            MainApp.changerScene("menu_principal/menu_principal.fxml");
        } catch (Exception e) {
            labelErreur.setText("Erreur de syntaxe. Ex: 2x^3 - 4x^2 + x + 6");
        }
    }

    private Polynome analyserFormuleMathematique(String expression) throws Exception {
        // 1. Nettoyage des espaces et uniformisation
        String expr = expression.replaceAll("\\s+", "");
        
        // On remplace les "-" par " + -" pour pouvoir découper proprement par les "+"
        expr = expr.replace("-", "+-");
        if (expr.startsWith("+")) {
            expr = expr.substring(1); // On enlève le tout premier "+" s'il y en a un
        }
        
        String[] morceaux = expr.split("\\+");
        
        ArrayList<Double> listeCoeffs = new ArrayList<>();
        ArrayList<Integer> listeDegres = new ArrayList<>();

        for (String morceau : morceaux) {
            if (morceau.isEmpty()) continue;

            double coeff = 1.0;
            int degre = 0;

            // Cas 1 : C'est une constante (ex: 6 ou -3) -> Pas de 'x'
            if (!morceau.contains("x")) {
                coeff = Double.parseDouble(morceau);
                degre = 0;
            } 
            // Cas 2 : Il y a un 'x'
            else {
                // On extrait le coefficient (ce qui est avant le 'x')
                String partieCoeff = morceau.split("x")[0];
                if (partieCoeff.isEmpty() || partieCoeff.equals("+")) {
                    coeff = 1.0;
                } else if (partieCoeff.equals("-")) {
                    coeff = -1.0;
                } else {
                    coeff = Double.parseDouble(partieCoeff);
                }

                // On extrait le degré (ce qui est après le 'x')
                if (morceau.contains("x^")) {
                    degre = Integer.parseInt(morceau.split("x\\^")[1]);
                } else {
                    degre = 1; // Juste "x" équivaut à "x^1"
                }
            }

            listeCoeffs.add(coeff);
            listeDegres.add(degre);
        }

        // Conversion ArrayList -> tableaux primitifs double[] et int[]
        double[] coeffsFinaux = new double[listeCoeffs.size()];
        int[] degresFinaux = new int[listeDegres.size()];
        
        for (int i = 0; i < listeCoeffs.size(); i++) {
            coeffsFinaux[i] = listeCoeffs.get(i);
            degresFinaux[i] = listeDegres.get(i);
        }

        return new Polynome(coeffsFinaux, degresFinaux);
    }

    @FXML
    private void retour() {
        MainApp.changerScene("menu_saisie/menu_saisie.fxml");
    }
}