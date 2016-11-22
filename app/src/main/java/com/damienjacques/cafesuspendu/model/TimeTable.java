package com.damienjacques.cafesuspendu.model;

import android.icu.util.DateInterval;
import android.support.annotation.*;

import java.io.Serializable;

public class TimeTable implements Serializable
{

    private Integer timeTableID;

    private DateInterval openingHour;

    private DateInterval closingHour;

    @NonNull
    private Integer dayOfWeek;

    @NonNull
    private UserCafe userCafe;
}
