package com.app.filmwatcher.repository;

import com.app.filmwatcher.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Здесь можно добавить специфичные методы, например, поиск по имени пользователя
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
