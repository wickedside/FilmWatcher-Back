package com.app.filmwatcher.repository;

import com.app.filmwatcher.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Методы для работы с комментариями
    List<Comment> findByMovieId(Integer movieId);
    List<Comment> findByUserId(Long userId);
}
