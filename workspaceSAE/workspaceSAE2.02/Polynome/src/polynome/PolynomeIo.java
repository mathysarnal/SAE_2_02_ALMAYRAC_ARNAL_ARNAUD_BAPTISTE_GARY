package polynome;

import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Composant de sauvegarde/chargement pour les polynômes.
 * <p>
 * Cette classe permet de sauvegarder et de charger des objets
 * de type Polynome dans des fichiers texte situés dans
 * un sous-dossier dédié nommé sauvegardes.
 * </p>
 * <p>
 * Elle prend en charge deux formats d'exportation distincts
 * selon la structure interne du polynôme :
 * </p>
 * <ul>
 *   <li>Le mode <b>COEFF</b> : pour les polynômes définis par 
 *       leurs monômes (coefficients et degrés).</li>
 *   <li>Le mode <b>RACINE</b> : pour les polynômes définis par 
 *       leurs racines réelles, leurs ordres de multiplicité 
 *       et leur coefficient dominant.</li>
 * </ul>
 */
public class PolynomeIo {
	
	/**
	 * Sauvegarde un polynôme dans un fichier au format texte standardisé.
	 * <p>
	 * Le fichier est automatiquement créé ou écrasé à l'intérieur du dossier sauvegardes.
	 * Si ce dossier de transit n'existe pas, la méthode se charge de le générer à la
	 * racine du projet.
	 * </p>
	 * <p>
	 * <b>Format des lignes générées :</b><br>
	 * Mode Coefficients :
	 * COEFF;coeff1;degre1;coeff2;degre2;...<br>
	 * Mode Racines :
	 * RACINE;coeffDominant;racine1;ordre1;racine2;ordre2;...
	 * </p>
	 * @param polynomeASauvegarder l'objet Polynome à enregistrer dans le fichier
	 * @param nomFichier le nom brut du fichier cible, sans le préfixe 
	 * du dossier ni l'extension (ex: "mon_polynome")
	 * @throws IOException si un problème d'accès physique ou
	 * d'écriture survient sur le disque dur
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
	 * Charge et reconstruit un polynôme à partir d'un fichier
	 * texte précédemment sauvegardé.
	 * <p>
	 * La méthode analyse le premier élément de la ligne (le marqueur de type) 
	 * afin d'aiguiller la lecture et d'appeler le constructeur de Polynome
	 * adéquat (par coefficients ou par racines).
	 * </p>
	 * @param nomFichier le nom brut du fichier à charger, sans le
	 * préfixe du dossier ni l'extension (ex: "mon_polynome")
	 * @return un nouvel objet Polynome initialisé avec les données 
	 * extraites du fichier, ou null si le type est inconnu
	 * @throws IOException si le fichier est introuvable, inaccessible
	 * ou si un problème de lecture survient
	 * @throws IllegalArgumentException si le contenu du fichier texte
	 * est vide ou si sa structure interne est corrompue
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