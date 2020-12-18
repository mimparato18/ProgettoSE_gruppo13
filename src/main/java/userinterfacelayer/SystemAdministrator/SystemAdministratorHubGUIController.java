
package userinterfacelayer.SystemAdministrator;

import businesslayer.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Administrator Hub to manage every activity that belongs to him
 *
 * @author camil
 */
public class SystemAdministratorHubGUIController implements Initializable {

    @FXML
    private Button btnUser;
    @FXML
    private Button btnSite;
    @FXML
    private Button btnTypo;
    @FXML
    private Button btnProcedure;
    @FXML
    private Button btnCompe;
    @FXML
    private Button btnLogout;

    private SystemAdministratorService admin;

    public SystemAdministratorHubGUIController(String username, String password) {
        this.admin = new SystemAdministratorService(new SystemAdministrator(username, password));
    }

    public SystemAdministratorHubGUIController(SystemAdministratorService admin) {
        this.admin = admin;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnProced_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ProcedureManagementGUI.fxml"));
            // Create the new controller and pass the currently selected data item to it
            ProcedureManagementGUIController controller = new ProcedureManagementGUIController(this.admin);
            // Set the controller to the loader
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Procedures Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnProcedure.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnUser_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SystemAdministratorGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            UserManagementGUIController controller = new UserManagementGUIController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("User Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnUser.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void btnSite_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SiteManagementGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            SiteManagementGUIController controller = new SiteManagementGUIController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Site Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnSite.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnTypo_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("TypologyManagementGUI.fxml"));

            // Create the new controller and pass the currently selected data item to it
            TypologyManagementGUIController controller = new TypologyManagementGUIController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Typology Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnTypo.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnLogout_OnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
    @FXML
    private void btnCompe_OnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CompetencesManagement.fxml"));

            // Create the new controller and pass the currently selected data item to it
            CompetencesManagementController controller = new CompetencesManagementController(this.admin);

            // Set the controller to the loader
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Competences Management");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            
        } catch (Exception e) {
            System.out.println("Can't load the window: " + e);
        }
        Stage stage = (Stage) btnCompe.getScene().getWindow();
        stage.close();
    }
    



}
