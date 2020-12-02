/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer;

/**
 *
 * @author camil
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.*;

public class LoginGUIController implements Initializable{

    private String username;
    private String pwd;
    final Stage newWindow = new Stage();
    
    @FXML
    private Button btnLog;
    private TextField textUser;
    private PasswordField textPwd;
    private Text textWarning;
    
    @FXML
    private void btnLog_OnAction(ActionEvent event) {
        username=textUser.getText();
        pwd=textPwd.getText();
        textWarning.setVisible(false);
        
        if(checkAvailability(username,pwd)){
            /*String role=user.getRole();
            if(role=="Planner"){
                
            }else if(role=="Maintainer"){
                
            }*/
        }else{
            textWarning.setVisible(true);
        }
    }
    
    private boolean checkAvailability(String username,String pwd){
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
    
}
