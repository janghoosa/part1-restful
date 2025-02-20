package com.sprint.mission.part1restful.exception;

import com.sprint.mission.part1restful.exception.comment.CommentNotFoundException;
import com.sprint.mission.part1restful.exception.post.PostNotFoundException;
import com.sprint.mission.part1restful.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<String> handleCommentNotFoundException(CommentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handleCommentNotFoundException(PostNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleCommentNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
