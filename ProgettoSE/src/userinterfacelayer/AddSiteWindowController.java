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
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class AddSiteWindowController implements Initializable {

    private SystemAdministratorController admin;
    @FXML
    private Button btnDone = new Button();
    @FXML
    private Label labResult = new Label();
    @FXML
    private TextField txtFact;
    @FXML
    private TextField txtDepart;

    public AddSiteWindowController(SystemAdministratorController admin) {
        this.admin = admin;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnDone_OnAction(ActionEvent event) {
        labResult.setVisible(false);
        labResult.setTextFill(Color.GREEN);
        labResult.setText("Done");
        labResult.setVisible(false);

        if (!txtFact.getText().isBlank() && !txtDepart.getText().isBlank()) {

            if (admin.addSite(txtFact.getText(), txtDepart.getText())) {
                labResult.setVisible(true);
                txtFact.clear();
                txtDepart.clear();
            } else {
                labResult.setText("Add failed");
                labResult.setTextFill(Color.RED);
                labResult.setVisible(true);
            }

        } else {
            labResult.setVisible(true);
        }
    }

}
