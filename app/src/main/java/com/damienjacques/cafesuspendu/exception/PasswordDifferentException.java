package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class PasswordDifferentException extends Exception
{
    private String message;

    public PasswordDifferentException()
    {
        this.message = "Erreur, les mots de passe sont différents.";
    }

    public String getMessage()
    {
        return message;
    }
}
