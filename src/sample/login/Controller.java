package sample.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class Controller
{
    @FXML
    protected JFXPasswordField password;

    @FXML
    protected JFXButton loginButton;

    @FXML
    protected Hyperlink signUpLink;


    protected void actionHandler(ActionEvent event) throws Exception {
        if (event.getSource () == loginButton)
        {
            // TODO : login here
            System.out.println ("log in");
        }
        else if (event.getSource () == signUpLink){
            // TODO : go to sign up scene
            System.out.println ("go to sign UP");
        }
    }
}