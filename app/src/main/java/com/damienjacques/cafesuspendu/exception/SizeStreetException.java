package com.damienjacques.cafesuspendu.exception;


public class SizeStreetException extends Exception
{
    private String message;

    public SizeStreetException()
    {
        this.message = "Erreur, la taille du nom du café ne peut pas dépasser 50 caractères";
    }

    public String getMessage()
    {
        return message;
    }
}
