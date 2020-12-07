/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Maintainer;

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
public class MaintainerHubGUIController implements Initializable {

    private String username;
    private String password;

    
    public MaintainerHubGUIController(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnLogout_OnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/userinterfacelayer/Maintainer/LoginWindow.fxml"));
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
