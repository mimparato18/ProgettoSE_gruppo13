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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class CompetencesManagementController implements Initializable {


    @FXML
    private TableView<DisplayCompetences> tableView=new TableView<DisplayCompetences>();
    @FXML
    private TableColumn<DisplayCompetences, String> colCompe;
    @FXML
    private TableColumn<DisplayCompetences, String> colMod;
    @FXML
    private TableColumn<DisplayCompetences, String> colDelete;
    @FXML
    private Button btnAddCompe;
    private Button btnDel[]=new Button[100];
    private Button btnMod[]=new Button[100];
    @FXML
    private Button btnHub;
    @FXML
    private Label labWarn;
    
    private SystemAdministratorService admin;
    
    private ArrayList<String> list;

    public CompetencesManagementController(SystemAdministratorService admin) {
        this.admin=admin;
    }
    
    

    
    /**
     * Initializes the controller class.
     */
    
    public class DisplayCompetences{

        private SimpleStringProperty compe;
        private Button btnDel;
        private Button btnMod;

        public DisplayCompetences(String compe, Button btnDel, Button btnMod) {
            this.compe = new SimpleStringProperty(compe);
            this.btnDel = btnDel;
            this.btnMod = btnMod;
        }
        public String getCompe() {
            return compe.get();
        }

        public void setCompe(String compe) {
            this.compe.set(compe);
        }

        public Button getBtnDel() {
            return btnDel;
        }

        public void setBtnDel(Button btnDel) {
            this.btnDel = btnDel;
        }

        public Button getBtnMod() {
            return btnMod;
        }

        public void setBtnMod(Button btnMod) {
            this.btnMod = btnMod;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();
    }   
    public void initializeTable(){
        ObservableList<DisplayCompetences> data;
        data = FXCollections.observableArrayList();
        list=admin.viewCompetencies();
        for (int i = 0; i < list.size(); i++) {
            btnMod[i] = new Button("Modify");
            btnDel[i] = new Button("Delete");
            btnMod[i].setOnAction(this::btnModify_OnAction);
            btnDel[i].setOnAction(this::btnDelete_OnAction);
            data.add(new DisplayCompetences(list.get(i), btnDel[i], btnMod[i]));

        }

        colCompe.setCellValueFactory(new PropertyValueFactory<DisplayCompetences, String>("compe"));
        colDelete.setCellValueFactory(new PropertyValueFactory<DisplayCompetences, String>("btnDel"));
        colMod.setCellValueFactory(new PropertyValueFactory<DisplayCompetences, String>("btnMod"));
        tableView.setItems(data);
    }

     public void btnModify_OnAction(ActionEvent ev) {
        list=admin.viewCompetencies();
        
        for (int i = 0; i < list.size(); i++) { //recognize in which row the button is to select that Item
            if (ev.getSource() == btnMod[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyCompetencesWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            ModifyCompetencesWindowController controller = new ModifyCompetencesWindowController(tableView.getSelectionModel().getSelectedItem(), this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Modify Department");

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
       
        list=admin.viewCompetencies();
        for (int i = 0; i < list.size(); i++) {
            if (ev.getSource() == btnDel[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        if (admin.deleteCompetence(tableView.getSelectionModel().getSelectedItem().getCompe())) {
            tableView.getItems().clear();
            this.initializeTable();
        } else {
            labWarn.setVisible(true);
        }  
     }
    @FXML
    private void btnAddCompe_OnAction(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CreateCompetencesWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            CreateCompetencesWindowController controller = new CreateCompetencesWindowController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Create Competences");

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
    private void btnHub_OnAction(ActionEvent event){
        try {
            
            
            String ui = "HomeGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ui));
             // Create the new controller and pass the currently selected data item to it
            SystemAdministratorHubGUIController controller = new SystemAdministratorHubGUIController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Admin Hub");
            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
