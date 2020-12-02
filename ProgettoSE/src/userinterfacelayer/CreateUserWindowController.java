/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer;

import businesslayer.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
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
    private TextArea txtAreaCompetencies;
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

    private SystemAdministratorService admin;

    public CreateUserWindowController(SystemAdministratorService admin) {
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
    private void comboRole_OnAction(ActionEvent event) {

        if (comboRole.getSelectionModel().getSelectedItem() == "Maintainer") {
            txtAreaCompetencies.setDisable(false);
        } else {
            txtAreaCompetencies.clear();
            txtAreaCompetencies.setDisable(true);
        }
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent event) {
        labelWarning.setVisible(false);
        labelDone.setTextFill(Color.GREEN);
        labelDone.setText("Done");
        labelDone.setVisible(false);

        if (!txtUser.getText().isBlank() && !passPwd.getText().isBlank() && comboRole.getValue() != null) {

            if (comboRole.getSelectionModel().getSelectedItem() != "Maintainer") {
                if (admin.addUser(txtUser.getText(), passPwd.getText(), comboRole.getValue())) {
                    labelDone.setVisible(true);
                    txtUser.clear();
                    passPwd.clear();
                    
                } else {
                    labelDone.setText("Add failed");
                    labelDone.setTextFill(Color.RED);
                    labelDone.setVisible(true);
                }
            } else {
                //add User with role Maintainer
                if (!txtAreaCompetencies.getText().isBlank()) {
                    if (admin.addUser(txtUser.getText(), passPwd.getText(), comboRole.getValue(), txtAreaCompetencies.getText())) {
                        labelDone.setVisible(true);
                        
                        txtUser.clear();
                        passPwd.clear();
                        txtAreaCompetencies.clear();
                    } else {
                        labelDone.setText("Add failed");
                        labelDone.setTextFill(Color.RED);
                        labelDone.setVisible(true);
                    }
                } else {
                    labelWarning.setVisible(true);
                }
            }
        } else {
            labelWarning.setVisible(true);
        }

    }

}
