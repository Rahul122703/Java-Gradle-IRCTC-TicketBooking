package IRCTC.entities;

import java.util.List;

import IRCTC.services.UserServices;

public class Ticket {
    private String ticketId;
    private String trainNo;
    private String source;
    private String destination;
    private int numberOfSeats;
    private String trainName;
    private String dateOfTravel;
    private Train train;
    private String userId;
    private String ticketInfo;

    public Ticket(
            String ticket_ticketId,
            String ticket_trainNo,
            String ticket_source,
            String ticket_destination, int numberOfSeats,
            String ticket_trainName,
            String ticket_dateOfTravel,
            Train ticket_train,
            String ticket_userId,
            String ticketInfo) {
        this.ticketId = ticket_ticketId;
        this.trainNo = ticket_trainNo;
        this.source = ticket_source;
        this.destination = ticket_destination;
        this.trainName = ticket_trainName;
        this.dateOfTravel = ticket_dateOfTravel;
        this.train = ticket_train;
        this.userId = ticket_userId;
    }

    public Ticket() {

    }

    public String getTicketId() {
        return this.ticketId;
    }

    public String getTrainNo() {
        return this.trainNo;
    }

    public int getNumberOfSeats() {
        return this.numberOfSeats;
    }

    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getTrainName() {
        return this.trainName;
    }

    public String getDateOfTravel() {
        return this.dateOfTravel;
    }

    public Train getTrain() {
        return this.train;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setTicketId(String newTicketId) {
        this.ticketId = newTicketId;
    }

    public void setTrainNo(String newTrainNo) {
        this.trainNo = newTrainNo;
    }

    public void setSource(String newSource) {
        this.source = newSource;
    }

    public void setDestination(String newDestination) {
        this.destination = newDestination;
    }

    public void setTrainName(String newTrainName) {
        this.trainName = newTrainName;
    }

    public void setNumberOfSeats(int newNumberOfSeats) {
        this.numberOfSeats = newNumberOfSeats;
    }

    public void setDateOfTravel(String newDateOfTravel) {
        this.dateOfTravel = newDateOfTravel;
    }

    public void setTrain(Train newTrain) {
        this.train = newTrain;
    }

    public void setUserId(String newUserId) {
        this.userId = newUserId;
    }

    public String getTicketInfo() {
        return String.format(
                "This ticket belongs to %s for %s from %s to %s on %s",
                UserServices.getCurrentUser().getName(), trainName, source, destination, dateOfTravel);
    }
}
