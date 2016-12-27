package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class BetweenZeroAndTwentyFourException extends Exception
{
    private String message;

    public BetweenZeroAndTwentyFourException()
    {
        this.message = "Erreur, l'heure de début doit comprise entre 0 et 24.";
    }

    public String getMessage()
    {
        return message;
    }
}
