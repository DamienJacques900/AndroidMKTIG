package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class BeginHourBeforeEndException extends Exception
{
    private String message;

    public BeginHourBeforeEndException()
    {
        this.message = "Erreur, l'heure de début doit être avant l'heure de fin.";
    }

    public String getMessage()
    {
        return message;
    }
}
