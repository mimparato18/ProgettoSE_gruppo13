/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.SystemAdministrator;

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
public class HomeGUIController implements Initializable {

    

    @FXML
    private Button btnUser;
    @FXML
    private Button btnSite;
    @FXML
    private Button btnTypo;
    @FXML
    private Button btnProcedure;
    @FXML
    private Button btnLogout;

    private SystemAdministratorService admin;
    
    public HomeGUIController(String username,String password) {
        this.admin= new SystemAdministratorService(new SystemAdministrator(username,password));
    }
    public HomeGUIController(SystemAdministratorService admin){
        this.admin=admin;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void btnProced_OnAction(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/SystemAdministrator/ProcedureManagementGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            ProcedureManagementGUIController controller = new ProcedureManagementGUIController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Procedure Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnProcedure.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void btnUser_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/SystemAdministrator/SystemAdministratorGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            SystemAdministratorGUIController controller = new SystemAdministratorGUIController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("User Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnUser.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void btnSite_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/SystemAdministrator/SiteManagementGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            SiteManagementGUIController controller = new SiteManagementGUIController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Site Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnSite.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnTypo_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/SystemAdministrator/TypologyManagementGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            TypologyManagementGUIController controller = new TypologyManagementGUIController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Typology Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnTypo.getScene().getWindow();
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
    

}