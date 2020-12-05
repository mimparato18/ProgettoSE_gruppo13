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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
                    loadAdminInterface();
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                } else if ("Maintainer".equals(role)) {
                    loadMaintainerInterface();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else if ("Planner".equals(role)) {
                    loadPlannerInterface();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {
                    labWarning.setText("Check credentials");
                    labWarning.setVisible(true);
                }
            }

        }

    }

    private void loadAdminInterface() {
        try {
            String ui = "/userinterfacelayer/HomeGUI.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(ui));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Admin Hub");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("Can't load the window" + ex);
        }
    }

    private void loadPlannerInterface() {
        try {
            String ui = "/userinterfacelayer/PlannerHubGUI.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(ui));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Planner Hub");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("Can't load the window" + ex);
        }
    }

    private void loadMaintainerInterface() {
        try {
            String ui = "/userinterfacelayer/MaintainerHubGUI.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(ui));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Maintainer Hub");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("Can't load the window" + ex);
        }
    }

}
