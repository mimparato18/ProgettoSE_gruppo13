/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer;

import businesslayer.Procedure;
import businesslayer.SystemAdministratorService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class ProcedureManagementGUIController implements Initializable {

    @FXML
    private TableView<DisplayProcedure> tableView;
    @FXML
    private TableColumn<DisplayProcedure, String> colProcedure;
    @FXML
    private TableColumn<DisplayProcedure, String> colMod;
    @FXML
    private TableColumn<DisplayProcedure, String> colDelete;
    @FXML
    private Button btnAddProced;
    @FXML
    private Label labWarn;
    @FXML
    private Button btnHub;
    private Button btnDel[]=new Button[100];
    private Button btnMod[]=new Button[100];

    /**
     * Initializes the controller class.
     */
    private SystemAdministratorService admin;


    public class DisplayProcedure {

        private SimpleStringProperty procedure;
        private Button btnDel;
        private Button btnMod;
        
        public DisplayProcedure(String procedure, Button btnMod, Button btnDel) {
            this.procedure = new SimpleStringProperty(procedure);
            this.btnDel = btnDel;
            this.btnMod = btnMod;
        }
        
        public String getProcedure() {
            return procedure.get();
        }

        public void setProcedure(String procedure) {
            this.procedure.set(procedure);
        }

        public Button getBtnMod() {
            return btnMod;
        }

        public void setBtnMod(Button btnMod) {
            this.btnMod = btnMod;
        }

        public Button getBtnDel() {
            return btnDel;
        }

        public void setBtnDel(Button btnDel) {
            this.btnDel = btnDel;
        }


        
        
    }

    public ProcedureManagementGUIController(SystemAdministratorService admin) {
        this.admin = admin;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();
    }
    
    public void initializeTable(){
        ObservableList<DisplayProcedure> data;
        data = FXCollections.observableArrayList();
        for (int i = 0; i < admin.viewProcedures().size(); i++) {
            btnMod[i] = new Button("Modify");
            btnDel[i] = new Button("Delete");
            btnMod[i].setOnAction(this::btnModify_OnAction);
            btnDel[i].setOnAction(this::btnDelete_OnAction);
            Procedure obj=null;
            obj=admin.viewProcedures().get(i);
            String name=obj.getName();

            data.add(new DisplayProcedure(name, btnMod[i], btnDel[i]));

        }

        colProcedure.setCellValueFactory(new PropertyValueFactory<DisplayProcedure, String>("procedure"));
        colMod.setCellValueFactory(new PropertyValueFactory<DisplayProcedure, String>("btnMod"));
        colDelete.setCellValueFactory(new PropertyValueFactory<DisplayProcedure, String>("btnDel"));
        tableView.setItems(data);
    }

    public void btnModify_OnAction(ActionEvent ev) {
        for (int i = 0; i < this.admin.viewProcedures().size(); i++) {
            if (ev.getSource() == btnMod[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/ModifyProcedureWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            ModifyProcedureWindowController controller = new ModifyProcedureWindowController(tableView.getSelectionModel().getSelectedItem(), this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Modify Procedure");

            // Centers the editor window over the current window
            stage.initOwner(tableView.getScene().getWindow());

            // Ensures the new window needs to be closed before the current window can be used again
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();

            tableView.getItems().clear();
            this.initializeTable();
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
    }
    
    public void btnDelete_OnAction(ActionEvent ev) {
       labWarn.setVisible(false);
        for (int i = 0; i < this.admin.viewProcedures().size(); i++) {
            if (ev.getSource() == btnDel[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        if (admin.deleteProcedure(tableView.getSelectionModel().getSelectedItem().getProcedure())) {
            tableView.getItems().clear();
            this.initializeTable();
        } else {
            labWarn.setVisible(true);
        }  
     }
    
    @FXML
    private void btnAddProced_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/CreateProcedureWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            CreateProcedureWindowController controller = new CreateProcedureWindowController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Create Procedure");

            // Centers the editor window over the current window
            stage.initOwner(tableView.getScene().getWindow());

            // Ensures the new window needs to be closed before the current window can be used again
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();

            tableView.getItems().clear();
            this.initializeTable();

        } catch (Exception e) {
            System.out.println("Can't load the window" + e);
        }
    }

    @FXML
    private void btnHub_OnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/userinterfacelayer/HomeGUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Admin Hub");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
