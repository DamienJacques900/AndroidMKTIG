package com.damienjacques.cafesuspendu.model;

import java.io.Serializable;
import java.util.List;


public class UserCafe implements Serializable
{
    private Integer userCafeId;
    private String password;
    private String cafeName;
    private String street;
    private String number;
    private Integer nbCoffeeRequiredForPromotion;
    private Double promotionValue;
    private List<Booking> bookings;
    private List<Charity> charities;

    private List<TimeTable> timeTables;
}
