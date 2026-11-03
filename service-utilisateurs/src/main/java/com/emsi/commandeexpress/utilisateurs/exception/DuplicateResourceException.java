package com.emsi.commandeexpress.utilisateurs.exception;

/**
 * Levée quand une valeur qui doit être unique existe déjà (réponse HTTP 409).
 */
public class DuplicateResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateResourceException(String message) {
        super(message);
    }
}
