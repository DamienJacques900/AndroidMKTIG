package com.damienjacques.cafesuspendu.exception;


public class ExistingCoffeeNameException extends Exception
{
    private String message;

    public ExistingCoffeeNameException()
    {
        this.message = "Erreur, le nom de café existe déjà.";
    }

    public String getMessage()
    {
        return message;
    }
}
