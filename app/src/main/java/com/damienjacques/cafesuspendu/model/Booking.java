package com.damienjacques.cafesuspendu.model;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable
{
    private Integer bookingId;
    private Date dateBooking;
    private String name;

    private UserCafe userCafe;
    public Terminal terminal;
}
