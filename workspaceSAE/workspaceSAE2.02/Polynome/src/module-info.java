/**
 * module-info.java                                         3 avr. 2026
 * IUT de Rodez, Info1 2025-2026, pas de copyright
 */
/**
 * 
 */
module Polynome {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
    
    opens polynome to javafx.fxml;
    opens polynome.menu_principal to javafx.fxml;
    opens polynome.menu_saisie to javafx.fxml;
    opens polynome.menu_operations to javafx.fxml;
    opens polynome.menu_fichier to javafx.fxml;

    // Permet d'exécuter la classe MainApp
    exports polynome;
}