package com.emsi.commandeexpress.catalogue.exception;

/**
 * Levée quand une ressource demandée n'existe pas (réponse HTTP 404).
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " introuvable avec l'id : " + id);
    }
}
