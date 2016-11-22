package com.damienjacques.cafesuspendu.model;

import java.io.Serializable;
import java.util.List;


public class UserPerson implements Serializable
{
    private String userPersonId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private List<Charity> charities;
}
