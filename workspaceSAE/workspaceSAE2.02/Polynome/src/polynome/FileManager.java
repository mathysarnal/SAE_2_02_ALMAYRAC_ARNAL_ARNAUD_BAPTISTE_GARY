package polynome;

import java.io.*;

public class FileManager {

    public static void sauvegarder(Polynome p, File fichier) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fichier))) {
            pw.println(p.toString());
        }
    }

    public static Polynome charger(File fichier) throws IOException {
        String ligne;

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            ligne = br.readLine();
        }

        if (ligne == null || ligne.isEmpty()) {
            throw new IOException("Fichier vide");
        }

        return PolynomeParser.parse(ligne);
    }
}