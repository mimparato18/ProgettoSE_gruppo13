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
public class SiteManagementGUIController implements Initializable {

    private ArrayList<Site> list = null;
    @FXML
    private TableColumn<DisplaySite, String> colFactory;
    @FXML
    private TableColumn<DisplaySite, String> colDepart;
    @FXML
    private TableColumn<DisplaySite, String> colMod;
    @FXML
    private TableColumn<DisplaySite, String> colDel;
    @FXML
    private Button btnSite;
    @FXML
    private TableView<DisplaySite> tableView = new TableView<DisplaySite>();
    private Button btnDel[] = new Button[100];
    private Button btnMod[] = new Button[100];
    @FXML
    private Button btnHub = new Button();
    @FXML
    private Label labWarning = new Label();

    private SystemAdministratorService admin;

    public SiteManagementGUIController(SystemAdministratorService admin) {
        this.admin = admin;
    }

    /**
     * Initializes the controller class.
     */
    public class DisplaySite {

        private SimpleStringProperty factSite;
        private SimpleStringProperty depart;
        private Button btnDel;
        private Button btnMod;

        public DisplaySite(String factSite, String depart, Button btnDel, Button btnMod) {
            this.factSite = new SimpleStringProperty(factSite);
            this.depart = new SimpleStringProperty(depart);
            this.btnDel = btnDel;
            this.btnDel.setText("Delete");
            this.btnMod = btnMod;
            this.btnMod.setText("Modify");

        }

        public String getFactSite() {
            return factSite.get();
        }

        public void setFactSite(String factSite) {
            this.factSite.set(factSite);
        }

        public String getDepart() {
            return depart.get();
        }

        public void setDepart(String depart) {
            this.depart.set(depart);
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

        public Site getSite() {
            return new Site(this.getFactSite(), this.getDepart());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();
    }

    public void initializeTable() {
        ObservableList<DisplaySite> data;
        data = FXCollections.observableArrayList();
        list = admin.viewSites();
        for (int i = 0; i < list.size(); i++) {
            btnMod[i] = new Button("Modify");
            btnDel[i] = new Button("Delete");
            btnMod[i].setOnAction(this::btnModify_OnAction);
            btnDel[i].setOnAction(this::btnDelete_OnAction);
            Site obj = null;

            obj = list.get(i);

            String factSite = obj.getBranchOffice();
            String depart = obj.getDepartment();

            data.add(new DisplaySite(factSite, depart, btnDel[i], btnMod[i]));

        }

        colFactory.setCellValueFactory(new PropertyValueFactory<DisplaySite, String>("factSite"));
        colDepart.setCellValueFactory(new PropertyValueFactory<DisplaySite, String>("depart"));
        colDel.setCellValueFactory(new PropertyValueFactory<DisplaySite, String>("btnDel"));
        colMod.setCellValueFactory(new PropertyValueFactory<DisplaySite, String>("btnMod"));
        tableView.setItems(data);
    }

    public void btnModify_OnAction(ActionEvent ev) {
        list = admin.viewSites();
        for (int i = 0; i < list.size(); i++) {
            if (ev.getSource() == btnMod[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyDepartmentWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            ModifyDepartmentWindowController controller = new ModifyDepartmentWindowController(tableView.getSelectionModel().getSelectedItem(), this.admin);

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
        list = admin.viewSites();
        labWarning.setVisible(false);
        for (int i = 0; i < list.size(); i++) {
            if (ev.getSource() == btnDel[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        if (admin.deleteSite(tableView.getSelectionModel().getSelectedItem().getSite())) {
            tableView.getItems().clear();
            this.initializeTable();
        } else {
            labWarning.setVisible(true);
        }

    }

    @FXML
    private void btnSite_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddSiteWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            AddSiteWindowController controller = new AddSiteWindowController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Create Site");

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
