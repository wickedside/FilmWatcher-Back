package com.app.filmwatcher.repository;

import com.app.filmwatcher.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Методы для работы с комментариями
    List<Comment> findByMovieId(Integer movieId);
    List<Comment> findByUser_UserId(Long userId);

}
