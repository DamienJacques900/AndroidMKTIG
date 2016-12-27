package com.damienjacques.cafesuspendu.exception;

public class EmptyInputException extends Exception
{
    private String message;

    public EmptyInputException()
    {
        this.message = "Erreur, tout les champs doivent être remplis";
    }

    public String getMessage()
    {
        return message;
    }
}
