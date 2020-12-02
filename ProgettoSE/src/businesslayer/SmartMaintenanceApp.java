/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import userinterfacelayer.*;
import dataaccesslayer.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author group13
 */
public class SmartMaintenanceApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        String ui="/userinterfacelayer/HomeGUI.fxml";
        //String uilog="LoginGUI.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(ui));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Admin Hub");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
