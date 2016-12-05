package com.damienjacques.cafesuspendu.model;

import android.support.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable
{
    @Size(max=30)
    private String userName;

    @Size(max=30)
    private String cafeName;

    @Size(max=50)
    private String street;

    @Size(max=5)
    private String number;

    private Integer nbCoffeeRequiredForPromotion;

    private Double promotionValue;

    private ArrayList<Booking> bookings;

    private ArrayList<TimeTable> timesTables;

    @Size(max=30)
    private String firstName;

    @Size(max=30)
    private String lastName;

    @Size(max=50)
    private String email;

    private Boolean emailConfirmed;

    @Size(max=12)
    private String phoneNumber;

    public User()
    {

    }

    public User(String userName)
    {
        this.userName = userName;
    }

    public User(String userName, String cafeName, String street, String number, Integer nbCoffeeRequiredForPromotion, Double promotionValue, ArrayList<Booking> bookings, ArrayList<TimeTable> timesTables, String firstName, String lastName, String email, Boolean emailConfirmed, String phoneNumber) {
        this.userName = userName;
        this.cafeName = cafeName;
        this.street = street;
        this.number = number;
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
        this.promotionValue = promotionValue;
        this.bookings = bookings;
        this.timesTables = timesTables;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNbCoffeeRequiredForPromotion(Integer nbCoffeeRequiredForPromotion) {
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
    }

    public void setPromotionValue(Double promotionValue) {
        this.promotionValue = promotionValue;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void setTimesTables(ArrayList<TimeTable> timesTables) {
        this.timesTables = timesTables;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getCafeName() {
        return cafeName;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public Integer getNbCoffeeRequiredForPromotion() {
        return nbCoffeeRequiredForPromotion;
    }

    public Double getPromotionValue() {
        return promotionValue;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<TimeTable> getTimesTables() {
        return timesTables;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
