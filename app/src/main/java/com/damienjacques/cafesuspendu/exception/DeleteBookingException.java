package com.damienjacques.cafesuspendu.exception;

/**
 * Created by Damien Jacques on 15-12-16.
 */

public class DeleteBookingException extends Exception
{
    private String message;

    public DeleteBookingException()
    {
        this.message = "Erreur de connexion aux données durant la tentative de suppression de la réservation";
    }

    public String getMessage()
    {
        return message;
    }
}
