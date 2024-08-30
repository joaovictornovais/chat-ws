package com.joao.chatws.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<StandardError> invalidArgument(InvalidArgumentException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Argument",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TokenCreationException.class)
    public ResponseEntity<StandardError> tokenCreation(TokenCreationException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Token Creation",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                "Entity not Found",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(FriendshipRequestNotForUserException.class)
    public ResponseEntity<StandardError> friendshipNotForUser(FriendshipRequestNotForUserException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                HttpStatus.FORBIDDEN.value(),
                "Friendship Request not for User",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

}
