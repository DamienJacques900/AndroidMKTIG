package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 27-12-16.
 */

public class SizeNameFirstNameException extends Exception
{
    private String message;

    public SizeNameFirstNameException()
    {
        this.message = "Erreur, la taille du nom et prénom ne peut pas dépasser 30 caractères";
    }

    public String getMessage()
    {
        return message;
    }
}
