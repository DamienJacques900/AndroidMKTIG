package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class TooMuchException extends Exception
{
    private String message;

    public TooMuchException()
    {
        this.message = "Erreur, le nombre doit être inférieur ou égale à 100";
    }

    public String getMessage()
    {
        return message;
    }
}
