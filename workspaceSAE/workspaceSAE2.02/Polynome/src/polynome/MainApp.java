package polynome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    // Initialisé à 0 par défaut pour éviter les valeurs nulles
    public static Polynome polynomeCourant = new Polynome(new double[]{0}, new int[]{0});

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        changerScene("menu_principal/menu_principal.fxml");
        stage.setTitle("Application Polynômes");
        stage.show();
    }

    public static void changerScene(String cheminFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/polynome/" + cheminFXML));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}