/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer;

import businesslayer.SystemAdministratorController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userinterfacelayer.TypologyManagementGUIController.DisplayTypology;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class ModifyTypologyWindowController implements Initializable {

    private DisplayTypology data;
    private SystemAdministratorController admin;
    @FXML
    private TextField txtNewTypo;
    @FXML
    private Label labWarning;

    public ModifyTypologyWindowController(DisplayTypology data,SystemAdministratorController admin) {
        this.data = data;
        this.admin=admin;
    }

    @FXML
    private Button btnModify;
    @FXML
    private Label labOldTypo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.labOldTypo.setText("You are modifying:   "+data.getTypo());
    }

    @FXML
    private void btnModify_OnAction(ActionEvent event) {
        boolean checkUpdate = false;
        boolean warn = false;
        if (txtNewTypo.getText().isEmpty()) {
            labWarning.setText("Missing Data");
            labWarning.setVisible(true);
        } else {

            checkUpdate = admin.updateTypology(data.getTypo(), txtNewTypo.getText());
            labWarning.setVisible(false);
            if (!checkUpdate) {
                warn = true;
            }

        }

        if (checkUpdate) {
            Stage stage = (Stage) btnModify.getScene().getWindow();
            stage.close();
        } else if (warn) {
            labWarning.setText("Update failed");
            labWarning.setVisible(true);
        }
    }

}
