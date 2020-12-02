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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class SystemAdministratorGUIController implements Initializable {

    @FXML
    private TableView<DisplayUser> tableView = new TableView<DisplayUser>();
    @FXML
    private Button btnCreate;
    private Label labWarn = new Label();
    private Button btnHub = new Button();

    @FXML
    private TableColumn<DisplayUser, String> colUser;
    @FXML
    private TableColumn<DisplayUser, String> colPass;
    @FXML
    private TableColumn<DisplayUser, String> colRole;
    @FXML
    private TableColumn<DisplayUser, String> colOptions;
    @FXML
    private TableColumn<DisplayUser, String> colModify;
    @FXML
    private TableColumn<DisplayUser, String> colDelete;
    @FXML
    private Button[] btnDel = new Button[100];
    private Button[] btnMod = new Button[100];

    private SystemAdministratorService admin;

    public SystemAdministratorGUIController(SystemAdministratorService admin) {
        this.admin = admin;
    }

    /**
     * Initializes the controller class.
     */
    public class DisplayUser {

        private SimpleStringProperty user;
        private SimpleStringProperty pass;
        private SimpleStringProperty role;
        private Button btnDel;
        private Button btnMod;

        public DisplayUser(String username, String password, String rolec, Button btnDel, Button btnMod) {
            this.user = new SimpleStringProperty(username);
            this.pass = new SimpleStringProperty(password);
            this.role = new SimpleStringProperty(rolec);
            this.btnDel = btnDel;
            this.btnDel.setText("Delete");
            this.btnMod = btnMod;
            this.btnMod.setText("Modify");

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

        public String getUser() {
            return user.get();
        }

        public void setUser(String username) {
            user.set(username);
        }

        public String getPass() {
            return pass.get();
        }

        public void setPass(String password) {
            pass.set(password);
        }

        public String getRole() {
            return role.get();
        }

        public void setRole(String rolec) {
            role.set(rolec);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initializeTable();
    }

    public void initializeTable() {
        ObservableList<DisplayUser> data;
        data = FXCollections.observableArrayList();

        for (int i = 0; i < admin.viewUsers().size(); i++) {   
            btnDel[i] = new Button("Delete");
            btnDel[i].setOnAction(this::btnDelete_OnAction);
            btnMod[i] = new Button("Modify");
            btnMod[i].setOnAction(this::btnModify_OnAction);
            User obj = null;

            obj = admin.viewUsers().get(i);

            String username = obj.getUsername();
            String password = obj.getPassword();
            String role = "";
            if (obj instanceof Planner) {
                role = "Planner";
            } else if (obj instanceof Maintainer) {
                role = "Maintainer";
            }

            data.add(new DisplayUser(username, password, role, btnDel[i], btnMod[i]));

        }

        colUser.setCellValueFactory(new PropertyValueFactory<DisplayUser, String>("user"));
        colPass.setCellValueFactory(new PropertyValueFactory<DisplayUser, String>("pass"));
        colRole.setCellValueFactory(new PropertyValueFactory<DisplayUser, String>("role"));
        colDelete.setCellValueFactory(new PropertyValueFactory<DisplayUser, String>("btnDel"));
        colModify.setCellValueFactory(new PropertyValueFactory<DisplayUser, String>("btnMod"));
        tableView.setItems(data);
    }

    public void btnDelete_OnAction(ActionEvent ev) {
        labWarn.setVisible(false);
        for (int i = 0; i < this.admin.viewUsers().size(); i++) {
            if (ev.getSource() == btnDel[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        if (admin.deleteUser(tableView.getSelectionModel().getSelectedItem().getUser())) {
            tableView.getItems().clear();
            this.initializeTable();
        } else {
            labWarn.setVisible(true);
        }

    }

    public void btnModify_OnAction(ActionEvent ev) {

        for (int i = 0; i < this.admin.viewUsers().size(); i++) {
            if (ev.getSource() == btnMod[i]) {
                tableView.getSelectionModel().clearAndSelect(i);
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/ModifyPasswordWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            ModifyPasswordWindowController controller = new ModifyPasswordWindowController(tableView.getSelectionModel().getSelectedItem(), this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Modify Password");

            // Centers the editor window over the current window
            stage.initOwner(tableView.getScene().getWindow());

            // Ensures the new window needs to be closed before the current window can be used again
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();

            tableView.getItems().clear();
            this.initializeTable();
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }

    }

    @FXML
    private void createUser_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userinterfacelayer/CreateUserWindow.fxml"));

            // Create the new controller and pass the currently selected data item to it
            CreateUserWindowController controller = new CreateUserWindowController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Create User");

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
