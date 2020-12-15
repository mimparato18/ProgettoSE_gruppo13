/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.Planner;

import businesslayer.MaintainerAvailabilityDto;
import businesslayer.MaintenanceActivity;
import businesslayer.PlannerService;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class MaintainerActivityGUIController implements Initializable {

    private PlannerService planner;
    private MaintenanceActivity act;
    @FXML
    private TextField txtWeek;
    @FXML
    private TextField txtAct;
    @FXML
    private TextArea txtSkills;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<DisplayAvail> tableWeek;
    @FXML
    private TableColumn<DisplayAvail, String> colMant;
    @FXML
    private TableColumn<DisplayAvail, String> colSkill;
    @FXML
    private TableColumn<DisplayAvail, String> colMonday;
    @FXML
    private TableColumn<DisplayAvail, String> colTuesday;
    @FXML
    private TableColumn<DisplayAvail, String> colWednesday;
    @FXML
    private TableColumn<DisplayAvail, String> colThursday;
    @FXML
    private TableColumn<DisplayAvail, String> colFriday;
    @FXML
    private TableColumn<DisplayAvail, String> colSaturday;
    @FXML
    private TableColumn<DisplayAvail, String> colSunday;

    /**
     * Initializes the controller class.
     */
    public MaintainerActivityGUIController(MaintenanceActivity act, PlannerService planner) {
        this.act = act;
        this.planner = planner;

    }

    public class DisplayAvail {

        private SimpleStringProperty name;
        private SimpleStringProperty skill;
        private SimpleStringProperty mon;
        private SimpleStringProperty tue;
        private SimpleStringProperty wed;
        private SimpleStringProperty thu;
        private SimpleStringProperty fri;
        private SimpleStringProperty sat;
        private SimpleStringProperty sun;

        public DisplayAvail(String name, String skill, String mon, String tue, String wed, String thu, String fri, String sat, String sun) {
            this.name = new SimpleStringProperty(name);
            this.skill = new SimpleStringProperty(skill);
            this.mon = new SimpleStringProperty(mon);
            this.tue = new SimpleStringProperty(tue);
            this.wed = new SimpleStringProperty(wed);
            this.thu = new SimpleStringProperty(thu);
            this.fri = new SimpleStringProperty(fri);
            this.sat = new SimpleStringProperty(sat);
            this.sun = new SimpleStringProperty(sun);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getSkill() {
            return skill.get();
        }

        public void setSkill(String skill) {
            this.skill.set(skill);
        }

        public String getMon() {
            return mon.get();
        }

        public void setMon(String mon) {
            this.mon.set(mon);
        }

        public String getTue() {
            return tue.get();
        }

        public void setTue(String tue) {
            this.tue.set(tue);
        }

        public String getWed() {
            return wed.get();
        }

        public void setWed(String wed) {
            this.wed.set(wed);
        }

        public String getThu() {
            return thu.get();
        }

        public void setThu(String thu) {
            this.thu.set(thu);
        }

        public String getFri() {
            return fri.get();
        }

        public void setFri(String fri) {
            this.fri.set(fri);
        }

        public String getSat() {
            return sat.get();
        }

        public void setSat(String sat) {
            this.sat.set(sat);
        }

        public String getSun() {
            return sun.get();
        }

        public void setSun(String sun) {
            this.sun.set(sun);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();
        btnBack.setOnAction(this::btnBack_OnAction);
        //Skills initialization
        try {
            ArrayList<String> list = act.getProcedure().getCompetencies();
            StringBuilder comp = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                comp.append(list.get(i) + "\n");

            }

            txtSkills.setText(comp.toString());
        } catch (Exception ex) {
            txtSkills.setText("No skills loaded");
        }
        //week and activity textfield initialization
        txtWeek.setText("" + act.getWeek());
        txtAct.setText(act.getId() + " - " + act.getSite().getBranchOffice() + "  " + act.getSite().getDepartment() + " - " + act.getTypology() + " - " + act.getInterventionTime() + "'");

        //initialization of callback on tableview cell reacvting to mouse clicking
        colMonday.setCellFactory(tc -> {
            TableCell<DisplayAvail, String> cell = new TableCell<DisplayAvail, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availMon = cell.getItem();
                    // do something with id...
                }

            });
            return cell;
        });
        colTuesday.setCellFactory(tc -> {
            TableCell<DisplayAvail, String> cell = new TableCell<DisplayAvail, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availTue = cell.getItem();
                    // do something with id...
                }

            });
            return cell;
        });
        colWednesday.setCellFactory(tc -> {
            TableCell<DisplayAvail, String> cell = new TableCell<DisplayAvail, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availWed = cell.getItem();
                    // do something with id...
                }

            });
            return cell;
        });
        colThursday.setCellFactory(tc -> {
            TableCell<DisplayAvail, String> cell = new TableCell<DisplayAvail, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availThu = cell.getItem();
                    System.out.println(availThu);
                }

            });
            return cell;
        });
        colFriday.setCellFactory(tc -> {
            TableCell<DisplayAvail, String> cell = new TableCell<DisplayAvail, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availFri = cell.getItem();
                    // do something with id...
                }

            });
            return cell;
        });

        colSaturday.setCellFactory(tc -> {
            TableCell<DisplayAvail, String> cell = new TableCell<DisplayAvail, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availSat = cell.getItem();
                    // do something with id...
                }

            });
            return cell;
        });

        colSunday.setCellFactory(tc -> {
            TableCell<DisplayAvail, String> cell = new TableCell<DisplayAvail, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availSun = cell.getItem();
                    // do something with id...
                }

            });
            return cell;
        });

    }

    private void initializeTable() {
        ObservableList<DisplayAvail> data;
        data = FXCollections.observableArrayList();
        ArrayList<MaintainerAvailabilityDto> list=planner.getAvailabilityByWeek(act.getId(), act.getWeek());
        for(int i=0;i<list.size();i++){
            MaintainerAvailabilityDto obj=null;
            obj=list.get(i);
            data.add(new DisplayAvail(obj.getMaintainer(),obj.getSkills(),obj.getAvailMon(),obj.getAvailTue(),obj.getAvailWed(),obj.getAvailThu(),obj.getAvailFri(),obj.getAvailSat(),obj.getAvailSun()));
        }
         
        colMant.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("name"));
        colSkill.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("skill"));
        colMonday.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("mon"));
        colTuesday.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("tue"));
        colWednesday.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("wed"));
        colThursday.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("thu"));
        colFriday.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("fri"));
        colSaturday.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("sat"));
        colSunday.setCellValueFactory(new PropertyValueFactory<DisplayAvail, String>("sun"));
        tableWeek.setItems(data);

    }

    private void btnBack_OnAction(ActionEvent ev) {
        try {
            String ui = "CheckAssignedActivityGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            CheckAssignedActivityGUIController controller = new CheckAssignedActivityGUIController(this.act, this.planner);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Check Activity.0");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load the window" + ex);
        }
        ((Node) (ev.getSource())).getScene().getWindow().hide();
    }
}
