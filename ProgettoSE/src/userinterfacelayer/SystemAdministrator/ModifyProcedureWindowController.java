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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import userinterfacelayer.SystemAdministrator.ProcedureManagementGUIController.DisplayProcedure;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class ModifyProcedureWindowController implements Initializable {


    @FXML
    private Button btnModify;
    @FXML
    private TextField txtNewProced;
    @FXML
    private Label labOldProced;
    @FXML
    private Label labWarning;
    
    private DisplayProcedure data;
    private SystemAdministratorService admin;

    public ModifyProcedureWindowController(DisplayProcedure data,SystemAdministratorService admin) {
        this.data=data;
        this.admin=admin;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.labOldProced.setText("You are modifying:   "+data.getProcedure());
    }    

    @FXML
    private void btnModify_OnAction(ActionEvent event) {
        boolean checkUpdate = false;
        boolean warn = false;
        if (txtNewProced.getText().isEmpty()) {
            labWarning.setTextFill(Color.RED);
            labWarning.setText("Missing Data");
            labWarning.setVisible(true);
        } else {

            checkUpdate = admin.updateProcedure(data.getProcedure(), txtNewProced.getText());
            labWarning.setVisible(false);
            if (!checkUpdate) {
                warn = true;
            }

        }

        if (checkUpdate) {
            Stage stage = (Stage) btnModify.getScene().getWindow();
            stage.close();
        } else if (warn) {
            labWarning.setTextFill(Color.RED);
            labWarning.setText("Update failed");
            labWarning.setVisible(true);
        }
    }
    
}
