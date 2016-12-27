package com.damienjacques.cafesuspendu.exception;


public class PasswordNotGoodException extends Exception
{
    private String message;

    public PasswordNotGoodException()
    {
        this.message = "Erreur, le mot de passe doit posséder une majuscule et un chiffre minimum";
    }

    public String getMessage()
    {
        return message;
    }
}
