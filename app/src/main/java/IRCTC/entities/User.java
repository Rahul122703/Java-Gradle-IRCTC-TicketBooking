package IRCTC.entities;

import java.util.*;

public class User {
    private String name;
    private String password;
    private String hashPasssord;
    private static List<Ticket> ticketBooked;
    private String userId;

    public User(String user_name,
            String user_password,
            String user_hashPassword,
            List<Ticket> user_ticketBooked,
            String user_userId) {
        this.name = user_name;
        this.password = user_password;
        this.hashPasssord = user_hashPassword;
        this.ticketBooked = user_ticketBooked;
        this.userId = user_userId;
    }

    public String getName() {
        return this.name;
    }

    public String password() {
        return this.password;
    }

    public String hashPasssord() {
        return this.hashPasssord;
    }

    public List<Ticket> ticketBooked() {
        return this.ticketBooked;
    }

    public String userId() {
        return this.userId;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setHashPasssord(String newSetHashPassword) {
        this.hashPasssord = newSetHashPassword;
    }

    public void setTicketBooked(List<Ticket> newTicketBooked) {
        this.ticketBooked = newTicketBooked;
    }

    public void setUserId(String newUserID) {
        this.userId = newUserID;
    }

    public static void printTickets() {
        for (int i = 0; i < ticketBooked.size(); i++) {
            System.out.println(ticketBooked.get(i).getTicketInfo());
        }
    }

}
