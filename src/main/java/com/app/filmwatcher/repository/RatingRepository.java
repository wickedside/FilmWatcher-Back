package com.app.filmwatcher.repository;

import com.app.filmwatcher.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    // Методы для работы с рейтингами
    List<Rating> findByMovieId(Integer movieId);
    List<Rating> findByUser_UserId(Long userId);
}
