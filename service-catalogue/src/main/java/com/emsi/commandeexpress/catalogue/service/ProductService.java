package com.emsi.commandeexpress.catalogue.service;

import com.emsi.commandeexpress.catalogue.dto.ProductRequest;
import com.emsi.commandeexpress.catalogue.dto.ProductResponse;
import com.emsi.commandeexpress.catalogue.entity.Product;
import com.emsi.commandeexpress.catalogue.exception.DuplicateResourceException;
import com.emsi.commandeexpress.catalogue.exception.ResourceNotFoundException;
import com.emsi.commandeexpress.catalogue.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Logique métier du catalogue : règles de gestion + conversion entité <-> DTO.
 */
@Service
public class ProductService {

    private static final String RESOURCE = "Produit";

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        String reference = request.reference().trim();
        if (productRepository.existsByReference(reference)) {
            throw new DuplicateResourceException("Un produit avec la référence '" + reference + "' existe déjà");
        }
        Product product = new Product();
        applyRequest(product, request);
        return toResponse(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return toResponse(getProductOrThrow(id));
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = getProductOrThrow(id);
        String reference = request.reference().trim();
        if (productRepository.existsByReferenceAndIdNot(reference, id)) {
            throw new DuplicateResourceException("Un autre produit utilise déjà la référence '" + reference + "'");
        }
        applyRequest(product, request);
        return toResponse(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(RESOURCE, id);
        }
        productRepository.deleteById(id);
    }

    // ----------------------------------------------------------------- utilitaires

    private Product getProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE, id));
    }

    private void applyRequest(Product product, ProductRequest request) {
        product.setReference(request.reference().trim());
        product.setName(request.name().trim());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setCategory(request.category());
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getReference(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getCreatedAt());
    }
}
