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
    private User userCafe;

    public TimeTable()
    {

    }

    public TimeTable(Integer timeTableID)
    {
        this.timeTableID = timeTableID;
    }

    public Integer getTimeTableID()
    {
        return timeTableID;
    }

    public DateInterval getOpeningHour()
    {
        return openingHour;
    }

    public DateInterval getClosingHour()
    {
        return closingHour;
    }

    @NonNull
    public Integer getDayOfWeek()
    {
        return dayOfWeek;
    }

    @NonNull
    public User getUserCafe()
    {
        return userCafe;
    }

    public void setTimeTableID(Integer timeTableID)
    {
        this.timeTableID = timeTableID;
    }

    public void setOpeningHour(DateInterval openingHour)
    {
        this.openingHour = openingHour;
    }

    public void setClosingHour(DateInterval closingHour)
    {
        this.closingHour = closingHour;
    }

    public void setDayOfWeek(@NonNull Integer dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
    }

    public void setUserCafe(@NonNull User userCafe)
    {
        this.userCafe = userCafe;
    }
}
