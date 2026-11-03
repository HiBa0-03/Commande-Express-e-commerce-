-- =====================================================================
-- Commande Express - V1 : une base MySQL indépendante par microservice
-- (principe "Database per Service")
--
-- Ce script est OPTIONNEL : les services créent la base eux-mêmes grâce à
-- "createDatabaseIfNotExist=true" dans l'URL JDBC. Il sert si tu préfères
-- créer les bases (et des utilisateurs dédiés) toi-même.
-- Les TABLES sont créées automatiquement par Hibernate (ddl-auto: update).
-- =====================================================================

CREATE DATABASE IF NOT EXISTS db_catalogue    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_utilisateurs CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_stock        CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_commande     CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- OPTIONNEL : un utilisateur MySQL par service (isolation renforcée).
-- Décommente, change les mots de passe, puis renseigne DB_USERNAME /
-- DB_PASSWORD au lancement de chaque service.
-- ---------------------------------------------------------------------
-- CREATE USER IF NOT EXISTS 'catalogue_user'@'localhost'    IDENTIFIED BY 'changeme';
-- CREATE USER IF NOT EXISTS 'utilisateurs_user'@'localhost' IDENTIFIED BY 'changeme';
-- CREATE USER IF NOT EXISTS 'stock_user'@'localhost'        IDENTIFIED BY 'changeme';
-- CREATE USER IF NOT EXISTS 'commande_user'@'localhost'     IDENTIFIED BY 'changeme';
--
-- GRANT ALL PRIVILEGES ON db_catalogue.*    TO 'catalogue_user'@'localhost';
-- GRANT ALL PRIVILEGES ON db_utilisateurs.* TO 'utilisateurs_user'@'localhost';
-- GRANT ALL PRIVILEGES ON db_stock.*        TO 'stock_user'@'localhost';
-- GRANT ALL PRIVILEGES ON db_commande.*     TO 'commande_user'@'localhost';
-- FLUSH PRIVILEGES;
