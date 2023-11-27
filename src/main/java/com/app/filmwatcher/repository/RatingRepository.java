package com.app.filmwatcher.repository;

import com.app.filmwatcher.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    // Методы для работы с рейтингами
    List<Rating> findByMovieId(Integer movieId);
    List<Rating> findByUserId(Long userId);
}
