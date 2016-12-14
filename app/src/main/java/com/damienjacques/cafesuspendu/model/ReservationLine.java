package com.damienjacques.cafesuspendu.model;

import android.widget.Button;

import java.util.Date;

/**
 * Created by Damien Jacques on 13-12-16.
 */

public class ReservationLine
{
    private String reservationName;

    private String reservationDate;

    private Button consummed;

    private Button notConsummed;

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

    public Button getConsummed()
    {
        return consummed;
    }

    public void setConsummed(Button consummed)
    {
        this.consummed = consummed;
    }

    public Button getNotConsummed()
    {
        return notConsummed;
    }

    public void setNotConsummed(Button notConsummed)
    {
        this.notConsummed = notConsummed;
    }
}
