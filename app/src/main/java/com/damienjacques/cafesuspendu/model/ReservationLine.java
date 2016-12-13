package com.damienjacques.cafesuspendu.model;

import java.util.Date;

/**
 * Created by Damien Jacques on 13-12-16.
 */

public class ReservationLine
{
    private String reservationName;

    private String reservationDate;

    public ReservationLine(String reservationName, String reservationDate)
    {
        this.reservationName = reservationName;
        this.reservationDate = reservationDate;
    }

    public String getReservationName()
    {
        return reservationName;
    }

    public void setReservationName(String reservationName)
    {
        this.reservationName = reservationName;
    }

    public String getReservationDate()
    {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate)
    {
        this.reservationDate = reservationDate;
    }
}
