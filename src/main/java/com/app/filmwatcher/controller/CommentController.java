package com.app.filmwatcher.controller;

import com.app.filmwatcher.model.Comment;
import com.app.filmwatcher.repository.UserRepository;
import com.app.filmwatcher.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserRepository userRepository;

    // Получение комментариев для фильма
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@RequestParam Integer movieId) {
        return ResponseEntity.ok(commentService.findByMovieId(movieId));
    }

    // Добавление нового комментария
    @PostMapping("/addComment")
    @CrossOrigin
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        comment.setUser(userRepository.findByUsername(auth.getName()).get());
        comment.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(commentService.saveComment(comment));
    }
}