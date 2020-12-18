/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.SystemAdministrator;

import businesslayer.SystemAdministratorService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userinterfacelayer.SystemAdministrator.CompetencesManagementController.DisplayCompetences;


/**
 * FXML Controller class
 *
 * @author camil
 */
public class ModifyCompetencesWindowController implements Initializable {

    public ModifyCompetencesWindowController(DisplayCompetences data, SystemAdministratorService admin) {
        this.admin=admin;
        this.data=data;
    }
    private DisplayCompetences data;
    private SystemAdministratorService admin;
    @FXML
    private Button btnModify;
    @FXML
    private Label labOldCompe;
    @FXML
    private Label labWarning;
    @FXML
    private TextField txtNewCompe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.labOldCompe.setText("You are modifying:   "+data.getCompe());
    }

    @FXML
    private void btnModify_OnAction(ActionEvent event) {
        boolean checkUpdate = false;
        boolean warn = false;
        if (txtNewCompe.getText().isEmpty()) {
            labWarning.setText("Missing Data");
            labWarning.setVisible(true);
        } else {

            checkUpdate = admin.updateCompetence(data.getCompe(), txtNewCompe.getText());
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
