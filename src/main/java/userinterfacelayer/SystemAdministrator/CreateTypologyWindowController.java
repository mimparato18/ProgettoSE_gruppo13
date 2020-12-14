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

/**
 * FXML Controller class
 *
 * @author camil
 */
public class CreateTypologyWindowController implements Initializable {

    @FXML
    private TextField txtTypo;
    @FXML
    private Button btnCreateTypology;
    @FXML
    private Label labResult = new Label();
    private SystemAdministratorService admin;

    public CreateTypologyWindowController(SystemAdministratorService admin) {
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
    private void btnCreateTypology_OnAction(ActionEvent event) {
        labResult.setVisible(false);
        labResult.setTextFill(Color.GREEN);
        labResult.setText("Done");

        if (!txtTypo.getText().isBlank()) {

            if (admin.addTypology(txtTypo.getText())) {
                labResult.setVisible(true);
                txtTypo.clear();
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
