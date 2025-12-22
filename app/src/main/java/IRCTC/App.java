package IRCTC;

import IRCTC.entities.Ticket;
import IRCTC.entities.User;
import IRCTC.entities.Train;
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
            System.out.println("        WELCOME TO IRCTC APP");
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

                case 1 -> {
                    clearScreen();
                    System.out.println("--- Login to Your Account ---");

                    System.out.print("Username: ");
                    String username = sc.next();

                    System.out.print("Password: ");
                    String password = sc.next();

                    User tempUser = new User(
                            username,
                            password,
                            UserServiceUtil.hashPassword(password),
                            new ArrayList<>(),
                            UUID.randomUUID().toString());

                    try {
                        userServices = new UserServices(tempUser);
                        if (userServices.loginUser()) {
                            loggedInUser = tempUser;
                            System.out.println("\nLogin successful. Welcome " + username);
                        } else {
                            userServices = null;
                            System.out.println("\nInvalid credentials.");
                        }
                    } catch (Exception e) {
                        System.out.println("\nLogin error.");
                    }

                    waitForEnter(sc);
                }

                case 2 -> {
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
                            System.out.println("\nAccount created successfully.");
                        } else {
                            System.out.println("\nSignup failed.");
                        }
                    } catch (Exception e) {
                        System.out.println("\nSystem error.");
                    }

                    waitForEnter(sc);
                }

                case 3 -> {
                    clearScreen();
                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                    } else {
                        System.out.println("Book seat feature coming soon.");
                    }
                    waitForEnter(sc);
                }

                case 4 -> {
                    clearScreen();
                    if (loggedInUser == null || userServices == null) {
                        System.out.println("Please login first.");
                    } else {
                        userServices.fetchBookings();
                    }
                    waitForEnter(sc);
                }

                case 5 -> {
                    clearScreen();
                    System.out.println("=== TRAIN SEARCH ===");

                    System.out.print("Enter Source Station: ");
                    String source = sc.next();

                    System.out.print("Enter Destination Station: ");
                    String destination = sc.next();

                    List<Train> trains = UserServices.searchTrains(source, destination);

                    if (trains.isEmpty()) {
                        System.out.println("\nNo trains found.");
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

                case 6 -> {
                    clearScreen();
                    if (loggedInUser == null || userServices == null) {
                        System.out.println("Please login first.");
                    } else {
                        System.out.print("Enter Ticket ID: ");
                        String ticketId = sc.next();
                        userServices.cancelBooking(ticketId);
                        System.out.println("\nTicket cancelled if it existed.");
                    }
                    waitForEnter(sc);
                }

                case 7 -> {
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
