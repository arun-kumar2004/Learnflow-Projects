import java.util.Scanner;

public class OnlineMovieTicket {
    private static String[] movies = {"Bhaag Milkha Bhaag (10:00 AM)", "The Dark Knight (1:00 PM)", "Inception (4:00 PM)"};
    private static int[][] seats = new int[3][10]; // Assuming each movie has 10 available seats
    private static int ticketNumber = 239;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Welcome to Online Movie Ticket Booking System");
            System.out.println("1. Browse Movies");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Tickets");
            System.out.println("4. Check Seat and Ticket Information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    browseMovies();
                    break;
                case 2:
                    bookTickets(scanner);
                    break;
                case 3:
                    cancelTickets(scanner);
                    break;
                case 4:
                    checkTicketInformation(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using our system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void browseMovies() {
        System.out.println("Available Movies:");
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i + 1) + ". " + movies[i]);
        }
    }

    private static void bookTickets(Scanner scanner) {
        browseMovies();
        System.out.print("Enter the movie number you want to book tickets for: ");
        int movieIndex = scanner.nextInt() - 1;
        if (movieIndex < 0 || movieIndex >= movies.length) {
            System.out.println("Invalid movie number. Please try again.");
            return;
        }
        System.out.println("Available Seats for " + movies[movieIndex] + ":");
        for (int i = 0; i < seats[movieIndex].length; i++) {
            if (seats[movieIndex][i] == 0) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
        System.out.print("Enter the seat number you want to book: ");
        int seatNumber = scanner.nextInt() - 1;
        if (seatNumber < 0 || seatNumber >= seats[movieIndex].length || seats[movieIndex][seatNumber] != 0) {
            System.out.println("Invalid seat number or seat already booked. Please try again.");
            return;
        }
        seats[movieIndex][seatNumber] = ticketNumber; // Mark seat as booked with ticket number
        System.out.println("Ticket booked successfully for " + movies[movieIndex] + ", Seat Number: " + (seatNumber + 1) + ", Ticket Number: " + ticketNumber);
        ticketNumber++; // Increment ticket number for next booking
    }

    private static void cancelTickets(Scanner scanner) {
        System.out.print("Enter your ticket number: ");
        int ticketNo = scanner.nextInt();
        boolean ticketFound = false;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == ticketNo) {
                    seats[i][j] = 0; // Mark seat as available
                    System.out.println("Ticket cancelled successfully for Ticket Number: " + ticketNo + ", Movie: " + movies[i] + ", Seat Number: " + (j + 1));
                    ticketFound = true;
                    break;
                }
            }
            if (ticketFound) {
                break;
            }
        }
        if (!ticketFound) {
            System.out.println("Ticket not found. Please enter a valid ticket number.");
        }
    }

    private static void checkTicketInformation(Scanner scanner) {
        System.out.print("Enter your ticket number: ");
        int ticketNo = scanner.nextInt();
        boolean ticketFound = false;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == ticketNo) {
                    System.out.println("Ticket Information:");
                    System.out.println("Movie: " + movies[i]);
                    System.out.println("Seat Number: " + (j + 1));
                    ticketFound = true;
                    break;
                }
            }
            if (ticketFound) {
                break;
            }
        }
        if (!ticketFound) {
            System.out.println("Ticket not found. Please enter a valid ticket number.");
        }
    }
}