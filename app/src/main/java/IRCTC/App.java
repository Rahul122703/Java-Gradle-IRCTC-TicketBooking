package IRCTC;

import IRCTC.entities.User;
import IRCTC.services.TrainServices;
import IRCTC.services.UserServices;
import IRCTC.utils.UserServiceUtil;

import java.util.*;

public class App {

    public static void main(String[] args) {

        System.out.println("--------- TICKET BOOKING APP ---------");

        Scanner sc = new Scanner(System.in);

        // UserServices userServices = null;
        UserServices userServices = null;
        User loggedInUser = null;
        try {
            TrainServices.setTrains();
        } catch (Exception e) {
            System.out.println("Error initializing train services." + e.getMessage());
            return;
        }
        int choice = 0;

        while (choice != 8) {

            System.out.println("\n======================================");
            System.out.println("        WELCOME TO IRCTC APP");
            System.out.println("======================================");
            System.out.println("1. Login User");
            System.out.println("2. Sign Up User");
            System.out.println("3. Book a Seat");
            System.out.println("4. View My Bookings");
            System.out.println("5. Search Trains");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Print All trains");

            System.out.println("8. Exit Application");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1: {
                    System.out.println("\n--- Login to Your Account ---");
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
                        boolean loginStatus = userServices.loginUser();

                        if (loginStatus) {
                            loggedInUser = tempUser;
                            System.out.println("Login Successful! Welcome " + username);
                        } else {
                            System.out.println("Login Failed! Invalid credentials.");
                            userServices = null;
                        }

                    } catch (Exception e) {
                        System.out.println("Login error. Please try again.");
                    }
                    break;
                }

                case 2: {
                    try {
                        System.out.println("\n--- Create New Account ---");
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

                        UserServices newUserData = new UserServices(newUser);
                        boolean signupStatus = newUserData.signUpUser(newUser);

                        if (signupStatus) {
                            System.out.println("Account created successfully. Please login.");
                        } else {
                            System.out.println("Signup failed. Try again.");
                        }

                    } catch (Exception e) {
                        System.out.println("System error while creating account.");
                        e.printStackTrace();
                    }
                    break;
                }

                case 3:
                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.println("Book a Seat selected");
                    break;

                case 4:
                    if (loggedInUser == null || userServices == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    userServices.fetchBookings();
                    break;

                case 5:
                    System.out.println("\n==================== TRAIN SEARCH ====================");
                    System.out.print("Enter Source Station      : ");
                    String source = sc.next();

                    System.out.print("Enter Destination Station : ");
                    String destination = sc.next();

                    System.out.println("\n--------------- Available Trains ----------------");
                    System.out.println(UserServices.searchTrains(source, destination));
                    System.out.println("--------------------------------------------------");
                    break;

                case 6:
                    if (loggedInUser == null || userServices == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.print("Enter Ticket ID to cancel: ");
                    String ticketId = sc.next();
                    userServices.cancelBooking(ticketId);
                    System.out.println("Booking cancelled (if ticket existed).");
                    break;

                case 7:
                    System.out.println("-------------------PRINTING ALL TRAINS--------------------");
                    TrainServices.printAllTrains();
                    break;

                case 8:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1–7.");
            }
        }

        sc.close();
    }
}
