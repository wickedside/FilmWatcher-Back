package com.app.filmwatcher.service;

import com.app.filmwatcher.model.Comment;
import com.app.filmwatcher.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findByMovieId(Integer movieId) {
        return commentRepository.findByMovieId(movieId);
    }

    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByUser_UserId(userId);
    }

    // Добавьте другие методы по мере необходимости
}

