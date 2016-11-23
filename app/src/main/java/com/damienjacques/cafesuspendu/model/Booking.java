package com.damienjacques.cafesuspendu.model;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable
{
    private Integer bookingId;

    @NonNull
    private Date dateBooking;

    @NonNull
    @Size(min = 6, max = 20)
    private String name;

    @NonNull
    private User userCafe;

    @NonNull
    public Terminal terminal;

    public Booking()
    {

    }

    public Booking(String name)
    {
        this.name = name;
    }

    public Integer getBookingId()
    {
        return bookingId;
    }

    @NonNull
    public Date getDateBooking()
    {
        return dateBooking;
    }

    @NonNull
    public String getName()
    {
        return name;
    }

    @NonNull
    public User getUserCafe()
    {
        return userCafe;
    }

    @NonNull
    public Terminal getTerminal()
    {
        return terminal;
    }

    public void setBookingId(Integer bookingId)
    {
        this.bookingId = bookingId;
    }

    public void setDateBooking(@NonNull Date dateBooking)
    {
        this.dateBooking = dateBooking;
    }

    public void setName(@NonNull String name)
    {
        this.name = name;
    }

    public void setUserCafe(@NonNull User userCafe)
    {
        this.userCafe = userCafe;
    }

    public void setTerminal(@NonNull Terminal terminal)
    {
        this.terminal = terminal;
    }
}
