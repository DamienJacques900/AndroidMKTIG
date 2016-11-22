package com.damienjacques.cafesuspendu.model;

import java.io.Serializable;
import java.util.Date;

public class Charity implements Serializable
{
    private Integer charityId;
    private Integer nbCoffeeOffered;
    private Integer nbCoffeeConsumed;
    private Date offeringTime;
    private byte[] rowVersion; //java.sql.Timestamp ?

    private UserPerson userPerson;
    private UserCafe userCafe;


}
