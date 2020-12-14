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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userinterfacelayer.SystemAdministrator.SystemAdministratorGUIController.DisplayUser;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class ModifyPasswordWindowController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnDone;
    @FXML
    private Label labWarning;

    private String role;
    private SystemAdministratorService admin;
    private DisplayUser data;

    public ModifyPasswordWindowController(DisplayUser data, SystemAdministratorService admin) {
        this.data = data;
        this.admin = admin;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUser.setText(data.getUser());
        txtPass.setText(data.getPass());
        role = data.getRole();
        btnDone.setOnAction(this::btnDone_OnAction);
    }

    @FXML
    private void btnDone_OnAction(ActionEvent event) {
        boolean checkUpdate = false;
        boolean warn = false;
        if (txtPass.getText().isEmpty()) {
            labWarning.setText("Missing password");
            labWarning.setVisible(true);
        } else {

            checkUpdate = admin.updateUser(txtUser.getText(), txtPass.getText(), role);
            labWarning.setVisible(false);
            if (!checkUpdate) {
                warn = true;
            }

        }

        if (checkUpdate) {
            Stage stage = (Stage) btnDone.getScene().getWindow();
            stage.close();
        } else if (warn) {
            labWarning.setText("Update failed");
            labWarning.setVisible(true);
        }
    }

}
