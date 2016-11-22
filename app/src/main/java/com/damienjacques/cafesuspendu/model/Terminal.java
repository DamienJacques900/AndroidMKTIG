package com.damienjacques.cafesuspendu.model;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class Terminal implements Serializable
{
    private Integer terminalId;

    @NonNull
    private Integer nbCookedCoffees;

    @NonNull
    @FloatRange(from=-90.0, to=+90.0)
    private Double latitude;

    @NonNull
    @FloatRange(from=-180.0, to=+180.0)
    private Double longitude;
}
