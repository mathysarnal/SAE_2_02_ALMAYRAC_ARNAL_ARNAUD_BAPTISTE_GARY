package polynome;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomeController {

    @FXML private TextField inputPolynome;
    @FXML private TextField inputX;
    @FXML private TextField inputA;
    @FXML private TextField inputB;
    @FXML private TextArea zoneResultat;

    private Polynome p;

    // ---------------------------
    //  PARSEUR DE POLYNÔME TEXTE
    // ---------------------------
    private Polynome parsePolynome(String texte) {

        // Nettoyage
        texte = texte.replace(" ", "")
                     .replace("-", "+-");

        if (texte.startsWith("+"))
            texte = texte.substring(1);

        String[] morceaux = texte.split("\\+");

        ArrayList<Double> coefs = new ArrayList<>();
        ArrayList<Integer> degs = new ArrayList<>();

        Pattern pattern = Pattern.compile("([+-]?[0-9]*\\.?[0-9]*)(x(?:\\^(-?\\d+))?)?");

        for (String m : morceaux) {
            if (m.isEmpty()) continue;

            Matcher matcher = pattern.matcher(m);

            if (matcher.matches()) {

                String coefStr = matcher.group(1);
                String xPart = matcher.group(2);
                String degStr = matcher.group(3);

                double coef;
                int deg;

                if (xPart == null) {
                    // CONSTANTE
                    coef = Double.parseDouble(coefStr);
                    deg = 0;
                } else {
                    // TERMES AVEC x
                    if (coefStr.equals("") || coefStr.equals("+"))
                        coef = 1;
                    else if (coefStr.equals("-"))
                        coef = -1;
                    else
                        coef = Double.parseDouble(coefStr);

                    if (degStr == null)
                        deg = 1;
                    else
                        deg = Integer.parseInt(degStr);
                }

                coefs.add(coef);
                degs.add(deg);
            }
        }

        double[] c = new double[coefs.size()];
        int[] d = new int[degs.size()];

        for (int i = 0; i < c.length; i++) {
            c[i] = coefs.get(i);
            d[i] = degs.get(i);
        }

        return new Polynome(c, d);
    }

    // ---------------------------
    //  ACTIONS UI
    // ---------------------------

    @FXML
    private void creerPolynome() {
        try {
            p = parsePolynome(inputPolynome.getText());
            zoneResultat.setText("Polynôme créé :\n" + p.toString());
        } catch (Exception e) {
            zoneResultat.setText("Erreur : " + e.getMessage());
        }
    }

    @FXML
    private void evaluer() {
        if (p == null) return;
        try {
            double x = Double.parseDouble(inputX.getText());
            zoneResultat.setText("P(" + x + ") = " + p.evaluer(x));
        } catch (Exception e) {
            zoneResultat.setText("Erreur : " + e.getMessage());
        }
    }

    @FXML
    private void deriver() {
        if (p == null) return;
        Polynome d = p.deriver();
        zoneResultat.setText("Dérivée :\n" + d.toString());
    }

    @FXML
    private void integrer() {
        if (p == null) return;
        Polynome prim = p.integrer();
        zoneResultat.setText("Primitive :\n" + prim.toString());
    }

    @FXML
    private void moyenne() {
        if (p == null) return;
        try {
            double a = Double.parseDouble(inputA.getText());
            double b = Double.parseDouble(inputB.getText());
            double m = p.moyenne(a, b);
            zoneResultat.setText("Moyenne sur [" + a + ", " + b + "] = " + m);
        } catch (Exception e) {
            zoneResultat.setText("Erreur : " + e.getMessage());
        }
    }

    @FXML
    private void additionner() {
        if (p == null) return;
        Polynome add = new Polynome(new double[]{3.0}, new int[]{2});
        Polynome res = p.additionner(add);
        zoneResultat.setText("P(x) + 3x² =\n" + res.toString());
    }
}
