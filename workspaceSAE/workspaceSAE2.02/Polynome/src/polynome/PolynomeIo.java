package polynome;

import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PolynomeIo {
	
	/**
	 * Sauvegarde un polynôme dans un fichier au format texte.
	 * Chaque monôme non nul est écrit sous la forme "coefficient;degré".
	 * @param polynomeASauvegarder l'objet Polynome que l'on souhaite enregistrer
	 * @param cheminFichier le nom ou le chemin du fichier cible (ex: "polynome.txt")
	 * @throws IOException si un problème d'écriture survient (fichier protégé, disque plein...)
	 */
	public void sauvegarder(Polynome polynomeASauvegarder, String nomFichier) throws IOException {
	    
		File dossier = new File("sauvegardes");
		
		if (!dossier.exists()) {
	        dossier.mkdir(); 
	    }
		
		String cheminComplet = dossier.getName() + File.separator + nomFichier + ".txt";
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminComplet))) {
			
			StringBuilder constructeurTexte = new StringBuilder();
			double[] racines = polynomeASauvegarder.getRacinesReelles();
			int[] ordres = polynomeASauvegarder.getOrdresRacines();
			
			if (racines.length > 0) {
				
				constructeurTexte.append("RACINE");
				
				double coeffDominant = polynomeASauvegarder.getCoefficient(polynomeASauvegarder.getDegre());
				constructeurTexte.append(";").append(coeffDominant);
				
				for (int monome = 0; monome < racines.length; monome++) {
					constructeurTexte.append(";").append(racines[monome]).append(";").append(ordres[monome]);
				}
				
			} else {
				
				constructeurTexte.append("COEFF");
				
				double[] coeffs = polynomeASauvegarder.getCoefficients();
				int[] degres = polynomeASauvegarder.getDegres();
				
				for (int monome = 0; monome < coeffs.length; monome++) {
					constructeurTexte.append(";").append(coeffs[monome]).append(";").append(degres[monome]);
				}
			}
			
			writer.write(constructeurTexte.toString()); //écriture de la ligne dans le fichier
			
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
	public Polynome charger(String nomFichier) throws IOException {
		
		String cheminComplet = "sauvegardes" + File.separator + nomFichier + ".txt";
		
		try (BufferedReader reader = new BufferedReader(new FileReader(cheminComplet))) {
			
			String ligne = reader.readLine();
			
			if (ligne == null || ligne.isEmpty()) {
				throw new IllegalArgumentException("Le fichier est vide, impossible de charger un polynôme.");
			}
			
			String elements[] = ligne.split(";");
			
			String type = elements[0];
			
			if (type.equals("COEFF")) {
	            
	            int nbMonomes = (elements.length - 1) / 2; // On calcule le nombre de monômes non nuls
	            
	            double[] coeffs = new double[nbMonomes];
	            int[] degres = new int[nbMonomes];
	            
	            int indexInsertion = 0;
	            
	            // On parcourt les éléments textuels de 2 en 2 en partant de l'indice 1
	            for (int nbPaire = 1; nbPaire < elements.length; nbPaire += 2) {
	                coeffs[indexInsertion] = Double.parseDouble(elements[nbPaire]);
	                degres[indexInsertion] = Integer.parseInt(elements[nbPaire + 1]);
	                indexInsertion++;
	            }
	            
	            // On reconstruit et on renvoie le polynôme
	            return new Polynome(coeffs, degres);
	            
	        } else if (type.equals("RACINE")) {
	        	
	        	int nbRacines = (elements.length - 2) / 2; // On calcule le nombre de racines (on enlève le type et le coeff dominant, puis / 2)
	        	
	        	double coeffDominant = Double.parseDouble(elements[1]);
	        	
	        	double[] racines = new double[nbRacines];
	        	int[] ordres = new int[nbRacines];
	        	
	        	int indexInsertion = 0;
	        	
	        	// On parcourt les éléments textuels de 2 en 2 en partant de l'indice 1
	            for (int nbPaire = 2; nbPaire < elements.length; nbPaire += 2) {
	                racines[indexInsertion] = Double.parseDouble(elements[nbPaire]);
	                ordres[indexInsertion] = Integer.parseInt(elements[nbPaire + 1]);
	                indexInsertion++;
	            }
	            
	            return new Polynome(racines, ordres, coeffDominant);
	        }
		}
		
	return null;
	
	}
}