🛒 Commande Express
Plateforme e-commerce basée sur une architecture microservices avec Spring Boot et Spring Cloud. Projet de – JEE Avancé / Spring Boot Microservices – EMSI .

📌 Description
Commande Express permet de gérer un catalogue de produits, des utilisateurs, des stocks et des commandes. Chaque domaine métier est isolé dans un microservice indépendant, avec sa propre base de données (Database per Service). Les services communiquent uniquement par API REST et ne partagent aucune relation JPA : ils se référencent entre eux par de simples identifiants.

Périmètre
Gestion des produits, des utilisateurs, du stock et des commandes
Découverte de services, passerelle API et configuration centralisée
Hors périmètre : authentification, paiement, livraison, interface web
Règles d'architecture
Database per Service : chaque service possède sa base, aucun accès direct aux données d'un autre service.
Aucune relation JPA entre deux services : uniquement des IDs (Long) comme références distantes.
La seule relation JPA du projet est interne à service-commande (Order 1─* OrderLine).
Les vérifications inter-services passent par des appels REST (jalons ultérieurs).
🧰 Technologies
| Technologie | Usage | | Java 17 | Langage | | Spring Boot 3.x | Framework applicatif | | Spring Cloud | Eureka, Gateway, Config Server | | Spring Data JPA / Hibernate | Accès aux données (ORM) | | MySQL 8.x | Base de données | | Maven | Gestion des dépendances et build | | REST API (JSON) | Communication | | Postman | Tests des API | | IntelliJ IDEA | IDE |

👩‍💻 Auteur
Projet réalisé par Hiba El khayat ,Assia Abouzraa ,Youssef elaissioui – EMSI – Année universitaire 2026-2027. Encadrant : Salim KHALIL