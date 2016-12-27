package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class SizeNumberException extends Exception
{
    private String message;

    public SizeNumberException()
    {
        this.message = "Erreur, la taille du nombre ne peut pas dépasser 5 caractères";
    }

    public String getMessage()
    {
        return message;
    }
}
