package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class BetweenZeroAndSixtyException extends Exception
{
    private String message;

    public BetweenZeroAndSixtyException()
    {
        this.message = "Erreur, les minutes doivent être comprisent entre 0 et 60.";
    }

    public String getMessage()
    {
        return message;
    }
}
