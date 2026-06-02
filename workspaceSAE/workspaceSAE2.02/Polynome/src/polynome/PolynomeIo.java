package polynome;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PolynomeIo {
	
	/**
	 * Sauvegarde un polynôme dans un fichier au format texte.
	 * Chaque monôme non nul est écrit sous la forme "coefficient;degré".
	 * @param polynomeASauvegarder l'objet Polynome que l'on souhaite enregistrer
	 * @param cheminFichier le nom ou le chemin du fichier cible (ex: "polynome.txt")
	 * @throws IOException si un problème d'écriture survient (fichier protégé, disque plein...)
	 */
	public void sauvegarder(Polynome polynomeASauvegarder, String cheminFichier) throws IOException {
	    
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
			
			StringBuilder sb = new StringBuilder();
			double[] racines = polynomeASauvegarder.getRacinesReelles();
			int[] ordres = polynomeASauvegarder.getOrdresRacines();
			
			if (racines.length > 0) {
				
				sb.append("RACINES");
				
				double coeffDominant = polynomeASauvegarder.getCoefficient(polynomeASauvegarder.getDegre());
				sb.append(";").append(coeffDominant);
				
				for (int i = 0; i < racines.length; i++) {
					sb.append(";").append(racines[i]).append(";").append(ordres[i]);
				}
				
			} else {
				
				sb.append("COEFF");
				
				double[] coeffs = polynomeASauvegarder.getCoefficients();
				int[] degres = polynomeASauvegarder.getDegres();
				
				for (int i = 0; i < coeffs.length; i++) {
					sb.append(";").append(coeffs[i]).append(";").append(degres[i]);
				}
			}
			
			writer.write(sb.toString()); //écriture de la ligne dans le fichier
			
			writer.newLine(); //saut de ligne pour avoir un polynome par ligne 
		}
	}
	
	/**
	 * Charge un polynôme à partir d'un fichier texte précédemment sauvegardé.
	 * Elle lit les coefficients et les degrés pour reconstruire l'objet.
	 * @param cheminFichier le nom ou le chemin du fichier à lire
	 * @return un nouvel objet Polynome initialisé avec les données du fichier
	 * @throws IOException si un problème de lecture survient (fichier introuvable...)
	 * @throws IllegalArgumentException si le contenu du fichier est mal formaté ou corrompu
	 */
	public Polynome charger(String cheminFichier) throws IOException {
	    // Étape suivante : nous coderons le corps de cette méthode ensemble !
	    return null; // Temporaire pour que le code compile
	}
}