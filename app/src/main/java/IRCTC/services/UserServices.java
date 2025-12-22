package IRCTC.services;

import IRCTC.entities.Ticket;
import IRCTC.entities.User;
import IRCTC.utils.UserServiceUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import IRCTC.entities.Train;

public class UserServices {
    private User user;
    public static final ObjectMapper OM = new ObjectMapper();
    private static final String USER_PATH = "D:\\RahulSharma\\Learn\\Java\\IRCTC\\app\\src\\main\\java\\IRCTC\\localDB\\users.json";
    private static List<User> usersList;

    public UserServices(User user1) throws Exception { // Constructor
        this.user = user1;
        loadAllUsers();
    }

    public UserServices() throws IOException {
        loadAllUsers();
    }

    public static List<User> loadAllUsers() throws IOException {
        File Allusers = new File(USER_PATH);
        return OM.readValue(Allusers, new TypeReference<List<User>>() {
        });
    }

    private static void addUserToFileList() throws IOException {
        File usersFile = new File(USER_PATH);
        OM.writeValue(usersFile, usersList);
    }

    public Boolean loginUser() {
        Optional<User> userPresent = usersList
                .stream().filter(currentUser -> currentUser.getName().equals(this.user.getName()) &&
                        UserServiceUtil.checkHashPassword(
                                this.user.password(),
                                currentUser.hashPasssord()))
                .findFirst();
        return userPresent.isPresent();
    }

    public static Boolean signUpUser(User currentUser) {
        try {
            usersList.add(currentUser);
            addUserToFileList();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public void fetchBookings() {
        this.user.printTickets();
    }

    public void cancelBooking(String ticketId) {
        try {
            List<Ticket> newTicketBooked = this.user.ticketBooked()
                    .stream()
                    .filter(currentTicket -> !currentTicket.getTicketId().equals(ticketId))
                    .toList();

            this.user.setTicketBooked(newTicketBooked);
            addUserToFileList();
        } catch (Exception e) {
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
