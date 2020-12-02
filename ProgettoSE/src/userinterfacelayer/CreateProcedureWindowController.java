/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer;

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

/**
 * FXML Controller class
 *
 * @author camil
 */
public class CreateProcedureWindowController implements Initializable {

    /**
     * Initializes the controller class.
     *
    */
    private SystemAdministratorService admin;
    @FXML
    private TextField txtProced;
    @FXML
    private Button btnDone;
    @FXML
    private Label labResult;

    public CreateProcedureWindowController(SystemAdministratorService admin) {
        this.admin = admin;
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    

    @FXML
    private void btnDone_OnAction(ActionEvent event) {
        labResult.setVisible(false);
        labResult.setTextFill(Color.GREEN);
        labResult.setText("Done");

        if (!txtProced.getText().isBlank()) {

            if (admin.addProcedure(txtProced.getText())) {
                labResult.setVisible(true);
                txtProced.clear();
            } else {
                labResult.setText("Add failed");
                labResult.setTextFill(Color.RED);
                labResult.setVisible(true);
            }

        } else {
            labResult.setText("Data missing");
            labResult.setTextFill(Color.RED);
            labResult.setVisible(true);
        }
    }

}
