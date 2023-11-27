package com.app.filmwatcher.repository;

import com.app.filmwatcher.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Методы для работы с ролями
}
