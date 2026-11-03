package com.emsi.commandeexpress.catalogue.repository;

import com.emsi.commandeexpress.catalogue.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByReference(String reference);

    boolean existsByReferenceAndIdNot(String reference, Long id);
}
