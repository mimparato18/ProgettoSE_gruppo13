/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

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
        String ui = "LoginWindow.fxml";
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(ui));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
