/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Planner;

import businesslayer.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class ManageActivityGUIController implements Initializable {

    private PlannerService planner;
    @FXML
    private TableView<String> tableView;
    @FXML
    private TableColumn<String, String> colID;
    @FXML
    private TableColumn<String, String> colSite;
    @FXML
    private TableColumn<String, String> colTypo;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnHub;
    @FXML
    private Label labWarning;
    
    public ManageActivityGUIController(PlannerService planner) {
        this.planner=planner;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnModify_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        //planner.deleteActivity();
    }

    @FXML
    private void btnHub_OnAction(ActionEvent event) {
    }
    
}
