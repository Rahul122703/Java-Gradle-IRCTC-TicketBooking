package IRCTC.services;

import IRCTC.entities.Ticket;
import IRCTC.entities.User;
import IRCTC.utils.UserServiceUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Ticker;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import IRCTC.entities.Train;

public class UserServices {
    private static User user;
    public static final ObjectMapper OM = new ObjectMapper();
    public static final String USER_PATH = "D:\\RahulSharma\\Learn\\Java\\IRCTC\\app\\src\\main\\java\\IRCTC\\localDB\\users.json";
    private static List<User> usersList;

    public UserServices(User user1) throws Exception {
        loadAllUsers();
        this.user = user1;
    }

    public static List<User> getAllUsers() {
        return usersList;
    }

    public static User getCurrentUser() {
        return user;
    }

    public UserServices() throws IOException {
        loadAllUsers();
    }

    public static void loadAllUsers() throws IOException {
        File Allusers = new File(USER_PATH);
        usersList = OM.readValue(Allusers, new TypeReference<List<User>>() {
        });
    }

    public static void addUserToFileList() throws IOException {
        File usersFile = new File(USER_PATH);
        OM.writeValue(usersFile, usersList);
    }

    public User loginUser() {
        Optional<User> userPresent = usersList.stream()
                .filter(u -> u.getName().equals(user.getName()) &&
                        UserServiceUtil.checkHashPassword(
                                user.getPassword(),
                                u.getHashPasssord()))
                .findFirst();

        if (userPresent.isPresent()) {
            this.user = userPresent.get(); 
            return this.user;
        }

        return null;
    }

    public Boolean signUpUser(User currentUser) {
        try {
            System.out.println("THIS IS THE USER " + user.getName());
            usersList.add(currentUser);
            addUserToFileList();
            return Boolean.TRUE;
        } catch (Exception e) {
            System.out.println(e);
            return Boolean.FALSE;
        }
    }

    public void fetchBookings() {
        this.user.printTickets();
    }

    public void cancelBooking(String ticketId) { // here
        System.out.print("ticketId : ");
        System.out.println(ticketId);
        Ticket ticketToCancel = null;
        System.out.printf("USER TICKET SIZE ARRAY %d: ", this.user.getTicketBooked().size());
        for (Ticket t : this.user.getTicketBooked()) {
            System.out.println("CURRENT TICKET ID");
            System.out.println(t.getTicketId());
        }
        for (Ticket t : this.user.getTicketBooked()) {
            System.out.println(t.getTicketId());
            if (t.getTicketId().equals(ticketId)) {
                ticketToCancel = t;
                break;
            }
        }

        if (ticketToCancel == null) {
            System.out.println("No ticket found with this ID.");
            return;
        }
        user.getTicketBooked().remove(ticketToCancel);
        Train train = ticketToCancel.getTrain();
        int seatsToFree = ticketToCancel.getNumberOfSeats();

        int freed = 0;
        for (List<Integer> row : train.getSeats()) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i) == 1) {
                    row.set(i, 0);
                    freed++;
                    if (freed == seatsToFree)
                        break;
                }
            }
            if (freed == seatsToFree)
                break;
        }
        try {
            UserServices.addUserToFileList();
            TrainServices.setNewTrainData();
            System.out.println("Ticket cancelled successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] countStations() {
        return TrainServices.stationCountArray();
    }

    public static List<Train> searchTrains(String source, String destination) {
        return TrainServices.availableTrains(source, destination);
    }

}
