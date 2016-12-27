package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class PhoneNumberFalseException extends Exception
{
    private String message;

    public PhoneNumberFalseException()
    {
        this.message = "Erreur, numéro de téléphone trop long.";
    }

    public String getMessage()
    {
        return message;
    }
}
