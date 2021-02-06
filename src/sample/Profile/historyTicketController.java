package sample.Profile;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Ticket;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class historyTicketController
{

    private Ticket currentTicket;

    @FXML
    private Label arrDate;

    @FXML
    private Label arrTime;

    @FXML
    private Label depDate;

    @FXML
    private Label depTime;

    @FXML
    private Label origin;

    @FXML
    private Label destination;

    @FXML
    private ImageView logo;

    @FXML
    private JFXButton retrieveBTN;

    @FXML
    void retrieve(ActionEvent event)
    {
        //TODO: add ticket to remaining tickets
        //TODO: remove this ticket from user's Tickets
    }

    public void setEveryThing(Ticket ticket)
    {
        this.currentTicket = ticket;

        depDate.setText(ticket.getDepartureDate().toString());

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

        depTime.setText(dh + ":" + dm);
        arrDate.setText(ticket.getArrivalDate().toString());
        arrTime.setText(ah + ":" + am);
        origin.setText(ticket.getDepartureCity());
        destination.setText(ticket.getArrivalCity());
        try {
            FileInputStream input = new FileInputStream("src/sample/Tickets/Pictures/" + ticket.getAirLineName() + ".png");
            Image image = new Image(input);
            logo.setImage(image);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}