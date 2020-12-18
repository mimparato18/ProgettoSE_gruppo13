/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Planner;

import businesslayer.MaintenanceActivity;
import businesslayer.PlannerService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class ModifyActivityGUIController implements Initializable {

    @FXML
    private TextField txtSite;
    @FXML
    private TextField txtTypo;
    @FXML
    private TextField txtMinutes;
    @FXML
    private TextField txtWeek;

    private MaintenanceActivity data;
    private PlannerService planner;
    @FXML
    private TextArea txtDesc;
    @FXML
    private TextArea txtMaterials;
    @FXML
    private TextArea txtWorkspace;
    @FXML
    private Button btnModify;
    @FXML
    private Label labInterrupt;
    @FXML
    private Label labWarning;
    @FXML
    private TextField txtID;

    public ModifyActivityGUIController(PlannerService planner, MaintenanceActivity data) {
        this.planner = planner;
        this.data = data;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModify.setOnAction(this::btnModify_OnAction);
        txtID.setText("" + data.getId());
        txtDesc.setText(data.getDescription());
        txtMaterials.setText(data.getMaterials());
        txtWorkspace.setText(data.getWorkspaceNotes());
        txtSite.setText(data.getSite().getBranchOffice() + " - " + data.getSite().getDepartment());
        txtTypo.setText(data.getTypology());
        txtMinutes.setText("" + data.getInterventionTime());
        txtWeek.setText("" + data.getWeek());
        if (data.isInterruptible()) {
            labInterrupt.setText("Status: Interruptible");
        } else {
            labInterrupt.setText("Status: Not Interruptible");
        }
    }

    @FXML
    private void btnModify_OnAction(ActionEvent event) {
        labWarning.setVisible(false);

        boolean checkUpdate = planner.updateActivity(data.getId(), data.getSite(), data.getTypology(), data.getDescription(), data.getInterventionTime(), data.isInterruptible(), data.getMaterials(), data.getWeek(), txtWorkspace.getText());
        if (checkUpdate) {
            Stage stage = (Stage) btnModify.getScene().getWindow();
            stage.close();
        } else {
            labWarning.setVisible(true);
        }
    }

}
