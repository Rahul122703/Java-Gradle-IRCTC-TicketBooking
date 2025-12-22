package IRCTC.entities;

import java.util.List;

public class Ticket {
    private String ticketId;
    private String trainNo;
    private String source;
    private String destination;
    private List<Boolean> seats;
    private String trainName;
    private String dateOfTravel;
    private Train train;
    private String userId;

    public Ticket(
            String ticket_ticketId,
            String ticket_trainNo,
            String ticket_source,
            String ticket_destination,
            List<Boolean> ticket_seats,
            String ticket_trainName,
            String ticket_dateOfTravel,
            Train ticket_train,
            String ticket_userId) {
        this.ticketId = ticket_ticketId;
        this.trainNo = ticket_trainNo;
        this.source = ticket_source;
        this.destination = ticket_destination;
        this.seats = ticket_seats;
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

    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public List<Boolean> getSeats() {
        return this.seats;
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

    public void setSeats(List<Boolean> newSeats) {
        this.seats = newSeats;
    }

    public void setTrainName(String newTrainName) {
        this.trainName = newTrainName;
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
                userId, trainName, source, destination, dateOfTravel);
    }
}
