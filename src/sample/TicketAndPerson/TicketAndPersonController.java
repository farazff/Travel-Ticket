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
    void goToHomePage() throws IOException
    {

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
    public void onlinePayment() throws IOException
    {
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
        bankPageController.setCurrentTicketAndUser(currentUser, currentTicket, "ticket");
//        bankPageController.setCurrentTicketAndUser(currentUser,currentTicket);

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
        profileController.serCurrentUser(currentUser);


        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void setCurrentTicketAndUser(Ticket ticket, User user)
    {
        this.currentUser = user;
        this.currentTicket = ticket;
//        System.out.println(currentUser.getFirstName());


        name.setText(currentUser.getFirstName());
        lastname.setText(currentUser.getLastName());
        SocialNumber.setText(currentUser.getSocialSecurityNumber());
        email.setText(currentUser.getEmail());
        phoneNumber.setText(currentUser.getPhoneNumber());


        String dh = ticket.getDepartureHour() + "";
        String dm = ticket.getDepartureMinute() + "";
        String ah = ticket.getArrivalHour() + "";
        String am = ticket.getArrivalMinute() + "";
        if( ticket.getDepartureHour() < 10 )
            dh = "0" + ticket.getDepartureHour();

        if( ticket.getDepartureMinute() < 10 )
            dm = "0" + ticket.getDepartureMinute();

        if( ticket.getArrivalHour() < 10 )
            ah = "0" + ticket.getArrivalHour();

        if( ticket.getArrivalMinute() < 10 )
            am = "0" + ticket.getArrivalMinute();


        DestDate.setText(currentTicket.getArrivalDate().getYear() + "/" + currentTicket.getArrivalDate().getMonth() + "/" + currentTicket.getArrivalDate().getDayOfMonth());
        OriginDate.setText(currentTicket.getDepartureDate().getYear() + "/" + currentTicket.getDepartureDate().getMonth() + "/" + currentTicket.getDepartureDate().getDayOfMonth());
        DestTime.setText(ah + ":" + am);
        OriginTime.setText(dh + ":" + dm);

        DestLocation.setText(currentTicket.getArrivalCity());
        OriginLocation.setText(currentTicket.getDepartureCity());

        price.setText(currentTicket.getPrice() + "$");

        try {
            companyIcon.setImage(new Image("/sample/TicketAndPerson/Pictures/" + currentTicket.getAirLineName().split(" ")[0] + ".png"));

        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
