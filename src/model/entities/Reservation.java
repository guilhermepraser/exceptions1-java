package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import model.Exception.DomainException;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static Date now = new Date();

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException{
        if (checkOut.before(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        } 
        if (checkIn.before(now) || checkOut.before(now)) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getcheckIn() {
        return checkIn;
    }

    public Date getcheckOut() {
        return checkOut;
    }

    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    public void updateDates(Date checkIn, Date checkOut) throws DomainException {
        if (getcheckOut().before(getcheckIn())) {
            throw new DomainException("Check-out date must be after check-in date");
        } 
        if (getcheckIn().before(now) || getcheckOut().before(now)) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Room ");
        sb.append(roomNumber);
        sb.append(", check-in: ");
        sb.append(sdf.format(checkIn));
        sb.append(", checkou: ");
        sb.append(sdf.format(checkOut));
        sb.append(", ");
        sb.append(duration());
        sb.append(" nights");
        return sb.toString();
    }

}
