package com.emsi.commandeexpress.utilisateurs.dto;

import java.time.LocalDateTime;

/**
 * Données renvoyées au client (l'entité n'est jamais exposée directement).
 */
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        LocalDateTime createdAt
) {
}
