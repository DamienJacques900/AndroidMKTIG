package com.damienjacques.cafesuspendu.model;

import android.icu.util.DateInterval;
import android.support.annotation.*;

import java.io.Serializable;
import java.util.Date;

public class TimeTable implements Serializable
{

    private Integer timeTableID;

    private Date openingHour;

    private Date closingHour;

    @NonNull
    private Integer dayOfWeek;

    @NonNull
    private User userCafe;

    public TimeTable()
    {

    }

    public TimeTable(Integer timeTableID, Date openingHour, Date closingHour)
    {
        this.timeTableID = timeTableID;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public TimeTable(Integer timeTableID)
    {
        this.timeTableID = timeTableID;
    }

    public Integer getTimeTableID()
    {
        return timeTableID;
    }

    public Date getOpeningHour()
    {
        return openingHour;
    }

    public Date getClosingHour()
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

    public void setOpeningHour(Date openingHour)
    {
        this.openingHour = openingHour;
    }

    public void setClosingHour(Date closingHour)
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
