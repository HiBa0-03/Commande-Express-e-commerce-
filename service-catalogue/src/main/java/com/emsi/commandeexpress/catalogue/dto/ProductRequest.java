package com.emsi.commandeexpress.catalogue.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Données reçues du client pour créer ou modifier un produit.
 */
public record ProductRequest(

        @NotBlank(message = "La référence est obligatoire")
        @Size(max = 50, message = "La référence ne doit pas dépasser 50 caractères")
        String reference,

        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 150, message = "Le nom ne doit pas dépasser 150 caractères")
        String name,

        @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
        String description,

        @NotNull(message = "Le prix est obligatoire")
        @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être strictement supérieur à 0")
        @Digits(integer = 8, fraction = 2, message = "Le prix doit avoir au maximum 8 chiffres entiers et 2 décimales")
        BigDecimal price,

        @Size(max = 100, message = "La catégorie ne doit pas dépasser 100 caractères")
        String category
) {
}
