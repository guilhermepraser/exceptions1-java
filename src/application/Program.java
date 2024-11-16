package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import model.Exception.DomainException;
import model.entities.Reservation;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            System.out.print("Romm number: ");
            int roomNumber = sc.nextInt();
            System.out.print(" Check-in date (DD/MM/AAAA): ");
            Date checkIn = sdf.parse(sc.next());            
            System.out.print("Check-out date (DD/MM/AAAA): ");
            Date checkOut = sdf.parse(sc.next());

            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
            
            System.out.println();
            System.out.println("Enter data to update the reservation:");            
            System.out.print(" Check-in date (DD/MM/AAAA): ");
            checkIn = sdf.parse(sc.nextLine());            
            System.out.print("Check-out date (DD/MM/AAAA): ");
            checkOut = sdf.parse(sc.nextLine());
            
            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
        }
        catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        catch (DomainException e) {
            System.out.println("Error in reservation: " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("Unexpected error");
        }
        sc.close();
    }
}
