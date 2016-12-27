package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class ExistingUserNameException extends Exception
{
    private String message;

    public ExistingUserNameException()
    {
        this.message = "Erreur, le nom d'utilisteur existe déjà, veuillez en essayer un autre.";
    }

    public String getMessage()
    {
        return message;
    }
}
