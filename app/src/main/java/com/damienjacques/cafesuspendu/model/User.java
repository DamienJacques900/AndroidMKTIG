package com.damienjacques.cafesuspendu.model;

import android.support.annotation.*;

import java.io.Serializable;
import java.util.List;


public class User implements Serializable
{
    //Coffee

    private String userCafeId;

    @Size(min=6, max=20)
    private String password;

    @Size(max=30)
    private String cafeName;

    @Size(max=50)
    private String street;

    @Size(max=5)
    private String number;

    private Integer nbCoffeeRequiredForPromotion;

    private Double promotionValue;

    private List<Booking> bookings;

    //Person
    @Size(min=6, max=20)
    private String userPersonId;

    @Size(max=30)
    private String firstName;

    @Size(max=30)
    private String lastName;

    @Size(max=50)
    private String email;

    @Size(max=12)
    private String phoneNumber;

    public User()
    {

    }

    public User(String userCafeId, String cafeName, String street, String number, Integer nbCoffeeRequiredForPromotion, Double promotionValue, String userPersonId, String firstName, String lastName, String email, String phoneNumber) {
        this.userCafeId = userCafeId;
        this.cafeName = cafeName;
        this.street = street;
        this.number = number;
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
        this.promotionValue = promotionValue;
        //this.bookings = bookings;
        this.userPersonId = userPersonId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(String userPersonId)
    {
        this.userPersonId = userPersonId;
    }

    public String getUserCafeId()
    {
        return userCafeId;
    }

    public String getPassword()
    {
        return password;
    }

    public String getCafeName()
    {
        return cafeName;
    }

    public String getStreet()
    {
        return street;
    }

    public String getNumber()
    {
        return number;
    }

    public Integer getNbCoffeeRequiredForPromotion()
    {
        return nbCoffeeRequiredForPromotion;
    }

    public Double getPromotionValue()
    {
        return promotionValue;
    }

    public List<Booking> getBookings()
    {
        return bookings;
    }

    public String getUserPersonId()
    {
        return userPersonId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setUserCafeId(String userCafeId)
    {
        this.userCafeId = userCafeId;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setCafeName(String cafeName)
    {
        this.cafeName = cafeName;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setNbCoffeeRequiredForPromotion(Integer nbCoffeeRequiredForPromotion)
    {
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
    }

    public void setPromotionValue(Double promotionValue)
    {
        this.promotionValue = promotionValue;
    }

    public void setBookings(List<Booking> bookings)
    {
        this.bookings = bookings;
    }

    public void setUserPersonId(String userPersonId)
    {
        this.userPersonId = userPersonId;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
