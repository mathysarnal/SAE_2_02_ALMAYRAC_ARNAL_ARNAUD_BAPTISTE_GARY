package polynome;

import polynome.Polynome;

public class PolynomeParser {

    public static Polynome parse(String texte) {

        texte = texte.replace(" ", "");

        if (texte.equals("0")) {
            return new Polynome(new double[]{0}, new int[]{0});
        }

        // Normalisation : remplacer les "-" par "+-"
        texte = texte.replace("-", "+-");
        if (texte.startsWith("+")) texte = texte.substring(1);

        String[] morceaux = texte.split("\\+");

        double[] tempCoefs = new double[200];
        boolean[] present = new boolean[200];

        for (String m : morceaux) {
            if (m.isEmpty()) continue;

            double coef;
            int deg;

            if (!m.contains("x")) {
                coef = Double.parseDouble(m);
                deg = 0;
            }
            else {
                String[] parts = m.split("x");

                if (parts[0].equals("") || parts[0].equals("+")) coef = 1;
                else if (parts[0].equals("-")) coef = -1;
                else coef = Double.parseDouble(parts[0]);

                if (parts.length == 1) {
                    deg = 1;
                }
                else {
                    deg = Integer.parseInt(parts[1].substring(1));
                }
            }

            tempCoefs[deg] += coef;
            present[deg] = true;
        }

        int nb = 0;
        for (int d = 0; d < tempCoefs.length; d++) {
            if (present[d] && tempCoefs[d] != 0) nb++;
        }

        if (nb == 0) {
            return new Polynome(new double[]{0}, new int[]{0});
        }

        double[] coefs = new double[nb];
        int[] degs = new int[nb];
        int idx = 0;

        for (int d = tempCoefs.length - 1; d >= 0; d--) {
            if (present[d] && tempCoefs[d] != 0) {
                coefs[idx] = tempCoefs[d];
                degs[idx] = d;
                idx++;
            }
        }

        return new Polynome(coefs, degs);
    }
}
