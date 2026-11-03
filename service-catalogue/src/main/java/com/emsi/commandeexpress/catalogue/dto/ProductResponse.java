package com.emsi.commandeexpress.catalogue.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Données renvoyées au client (l'entité n'est jamais exposée directement).
 */
public record ProductResponse(
        Long id,
        String reference,
        String name,
        String description,
        BigDecimal price,
        String category,
        LocalDateTime createdAt
) {
}
