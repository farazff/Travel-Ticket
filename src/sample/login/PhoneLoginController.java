package sample.login;


import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.NullUser;
import model.User;
import model.connections.userInformationClient.Client;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PhoneLoginController extends Controller
        implements Initializable {


    @FXML
    private JFXTextField phoneNumber;

    @FXML
    private Hyperlink enterWithEmailLink;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private JFXTextField phoneNumberCode;

    @FXML
    private Label invalidPhoneNumberWarnLabel;

    @FXML
    protected void actionHandler(ActionEvent event) throws Exception{
        if (event.getSource () == loginButton)
        {
            boolean validPassword = super.isPasswordValid (), validPhone;


            if (phoneNumber.getText ().length () != 10)
            {
                if (!invalidPhoneNumberWarnLabel.isVisible ())
                    invalidPhoneNumberWarnLabel.setVisible (true);
                password.setText ("");

                validPhone = false;
            }
            else
            {
                validPhone = !invalidPhoneNumberWarnLabel.isVisible ();
            }


            if (validPhone && validPassword)
            {


                AnchorPane load = FXMLLoader.load(getClass().getResource("/sample/Loading/Loading.fxml"));
                mainPane.getChildren().add(load);
                Client client = connect ();


                PauseTransition pause = new PauseTransition(Duration.seconds(2));

                pause.setOnFinished(f -> {
                    User user = serverResponse (client);

                    if (user != null)
                    {
                        System.out.println (user.getPhoneNumber () + user.getEmail ());

                        Stage stage;
                        stage = (Stage) loginButton.getScene().getWindow();
                        Scene scene = new Scene(profileRoot);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else
                    {
                        mainPane.getChildren ().remove (load);
                        password.setText ("");
                    }

                });
                pause.play();





            }
        }
        else if (event.getSource () == enterWithEmailLink)
        {

            Stage stage;
            Parent root;

            stage = (Stage) enterWithEmailLink.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("loginView_2.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (event.getSource () == signUpLink){

            Stage stage;
            Parent root;

            stage = (Stage) signUpLink.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/sample/signup/signUpView.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void keyHandler(KeyEvent event) {

        if (phoneNumber.getText ().length () != 10 &&
                phoneNumber.getText ().length () != 0)
        {
            if (!invalidPhoneNumberWarnLabel.isVisible ())
                invalidPhoneNumberWarnLabel.setVisible (true);
            return;
        }
        else
        {
            for (char c : phoneNumber.getText ().toCharArray ())
                if (c > '9' || c < '0')
                {
                    if (!invalidPhoneNumberWarnLabel.isVisible ())
                        invalidPhoneNumberWarnLabel.setVisible (true);
                    return;
                }
        }
        if (invalidPhoneNumberWarnLabel.isVisible ())
            invalidPhoneNumberWarnLabel.setVisible (false);

    }

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        super.initialize (location, resources);
        invalidPhoneNumberWarnLabel.setVisible (false);

    }

    private Client connect ()
    {
        Client client = new Client ("127.0.0.1",phoneNumberCode.getText () +
                phoneNumber.getText (), password.getText (),"Login");
        new Thread (client).start ();
        return client;
    }


}