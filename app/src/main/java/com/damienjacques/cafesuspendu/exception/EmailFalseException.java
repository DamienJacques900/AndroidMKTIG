package com.damienjacques.cafesuspendu.exception;


public class EmailFalseException extends Exception
{
    private String message;

    public EmailFalseException()
    {
        this.message = "Erreur, e-mail invalide";
    }

    public String getMessage()
    {
        return message;
    }
}
