/*
 * This source file was generated by the Gradle 'init' task
 */
package ticket.booking;

import ticket.booking.services.UserBookingService;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking Sytem...");
        Scanner scanner = new Scanner(System.in);
        int option = 0;

       try {
           UserBookingService userBookingService = new UserBookingService();
       } catch (IOException ex) {
           System.out.println("Something went wrong");
           return;
       }

       while(option != 7) {

       }
    }
}
