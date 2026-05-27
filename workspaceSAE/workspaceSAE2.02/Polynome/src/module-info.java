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
    requires org.junit.jupiter.api;

    exports polynome;
    opens polynome to javafx.fxml, javafx.graphics;
}
