package com.damienjacques.cafesuspendu.model;

import android.widget.ProgressBar;

public class PromotionLine
{
    private String cafeName;

    private String descriptionPromo;

    private ProgressBar progressBarPromo;

    public PromotionLine(String cafeName, String descriptionPromo, ProgressBar progressBarPromo)
    {
        this.cafeName = cafeName;
        this.descriptionPromo = descriptionPromo;
        this.progressBarPromo = progressBarPromo;
    }

    public String getCafeName()
    {
        return cafeName;
    }

    public String getDescriptionPromo()
    {
        return descriptionPromo;
    }

    public ProgressBar getProgressBarPromo()
    {
        return progressBarPromo;
    }

    public void setCafeName(String cafeName)
    {
        this.cafeName = cafeName;
    }

    public void setDescriptionPromo(String descriptionPromo)
    {
        this.descriptionPromo = descriptionPromo;
    }

    public void setProgressBarPromo(ProgressBar progressBarPromo)
    {
        this.progressBarPromo = progressBarPromo;
    }
}
