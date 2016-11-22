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
    private UserPerson userPerson;

    @NonNull
    private UserCafe userCafe;


}
