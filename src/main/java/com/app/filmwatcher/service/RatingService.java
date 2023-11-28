package com.app.filmwatcher.service;

import com.app.filmwatcher.model.Rating;
import com.app.filmwatcher.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> findByMovieId(Integer movieId) {
        return ratingRepository.findByMovieId(movieId);
    }

    public List<Rating> findByUserId(Long userId) {
        return ratingRepository.findByUser_UserId(userId);
    }

    // Добавьте другие методы по мере необходимости
}

