package com.damienjacques.cafesuspendu.model;

/**
 * Created by Damien Jacques on 13-12-16.
 */

public class HistoryLine
{
    private String coffeeName;

    private String description;

    public HistoryLine(String coffeeName, String description)
    {
        this.coffeeName = coffeeName;
        this.description = description;
    }

    public String getCoffeeName()
    {
        return coffeeName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setCoffeeName(String coffeeName)
    {
        this.coffeeName = coffeeName;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
