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

    private Integer idBooking;

    public ReservationLine(String reservationName, String reservationDate, Integer idBooking)
    {
        this.reservationName = reservationName;
        this.reservationDate = reservationDate;
        this.idBooking = idBooking;
    }

    public Integer getIdBooking()
    {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking)
    {
        this.idBooking = idBooking;
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
