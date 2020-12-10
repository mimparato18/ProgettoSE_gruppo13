/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Planner;

import businesslayer.MaintenanceActivity;
import businesslayer.PlannerService;
import businesslayer.Site;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class AssignActivityGUIController implements Initializable {

    private ObservableList<String> list = (ObservableList<String>) FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "31", "32", "33", "34", "35",
            "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52");

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
    private TableColumn<DisplayActivity, String> colTime;
    @FXML
    private Button btnAssignSel;
    @FXML
    private Button btnHub;
    @FXML
    private Label labWarning;
    @FXML
    private ComboBox<String> comboWeek;

    public AssignActivityGUIController(PlannerService planner) {
        this.planner = planner;
    }

    public class DisplayActivity {

        private SimpleIntegerProperty id;
        private SimpleStringProperty site;
        private SimpleStringProperty typology;
        private SimpleIntegerProperty time;

        public DisplayActivity(int id, Site site, String typology,int time) {
            this.id = new SimpleIntegerProperty(id);
            this.site = new SimpleStringProperty(site.getBranchOffice() + " - " + site.getDepartment());
            this.typology = new SimpleStringProperty(typology);
            this.time = new SimpleIntegerProperty(time);
        }

        public int getId() {
            return id.get();
        }

        public void setId(int id) {
            this.id.set(id);
        }
        public int getTime() {
            return time.get();
        }

        public void setTime(int time) {
            this.time.set(time);
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

        comboWeek.getItems().addAll(list);
        comboWeek.getSelectionModel().selectFirst();
        this.initializeTable(comboWeek.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void btnAssignSel_OnAction(ActionEvent event) {
        if(tableView.getSelectionModel().getSelectedItem()==null){
            labWarning.setText("Select an activity");
            labWarning.setVisible(true);
        }else{
            labWarning.setVisible(false);
        }
        //find the MaintenanceActivity to pass when the displayActivity is selected
    }

    @FXML
    private void comboWeek_OnAction(ActionEvent event) {
        this.initializeTable(comboWeek.getSelectionModel().getSelectedIndex());
    }

    private void initializeTable(int week) {
        week++;
        ObservableList<DisplayActivity> data;
        data = FXCollections.observableArrayList();
        for(int i=0;i<planner.getActivitiesByWeek(week).size();i++){
            MaintenanceActivity obj=null;
            obj=planner.getActivitiesByWeek(week).get(i);
            data.add(new DisplayActivity(obj.getId(),obj.getSite(),obj.getTypology(),obj.getInterventionTime()));
            
        }
        colID.setCellValueFactory(new PropertyValueFactory<DisplayActivity, String>("id"));
        colSite.setCellValueFactory(new PropertyValueFactory<DisplayActivity, String>("site"));
        colTypo.setCellValueFactory(new PropertyValueFactory<DisplayActivity, String>("typology"));
        colTime.setCellValueFactory(new PropertyValueFactory<DisplayActivity, String>("time"));
        tableView.setItems(data);

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
