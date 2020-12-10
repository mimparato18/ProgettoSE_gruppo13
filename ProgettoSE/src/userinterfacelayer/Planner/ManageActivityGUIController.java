/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Planner;

import businesslayer.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class ManageActivityGUIController implements Initializable {

    private PlannerService planner;
    @FXML
    private TableView<DisplayActivity> tableView;
    @FXML
    private TableColumn<DisplayActivity, String> colID;
    @FXML
    private TableColumn<DisplayActivity, String> colSite;
    @FXML
    private TableColumn<DisplayActivity, String> colTypo;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnHub;
    @FXML
    private Button btnCreateActivity;
    @FXML
    private Label labWarning;

    public ManageActivityGUIController(PlannerService planner) {
        this.planner = planner;
    }

    private MaintenanceActivity findObject() {
        DisplayActivity obj = tableView.getSelectionModel().getSelectedItem();
        if (obj == null) {
            return null;
        }
        for (int i = 0; i < planner.viewActivities().size(); i++) {
            MaintenanceActivity act = null;
            act = planner.viewActivities().get(i);
            if (obj.getId() == act.getId()) {
                return act;
            }
        }
        return null;
    }

    public class DisplayActivity {

        private SimpleIntegerProperty id;
        private SimpleStringProperty site;
        private SimpleStringProperty typology;

        public DisplayActivity(int id, Site site, String typology) {
            this.id = new SimpleIntegerProperty(id);
            this.site = new SimpleStringProperty(site.getBranchOffice() + " - " + site.getDepartment());
            this.typology = new SimpleStringProperty(typology);
        }

        public int getId() {
            return id.get();
        }

        public void setId(int id) {
            this.id.set(id);
        }

        public String getSite() {
            return site.get();
        }

        public void setSite(String site) {
            this.site.set(site);
        }

        public String getTypology() {
            return typology.get();
        }

        public void setTypology(String typology) {
            this.typology.set(typology);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();
    }

    @FXML
    private void btnModify_OnAction(ActionEvent event) {
        MaintenanceActivity act;
        act = findObject();
        if (act == null) {
            labWarning.setText("Select an Activity");
            labWarning.setTextFill(Color.RED);
            labWarning.setVisible(true);
        } else {
            try {
                labWarning.setVisible(false);
                String ui = "/userinterfacelayer/Planner/ModifyActivityGUI.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ui));
                // Create the new controller and pass the currently selected data item to it
                ModifyActivityGUIController controller = new ModifyActivityGUIController(this.planner, act);

                // Set the controller to the loader
                loader.setController(controller);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.initStyle(StageStyle.UTILITY);
                stage.setResizable(false);
                stage.setTitle("Modify Activity");
                stage.setScene(new Scene(loader.load()));
                stage.showAndWait();
            } catch (Exception ex) {

                System.out.println("Can't load the window" + ex);
            }
        }
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        labWarning.setText("");
        labWarning.setVisible(false);

        if (tableView.getSelectionModel().getSelectedItem() == null) {

            labWarning.setText("Select an Activity");
            labWarning.setTextFill(Color.RED);
            labWarning.setVisible(true);

        } else {

            if (planner.deleteActivity(tableView.getSelectionModel().getSelectedItem().getId())) {

                labWarning.setVisible(false);
                tableView.getItems().clear();
                this.initializeTable();

            } else {

                labWarning.setText("Delete failed");
                labWarning.setTextFill(Color.RED);
                labWarning.setVisible(true);
            }
        }
    }

    private void initializeTable() {
        ObservableList<DisplayActivity> data;
        data = FXCollections.observableArrayList();

        for (int i = 0; i < planner.viewActivities().size(); i++) {
            MaintenanceActivity obj = null;
            obj = planner.viewActivities().get(i);
            data.add(new DisplayActivity(obj.getId(), obj.getSite(), obj.getTypology()));
        }
        colID.setCellValueFactory(new PropertyValueFactory<DisplayActivity, String>("id"));
        colSite.setCellValueFactory(new PropertyValueFactory<DisplayActivity, String>("site"));
        colTypo.setCellValueFactory(new PropertyValueFactory<DisplayActivity, String>("typology"));
        tableView.setItems(data);
    }

    @FXML
    private void btnCreateActivity_OnAction(ActionEvent event) {
        try {
            labWarning.setVisible(false);
            String ui = "/userinterfacelayer/Planner/CreateActivityGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            CreateActivityGUIController controller = new CreateActivityGUIController(this.planner);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setTitle("Activity Creation");
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();
        } catch (Exception ex) {
            System.out.println("Can't load the window" + ex);
        }
        tableView.getItems().clear();
        this.initializeTable();
    }

    @FXML
    private void btnHub_OnAction(ActionEvent event) {
        try {
            String ui = "/userinterfacelayer/Planner/PlannerHubGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            PlannerHubGUIController controller = new PlannerHubGUIController(this.planner);

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
