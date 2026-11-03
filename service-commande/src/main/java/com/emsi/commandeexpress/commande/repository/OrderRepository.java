package com.emsi.commandeexpress.commande.repository;

import com.emsi.commandeexpress.commande.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
