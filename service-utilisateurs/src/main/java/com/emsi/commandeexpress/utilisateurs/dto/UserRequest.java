package com.emsi.commandeexpress.utilisateurs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Données reçues du client pour créer ou modifier un utilisateur.
 */
public record UserRequest(

        @NotBlank(message = "Le prénom est obligatoire")
        @Size(max = 100, message = "Le prénom ne doit pas dépasser 100 caractères")
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
        String lastName,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Le format de l'email est invalide")
        @Size(max = 150, message = "L'email ne doit pas dépasser 150 caractères")
        String email,

        @Pattern(regexp = "^\\+?[0-9 .\\-]{6,20}$", message = "Le numéro de téléphone est invalide (6 à 20 chiffres, + . - espace autorisés)")
        String phone,

        @Size(max = 255, message = "L'adresse ne doit pas dépasser 255 caractères")
        String address
) {
}
