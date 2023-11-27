package com.app.filmwatcher.service;

import com.app.filmwatcher.model.User;
import com.app.filmwatcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) throws Exception {
        // Проверка на уникальность username
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username уже используется.");
        }

        // Проверка на уникальность email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email уже используется.");
        }

        // Проверка длины пароля
        if (user.getPassword().length() < 6) {
            throw new Exception("Пароль должен быть 6 символов и больше.");
        }

        // Сохранение пользователя
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Другие методы...
}


