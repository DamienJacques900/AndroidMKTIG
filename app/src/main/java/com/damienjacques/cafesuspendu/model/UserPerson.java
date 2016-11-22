package com.damienjacques.cafesuspendu.model;

import android.support.annotation.*;

import java.io.Serializable;
import java.util.List;


public class UserPerson implements Serializable
{
    @NonNull
    @Size(min=6, max=20)
    private String userPersonId;

    @NonNull
    @Size(min=6, max=20)
    private String password;

    @NonNull
    @Size(max=30)
    private String firstName;

    @NonNull
    @Size(max=30)
    private String lastName;

    @NonNull
    @Size(max=50)
    private String email;

    @NonNull
    @Size(max=12)
    private String phoneNumber;

    private List<Charity> charities;
}
