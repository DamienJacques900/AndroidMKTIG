package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class SizeCoffeNameException  extends Exception
{
    private String message;

    public SizeCoffeNameException()
    {
        this.message = "Erreur, la taille du nom du café ne peut pas dépasser 30 caractères";
    }

    public String getMessage()
    {
        return message;
    }
}
