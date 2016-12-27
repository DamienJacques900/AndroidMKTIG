package com.damienjacques.cafesuspendu.model;

import android.support.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable
{
    private String id;

    @Size(max=30)
    private String userName;

    @Size(max=30)
    private String cafeName;

    @Size(max=30)
    private String password;

    @Size(max=30)
    private String confirmPassword;

    @Size(max=50)
    private String street;

    @Size(max=5)
    private String number;

    private Integer nbCoffeeRequiredForPromotion;

    private Float promotionValue;

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

    private String roles;

    public User()
    {

    }

    //***********************COMMENTAIRE****************************
    //*Constructeur pour l'inscription des personnes               *
    //**************************************************************
    public User(String userName, String password, String confirmPassword, String firstName, String lastName, String email, String phoneNumber, String roles)
    {
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public User (String userName)
    {
        this.userName = userName;
    }

    public User(String userName, int nbCoffeeRequiredForPromotion, Float promotionValue)
    {
        this.userName = userName;
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
        this.promotionValue = promotionValue;
    }

    public User(String userName,String roles,String email,String phoneNumber,Integer nbCoffeeRequiredForPromotion ,Float promotionValue)
    {
        this.userName = userName;
        this.roles = roles;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
        this.promotionValue = promotionValue;
    }

    public User(String userName,String roles,String email,String phoneNumber,Integer nbCoffeeRequiredForPromotion ,Float promotionValue,String id)
    {
        this.userName = userName;
        this.roles = roles;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
        this.promotionValue = promotionValue;
        this.id = id;
    }

    public User(String userName,String roles,String email,String phoneNumber,Integer nbCoffeeRequiredForPromotion ,Float promotionValue,String id, String cafeName)
    {
        this.userName = userName;
        this.roles = roles;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
        this.promotionValue = promotionValue;
        this.id = id;
        this.cafeName = cafeName;
    }


    public User(String userName, String cafeName, String street, String number, Integer nbCoffeeRequiredForPromotion, Float promotionValue, ArrayList<Booking> bookings, ArrayList<TimeTable> timesTables, String firstName, String lastName, String email, Boolean emailConfirmed, String phoneNumber) {
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

    public User(String userName, String phoneNumber, String email, String lastName, String firstName, String password,String roles)
    {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.roles = roles;
    }

    //***********************COMMENTAIRE****************************
    //*Constructeur pour l'inscription des cafés                   *
    //**************************************************************
    public User(String cafeName, String userName, String password, String confirmPassword,String street, String number, String email, Integer nbCoffeeRequiredForPromotion, Float promotionValue, String roles)
    {
        this.cafeName = cafeName;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.street = street;
        this.number = number;
        this.email = email;
        this.nbCoffeeRequiredForPromotion = nbCoffeeRequiredForPromotion;
        this.promotionValue = promotionValue;
        this.roles = roles;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public void setPromotionValue(Float promotionValue) {
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

    public String getRoles() {
        return roles;
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

    public Float getPromotionValue() {
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
