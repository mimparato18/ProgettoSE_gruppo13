package userinterfacelayer.Planner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import userinterfacelayer.Planner.MaintainerActivityGUIController.DisplayAvail;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class MaintainerAvailDayGUIController implements Initializable {

    @FXML
    private TextField txtWeek;
    @FXML
    private TextField txtAct;
    @FXML
    private TextArea txtWorkspace;
    @FXML
    private TableView<DisplayAvailHours> tableDay;
    @FXML
    private TableColumn<DisplayAvailHours, String> colMant;
    @FXML
    private TableColumn<DisplayAvailHours, String> colSkill;
    @FXML
    private TableColumn<DisplayAvailHours, String> colAvail;
    @FXML
    private TableColumn<DisplayAvailHours, String> colFirst;
    @FXML
    private TableColumn<DisplayAvailHours, String> colSecond;
    @FXML
    private TableColumn<DisplayAvailHours, String> colThird;
    @FXML
    private TableColumn<DisplayAvailHours, String> colFourth;
    @FXML
    private TableColumn<DisplayAvailHours, String> colFifth;
    @FXML
    private TableColumn<DisplayAvailHours, String> colSixth;
    @FXML
    private TableColumn<DisplayAvailHours, String> colSeventh;
    @FXML
    private Button btnSend;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtDayNum;
    @FXML
    private Label labDay;
    @FXML
    private TextField txtDayAvail;
    @FXML
    private Label labMaint;

    private DisplayAvail data;
    private String availDay, day;
    private MaintenanceActivity act;
    private PlannerService planner;

    public MaintainerAvailDayGUIController(MaintenanceActivity act, PlannerService planner, String availDay, DisplayAvail data, String day) {
        this.act = act;
        this.planner = planner;
        this.availDay = availDay;
        this.data = data;
        this.day = day;
    }

    public class DisplayAvailHours {

        private SimpleStringProperty mant;
        private SimpleStringProperty skills;
        private SimpleStringProperty first;
        private SimpleStringProperty second;
        private SimpleStringProperty third;
        private SimpleStringProperty fourth;
        private SimpleStringProperty fifth;
        private SimpleStringProperty sixth;
        private SimpleStringProperty seventh;

        public DisplayAvailHours(String mant, String skills, String first, String second, String third, String fourth, String fifth, String sixth, String seventh) {
            this.mant = new SimpleStringProperty(mant);
            this.skills = new SimpleStringProperty(skills);
            this.first = new SimpleStringProperty(first);
            this.second = new SimpleStringProperty(second);
            this.third = new SimpleStringProperty(third);
            this.fourth = new SimpleStringProperty(fourth);
            this.fifth = new SimpleStringProperty(fifth);
            this.sixth = new SimpleStringProperty(sixth);
            this.seventh = new SimpleStringProperty(seventh);
        }

        public String getMant() {
            return mant.get();
        }

        public void setMant(String mant) {
            this.mant.set(mant);
        }

        public String getSkills() {
            return skills.get();
        }

        public void setSkills(String skills) {
            this.skills.set(skills);
        }

        public String getFirst() {
            return first.get();
        }

        public void setFirst(String first) {
            this.first.set(first);
        }

        public String getSecond() {
            return second.get();
        }

        public void setSecond(String second) {
            this.second.set(second);
        }

        public String getThird() {
            return third.get();
        }

        public void setThird(String third) {
            this.third.set(third);
        }

        public String getFourth() {
            return fourth.get();
        }

        public void setFourth(String fourth) {
            this.fourth.set(fourth);
        }

        public String getFifth() {
            return fifth.get();
        }

        public void setFifth(String fifth) {
            this.fifth.set(fifth);
        }

        public String getSixth() {
            return sixth.get();
        }

        public void setSixth(String sixth) {
            this.sixth.set(sixth);
        }

        public String getSeventh() {
            return seventh.get();
        }

        public void setSeventh(String seventh) {
            this.seventh.set(seventh);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtDayAvail.setText(availDay);
        labMaint.setText("Availability \"" + data.getName() + "\":");
        txtWorkspace.setText(act.getWorkspaceNotes());
        txtWeek.setText("" + act.getWeek());
        txtAct.setText(act.getId() + " - " + act.getSite().getBranchOffice() + "  " + act.getSite().getDepartment() + " - " + act.getTypology() + " - " + act.getInterventionTime() + "'");
        labDay.setText(day + ":");
        txtDayNum.setText("" + planner.getDayOfMonth(day, act.getWeek()));
        this.initializeTable();
        
        colFirst.setCellFactory(tc -> {
            TableCell<DisplayAvailHours, String> cell = new TableCell<DisplayAvailHours, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item);

                        int value = Integer.parseInt(item.replace(" min", ""));
                        if (value == 60) {
                            setTextFill(Color.DARKGREEN);
                        } else if (value < 60 && value >=50 ) {
                            setTextFill(Color.GREEN);
                        } else if (value < 50 && value >= 30) {
                            setTextFill(Color.YELLOW);
                        } else if (value < 30 && value >= 1) {
                            setTextFill(Color.ORANGE);
                        } else if (value == 0) {
                            setTextFill(Color.RED);
                        }
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availMon = cell.getItem();
                }

            });
            return cell;
        });
        colSecond.setCellFactory(tc -> {
            TableCell<DisplayAvailHours, String> cell = new TableCell<DisplayAvailHours, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item);

                        int value = Integer.parseInt(item.replace(" min", ""));
                        if (value == 60) {
                            setTextFill(Color.DARKGREEN);
                        } else if (value < 60 && value >=50 ) {
                            setTextFill(Color.GREEN);
                        } else if (value < 50 && value >= 30) {
                            setTextFill(Color.YELLOW);
                        } else if (value < 30 && value >= 1) {
                            setTextFill(Color.ORANGE);
                        } else if (value == 0) {
                            setTextFill(Color.RED);
                        }
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availTue = cell.getItem();
                }

            });
            return cell;
        });
        colThird.setCellFactory(tc -> {
            TableCell<DisplayAvailHours, String> cell = new TableCell<DisplayAvailHours, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item);

                        int value = Integer.parseInt(item.replace(" min", ""));
                        if (value == 60) {
                            setTextFill(Color.DARKGREEN);
                        } else if (value < 60 && value >=50 ) {
                            setTextFill(Color.GREEN);
                        } else if (value < 50 && value >= 30) {
                            setTextFill(Color.YELLOW);
                        } else if (value < 30 && value >= 1) {
                            setTextFill(Color.ORANGE);
                        } else if (value == 0) {
                            setTextFill(Color.RED);
                        }
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availWed = cell.getItem();
                }

            });
            return cell;
        });
        colFourth.setCellFactory(tc -> {
            TableCell<DisplayAvailHours, String> cell = new TableCell<DisplayAvailHours, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item);

                        int value = Integer.parseInt(item.replace(" min", ""));
                        if (value == 60) {
                            setTextFill(Color.DARKGREEN);
                        } else if (value < 60 && value >=50 ) {
                            setTextFill(Color.GREEN);
                        } else if (value < 50 && value >= 30) {
                            setTextFill(Color.YELLOW);
                        } else if (value < 30 && value >= 1) {
                            setTextFill(Color.ORANGE);
                        } else if (value == 0) {
                            setTextFill(Color.RED);
                        }
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availThu = cell.getItem();
                }

            });
            return cell;
        });
        colFifth.setCellFactory(tc -> {
            TableCell<DisplayAvailHours, String> cell = new TableCell<DisplayAvailHours, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item);

                        int value = Integer.parseInt(item.replace(" min", ""));
                        if (value == 60) {
                            setTextFill(Color.DARKGREEN);
                        } else if (value < 60 && value >=50 ) {
                            setTextFill(Color.GREEN);
                        } else if (value < 50 && value >= 30) {
                            setTextFill(Color.YELLOW);
                        } else if (value < 30 && value >= 1) {
                            setTextFill(Color.ORANGE);
                        } else if (value == 0) {
                            setTextFill(Color.RED);
                        }
                    }

                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availFri = cell.getItem();
                }

            });
            return cell;
        });

        colSixth.setCellFactory(tc -> {
            TableCell<DisplayAvailHours, String> cell = new TableCell<DisplayAvailHours, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item);

                        int value = Integer.parseInt(item.replace(" min", ""));
                        if (value == 60) {
                            setTextFill(Color.DARKGREEN);
                        } else if (value < 60 && value >=50 ) {
                            setTextFill(Color.GREEN);
                        } else if (value < 50 && value >= 30) {
                            setTextFill(Color.YELLOW);
                        } else if (value < 30 && value >= 1) {
                            setTextFill(Color.ORANGE);
                        } else if (value == 0) {
                            setTextFill(Color.RED);
                        }
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availSat = cell.getItem();
                }

            });
            return cell;
        });

        colSeventh.setCellFactory(tc -> {
            TableCell<DisplayAvailHours, String> cell = new TableCell<DisplayAvailHours, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item);

                        int value = Integer.parseInt(item.replace(" min", ""));
                        if (value == 60) {
                            setTextFill(Color.DARKGREEN);
                        } else if (value < 60 && value >=50 ) {
                            setTextFill(Color.GREEN);
                        } else if (value < 50 && value >= 30) {
                            setTextFill(Color.YELLOW);
                        } else if (value < 30 && value >= 1) {
                            setTextFill(Color.ORANGE);
                        } else if (value == 0) {
                            setTextFill(Color.RED);
                        }
                    }
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    String availSun = cell.getItem();
                }

            });
            return cell;
        });
    }

    private void initializeTable() {
        ObservableList<DisplayAvailHours> coll;
        coll = FXCollections.observableArrayList();
        ArrayList<String> list = planner.getAvailabilityByWeekAndDay(act.getWeek(), data.getName(), day);

        coll.add(new DisplayAvailHours(data.getName(), data.getSkill(), list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6)));

        colMant.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("mant"));
        colSkill.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("skills"));
        colFirst.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("first"));
        colSecond.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("second"));
        colThird.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("third"));
        colFourth.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("fourth"));
        colFifth.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("fifth"));
        colSixth.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("sixth"));
        colSeventh.setCellValueFactory(new PropertyValueFactory<DisplayAvailHours, String>("seventh"));
        tableDay.setItems(coll);
    }

    @FXML
    private void btnSend_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnBack_OnAction(ActionEvent event) {
        try {
            String ui = "MaintainerActivityGUI.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ui));
            // Create the new controller and pass the currently selected data item to it
            MaintainerActivityGUIController controller = new MaintainerActivityGUIController(this.act, this.planner);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Check Activity");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load the window" + ex);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
