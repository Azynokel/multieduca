/*
 * Autor: Basim Bennaji
 * Thema: Autoren des Programms (Credits).
 * Erstellungsdatum: 03.03.2023
 * Letzte Aenderung: 04.03.2023 01:20
 * Icons: https://ionic.io/ionicons
 * Change-Log:
 *  -Funktion switchToHome hinzugefuegt. 04.03.2023 ~basim
 *  -Labels werden mit den Namen der Autoren initializiert. 04.03.2023 ~basim
 */
 package sample;

 import java.io.IOException;

 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Node;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.stage.Stage;
 
 public class InfoController {
     
     private Stage stage;
     private Scene scene;
 
     @FXML
     private Button homebtn;
 
     @FXML
     private Label people1;
 
     @FXML
     private Label people2;
     
     public void switchToHome (ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../rsc/Startscreen.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         
     }
     
     @FXML
     void initialize() {
         people2.setText("Basim Bennaji \n Till Dietrich \n Emil Habermann \n Alexander Horns \n Moritz Oehme \n Felix Trautwein");
         people1.setText("Niklas Bamberg \n Alexander Betke \n Maximilian Gombala \n Samuel Hoffleit \n Jonas Lossin \n Rosan Sharma");
 
     }
 
 }