package com.damienjacques.cafesuspendu.model;

import android.icu.util.DateInterval;

import java.io.Serializable;

public class TimeTable implements Serializable
{
    private Integer timeTableID;
    private DateInterval openingHour;
    private DateInterval closingHour;
    private Integer dayOfWeek;
    //private UserCafe userCafe;
}
