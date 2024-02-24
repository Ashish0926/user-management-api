package com.ashish.springcrudapi.exception;

import com.ashish.springcrudapi.dto.response.ErrorResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleException(final DataIntegrityViolationException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(final Exception ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    private ResponseEntity<ErrorResponseDto> createErrorResponse(final HttpStatus status, final String message) {
        final ErrorResponseDto errorResponseDto
            = ErrorResponseDto.builder()
            .statusCode(status.value())
            .message(message)
            .build();
        return ResponseEntity.status(status).body(errorResponseDto);
    }
}
