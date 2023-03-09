package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Autor: Samuel Hoffleit, Basim Bennaji, Moritz Oehme
 * Ueberarbeitet:
 * Datum: 2023-03-09
 *
 * Zweck: 
 */
 
public class SpielStartenHostController {

    private Stage stage;
    private Scene scene;

    public SpielStartenHostController() throws IOException {

    }

    public void switchToSpielLaeuftHost(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../rsc/SpielLaeuftHost.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
