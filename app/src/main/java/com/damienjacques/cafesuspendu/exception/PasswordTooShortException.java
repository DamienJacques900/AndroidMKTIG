package com.damienjacques.cafesuspendu.exception;


public class PasswordTooShortException extends Exception
{
    private String message;

    public PasswordTooShortException()
    {
        this.message = "Erreur, le mot de passe doit faire au moins 6 caractères.";
    }

    public String getMessage()
    {
        return message;
    }
}
