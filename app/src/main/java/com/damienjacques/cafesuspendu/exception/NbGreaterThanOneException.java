package com.damienjacques.cafesuspendu.exception;

public class NbGreaterThanOneException extends Exception
{
    private String message;

    public NbGreaterThanOneException()
    {
        this.message = "Erreur, le nombre doit au moins être de 1 et pour les décimal de 0.1";
    }

    public String getMessage()
    {
        return message;
    }
}
