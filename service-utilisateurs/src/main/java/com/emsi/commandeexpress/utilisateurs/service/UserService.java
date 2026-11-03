package com.emsi.commandeexpress.utilisateurs.service;

import com.emsi.commandeexpress.utilisateurs.dto.UserRequest;
import com.emsi.commandeexpress.utilisateurs.dto.UserResponse;
import com.emsi.commandeexpress.utilisateurs.entity.User;
import com.emsi.commandeexpress.utilisateurs.exception.DuplicateResourceException;
import com.emsi.commandeexpress.utilisateurs.exception.ResourceNotFoundException;
import com.emsi.commandeexpress.utilisateurs.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

/**
 * Logique métier des utilisateurs : règles de gestion + conversion entité <-> DTO.
 */
@Service
public class UserService {

    private static final String RESOURCE = "Utilisateur";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        String email = normalizeEmail(request.email());
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("Un utilisateur avec l'email '" + email + "' existe déjà");
        }
        User user = new User();
        applyRequest(user, request);
        return toResponse(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return toResponse(getUserOrThrow(id));
    }

    @Transactional
    public UserResponse update(Long id, UserRequest request) {
        User user = getUserOrThrow(id);
        String email = normalizeEmail(request.email());
        if (userRepository.existsByEmailAndIdNot(email, id)) {
            throw new DuplicateResourceException("Un autre utilisateur utilise déjà l'email '" + email + "'");
        }
        applyRequest(user, request);
        return toResponse(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(RESOURCE, id);
        }
        userRepository.deleteById(id);
    }

    // ----------------------------------------------------------------- utilitaires

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE, id));
    }

    /** L'email est comparé et stocké en minuscules, sans espaces autour. */
    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }

    private void applyRequest(User user, UserRequest request) {
        user.setFirstName(request.firstName().trim());
        user.setLastName(request.lastName().trim());
        user.setEmail(normalizeEmail(request.email()));
        user.setPhone(request.phone());
        user.setAddress(request.address());
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getCreatedAt());
    }
}
