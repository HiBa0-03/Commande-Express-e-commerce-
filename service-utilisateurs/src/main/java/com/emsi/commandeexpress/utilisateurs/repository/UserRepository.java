package com.emsi.commandeexpress.utilisateurs.repository;

import com.emsi.commandeexpress.utilisateurs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
