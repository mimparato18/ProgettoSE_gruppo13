/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.SystemAdministrator;

import businesslayer.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class CreateUserWindowController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField passPwd;
    @FXML
    private Button btnAdd;
    @FXML
    private ComboBox<String> comboRole;
    @FXML
    private Label labelWarning;
    @FXML
    private Label labelDone;
    @FXML
    private CheckBox[] chk = new CheckBox[70];
    private ArrayList<String> list;
    private SystemAdministratorService admin;
    @FXML
    private TableView<DisplayComp> tableComp;
    @FXML
    private TableColumn<DisplayComp, String> colCheck;
    @FXML
    private TableColumn<DisplayComp, String> colCompetence;

    public CreateUserWindowController(SystemAdministratorService admin) {
        this.admin = admin;
    }

    public class DisplayComp {

        private CheckBox chk;
        private SimpleStringProperty comp;

        public DisplayComp(CheckBox chk, String comp) {
            this.chk = chk;
            this.comp = new SimpleStringProperty(comp);
        }

        public String getComp() {
            return comp.get();
        }

        public void setComp(String comp) {
            this.comp.set(comp);
        }

        public CheckBox getChk() {
            return chk;
        }

        public void setChk(CheckBox chk) {
            this.chk = chk;
        }
    }

    private void initializeTable() {
        comboRole.getItems().addAll("Planner", "Maintainer");
        list = admin.viewCompetencies();
        ObservableList<DisplayComp> data;
        data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            chk[i] = new CheckBox();
            chk[i].setOnAction(this::check_OnAction);
            data.add(new DisplayComp(chk[i], list.get(i)));
        }
        colCheck.setCellValueFactory(new PropertyValueFactory<DisplayComp, String>("chk"));
        colCompetence.setCellValueFactory(new PropertyValueFactory<DisplayComp, String>("comp"));
        tableComp.setItems(data);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();

    }

    private void check_OnAction(ActionEvent t) {
        
    }

    @FXML
    private void comboRole_OnAction(ActionEvent event) {

        if (comboRole.getSelectionModel().getSelectedItem() == "Maintainer") {
            tableComp.setDisable(false);
        } else {
            tableComp.setDisable(true);
        }
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent event) {
        labelWarning.setVisible(false);
        labelDone.setTextFill(Color.GREEN);
        labelDone.setText("Done");
        labelDone.setVisible(false);

        if (!txtUser.getText().isBlank() && !passPwd.getText().isBlank() && comboRole.getValue() != null) {

            if (comboRole.getSelectionModel().getSelectedItem() != "Maintainer") {
                if (admin.addUser(txtUser.getText(), passPwd.getText(), comboRole.getValue())) {
                    labelDone.setVisible(true);
                    txtUser.clear();
                    passPwd.clear();

                } else {
                    labelDone.setText("Add failed");
                    labelDone.setTextFill(Color.RED);
                    labelDone.setVisible(true);
                }
            } else {
                //add User with role Maintainer
                    ArrayList<String> listComp= new ArrayList<>();
                    ObservableList<DisplayComp> list=tableComp.getItems();
                    for(int i=0; i < list.size();i++){
                        
                        if(list.get(i).getChk().isSelected()){
                            listComp.add(list.get(i).getComp());
                            tableComp.getItems().get(i).getChk().setSelected(false);
                            
                        }
                        
                    }
                if(listComp.size()>0){
                    if (admin.addUser(txtUser.getText(), passPwd.getText(), comboRole.getValue(),listComp)) {
                        labelDone.setVisible(true);

                        txtUser.clear();
                        passPwd.clear();
                    } else {
                        labelDone.setText("Add failed");
                        labelDone.setTextFill(Color.RED);
                        labelDone.setVisible(true);
                    }
                }else{
                    labelWarning.setVisible(true);
                }
            }
        } else {
            labelWarning.setVisible(true);
        }

    }

}
