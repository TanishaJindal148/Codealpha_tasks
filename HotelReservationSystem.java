import java.util.*;
import java.io.*;

public class HotelReservationSystem {

    // ---------- ROOM CLASS ----------
    static class Room {
        int roomNumber;
        String category;
        int price;
        boolean available;

        Room(int roomNumber, String category, int price) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.price = price;
            this.available = true;
        }
    }

    // ---------- BOOKING CLASS ----------
    static class Booking {
        int bookingId;
        String name;
        int roomNumber;
        String category;
        int amount;

        Booking(int bookingId, String name, int roomNumber, String category, int amount) {
            this.bookingId = bookingId;
            this.name = name;
            this.roomNumber = roomNumber;
            this.category = category;
            this.amount = amount;
        }
    }

    // ---------- DATA ----------
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static int bookingId = 1;
    static Scanner sc = new Scanner(System.in);

    // ---------- MAIN ----------
    public static void main(String[] args) {

        // rooms create
        rooms.add(new Room(101, "Standard", 2000));
        rooms.add(new Room(102, "Standard", 2000));
        rooms.add(new Room(201, "Deluxe", 3500));
        rooms.add(new Room(301, "Suite", 5000));

        while (true) {
            System.out.println("\n--- HOTEL RESERVATION SYSTEM ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 1) viewRooms();
            else if (choice == 2) bookRoom();
            else if (choice == 3) cancelBooking();
            else if (choice == 4) viewBookings();
            else if (choice == 5) {
                System.out.println("Thank you 😊");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    // ---------- VIEW ROOMS ----------
    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (r.available) {
                System.out.println(r.roomNumber + " | " + r.category + " | ₹" + r.price);
            }
        }
    }

    // ---------- BOOK ROOM ----------
    static void bookRoom() {
        System.out.print("Enter your name: ");
        String name = sc.next();

        System.out.print("Enter category (Standard/Deluxe/Suite): ");
        String cat = sc.next();

        for (Room r : rooms) {
            if (r.category.equalsIgnoreCase(cat) && r.available) {

                System.out.println("Pay ₹" + r.price);
                System.out.println("Payment Successful ✅");

                r.available = false;

                Booking b = new Booking(bookingId++, name, r.roomNumber, cat, r.price);
                bookings.add(b);
                saveToFile(b);

                System.out.println("Room Booked 🎉");
                System.out.println("Booking ID: " + b.bookingId);
                return;
            }
        }
        System.out.println("No room available");
    }

    // ---------- CANCEL BOOKING ----------
    static void cancelBooking() {
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();

        for (Booking b : bookings) {
            if (b.bookingId == id) {
                for (Room r : rooms) {
                    if (r.roomNumber == b.roomNumber) {
                        r.available = true;
                    }
                }
                bookings.remove(b);
                System.out.println("Booking Cancelled ❌");
                return;
            }
        }
        System.out.println("Booking not found");
    }

    // ---------- VIEW BOOKINGS ----------
    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings");
            return;
        }

        for (Booking b : bookings) {
            System.out.println("ID: " + b.bookingId +
                    " | Name: " + b.name +
                    " | Room: " + b.roomNumber +
                    " | " + b.category +
                    " | ₹" + b.amount);
        }
    }

    // ---------- FILE SAVE ----------
    static void saveToFile(Booking b) {
        try {
            FileWriter fw = new FileWriter("bookings.txt", true);
            fw.write(b.bookingId + "," + b.name + "," + b.roomNumber + "," + b.category + "," + b.amount + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("File error");
        }
    }
}
