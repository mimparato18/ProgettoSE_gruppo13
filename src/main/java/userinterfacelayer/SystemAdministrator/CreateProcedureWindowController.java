/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.SystemAdministrator;

import businesslayer.SystemAdministratorService;
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
    @FXML
    private TableView<DisplayComp> tableComp;
    @FXML
    private TableColumn<DisplayComp, String> colCheck;
    @FXML
    private TableColumn<DisplayComp, String> colComp;
    @FXML
    private CheckBox[] chk = new CheckBox[70];
    private ArrayList<String> list;

    public CreateProcedureWindowController(SystemAdministratorService admin) {
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

        list = admin.viewCompetencies();
        ObservableList<DisplayComp> data;
        data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            chk[i] = new CheckBox();
            data.add(new DisplayComp(chk[i], list.get(i)));
        }
        colCheck.setCellValueFactory(new PropertyValueFactory<DisplayComp, String>("chk"));
        colComp.setCellValueFactory(new PropertyValueFactory<DisplayComp, String>("comp"));
        tableComp.setItems(data);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();

    }

    @FXML
    private void btnDone_OnAction(ActionEvent event) {
        labResult.setVisible(false);
        labResult.setTextFill(Color.GREEN);
        labResult.setText("Done");

        ArrayList<String> listComp = new ArrayList<>();
        ObservableList<DisplayComp> list = tableComp.getItems();
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getChk().isSelected()) {
                listComp.add(list.get(i).getComp());
                tableComp.getItems().get(i).getChk().setSelected(false);

            }

        }
        

        if (!txtProced.getText().isBlank() && listComp.size() > 0) {

            if (admin.addProcedure(txtProced.getText(),listComp)) {
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
