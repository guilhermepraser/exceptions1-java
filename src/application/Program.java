package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import model.entities.Reservation;

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();

        System.out.print("Romm number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        System.out.print(" Check-in date (DD/MM/AAAA): ");
        Date checkIn = sdf.parse(sc.nextLine());
        
        System.out.print("Check-out date (DD/MM/AAAA): ");
        Date checkOut = sdf.parse(sc.nextLine());

        if (checkOut.before(checkIn)) {
            System.out.println("Error in reservation: Check-out date must be after check-in date");
        } 
        else if (checkIn.before(now) || checkOut.before(now)) {
            System.out.println("Error in reservation: Reservation dates for update must be future dates");
        } 
        else {
            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
            
            System.out.println();
            System.out.println("Enter data to update the reservation:");
            
            System.out.print(" Check-in date (DD/MM/AAAA): ");
            checkIn = sdf.parse(sc.nextLine());
            
            System.out.print("Check-out date (DD/MM/AAAA): ");
            checkOut = sdf.parse(sc.nextLine());
            
            String error = reservation.updateDates(checkIn, checkOut);

            if (error != null) {
                System.out.println("Error in reservation: " + error);
            } 
            else {
                System.out.println("Reservation: " + reservation);
            }
        }
        sc.close();
    }
}
