package ua.com.gunin.NIX11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.gunin.NIX11.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByNameContainingIgnoreCase(String name);

    List<User> findBySurnameContainingIgnoreCase(String surName);

    Optional<User> findByEmail(String email);

    List<User> findByCityIgnoreCase(String city);

    User findFirstByPhoneNumber(String phoneNumber);

    User findFirstByUsername(String username);

    User findFirstByName(String name);
}
