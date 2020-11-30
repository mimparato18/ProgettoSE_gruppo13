/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer;

import businesslayer.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class CreateUserWindowController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField passPwd;
    @FXML
    private Button btnAdd;
    @FXML
    private ComboBox<String> comboRole;
    @FXML
    private Label labelWarning;
    @FXML
    private Label labelDone;

    private SystemAdministratorController admin;
    

    public CreateUserWindowController(SystemAdministratorController admin) {
        this.admin = admin;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboRole.getItems().addAll("Planner", "Maintainer");
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent event) {
        labelWarning.setVisible(false);
        labelDone.setTextFill(Color.GREEN);
        labelDone.setText("Done");
        labelDone.setVisible(false);

        if (!txtUser.getText().isEmpty() && !passPwd.getText().isBlank() && comboRole.getValue() != null) {

            if (admin.addUser(txtUser.getText(), passPwd.getText(), comboRole.getValue())) {
                labelDone.setVisible(true);
            } else {
                labelDone.setText("Add failed");
                labelDone.setTextFill(Color.RED);
                labelDone.setVisible(true);
            }

        } else {
            labelWarning.setVisible(true);
        }

    }

}
