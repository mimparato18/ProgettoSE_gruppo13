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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userinterfacelayer.SiteManagementGUIController.DisplaySite;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class ModifyDepartmentWindowController implements Initializable {

    @FXML
    private TextField txtFactSite;
    @FXML
    private TextField txtDepartment;
    @FXML
    private Button btnDone;
    @FXML
    private Label labWarning;

    private SystemAdministratorController admin;
    private DisplaySite data;

    /**
     * Initializes the controller class.
     */
    public ModifyDepartmentWindowController(DisplaySite data, SystemAdministratorController admin) {
        this.data = data;
        this.admin = admin;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFactSite.setText(data.getFactSite());
        txtDepartment.setText(data.getDepart());
        btnDone.setOnAction(this::btnDone_OnAction);
    }

    @FXML
    private void btnDone_OnAction(ActionEvent event) {
        boolean checkUpdate = false;
        boolean warn = false;
        if (txtDepartment.getText().isEmpty()) {
            labWarning.setText("Missing password");
            labWarning.setVisible(true);
        } else {

            checkUpdate = admin.updateSite(data.getSite(), txtDepartment.getText());
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
