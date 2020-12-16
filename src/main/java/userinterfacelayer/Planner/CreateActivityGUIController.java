/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Planner;

import businesslayer.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class CreateActivityGUIController implements Initializable {

    private ObservableList<String> list = (ObservableList<String>) FXCollections.observableArrayList("1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45",
            "46", "47", "48", "49", "50", "51", "52");
    private PlannerService planner;
    @FXML
    private TextArea txtDesc;
    @FXML
    private Label labWarning;
    @FXML
    private TextArea txtMaterials;
    @FXML
    private TextArea txtWorkspace;
    @FXML
    private ComboBox<String> comboSite;
    @FXML
    private ComboBox<String> comboTypo;
    @FXML
    private CheckBox checkInterrupt;
    @FXML
    private Spinner<Integer> spinH;
    @FXML
    private Spinner<Integer> spinM;
    @FXML
    private ComboBox<String> comboWeek;
    @FXML
    private Button btnCreate;
    @FXML
    private TextField txtID;

    public CreateActivityGUIController(PlannerService planner) {
        this.planner = planner;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkInterrupt.setOnAction(this::checkInterrupt_OnAction);
        btnCreate.setOnAction(this::btnCreate_OnAction);
        comboWeek.getItems().addAll(this.list);
        comboSite.getItems().addAll(planner.getAllSites());
        comboTypo.getItems().addAll(planner.getAllTypologies());
        spinH.setValueFactory(new IntegerSpinnerValueFactory(0, 8));
        spinM.setValueFactory(new IntegerSpinnerValueFactory(0, 59));
        txtID.setText(planner.getNextId());
    }

    private void resetFields() {
        txtDesc.clear();
        txtMaterials.clear();
        txtWorkspace.clear();
        txtID.setText(planner.getNextId());
        checkInterrupt.setSelected(false);
        spinH.setValueFactory(new IntegerSpinnerValueFactory(0, 8));
        spinM.setValueFactory(new IntegerSpinnerValueFactory(0, 59));
        comboWeek.getSelectionModel().clearSelection();
        comboSite.getSelectionModel().clearSelection();
        comboTypo.getSelectionModel().clearSelection();
        
    }
    private void checkInterrupt_OnAction(ActionEvent event) {

    }

    private void btnCreate_OnAction(ActionEvent event) {
        if (!txtDesc.getText().isBlank() && comboWeek.getSelectionModel().getSelectedItem()!=null && (spinH.getValue() != 0 || spinM.getValue() != 0) && comboTypo.getSelectionModel().getSelectedItem()!=null && comboSite.getSelectionModel().getSelectedItem()!=null) {
            //the creation is permitted only if all the needed fields are filled
            boolean checkAdd = planner.addActivity(comboSite.getValue(), comboTypo.getValue(), txtDesc.getText(),spinH.getValue(),spinM.getValue(), checkInterrupt.isSelected(), txtMaterials.getText(), comboWeek.getValue(), txtWorkspace.getText());
            if(!checkAdd){
                labWarning.setText(" Creation Failed");
                labWarning.setTextFill(Color.RED);
                labWarning.setVisible(true);
            }else{
                labWarning.setText("Done");
                labWarning.setTextFill(Color.GREEN);
                labWarning.setVisible(true);
                resetFields();
            }
        } else {
            labWarning.setText("  Missing data");
            labWarning.setTextFill(Color.RED);
            labWarning.setVisible(true);
        }

    }


}
