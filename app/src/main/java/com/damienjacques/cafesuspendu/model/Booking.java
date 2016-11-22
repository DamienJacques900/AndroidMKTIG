package com.damienjacques.cafesuspendu.model;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable
{
    private Integer bookingId;
    //

    @NonNull
    private Date dateBooking;

    @NonNull
    @Size(min = 6, max = 20)
    private String name;

    @NonNull
    private UserCafe userCafe;

    @NonNull
    public Terminal terminal;
}
