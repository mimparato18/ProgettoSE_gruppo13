/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer;

import businesslayer.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userinterfacelayer.Maintainer.MaintainerHubGUIController;
import userinterfacelayer.Planner.PlannerHubGUIController;
import userinterfacelayer.SystemAdministrator.SystemAdministratorHubGUIController;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class LoginWindowController implements Initializable {

    private String username;
    private String password;
    private LoginService user = new LoginService();
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Label labWarning = new Label();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String username = "";
        String password = "";
    }

    @FXML
    private void btnLogin_OnAction(ActionEvent event) {
        username = txtUsername.getText();
        password = txtPass.getText();
        if (username.isBlank()) {
            labWarning.setText("Missing username");
            labWarning.setVisible(true);
        } else {
            if (password.isBlank()) {

                labWarning.setText("Missing password");
                labWarning.setVisible(true);
            } else {
                String role = user.checkCredentials(username, password);

                if ("System Administrator".equals(role)) {
                    loadAdminInterface(username, password);
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                } else if ("Maintainer".equals(role)) {
                    loadMaintainerInterface(username, password);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else if ("Planner".equals(role)) {
                    loadPlannerInterface(username, password);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {
                    labWarning.setText("Check credentials");
                    labWarning.setVisible(true);
                }
            }

        }

    }

    private void loadAdminInterface(String username, String password) {
        try {
            String ui = "HomeGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            SystemAdministratorHubGUIController controller = new SystemAdministratorHubGUIController(username, password);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Admin Hub");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException ex) {
            System.out.println("Can't load the window" + ex);
        }
    }

    private void loadPlannerInterface(String username, String password) {
        try {
            String ui = "PlannerHubGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            PlannerHubGUIController controller = new PlannerHubGUIController(username, password);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Planner Hub");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException ex) {
            System.out.println("Can't load the window" + ex);
        }
    }

    private void loadMaintainerInterface(String username, String password) {
        try {
            String ui = "MaintainerHubGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            MaintainerHubGUIController controller = new MaintainerHubGUIController(username, password);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Maintainer Hub");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException ex) {
            System.out.println("Can't load the window" + ex);
        }
    }

}
