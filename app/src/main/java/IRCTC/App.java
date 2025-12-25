package IRCTC;

import IRCTC.entities.Ticket;
import IRCTC.entities.User;
import IRCTC.entities.Train;
import IRCTC.services.TicketServices;
import IRCTC.services.TrainServices;
import IRCTC.services.UserServices;
import IRCTC.utils.UserServiceUtil;

import java.util.*;

public class App {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void waitForEnter(Scanner sc) {
        System.out.println("\nPress ENTER to continue...");
        sc.nextLine();
        sc.nextLine();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserServices userServices = null;
        User loggedInUser = null;

        try {
            TrainServices.setTrains();
        } catch (Exception e) {
            System.out.println("Error initializing train services.");
            return;
        }

        int choice = 0;

        while (choice != 8) {

            clearScreen();

            System.out.println("======================================");
            System.out.printf(
                    "        WELCOME TO IRCTC APP %s%n",
                    loggedInUser != null ? loggedInUser.getName() : "Not Logged In");
            System.err.println();
            System.out.println("======================================");
            System.out.println("1. Login User");
            System.out.println("2. Sign Up User");
            System.out.println("3. Book a Seat");
            System.out.println("4. View My Bookings");
            System.out.println("5. Search Trains");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Print All Trains");
            System.out.println("8. Exit Application");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1 -> { // Login
                    clearScreen();
                    System.out.println("--- Login to Your Account ---");

                    System.out.print("Username: ");
                    String username = sc.next();

                    System.out.print("Password: ");
                    String password = sc.next();

                    User tempUser = new User(
                            username,
                            password,
                            null,
                            new ArrayList<>(),
                            null);

                    try {
                        userServices = new UserServices(tempUser);
                        User realUser = userServices.loginUser();

                        if (realUser != null) {
                            loggedInUser = realUser;
                            System.out.println("Login successful");
                        } else {
                            userServices = null;
                            System.out.println("Invalid credentials");
                        }
                    } catch (Exception e) {
                        System.out.println("Login error.");
                    }

                    waitForEnter(sc);
                }

                case 2 -> { // Signup
                    clearScreen();
                    System.out.println("--- Create New Account ---");

                    System.out.print("Choose Username: ");
                    String username = sc.next();

                    System.out.print("Choose Password: ");
                    String password = sc.next();

                    User newUser = new User(
                            username,
                            password,
                            UserServiceUtil.hashPassword(password),
                            new ArrayList<>(),
                            UUID.randomUUID().toString());

                    try {
                        UserServices us = new UserServices(newUser);
                        if (us.signUpUser(newUser)) {
                            System.out.println("Account created successfully.");
                        } else {
                            System.out.println("Signup failed.");
                        }
                    } catch (Exception e) {
                        System.out.println("System error.");
                    }

                    waitForEnter(sc);
                }

                case 3 -> { // Book a seat
                    clearScreen();

                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                        waitForEnter(sc);
                        break;
                    }

                    System.out.print("Enter Source Station: ");
                    String source = sc.next();

                    System.out.print("Enter Destination Station: ");
                    String destination = sc.next();

                    List<Train> trains = UserServices.searchTrains(source, destination);

                    if (trains.isEmpty()) {
                        System.out.println("No trains found.");
                        waitForEnter(sc);
                        break;
                    }

                    int option = 1;
                    for (Train train : trains) {
                        System.out.println("--------------------------------------------");
                        System.out.println("Option -> " + option++);
                        System.out.println("Train Name  : " + train.getName());
                        System.out.println("Train No    : " + train.getTrainNo());
                        System.out.println("Source      : " + source + " -> " + train.getTiming().get(source));
                        System.out.println(
                                "Destination : " + destination + " -> " + train.getTiming().get(destination));
                        System.out.println("--------------------------------------------");
                    }
                    System.out.print("Choose train option: ");
                    int trainOption = sc.nextInt();

                    System.out.print("Number of seats: ");
                    int seats = sc.nextInt();

                    Train chosenTrain = trains.get(trainOption - 1);

                    if (TicketServices.checkSeatAvaliablity(chosenTrain, seats)) {

                        Ticket ticket = new Ticket(
                                UUID.randomUUID().toString(),
                                chosenTrain.getTrainNo(),
                                source,
                                destination,
                                seats,
                                chosenTrain.getName(),
                                chosenTrain.getTiming().get(source),
                                chosenTrain,
                                loggedInUser.getUserId(),
                                "");

                        TicketServices.bookSeats(
                                chosenTrain.getTrainId(),
                                loggedInUser.getUserId(),
                                ticket,
                                seats);

                        System.out.println("Ticket booked successfully.");
                    } else {
                        System.out.println("Not enough seats available.");
                    }

                    waitForEnter(sc);
                }

                case 4 -> { // View bookings
                    clearScreen();

                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                    } else {
                        loggedInUser.printTickets();
                    }

                    waitForEnter(sc);
                }

                case 5 -> { // Train search
                    clearScreen();

                    System.out.print("Enter Source Station: ");
                    String source = sc.next();

                    System.out.print("Enter Destination Station: ");
                    String destination = sc.next();

                    List<Train> trains = UserServices.searchTrains(source, destination);

                    if (trains.isEmpty()) {
                        System.out.println("No trains found.");
                    } else {
                        for (Train train : trains) {
                            System.out.println("--------------------------------------------");
                            System.out.println("Train Name  : " + train.getName());
                            System.out.println("Train No    : " + train.getTrainNo());
                            System.out.println("Source      : " + source + " -> " + train.getTiming().get(source));
                            System.out.println(
                                    "Destination : " + destination + " -> " + train.getTiming().get(destination));
                            System.out.println("--------------------------------------------");
                        }
                    }

                    waitForEnter(sc);
                }
                case 6 -> { // Cancel Ticket
                    System.out.println("Enter Ticket ID to Cancel the ticket");
                    String ticketID = sc.next();
                    userServices.cancelBooking(ticketID);
                    waitForEnter(sc);
                }
                case 7 -> { // Print all trains
                    clearScreen();
                    TrainServices.printAllTrains();
                    waitForEnter(sc);
                }

                case 8 -> System.out.println("Exiting application...");

                default -> {
                    System.out.println("Invalid choice.");
                    waitForEnter(sc);
                }
            }
        }

        sc.close();
    }
}
