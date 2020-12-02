/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer;

import businesslayer.*;
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
public class TypologyManagementGUIController implements Initializable {


    @FXML
    private TableView<DisplayTypology> tableView=new TableView<DisplayTypology>();
    @FXML
    private TableColumn<DisplayTypology, String> colTypo;
    @FXML
    private TableColumn<DisplayTypology, String> colMod;
    @FXML
    private TableColumn<DisplayTypology, String> colDelete;
    @FXML
    private Button btnAddTypo;
    private Button btnDel[]=new Button[100];
    private Button btnMod[]=new Button[100];
    @FXML
    private Button btnHub;
    @FXML
    private Label labWarn;
    
    private SystemAdministratorService admin;

    public TypologyManagementGUIController(SystemAdministratorService admin) {
        this.admin=admin;
    }
    
    

    
    /**
     * Initializes the controller class.
     */
    
    public class DisplayTypology{

        private SimpleStringProperty typo;
        private Button btnDel;
        private Button btnMod;

        public DisplayTypology(String typo, Button btnDel, Button btnMod) {
            this.typo = new SimpleStringProperty(typo);
            this.btnDel = btnDel;
            this.btnMod = btnMod;
        }
        public String getTypo() {
            return typo.get();
        }

        public void setTypo(String typo) {
            this.typo.set(typo);
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
        ObservableList<DisplayTypology> data;
        data = FXCollections.observableArrayList();
        for (int i = 0; i < admin.viewTypologies().size(); i++) {
            btnMod[i] = new Button("Modify");
            btnDel[i] = new Button("Delete");
            btnMod[i].setOnAction(this::btnModify_OnAction);
            btnDel[i].setOnAction(this::btnDelete_OnAction);
            data.add(new DisplayTypology(admin.viewTypologies().get(i), btnDel[i], btnMod[i]));

        }

        colTypo.setCellValueFactory(new PropertyValueFactory<DisplayTypology, String>("typo"));
        colDelete.setCellValueFactory(new PropertyValueFactory<DisplayTypology, String>("btnDel"));
        colMod.setCellValueFactory(new PropertyValueFactory<DisplayTypology, String>("btnMod"));
        tableView.setItems(data);
    }

     public void btnModify_OnAction(ActionEvent ev) {
        for (int i = 0; i < this.admin.viewSites().size(); i++) {
            if (ev.getSource() == btnMod[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/ModifyTypologyWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            ModifyTypologyWindowController controller = new ModifyTypologyWindowController(tableView.getSelectionModel().getSelectedItem(), this.admin);

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
        for (int i = 0; i < this.admin.viewTypologies().size(); i++) {
            if (ev.getSource() == btnDel[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        if (admin.deleteTypology(tableView.getSelectionModel().getSelectedItem().getTypo())) {
            tableView.getItems().clear();
            this.initializeTable();
        } else {
            labWarn.setVisible(true);
        }  
     }
    @FXML
    private void btnAddTypo_OnAction(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/CreateTypologyWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            CreateTypologyWindowController controller = new CreateTypologyWindowController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Create Typology");

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
