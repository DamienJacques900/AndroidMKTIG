package com.damienjacques.cafesuspendu.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Charity implements Serializable
{
    private Integer charityId;

    @NonNull
    private Integer nbCoffeeOffered;

    @NonNull
    private Integer nbCoffeeConsumed;

    @NonNull
    private Date offeringTime;


    private byte[] rowVersion; //java.sql.Timestamp ?

    @NonNull
    private User userPerson;

    @NonNull
    private User userCoffee;

    public Charity()
    {

    }

    public Charity(@NonNull Integer nbCoffeeOffered, @NonNull Integer nbCoffeeConsumed, @NonNull Date offeringTime, @NonNull User userPerson, @NonNull User userCafe) {
        this.nbCoffeeOffered = nbCoffeeOffered;
        this.nbCoffeeConsumed = nbCoffeeConsumed;
        this.offeringTime = offeringTime;
        this.userPerson = userPerson;
        this.userCoffee = userCafe;
    }

    public Charity(@NonNull Date offeringTime, @NonNull User userPerson, @NonNull User userCoffee, @NonNull Integer nbCoffeeOffered,@NonNull Integer nbCoffeeConsumed)
    {
        this.offeringTime = offeringTime;
        this.userPerson = userPerson;
        this.userCoffee = userCoffee;
        this.nbCoffeeOffered = nbCoffeeOffered;
        this.nbCoffeeConsumed = nbCoffeeConsumed;
    }

    public Charity(Integer charityId)
    {
        this.charityId = charityId;
    }

    public Integer getCharityId()
    {
        return charityId;
    }

    @NonNull
    public Integer getNbCoffeeOffered()
    {
        return nbCoffeeOffered;
    }

    @NonNull
    public Integer getNbCoffeeConsumed()
    {
        return nbCoffeeConsumed;
    }

    @NonNull
    public Date getOfferingTime()
    {
        return offeringTime;
    }

    public byte[] getRowVersion()
    {
        return rowVersion;
    }

    @NonNull
    public User getUserPerson()
    {
        return userPerson;
    }

    @NonNull
    public User getUserCafe()
    {
        return userCoffee;
    }

    public void setCharityId(Integer charityId)
    {
        this.charityId = charityId;
    }

    public void setNbCoffeeOffered(@NonNull Integer nbCoffeeOffered)
    {
        this.nbCoffeeOffered = nbCoffeeOffered;
    }

    public void setNbCoffeeConsumed(@NonNull Integer nbCoffeeConsumed)
    {
        this.nbCoffeeConsumed = nbCoffeeConsumed;
    }

    public void setOfferingTime(@NonNull Date offeringTime)
    {
        this.offeringTime = offeringTime;
    }

    public void setRowVersion(byte[] rowVersion)
    {
        this.rowVersion = rowVersion;
    }

    public void setUserPerson(@NonNull User userPerson)
    {
        this.userPerson = userPerson;
    }

    public void setUserCafe(@NonNull User userCafe)
    {
        this.userCoffee = userCafe;
    }
}
