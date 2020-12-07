/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Planner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class CreateActivityGUIController implements Initializable {

    @FXML
    private TextArea txtDesc;
    @FXML
    private TextArea txtMaterials;
    @FXML
    private TextArea txtWorkspace;
    @FXML
    private ComboBox<?> comboSite;
    @FXML
    private ComboBox<?> comboTypo;
    @FXML
    private CheckBox checkInterrupt;
    @FXML
    private Spinner<?> spinH;
    @FXML
    private Spinner<?> spinM;
    @FXML
    private ComboBox<?> comboWeek;
    @FXML
    private Button btnCreate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void checkInterrupt_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnCreate_OnAction(ActionEvent event) {
    }
    
}
