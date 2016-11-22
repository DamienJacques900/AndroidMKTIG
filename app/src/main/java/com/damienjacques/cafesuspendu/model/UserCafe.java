package com.damienjacques.cafesuspendu.model;

import android.support.annotation.*;

import java.io.Serializable;
import java.util.List;


public class UserCafe implements Serializable
{
    @NonNull
    @Size(min=6, max=20)
    private Integer userCafeId;

    @NonNull
    @Size(min=6, max=20)
    private String password;

    @NonNull
    @Size(max=30)
    private String cafeName;

    @NonNull
    @Size(max=50)
    private String street;

    @NonNull
    @Size(max=5)
    private String number;

    @NonNull
    private Integer nbCoffeeRequiredForPromotion;

    @NonNull
    private Double promotionValue;


    private List<Booking> bookings;


    private List<Charity> charities;


    private List<TimeTable> timeTables;
}
