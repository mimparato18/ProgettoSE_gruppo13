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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
public class CheckAssignedActivityGUIController implements Initializable {
    
    private MaintenanceActivity act;
    private PlannerService planner;

    @FXML
    private TextField txtWeek;
    @FXML
    private TextField txtActivity;
    @FXML
    private TextArea txtWorkspace;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtFile;
    @FXML
    private Button btnFileChooser;
    @FXML
    private TextArea txtSkills;
    @FXML
    private Button btnForward;
    @FXML
    private Button btnBack;
    @FXML
    private Label labWarning;

    public CheckAssignedActivityGUIController(MaintenanceActivity act,PlannerService planner) {
        this.act=act;
        this.planner=planner;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtWeek.setText(""+act.getWeek());
        txtActivity.setText(act.getId()+" - "+act.getSite().getBranchOffice()+"  "+act.getSite().getDepartment()+" - "+act.getTypology()+" - "+act.getInterventionTime()+"'");
        txtWorkspace.setText(act.getWorkspaceNotes());
        txtDescription.setText(act.getDescription());
    }    

    @FXML
    private void btnFileChooser_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnForward_OnAction(ActionEvent event) {
        labWarning.setVisible(false);
        boolean checkUpdate=planner.updateActivity(act.getId(), act.getSite(), act.getTypology(), act.getDescription(), act.getInterventionTime(), act.isInterruptible(), act.getMaterials(), act.getWeek(), txtWorkspace.getText());
        if(checkUpdate){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/Planner/MaintainerActivityGUI.fxml"));

                // Create the new controller and pass the currently selected data item to it
                MaintainerActivityGUIController controller = new MaintainerActivityGUIController();

                // Set the controller to the loader
                loader.setController(controller);

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Checking Activity");
                stage.setScene(new Scene(loader.load()));
                stage.show();

            } catch (Exception e) {
                System.out.println("Can't load the window: " + e);
            }
            Stage stage = (Stage) btnForward.getScene().getWindow();
            stage.close();
        }else{
            labWarning.setVisible(true);
        }
    }

    @FXML
    private void btnBack_OnAction(ActionEvent event) {
        try {
            String ui = "/userinterfacelayer/Planner/AssignActivityGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            AssignActivityGUIController controller = new AssignActivityGUIController(this.planner);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Planner Hub");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load the window" + ex);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
