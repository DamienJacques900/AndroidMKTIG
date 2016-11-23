package com.damienjacques.cafesuspendu.model;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class Terminal implements Serializable
{
    private Integer terminalId;

    @NonNull
    private Integer nbBookedCoffees;

    @NonNull
    @FloatRange(from=-90.0, to=+90.0)
    private Double latitude;

    @NonNull
    @FloatRange(from=-180.0, to=+180.0)
    private Double longitude;

    public Terminal()
    {

    }

    public Terminal(Integer terminalId, Integer nbBookedCoffees, Double latitude, Double longitude)
    {
        this.terminalId = terminalId;
        this.nbBookedCoffees = nbBookedCoffees;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public Integer getNbBookedCoffees()
    {
        return nbBookedCoffees;
    }

    @NonNull
    public Double getLatitude()
    {
        return latitude;
    }

    public Integer getTerminalId()
    {

        return terminalId;
    }

    @NonNull
    public Double getLongitude()
    {
        return longitude;
    }

    public void setTerminalId(Integer terminalId)
    {
        this.terminalId = terminalId;
    }

    public void setNbBookedCoffees(@NonNull Integer nbBookedCoffees)
    {
        this.nbBookedCoffees = nbBookedCoffees;
    }

    public void setLatitude(@NonNull Double latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(@NonNull Double longitude)
    {
        this.longitude = longitude;
    }
}
