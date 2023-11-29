package com.app.filmwatcher.controller;

import com.app.filmwatcher.model.Comment;
import com.app.filmwatcher.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Получение комментариев для фильма
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@RequestParam Integer movieId) {
        return ResponseEntity.ok(commentService.findByMovieId(movieId));
    }

    // Добавление нового комментария
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        System.out.println(comment.getCommentText());
        return ResponseEntity.ok(commentService.saveComment(comment));
    }
}
