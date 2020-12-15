package userinterfacelayer.Planner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class MaintainerAvailDayGUIController implements Initializable {


    @FXML
    private TextField txtWeek;
    @FXML
    private TextField txtAct;
    @FXML
    private TextArea txtWorkspace;
    @FXML
    private TableView<?> tableDay;
    @FXML
    private TableColumn<?, ?> colMant;
    @FXML
    private TableColumn<?, ?> colSkill;
    @FXML
    private TableColumn<?, ?> colAvail;
    @FXML
    private TableColumn<?, ?> colFirst;
    @FXML
    private TableColumn<?, ?> colSecond;
    @FXML
    private TableColumn<?, ?> colThird;
    @FXML
    private TableColumn<?, ?> colFourth;
    @FXML
    private TableColumn<?, ?> colFifth;
    @FXML
    private TableColumn<?, ?> colSixth;
    @FXML
    private TableColumn<?, ?> colSeventh;
    @FXML
    private Button btnSend;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtDayNum;
    @FXML
    private Label labDay;
    @FXML
    private TextField txtDayAvail;
    @FXML
    private Label labMaint;

    
    private MaintenanceActivity act;
    private PlannerService planner;
    
    public MaintainerAvailDayGUIController(MaintenanceActivity act, PlannerService planner) {
        this.act = act;
        this.planner = planner;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSend_OnAction(ActionEvent event) {
    }
    @FXML
    private void btnBack_OnAction(ActionEvent event) {
        try {
            String ui = "MaintainerActivityGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            MaintainerActivityGUIController controller = new MaintainerActivityGUIController(this.act,this.planner);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Check Activity");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load the window" + ex);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
