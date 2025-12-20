package IRCTC;

import IRCTC.entities.User;
import IRCTC.services.UserServices;
import IRCTC.utils.UserServiceUtil;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        System.out.println("---------TICKET BOOKING APP-----------");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        try {
            UserServices userServicesObject = new UserServices();

        } catch (Exception e) {
            System.out.println("THERE WAS SOME ERROR PLZ TRY AGAIN LATER");
        }

        while (option != 7) {

            System.out.println("\n======================================");
            System.out.println("        WELCOME TO IRCTC APP");
            System.out.println("======================================");
            System.out.println("Please choose an option (1–7):\n");

            System.out.println("1. Login User");
            System.out.println("2. Sign Up User");
            System.out.println("3. Book a Seat");
            System.out.println("4. View My Bookings");
            System.out.println("5. Search Trains");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit Application");

            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            System.out.println("\nYou selected option: " + choice);

            sc.close();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Login to Your Account ---");
                    System.out.print("Username: ");
                    String login_userName = sc.next();
                    System.out.print("Password: ");
                    String login_uuserPassword = sc.next();
                    User login_newUser = new User(
                            login_userName,
                            login_uuserPassword,
                            UserServiceUtil.hashPassword(login_uuserPassword),
                            new ArrayList<>(),
                            UUID.randomUUID().toString());
                    try {
                        UserServices userServices = new UserServices(login_newUser);
                        Boolean loginStatus = userServices.loginUser();
                        if (loginStatus) {
                            System.out.println("Login Sucessful!! + Welcome back" + login_userName);
                        } else {
                            System.out.println(
                                    "Login Failed!! + Invalid Credentials or " + login_userName + " dosen't exist");
                        }
                    } catch (Exception e) {
                        System.out.println("There was some error please try again later");
                    }
                case 2:
                    System.out.println("Sign Up User selected");
                    System.out.print("Username: ");
                    String userName = sc.next();
                    System.out.print("Password: ");
                    String userPassword = sc.next();
                    User newUser = new User(
                            userName,
                            userPassword,
                            UserServiceUtil.hashPassword(userPassword),
                            new ArrayList<>(),
                            UUID.randomUUID().toString());
                    UserServices.signUpUser(newUser);
                    System.out.println("\nLogin request submitted successfully.");
                    System.out.println("Please wait while we verify your credentials...");
                    break;

                case 3:
                    System.out.println("Book a Seat selected");
                    break;

                case 4:
                    System.out.println("View My Bookings selected");
                    break;

                case 5:
                    System.out.println("Search Trains selected");
                    break;

                case 6:
                    System.out.println("Cancel Booking selected");
                    break;

                case 7:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice! Please select between 1 and 7.");
            }

        }
    }
}
