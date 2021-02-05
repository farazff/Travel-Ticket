package sample.TicketAndPerson;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.stage.Stage;

import model.Ticket;
import model.User;
import sample.Bank.BankPageController;
import sample.Profile.ProfileController;
import sample.Search.SearchController;
import sample.Tickets.TicketsPageController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TicketAndPersonController implements Initializable {
    @FXML
    private JFXButton home;

    private Ticket currentTicket;
    private User currentUser;

    @FXML
    private Label DestDate, OriginDate, DestTime, OriginTime, DestLocation, OriginLocation, price, name, lastname, phoneNumber, email, SocialNumber;
    @FXML
    private ImageView companyIcon;

    @FXML
    private ArrayList<Object> inputAndOutputArray;


    @FXML
    void goToHomePage() throws IOException {

//        Stage stage;
//        Parent root;
//
//        stage = (Stage) home.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("/sample/Search/Search.fxml"));
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        Stage stage;
        stage = (Stage) home.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Search/Search.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, e);
        }

        SearchController searchController = loader.getController();
        searchController.setCurrentUser(currentUser);

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private JFXButton account;

    @FXML
    private JFXButton payOnline;

    @FXML
    public void onlinePayment() throws IOException {


//        Stage stage;
//        Parent root;
//
//        stage = (Stage) payOnline.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("/sample/Bank/BankPage.fxml"));
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        Stage stage;
        stage = (Stage) home.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Bank/BankPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, e);
        }

        BankPageController bankPageController = loader.getController();
        //TODO
        bankPageController.setCurrentTicketAndUser(currentUser,currentTicket);

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    @FXML
    public void goToAccountPage() throws IOException {
//        Stage stage;
//        Parent root;
//
//        stage = (Stage) home.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("/sample/Profile/profileView.fxml"));
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        Stage stage;
        stage = (Stage) home.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Profile/profileView.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, e);
        }

        ProfileController profileController = loader.getController();
        //TODO
        // profileController.setUser(currentUser);


        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void setCurrentTicketAndUser(Ticket ticket, User user) {
        this.currentUser = user;
        this.currentTicket = ticket;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name.setText(currentUser.getFirstName());
        lastname.setText(currentUser.getLastName());
        SocialNumber.setText(currentUser.getSocialSecurityNumber());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhoneNumber());
        ///
        DestDate.setText(currentTicket.getArrivalYear() + "/" + currentTicket.getArrivalMonth() + "/" + currentTicket.getArrivalDay());
        OriginDate.setText(currentTicket.getDepartureYear() + "/" + currentTicket.getDepartureYear() + "/" + currentTicket.getDepartureDay());
        DestTime.setText(currentTicket.getArrivalHour() + ":" + currentTicket.getArrivalMinute());
        OriginTime.setText(currentTicket.getDepartureHour() + ":" + currentTicket.getDepartureMinute());

        ////
        DestLocation.setText(currentTicket.getArrivalCity());
        OriginTime.setText(currentTicket.getDepartureCity());

        price.setText(currentTicket.getSold() + " ");
        try {
            companyIcon.setImage(new Image("/sample/TicketAndPerson/Pictures/" + currentTicket.getAirLineName().split(" ")[0] + ".png"));

        } catch (NullPointerException e) {
            System.err.println(e);
        }

    }
}
