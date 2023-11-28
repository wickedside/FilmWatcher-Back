package com.app.filmwatcher.repository;

import com.app.filmwatcher.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findRoleByRoleId(Long id);
    // Методы для работы с ролями
}
