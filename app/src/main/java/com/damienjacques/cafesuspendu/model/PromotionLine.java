package com.damienjacques.cafesuspendu.model;

import android.widget.ProgressBar;

public class PromotionLine
{
    private String cafeName;

    private String descriptionPromo;

    private ProgressBar progressBarPromo;

    private Integer progressStatus;

    public PromotionLine(String cafeName, String descriptionPromo, ProgressBar progressBarPromo, Integer progressStatus)
    {
        this.cafeName = cafeName;
        this.descriptionPromo = descriptionPromo;
        this.progressBarPromo = progressBarPromo;
        this.progressStatus = progressStatus;
    }

    public Integer getProgressStatus()
    {
        return progressStatus;
    }

    public void setProgressStatus(Integer progressStatus)
    {
        this.progressStatus = progressStatus;
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
