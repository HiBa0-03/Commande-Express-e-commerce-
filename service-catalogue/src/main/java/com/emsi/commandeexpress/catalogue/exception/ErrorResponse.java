package com.emsi.commandeexpress.catalogue.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Format unique de toutes les erreurs renvoyées par l'API.
 * "validationErrors" contient le détail champ par champ (vide hors erreurs de validation).
 */
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> validationErrors
) {
}
