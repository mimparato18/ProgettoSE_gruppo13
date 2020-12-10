/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Planner;

import businesslayer.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class PlannerHubGUIController implements Initializable {
    
    private String username;
    private String password;

    private PlannerService planner=new PlannerService(new Planner(username,password));

    @FXML
    private Button btnActivity;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnAssign;

    public PlannerHubGUIController(String username,String password) {
        this.username=username;
        this.password=password;
    }
    public PlannerHubGUIController(PlannerService planner){
        this.planner=planner;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnActivity_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/Planner/ManageActivityGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            ManageActivityGUIController controller = new ManageActivityGUIController(this.planner);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Activity Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnActivity.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void btnLogout_OnAction(ActionEvent event) {
        try {
            
            
            Parent root = FXMLLoader.load(getClass().getResource("/userinterfacelayer/LoginWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
    }

    @FXML
    private void btnAssign_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/Planner/AssignActivityGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            AssignActivityGUIController controller = new AssignActivityGUIController(this.planner);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Assign Activity");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnAssign.getScene().getWindow();
        stage.close();
    }
}
